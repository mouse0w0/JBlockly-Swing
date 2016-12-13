/*
 * Copyright (c) 2016, Unknown Domain. All rights reserved.
 * GUN AGPLv3. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package team.unstudio.jblockly.core;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.swing.JPanel;

import team.unstudio.jblockly.core.component.BlockLine;

/*
 * Code block
 */
public class Block extends JPanel implements Cloneable {

	private static final long serialVersionUID = 1L;

	/**
	 * LayoutType: Automatic: xxxx External: xxxx Inline: xxxx
	 */
	public enum LayoutType {
		Automatic, External, Inline
	}
	
	/**
	 * ConnectionType: None: xxxx Left: xxxx TopAndBottom: xxxx Top: xxxx
	 * Bottom: xxxx
	 */
	public enum ConnectionType {
		None, Left, TopAndBottom, Top, Bottom
	}
	
	private int xOld=0,yOld=0;
	
	public Block() {
		uuid = UUID.randomUUID();
		
		setLayout(null);
		setOpaque(false);
		setBorder(null);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				workspace.setSelectBlock(Block.this);
				setSelected(true);
				if(e.getButton() == MouseEvent.BUTTON1&&moveable){
					int x=getX(),y=getY();
					Container parent = getParent(),c = getParent();
					while(!(c instanceof BlockWorkspace)){
						x+=c.getX();
						y+=c.getY();
						c=c.getParent();
					}
					xOld = e.getXOnScreen() - x;
					yOld = e.getYOnScreen() - y;
					
					Block.this.parent=null;
					workspace.add(Block.this);
					setLocation(x, y);
					//parent.revalidate();
				}else if(e.getButton() == MouseEvent.BUTTON3){
					BlockMenu.INSTANCE.show(Block.this, e.getX(), e.getY());
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				workspace.repaint();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if(isSelected()) setLocation(e.getXOnScreen() - xOld, e.getYOnScreen() - yOld);
			}
		});
	}

	private BlockWorkspace workspace = null;

	public BlockWorkspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(BlockWorkspace workspace) {
		this.workspace = workspace;
	}

	private Block parent = null;

	public Block getBlockParent() {
		return parent;
	}

	public void setBlockParent(Block parent) {
		if(this.parent!=null)parent.remove(this);
		this.parent = parent;
		if(parent!=null)parent.add(this);
		else{
			workspace.add(this);
			int x=getX(),y=getY();
			Container c = getParent();
			while(!(c instanceof BlockWorkspace)){
				x+=c.getX();
				y+=c.getY();
				c=c.getParent();
			}
			setLocation(x, y);
		}
	}

	private Block next = null;

	public Block getNext() {
		return next;
	}

	public void setNext(Block next) {
		this.next = next;
	}

	private UUID uuid;
	private final List<BlockLine> lines = new ArrayList<>();
	private boolean moveable = true;
	private boolean editable = true;
	private boolean folded = false;
	private LayoutType layoutType = LayoutType.Automatic;
	private ConnectionType connectionType = ConnectionType.None;
	private String tooltip;
	
	public UUID getUUID(){
		return uuid;
	}
	
	public void setUUID(UUID uuid){
		this.uuid=uuid;
	}

	public void addLine(BlockLine line) {
		line.setParent(this);
		lines.add(line);
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public boolean isMoveable() {
		return moveable;
	}

	public void setMoveable(boolean moveable) {
		this.moveable = moveable;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isFolded() {
		return folded;
	}

	public void setFolded(boolean folded) {
		this.folded = folded;
	}

	public LayoutType getLayoutType() {
		return layoutType;
	}

	public void setLayoutType(LayoutType layoutType) {
		this.layoutType = layoutType;
	}

	public ConnectionType getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(ConnectionType connectionType) {
		this.connectionType = connectionType;
	}

	protected Area area;
	private int color = 0X000000;
	private boolean selected = false;
	
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	
	public void setColor(int r,int g,int b) {
		setColor((r<<16)+(g<<8)+b);
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public void dispose() {
		for (BlockLine line : lines)
			line.setParent(null);
	}

	@Override
	public void doLayout() {
		int x = 0, y = BlockUtils.VGAP;
		StringBuilder svg = new StringBuilder(BlockRender.getBlockTop(connectionType));
		for (BlockLine line : lines) {
			line.setLocation(x, y);
			svg.append(BlockRender.getBlockSide(line.getLineType(), line.getX(), line.getY(), line.getComponentWidth(), line.getComponentHeight()));
			y+=line.getComponentHeight()+BlockUtils.VGAP;
		}
		svg.append(BlockRender.getBlockBottom(connectionType, x, y));
		if(next!=null)next.setLocation(x, y);
		area = new Area(BlockRender.getPathFromSVG(svg.toString()));
		setBounds(area.getBounds());
	}
	
	@Override
	public boolean contains(int x, int y) {
		return area.contains(x, y);
	}
	
	@Override
	public boolean contains(Point p) {
		return contains(p.x,p.y);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_DEFAULT);
		
		Color color = new Color(this.color>>16,(this.color>>8)%256,this.color%256);
		
		g2d.setColor(color);
		g2d.fill(area);
		
		g2d.setColor(color.darker());
		g2d.draw(area);
	}
}
