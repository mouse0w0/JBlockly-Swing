package team.unstudio.jblockly.ui.block;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;

public class JBlockMultiPackage extends JBlockMulti{

	private static final long serialVersionUID = 1L;
	
	protected JBlockBase branch;

	public JBlockMultiPackage() {
		blockColor = Color.decode("#11cc88");
	}
	
	@Override
	public boolean addBlock(int x, int y, JBlock block) {
		if(new Rectangle(20,realSize.height-5,20,10).contains(x, y)) return addBranch(block);
		else if(new Rectangle(10, getPreferredSize().height-10-(getNextBlock()==null?0:getNextBlock().getPreferredSize().height-5), 20, 10).contains(x, y)) return addBlock(block);
		else{
			Component b = getComponentAt(x, y);
			if(b!=null&&!b.equals(this)&&b instanceof JBlock)return ((JBlock) b).addBlock(x-b.getX(), y-b.getY(), block);
		}
		return false;
	}
	
	@Override
	public void doLayout() {
		int width = 5,height = 35;
		for(Component c:getComponents()){
			if(!(c instanceof JBlockBase)){
				Dimension d=c.getPreferredSize();
				c.setBounds(width, 10, d.width, d.height);
				width+=d.width+5;
				if(d.height+20>height)height=d.height+20;
			}
		}
		setRealSize(width,height);
		if(branch!=null){
			branch.setLocation(10, height);
			branch.doLayout();
			height+=branch.getPreferredSize().height;
			if(width<branch.getPreferredSize().width+10)width=branch.getPreferredSize().width+10;
		}
		height+=20;
		if(getNextBlock()!=null){
			getNextBlock().setLocation(0, height);
			getNextBlock().doLayout();
			height+=getNextBlock().getPreferredSize().height;
			if(width<getNextBlock().getPreferredSize().width)width=getNextBlock().getPreferredSize().width;
		}
		setPreferredSize(new Dimension(width,height+5));
		setSize(width, height+5);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		drawCodeBlock(g, false, false, 10);
		int width=getRealSize().width,height=getRealSize().height;
		
		g2d.setColor(blockColor);
		Polygon p = new Polygon();
		p.addPoint(0, height - 1);
		p.addPoint(10, height - 1);
		int i = branch!=null?branch.getPreferredSize().height+10:10;
		p.addPoint(10, height - 1 + i);
		p.addPoint(width - 1, height - 1 + i);
		p.addPoint(width - 1, height - 1 + i + 10);
		p.addPoint(26, height - 1 + i + 10);
		p.addPoint(23, height + 4 + i + 10);
		p.addPoint(13, height + 4 + i + 10);
		p.addPoint(10, height - 1 + i + 10);
		p.addPoint(0, height - 1 + i + 10);
		g2d.fillPolygon(p);
		{
			g2d.setColor(blockColor.brighter());
			p = new Polygon();
			g2d.drawLine(0, height - 1 + i + 10, 0, height - 1);
			p.addPoint(10, height - 1 + i);
			p.addPoint(width -1, height - 1 + i);
			g2d.drawPolyline(p.xpoints, p.ypoints, p.npoints);
		}
		{
			g2d.setColor(blockColor.darker());
			g2d.drawLine(10, height - 1 + i, 10, height - 1);
			p = new Polygon();
			p.addPoint(width-1, height - 1 + i);
			p.addPoint(width-1, height - 1 + i + 10);
			p.addPoint(26, height-1 + i + 10);
			p.addPoint(23, height+4 + i + 10);
			p.addPoint(13, height+4 + i + 10);
			p.addPoint(10, height-1 + i + 10);
			p.addPoint(0, height-1 + i + 10);
			g2d.drawPolyline(p.xpoints, p.ypoints, p.npoints);
		}
	}
	
	public JBlockBase getBranchBlock() {
		return branch;
	}

	public boolean addBranch(JBlock block) {
		if(block instanceof JBlockBase&&block.onBeInserted(this)){
			if(branch!=null) block.addBlock(branch);
			branch = (JBlockBase) block;
			add(block);
			return true;
		}
		return false;
	}
	
	@Override
	public void revalidate() {
		if(branch!=null&&!branch.getParent().equals(this))branch=null;
		super.revalidate();
	}
}
