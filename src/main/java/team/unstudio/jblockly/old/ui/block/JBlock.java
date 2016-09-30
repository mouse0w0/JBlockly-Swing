package team.unstudio.jblockly.ui.block;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JPanel;

import team.unstudio.jblockly.ui.BlockMenu;
import team.unstudio.jblockly.ui.BlockWorkspace;

public abstract class JBlock extends JPanel{

	private static final long serialVersionUID = 1L;
	
	protected Dimension realSize;
	
	private boolean dragging = false;
	private int xOld,yOld;

	public JBlock() {
		setLayout(null);
		setOpaque(false);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1&&isCanDrag(e.getX(), e.getY())){
					int x=getX(),y=getY();
					Container parent = getParent(),c = getParent();
					BlockWorkspace editor = getWorkspace();
					while(!(c instanceof BlockWorkspace)){
						x+=c.getX();
						y+=c.getY();
						c=c.getParent();
					}
					xOld = e.getXOnScreen() - x;
					yOld = e.getYOnScreen() - y;
					
					dragging = true;
					setLocation(x, y);
					editor.setDragging(JBlock.this);
					parent.revalidate();
				}else if(e.getButton() == MouseEvent.BUTTON3){
					BlockMenu.INSTANCE.show(JBlock.this, e.getX(), e.getY());
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(dragging){
					BlockWorkspace editor = getWorkspace();
					
					int x = getInsertPoint().x,y = getInsertPoint().y;
					Component c = editor.getDisplayLayer().getComponentAt(x, y);
					if(c instanceof JBlock&&((JBlock) c).addBlock(x-c.getX(), y-c.getY(), JBlock.this)) c.revalidate();
					else editor.getDisplayLayer().add(JBlock.this,0);
					
					dragging = false;
					editor.setDragging(null);
					editor.repaint();;
				}
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if(dragging) setLocation(e.getXOnScreen() - xOld, e.getYOnScreen() - yOld);
			}
		});
	}

	public BlockWorkspace getWorkspace() {
		Container c = getParent();
		while(c instanceof JPanel){
			if(c instanceof BlockWorkspace) return (BlockWorkspace) c;
			c = c.getParent();
		}
		return null;
	}
	
	public boolean isCanDrag(int x,int y) {
		return new Polygon(new int[]{0,realSize.width,realSize.width,0}, new int[]{0,0,realSize.height,realSize.height}, 4).contains(x, y);
	}
	
	public boolean isDragging() {
		return dragging;
	}
	
	public Dimension getRealSize() {
		return realSize;
	}

	public void setRealSize(Dimension realSize) {
		this.realSize = realSize;
	}
	
	public void setRealSize(int width,int height) {
		this.realSize = new Dimension(width, height);
	}
	
	protected Color blockColor = Color.white;
	
	public Color getColor(){
		return blockColor;
	}

	public abstract Point getInsertPoint();
	
	public abstract boolean addBlock(int x, int y, JBlock block);
	
	public abstract boolean addBlock(JBlock block);
	
	public abstract boolean onBeInserted(JBlock block);
}
