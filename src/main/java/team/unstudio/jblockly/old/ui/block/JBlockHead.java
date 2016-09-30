package team.unstudio.jblockly.ui.block;

import java.awt.Color;
import java.awt.Graphics;

public class JBlockHead extends JBlockMulti{

	private static final long serialVersionUID = 1L;
	
	public JBlockHead() {
		blockColor = new Color(210,155,35);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Color light=new Color(240,180,40),
				dark=new Color(180,130,20),
				normal=new Color(210,155,35);
		
		drawCodeBlock(g, true, false, normal, light, dark);
		/*//normal
		{
			g.setColor(normal);
			Polygon p = new Polygon();
			p.addPoint(0, 0);
			p.addPoint(getWidth()-1, 0);
			p.addPoint(getWidth()-1, getRealSize().height-1);
			p.addPoint(26, getRealSize().height-1);
			p.addPoint(23, getRealSize().height+4);
			p.addPoint(13, getRealSize().height+4);
			p.addPoint(10, getRealSize().height-1);
			p.addPoint(0, getRealSize().height-1);
			g.fillPolygon(p);
		}
		
		//light
		{
			g.setColor(light);
			Polygon p = new Polygon();
			p.addPoint(0, getRealSize().height-1);
			p.addPoint(0, 0);
			p.addPoint(getWidth()-1, 0);
			g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
		}
		
		//dark
		{
			g.setColor(dark);
			Polygon p = new Polygon();
			p.addPoint(getWidth()-1, 0);
			p.addPoint(getWidth()-1, getRealSize().height-1);
			p.addPoint(26, getRealSize().height-1);
			p.addPoint(23, getRealSize().height+4);
			p.addPoint(13, getRealSize().height+4);
			p.addPoint(10, getRealSize().height-1);
			p.addPoint(0, getRealSize().height-1);
			g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
		}*/
	}
	
	@Override
	public boolean onBeInserted(JBlock block) {
		return false;
	}
}
