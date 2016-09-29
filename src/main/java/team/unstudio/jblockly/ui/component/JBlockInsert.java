package team.unstudio.jblockly.ui.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

import team.unstudio.jblockly.ui.BlockMenu;
import team.unstudio.jblockly.ui.BlockWorkspace;

public class JBlockInsert extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private boolean dragging = false;
	private int xOld,yOld;

	public JBlockInsert() {
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
					editor.setDragging(JBlockInsert.this);
					setLocation(x, y);
					parent.revalidate();
				}else if(e.getButton() == MouseEvent.BUTTON3){
					BlockMenu.INSTANCE.show(JBlockInsert.this, e.getX(), e.getY());
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(dragging){
					BlockWorkspace editor = getWorkspace();
					int x = getInsertPoint().x,y = getInsertPoint().y;
					Component c = editor.getDisplayLayer().getComponentAt(x, y);
					while(c instanceof JPanel){
						if(c instanceof JBlockInsertSlot){
							if(((JBlockInsertSlot) c).setInsert(x-=c.getX(), y-=c.getY(), JBlockInsert.this)) c.revalidate();
							else editor.getDisplayLayer().add(JBlockInsert.this,0);
							dragging = false;
							editor.setDragging(null);
							editor.repaint();
							return;
						}
						if(c.getComponentAt(x-=c.getX(), y-=c.getY()).equals(c))break;
						else c = c.getComponentAt(x-=c.getX(), y-=c.getY());
					}
					
					editor.getDisplayLayer().add(JBlockInsert.this,0);
					dragging = false;
					editor.setDragging(null);
					editor.repaint();
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
	
	@Override
	public void doLayout() {
		int width = 10;
		for(Component c:getComponents()){
			Dimension d=c.getPreferredSize();
			c.setBounds(width, 5, d.width, d.height);
			width+=c.getWidth()+5;
		}
		if(width<35)width=35;
		setPreferredSize(new Dimension(width+5,25));
		setSize(width+5, 25);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Color light=new Color(108,138,223),
				dark=new Color(54,85,168),
				normal=new Color(74,107,211);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		int width= getPreferredSize().width,height= getPreferredSize().height;
		
		//normal
		{
			g2d.setColor(normal);
			Polygon p = new Polygon();
			p.addPoint(0, (int)(height+1)/2-1);
			p.addPoint(9, 0);
			p.addPoint(width-10, 0);
			p.addPoint(width-1, (int)(height+1)/2-1);
			p.addPoint(width-10, height-1);
			p.addPoint(9, height-1);
			g2d.fillPolygon(p);
		}
		
		//light
		{
			g2d.setColor(light);
			Polygon p = new Polygon();
			p.addPoint(0, (int)(height+1)/2-1);
			p.addPoint(9, 0);
			p.addPoint(width-10, 0);
			p.addPoint(width-1, (int)(height+1)/2-1);
			g2d.drawPolyline(p.xpoints, p.ypoints, p.npoints);
		}
		
		//dark
		{
			g2d.setColor(dark);
			Polygon p = new Polygon();
			p.addPoint(width-1, (int)(height+1)/2-1);
			p.addPoint(width-10, height-1);
			p.addPoint(9, height-1);
			p.addPoint(0, (int)(height+1)/2-1);
			g2d.drawPolyline(p.xpoints, p.ypoints, p.npoints);
		}
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
		return new Polygon(new int[]{0,getWidth(),getWidth(),0}, new int[]{0,0,getHeight(),getHeight()}, 4).contains(x, y);
	}
	
	public boolean isDragging() {
		return dragging;
	}
	
	public Point getInsertPoint() {
		return new Point(getX()+getWidth()/2, getY()+getHeight()/2);
	}
}
