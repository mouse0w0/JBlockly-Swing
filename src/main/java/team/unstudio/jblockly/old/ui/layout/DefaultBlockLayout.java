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

package team.unstudio.jblockly.old.ui.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

import team.unstudio.jblockly.core.BlockUtils;
import team.unstudio.jblockly.old.block.BlockDescriber.HeadType;
import team.unstudio.jblockly.old.ui.block.Block;

public class DefaultBlockLayout implements LayoutManager{
	
	private int vgap,hgap;
	private Area blockArea;
	private final List<Line2D> darkShadow = new ArrayList<>();
	
	public DefaultBlockLayout(int vgap,int hgap) {
		this.vgap = vgap;
		this.hgap = hgap;
	}

	public DefaultBlockLayout() {
		this(BlockUtils.VGAP,BlockUtils.HGAP);
	}
	
	@Override
	public void layoutContainer(Container parent) {
		if(!(parent instanceof Block)) throw new IllegalArgumentException("Container type must be Block");
		
		Block block = (Block) parent;
		Path2D path = new Path2D.Double();
		List<Area> insertSlotAreas = new ArrayList<>();
		darkShadow.clear();
		
	}
	
	@Override
	public Dimension minimumLayoutSize(Container parent) {
		return preferredLayoutSize(parent);
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		if(!(parent instanceof Block)) throw new IllegalArgumentException("Container type must be Block");
		return null;
	}
	
	@Override
	public void addLayoutComponent(String name, Component comp) {}

	@Override
	public void removeLayoutComponent(Component comp) {}
	
	protected void prePaintTop(Block block,Path2D path,int x,int y,int width,List<Line2D> darkShadow){
		if(block.getBlockDescriber().getHeadType() == HeadType.NEXT){
			path.moveTo(x, y);
			path.lineTo(x+BlockUtils.NEXT_SLOT_OFFSET_X-1, y);
			path.lineTo(x+BlockUtils.NEXT_SLOT_OFFSET_X-1, y+BlockUtils.NEXT_SLOT_HEIGHT-1);
			path.lineTo(x+BlockUtils.NEXT_SLOT_OFFSET_X+BlockUtils.INSERT_WIDTH-1, y+BlockUtils.NEXT_SLOT_HEIGHT-1);
			path.lineTo(x+BlockUtils.NEXT_SLOT_OFFSET_X+BlockUtils.INSERT_WIDTH-1, y);
			path.lineTo(x+width-1, y);
			darkShadow.add(new Line2D.Double(x+BlockUtils.NEXT_SLOT_OFFSET_X-1, y, x+BlockUtils.NEXT_SLOT_OFFSET_X-1, y+BlockUtils.NEXT_SLOT_HEIGHT-1));
		}else if(block.getBlockDescriber().getHeadType() == HeadType.INSERT){
			path.moveTo(x+BlockUtils.INSERT_WIDTH-1, y+BlockUtils.INSERT_OFFSET_Y+BlockUtils.INSERT_HEIGHT-1);
			path.lineTo(x, y+BlockUtils.INSERT_OFFSET_Y+BlockUtils.INSERT_HEIGHT-1);
			path.lineTo(x, y+BlockUtils.INSERT_OFFSET_Y-1);
			path.lineTo(x+BlockUtils.INSERT_WIDTH-1, y+BlockUtils.INSERT_OFFSET_Y-1);
			path.lineTo(x+BlockUtils.INSERT_WIDTH-1, y);
			path.lineTo(x+width-1, y);
			darkShadow.add(new Line2D.Double(x+BlockUtils.INSERT_WIDTH-1, y+BlockUtils.INSERT_OFFSET_Y+BlockUtils.INSERT_HEIGHT-1, x, y+BlockUtils.INSERT_OFFSET_Y+BlockUtils.INSERT_HEIGHT-1));
		}else{
			path.moveTo(x, y);
			path.lineTo(x+width-1, y);
		}
	}
	
	protected void prePaintSub(Block block,Path2D path,int x,int y,int width,List<Line2D> darkShadow){
		path.lineTo(x+BlockUtils.SIDE_WIDTH-1, y);
		path.lineTo(x+BlockUtils.SIDE_WIDTH+width-1, y);
	}
	
	protected void prePaintBranch(Block block,Path2D path,int x,int y,int width,List<Line2D> darkShadow){
		path.lineTo(x+width-1, y);
		path.lineTo(x+BlockUtils.SIDE_WIDTH+BlockUtils.NEXT_OFFSET_X+BlockUtils.NEXT_WIDTH-1, y);
		path.lineTo(x+BlockUtils.SIDE_WIDTH+BlockUtils.NEXT_OFFSET_X+BlockUtils.NEXT_WIDTH-1, y+BlockUtils.NEXT_HEIGHT-1);
		path.lineTo(x+BlockUtils.SIDE_WIDTH+BlockUtils.NEXT_OFFSET_X-1, y+BlockUtils.NEXT_HEIGHT-1);
		path.lineTo(x+BlockUtils.SIDE_WIDTH+BlockUtils.NEXT_OFFSET_X-1, y);
		path.lineTo(x+BlockUtils.SIDE_WIDTH-1, y);
		darkShadow.add(new Line2D.Double(x+width-1, y, x+BlockUtils.SIDE_WIDTH+BlockUtils.NEXT_OFFSET_X+BlockUtils.NEXT_WIDTH-1, y));
		darkShadow.add(new Line2D.Double(x+BlockUtils.SIDE_WIDTH+BlockUtils.NEXT_OFFSET_X+BlockUtils.NEXT_WIDTH-1, y, x+BlockUtils.SIDE_WIDTH+BlockUtils.NEXT_OFFSET_X+BlockUtils.NEXT_WIDTH-1, y+BlockUtils.NEXT_HEIGHT-1));
		darkShadow.add(new Line2D.Double(x+BlockUtils.SIDE_WIDTH+BlockUtils.NEXT_OFFSET_X+BlockUtils.NEXT_WIDTH-1, y+BlockUtils.NEXT_HEIGHT-1, x+BlockUtils.SIDE_WIDTH+BlockUtils.NEXT_OFFSET_X-1, y+BlockUtils.NEXT_HEIGHT-1));
		darkShadow.add(new Line2D.Double(x+BlockUtils.SIDE_WIDTH+BlockUtils.NEXT_OFFSET_X-1, y, x+BlockUtils.SIDE_WIDTH-1, y));
	}
	
	protected void prePaintBottom(Block block,Path2D path,int x,int y,int width,List<Line2D> darkShadow,boolean next){
		path.lineTo(x+width-1, y);
		if(next){
			path.lineTo(x+BlockUtils.NEXT_OFFSET_X+BlockUtils.NEXT_WIDTH-1, y);
			path.lineTo(x+BlockUtils.NEXT_OFFSET_X+BlockUtils.NEXT_WIDTH-1, y+BlockUtils.NEXT_HEIGHT-1);
			path.lineTo(x+BlockUtils.NEXT_OFFSET_X-1, y+BlockUtils.NEXT_HEIGHT-1);
			path.lineTo(x+BlockUtils.NEXT_OFFSET_X-1, y);
			darkShadow.add(new Line2D.Double(x+width-1, y, x+BlockUtils.NEXT_OFFSET_X+BlockUtils.NEXT_WIDTH-1, y));
			darkShadow.add(new Line2D.Double(x+BlockUtils.NEXT_OFFSET_X+BlockUtils.NEXT_WIDTH-1, y, x+BlockUtils.NEXT_OFFSET_X+BlockUtils.NEXT_WIDTH-1, y+BlockUtils.NEXT_HEIGHT-1));
			darkShadow.add(new Line2D.Double(x+BlockUtils.NEXT_OFFSET_X+BlockUtils.NEXT_WIDTH-1, y+BlockUtils.NEXT_HEIGHT-1, x+BlockUtils.NEXT_OFFSET_X-1, y+BlockUtils.NEXT_HEIGHT-1));
			darkShadow.add(new Line2D.Double(x+BlockUtils.NEXT_OFFSET_X-1, y, x, y));
		}else{
			darkShadow.add(new Line2D.Double(x+width-1, y, x, y));
		}
		path.lineTo(x, y);
	}
	
	protected Area prePaintInsertEndSlot(Block block,Path2D path,int x,int y,List<Line2D> darkShadow){
		path.lineTo(x+BlockUtils.INSERT_SLOT_WIDTH-1, y);
		path.lineTo(x, y);
		path.lineTo(x, y+BlockUtils.INSERT_SLOT_HEIGHT-1);
		path.lineTo(x+BlockUtils.INSERT_SLOT_WIDTH-1, y+BlockUtils.INSERT_SLOT_HEIGHT-1);
		darkShadow.add(new Line2D.Double(x+BlockUtils.INSERT_SLOT_WIDTH-1, y, x, y));
		darkShadow.add(new Line2D.Double(x, y,x, y+BlockUtils.INSERT_SLOT_HEIGHT-1));
		Polygon p = new Polygon(new int[]{x+BlockUtils.INSERT_SLOT_WIDTH-1,x,x,x+BlockUtils.INSERT_SLOT_WIDTH-1},new int[]{y,y,y+BlockUtils.INSERT_SLOT_HEIGHT-1,y+BlockUtils.INSERT_SLOT_HEIGHT-1},4);
		return new Area(p);
	}
	
	protected Area prePaintInsertSlot(Block block,int x,int y,int width,int height,List<Line2D> darkShadow){
		Polygon p = new Polygon();
		p.addPoint(x+BlockUtils.INSERT_SLOT_WIDTH-1, y);
		p.addPoint(x+BlockUtils.INSERT_SLOT_WIDTH-1, y+BlockUtils.INSERT_SLOT_OFFSET_Y-1);
		p.addPoint(x, y+BlockUtils.INSERT_SLOT_OFFSET_Y-1);
		p.addPoint(x, y+BlockUtils.INSERT_SLOT_OFFSET_Y+BlockUtils.INSERT_SLOT_HEIGHT-1);
		p.addPoint(x+BlockUtils.INSERT_SLOT_WIDTH-1, y+BlockUtils.INSERT_SLOT_OFFSET_Y+BlockUtils.INSERT_SLOT_HEIGHT-1);
		p.addPoint(x+BlockUtils.INSERT_SLOT_WIDTH-1, y+height-1);
		p.addPoint(x+width-1, y+height-1);
		p.addPoint(x+width-1, y);
		darkShadow.add(new Line2D.Double(x+BlockUtils.INSERT_SLOT_WIDTH-1, y,x+BlockUtils.INSERT_SLOT_WIDTH-1, y+BlockUtils.INSERT_SLOT_OFFSET_Y-1));
		darkShadow.add(new Line2D.Double(x+BlockUtils.INSERT_SLOT_WIDTH-1, y+BlockUtils.INSERT_SLOT_OFFSET_Y-1, x, y+BlockUtils.INSERT_SLOT_OFFSET_Y-1));
		darkShadow.add(new Line2D.Double(x, y+BlockUtils.INSERT_SLOT_OFFSET_Y-1, x, y+BlockUtils.INSERT_SLOT_OFFSET_Y+BlockUtils.INSERT_SLOT_HEIGHT-1));
		darkShadow.add(new Line2D.Double(x+BlockUtils.INSERT_SLOT_WIDTH-1, y+BlockUtils.INSERT_SLOT_OFFSET_Y+BlockUtils.INSERT_SLOT_HEIGHT-1, x+BlockUtils.INSERT_SLOT_WIDTH-1, y+height-1));
		darkShadow.add(new Line2D.Double(x+BlockUtils.INSERT_SLOT_WIDTH-1, y, x+width-1, y));
		return new Area(p);
	}
	
	public int getVgap() {
		return vgap;
	}

	public void setVgap(int vgap) {
		this.vgap = vgap;
	}

	public int getHgap() {
		return hgap;
	}

	public void setHgap(int hgap) {
		this.hgap = hgap;
	}

	public Area getBlockArea() {
		return blockArea;
	}

	public List<Line2D> getDarkShadow() {
		return darkShadow;
	}
}
