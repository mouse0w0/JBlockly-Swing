package team.unstudio.jblockly.ui.block;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import team.unstudio.jblockly.block.BlockDescriber;
import team.unstudio.jblockly.block.IInput;
import team.unstudio.jblockly.ui.BlockMenu;
import team.unstudio.jblockly.ui.BlockWorkspace;
import team.unstudio.jblockly.ui.layout.DefaultBlockLayout;

public class Block extends JPanel{

	private static final long serialVersionUID = 1L;

	private final BlockDescriber describer;
	
	private BlockWorkspace workspace;
	private Block parentBlock;
	private Block nextBlock;
	private IInput[] inputs = new IInput[0];
	
	private boolean editable = true;
	private boolean moveable = true;
	private boolean disable = false;
	private boolean folded = false;
	private String tooltip = null;
	
	private boolean dragging = false,selected = false;
	private int xOld,yOld;
	private Area blockArea;
	
	public Block(BlockDescriber describer) {
		this.describer = describer;
		
		setLayout(new DefaultBlockLayout());
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
					
					dragging = true;
					setLocation(x, y);
					workspace.setDragging(Block.this);
					parent.revalidate();
				}else if(e.getButton() == MouseEvent.BUTTON3){
					BlockMenu.INSTANCE.show(Block.this, e.getX(), e.getY());
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(!dragging) return;
				
				dragging = false;
				workspace.setDragging(null);
				workspace.repaint();;
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if(dragging) setLocation(e.getXOnScreen() - xOld, e.getYOnScreen() - yOld);
			}
		});
	}

	public BlockDescriber getBlockDescriber() {
		return describer;
	}
	
	@Override
	public void setLayout(LayoutManager mgr) {
		if(!(mgr instanceof DefaultBlockLayout)) throw new IllegalArgumentException("Layout type must be DefaultBlockLayout");
		super.setLayout(mgr);
	}
	
	@Override
	public DefaultBlockLayout getLayout() {
		return (DefaultBlockLayout) super.getLayout();
	}

	public BlockWorkspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(BlockWorkspace workspace) {
		this.workspace = workspace;
	}

	public Block getParentBlock() {
		return parentBlock;
	}

	public void setParentBlock(Block parentBlock) {
		this.parentBlock = parentBlock;
	}

	public Block getNextBlock() {
		return nextBlock;
	}

	public void setNextBlock(Block nextBlock) {
		this.nextBlock = nextBlock;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isMoveable() {
		return moveable;
	}

	public void setMoveable(boolean moveable) {
		this.moveable = moveable;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public boolean isFolded() {
		return folded;
	}

	public void setFolded(boolean folded) {
		this.folded = folded;
	}
	
	//Render
	
	private List<Line2D> darkShadow = new ArrayList<>();
	private List<Area> insertSlotAreas = new ArrayList<>();
	
	@Override
	protected void paintComponent(Graphics g) {
		darkShadow.clear();
		insertSlotAreas.clear();
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		Path2D path = new Path2D.Double();
		
		if(isFolded()) paintFolded(g2d);
		else{
			
		}
		
		Area area = new Area(path);
		for(Area a:insertSlotAreas) area.subtract(a);
		
		g2d.setColor(describer.getColor());
		g2d.fill(area);
		
		paintShadow(g2d);
	}
	
//	protected void paintTop(Path2D path,int x,int y){
//		if(describer.getBlockType() == BlockDescriber.INSERT){
//			final int insertOffsetY = 10,insertHeightL = 15,insertHeightS = 9,insetWidth = 7;
//			path.moveTo(insetWidth-1, insertOffsetY+insertHeightS-1);
//		}else{
//			
//		}
//	}
//	
//	protected void paintSub(Path2D path,int x,int y){
//		
//	}
//	
//	protected void paintBranch(Path2D path,int x,int y){
//		
//	}
//	
//	protected void paintBottom(Path2D path,int x,int y){
//		
//	}
	
	protected void paintFolded(Graphics2D g){
		
	}
	
	protected void paintShadow(Graphics2D g) {
		g.setColor(describer.getColor().darker());
		for(Line2D line:darkShadow) g.draw(line);
	}
}
