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
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

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
	
	int xOld=0,yOld=0;
	
	public Block() {
		setLayout(null);
		setOpaque(false);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
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
					
					setSelected(true);
					setLocation(x, y);
					workspace.setSelectBlock(Block.this);
					parent.revalidate();
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
		this.parent = parent;
	}

	private Block next = null;

	public Block getNext() {
		return next;
	}

	public void setNext(Block next) {
		this.next = next;
	}

	private String message;
	private final List<BlockLine> lines = new ArrayList<>();

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void addLine(BlockLine line) {
		line.setParent(this);
		lines.add(line);
	}

	private boolean moveable = true;
	private boolean editable = true;
	private boolean folded = false;
	private LayoutType layoutType = LayoutType.Automatic;
	private ConnectionType connectionType = ConnectionType.None;
	private String tooltip;

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
			line.dispose();
	}

	@Override
	public void doLayout() {
		int x = 0, y = BlockUtils.VGAP;
		StringBuilder svg = new StringBuilder(BlockRender.getBlockTop(connectionType));
		for (BlockLine line : lines) {
			line.setLocation(x, y);
			y+=line.getHeight()+BlockUtils.VGAP;
		}
		if(next!=null)next.setLocation(x, y);
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
		
		g2d.setColor(new Color(color>>16,(color>>8)%256,color%256));
		g2d.fill(area);
	}
}
