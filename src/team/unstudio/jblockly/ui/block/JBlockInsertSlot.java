package team.unstudio.jblockly.ui.block;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;

import team.unstudio.jblockly.ui.BlockWorkspace;

public class JBlockInsertSlot extends JBlock{

	private static final long serialVersionUID = 1L;
	
	protected JBlockInsert insert;
	
	public JBlockInsertSlot() {
		blockColor = new Color(74,107,211);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		int width = getPreferredSize().width,height = getPreferredSize().height;
		
		g2d.setColor(getColor());
		Polygon p = new Polygon();
		p.addPoint(10, 0);
		p.addPoint(width-1, 0);
		p.addPoint(width-1, height-1);
		p.addPoint(10, height-1);
		p.addPoint(10, 19);
		p.addPoint(0, 22);
		p.addPoint(0, 5);
		p.addPoint(10, 8);
		g2d.fill(p);
		
		g2d.setColor(getColor().darker());
		p = new Polygon();
		p.addPoint(0, 22);
		p.addPoint(0, 5);
		p.addPoint(10, 8);
		p.addPoint(10, 0);
		p.addPoint(width-1, 0);
		g2d.drawPolyline(p.xpoints, p.ypoints, p.npoints);
		g2d.drawLine(10, 19, 10, height-1);
		
		g2d.setColor(getColor().brighter());
		p = new Polygon();
		p.addPoint(width-1, 0);
		p.addPoint(width-1, height-1);
		p.addPoint(10, height-1);
		g2d.drawPolyline(p.xpoints, p.ypoints, p.npoints);
		g2d.drawLine(0, 22, 10, 19);
	}
	
	@Override
	public void doLayout() {
		if(insert!=null){
			insert.doLayout();
			insert.setLocation(1, 1);
			setPreferredSize(new Dimension(insert.getPreferredSize().width+2,insert.getPreferredSize().height+2));
			setSize(insert.getPreferredSize().width+2,insert.getPreferredSize().height+2);
		}else{
			setPreferredSize(new Dimension(40,28));
			setSize(40,28);
		}
	}

	@Override
	public Point getInsertPoint() {
		return null;
	}

	@Override
	public boolean addBlock(int x, int y, JBlock block) {
		if(insert!=null&&insert.addBlock(x-insert.getX(), y-insert.getY(), block)) return true;
		else return addBlock(block);
	}

	@Override
	public boolean addBlock(JBlock block) {
		if(block instanceof JBlockInsert&&block.onBeInserted(this)){
			if(insert!=null&&!insert.equals(block)){
				int x=insert.getX()+50,y=insert.getY()+30;
				Container c = insert.getParent();
				BlockWorkspace editor = insert.getWorkspace();
				while(!(c instanceof BlockWorkspace)){
					x+=c.getX();
					y+=c.getY();
					c=c.getParent();
				}
				editor.getDisplayLayer().add(insert, 0);
				insert.setLocation(x, y);
			}
			insert = (JBlockInsert) block;
			add(block);
			return true;
		}
		return false;
	}

	@Override
	public boolean onBeInserted(JBlock block) {
		return false;
	}
	
	@Override
	public boolean isCanDrag(int x, int y) {
		return false;
	}

	public JBlockInsert getInsertBlock() {
		return insert;
	}
	
	@Override
	public void revalidate() {
		if(insert!=null&&!insert.getParent().equals(this)) insert = null;
		super.revalidate();
	}
}
