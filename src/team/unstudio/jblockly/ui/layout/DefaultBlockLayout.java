package team.unstudio.jblockly.ui.layout;

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

import team.unstudio.jblockly.block.BlockUtils;
import team.unstudio.jblockly.block.BlockDescriber.BlockType;
import team.unstudio.jblockly.ui.block.Block;

public class DefaultBlockLayout implements LayoutManager{
	
	private int vgap,hgap;
	private Area blockArea;
	private List<Line2D> darkShadow = new ArrayList<>();
	
	public DefaultBlockLayout(int vgap,int hgap) {
		this.vgap = vgap;
		this.hgap = hgap;
	}
	
	public DefaultBlockLayout() {
		this(10,10);
	}
	
	@Override
	public void layoutContainer(Container parent) {
		if(!(parent instanceof Block)) throw new IllegalArgumentException("Container type must be Block");
		
		Block block = (Block) parent;
		Path2D path = new Path2D.Double();
		List<Line2D> darkShadow = new ArrayList<>();
		List<Area> insertSlotAreas = new ArrayList<>();
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
		if(block.getBlockDescriber().getBlockType() == BlockType.INSERT){
			path.moveTo(x+BlockUtils.INSERT_WIDTH-1, y+BlockUtils.INSERT_OFFSET_Y+BlockUtils.INSERT_HEIGHT-1);
			path.lineTo(x, y+BlockUtils.INSERT_OFFSET_Y+BlockUtils.INSERT_HEIGHT-1);
			path.lineTo(x, y+BlockUtils.INSERT_OFFSET_Y-1);
			path.lineTo(x+BlockUtils.INSERT_WIDTH-1, y+BlockUtils.INSERT_OFFSET_Y-1);
			path.lineTo(x+BlockUtils.INSERT_WIDTH-1, y);
			path.lineTo(x+width-1, y);
			darkShadow.add(new Line2D.Double(x+BlockUtils.INSERT_WIDTH-1, y+BlockUtils.INSERT_OFFSET_Y+BlockUtils.INSERT_HEIGHT-1, x, y+BlockUtils.INSERT_OFFSET_Y+BlockUtils.INSERT_HEIGHT-1));
		}else if(block.getBlockDescriber().getBlockType() == BlockType.NOMRAL||block.getBlockDescriber().getBlockType() == BlockType.END){
			path.moveTo(x, y);
			path.lineTo(x+BlockUtils.NEXT_SLOT_OFFSET_X-1, y);
			path.lineTo(x+BlockUtils.NEXT_SLOT_OFFSET_X-1, y+BlockUtils.NEXT_SLOT_HEIGHT-1);
			path.lineTo(x+BlockUtils.NEXT_SLOT_OFFSET_X+BlockUtils.INSERT_WIDTH-1, y+BlockUtils.NEXT_SLOT_HEIGHT-1);
			path.lineTo(x+BlockUtils.NEXT_SLOT_OFFSET_X+BlockUtils.INSERT_WIDTH-1, y);
			path.lineTo(x+width-1, y);
			darkShadow.add(new Line2D.Double(x+BlockUtils.NEXT_SLOT_OFFSET_X-1, y, x+BlockUtils.NEXT_SLOT_OFFSET_X-1, y+BlockUtils.NEXT_SLOT_HEIGHT-1));
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
	
	protected void prePaintBottom(Block block,Path2D path,int x,int y,int width,List<Line2D> darkShadow){
		path.lineTo(x+width-1, y);
		if(block.getBlockDescriber().getBlockType() == BlockType.HEAD||block.getBlockDescriber().getBlockType() == BlockType.NOMRAL){
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
}
