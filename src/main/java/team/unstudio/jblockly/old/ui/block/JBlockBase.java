/*
 * Copyright (c) 2016, Unknown Domain. All rights reserved.
 * GUN GPLv3. Use is subject to license terms.
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

package team.unstudio.jblockly.old.ui.block;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;

public class JBlockBase extends JBlock{

	private static final long serialVersionUID = 1L;
	
	protected JBlockBase next;
	
	public JBlockBase() {
		blockColor = new Color(74,107,211);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Color light=new Color(108,138,223),
				dark=new Color(54,85,168),
				normal=new Color(74,107,211);
		
		drawCodeBlock(g, false, false, normal, light, dark);
		/*//normal
		{
			g.setColor(normal);
			Polygon p = new Polygon();
			p.addPoint(0, 0);
			p.addPoint(10, 0);
			p.addPoint(13, 5);
			p.addPoint(23, 5);
			p.addPoint(26, 0);
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
			p.addPoint(10, 0);
			p.addPoint(13, 5);
			p.addPoint(23, 5);
			p.addPoint(26, 0);
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
	public void doLayout() {
		int width = 5,height = 35;
		for(Component c:getComponents()){
			if(!(c instanceof JBlockBase)){
				c.doLayout();
				Dimension d=c.getPreferredSize();
				c.setBounds(width, 10, d.width, d.height);
				width+=c.getWidth()+5;
				if(d.height+20>height)height=d.height+20;
			}
		}
		setRealSize(width, height);
		if(next!=null){
			next.doLayout();
			Dimension d=next.getPreferredSize();
			next.setBounds(0, height, d.width, d.height-5);
			if(d.width>width)width = d.width;
			height+=d.height-5;
		}
		setPreferredSize(new Dimension(width,height+5));
		setSize(width, height+5);
	}
	
	/**
	 * 绘画方块
	 * @param g
	 * @param isHeader 是否为方块首部。true时将不绘画插槽
	 * @param isFooter 是否为方块尾部。true时将不绘画插头
	 */
	protected void drawCodeBlock(Graphics g, boolean isHeader, boolean isFooter) {
		this.drawCodeBlock(g, isHeader, isFooter, 0);
	}
	/**
	 * 绘画方块
	 * @param g
	 * @param isHeader 是否为方块首部。true时将不绘画插槽
	 * @param isFooter 是否为方块尾部。true时将不绘画插头
	 * @param offset 插口偏移
	 */
	protected void drawCodeBlock(Graphics g, boolean isHeader, boolean isFooter, int offset) {
		this.drawCodeBlock(g, isHeader, isFooter, getColor(), offset);
	}
	/**
	 * 绘画方块
	 * @param g
	 * @param isHeader 是否为方块首部。true时将不绘画插槽
	 * @param isFooter 是否为方块尾部。true时将不绘画插头
	 * @param main 主要颜色
	 */
	protected void drawCodeBlock(Graphics g, boolean isHeader, boolean isFooter, Color main) {
		this.drawCodeBlock(g, isHeader, isFooter, main, main.brighter(), main.darker());
	}
	/**
	 * 绘画方块
	 * @param g
	 * @param isHeader 是否为方块首部。true时将不绘画插槽
	 * @param isFooter 是否为方块尾部。true时将不绘画插头
	 * @param main 主要颜色
	 * @param offset 插口偏移
	 */
	protected void drawCodeBlock(Graphics g, boolean isHeader, boolean isFooter, Color main, int offset) {
		this.drawCodeBlock(g, isHeader, isFooter, main, main.brighter(), main.darker(), offset);
	}
	/**
	 * 绘画方块
	 * @param g
	 * @param isHeader 是否为方块首部。true时将不绘画插槽
	 * @param isFooter 是否为方块尾部。true时将不绘画插头
	 * @param main 主要颜色
	 * @param light 亮部颜色
	 * @param dark 暗部颜色
	 */
	protected void drawCodeBlock(Graphics g, boolean isHeader, boolean isFooter, Color main, Color light, Color dark) {
		this.drawCodeBlock(g, isHeader, isFooter, main, light, dark, 0);
	}
	/**
	 * 绘画方块
	 * @param g
	 * @param isHeader 是否为方块首部。true时将不绘画插槽
	 * @param isFooter 是否为方块尾部。true时将不绘画插头
	 * @param main 主要颜色
	 * @param light 亮部颜色
	 * @param dark 暗部颜色
	 * @param offset 插口偏移
	 */
	protected void drawCodeBlock(Graphics g, boolean isHeader, boolean isFooter, Color main, Color light, Color dark, int offset) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		//normal
		{
			g2d.setColor(main);
			Polygon p = new Polygon();
			p.addPoint(0, 0);
			if(!isHeader){
				p.addPoint(10, 0);
				p.addPoint(13, 5);
				p.addPoint(23, 5);
				p.addPoint(26, 0);
			}
			p.addPoint(realSize.width-1, 0);
			p.addPoint(realSize.width-1, getRealSize().height-1);
			if(!isFooter){
				p.addPoint(26 + offset, getRealSize().height-1);
				p.addPoint(23 + offset, getRealSize().height+4);
				p.addPoint(13 + offset, getRealSize().height+4);
				p.addPoint(10 + offset, getRealSize().height-1);
			}
			p.addPoint(0, getRealSize().height-1);
			g2d.fillPolygon(p);
		}
		
		//light
		{
			g2d.setColor(light);
			Polygon p = new Polygon();
			p.addPoint(0, getRealSize().height-1);
			p.addPoint(0, 0);
			if(!isHeader){
				p.addPoint(10, 0);
				p.addPoint(13, 5);
				p.addPoint(23, 5);
				p.addPoint(26, 0);
			}
			p.addPoint(realSize.width-1, 0);
			g2d.drawPolyline(p.xpoints, p.ypoints, p.npoints);
		}
		
		//dark
		{
			g2d.setColor(dark);
			Polygon p = new Polygon();
			p.addPoint(realSize.width-1, 0);
			p.addPoint(realSize.width-1, getRealSize().height-1);
			if(!isFooter){
				p.addPoint(26 + offset, getRealSize().height-1);
				p.addPoint(23 + offset, getRealSize().height+4);
				p.addPoint(13 + offset, getRealSize().height+4);
				p.addPoint(10 + offset, getRealSize().height-1);
			}
			p.addPoint(0, getRealSize().height-1);
			g2d.drawPolyline(p.xpoints, p.ypoints, p.npoints);
		}
	}
	
	public boolean addBlock(int x, int y, JBlock block) {
		if(new Rectangle(10,realSize.height-5,20,10).contains(x, y)) return addBlock(block);
		else{
			Component b = getComponentAt(x, y);
  			if(b!=null&&!b.equals(this)&&b instanceof JBlock)return ((JBlock) b).addBlock(x-b.getX(), y-b.getY(), block);
		}
		return false;
	}
	
	public Point getInsertPoint() {
		return new Point(getX()+18, getY());
	}

	@Override
	public boolean addBlock(JBlock block) {
		if(block instanceof JBlockBase&&block.onBeInserted(this)){
			if(next!=null) block.addBlock(next);
			next = (JBlockBase) block;
			add(block);
			return true;
		}
		return false;
	}

	@Override
	public boolean onBeInserted(JBlock block) {
		return true;
	}

	public JBlockBase getNextBlock() {
		return next;
	}
	
	@Override
	public void revalidate() {
		if(next!=null&&!next.getParent().equals(this)) next = null;
		super.revalidate();
	}
}


