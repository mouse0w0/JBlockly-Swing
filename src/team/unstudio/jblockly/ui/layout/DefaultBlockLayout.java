package team.unstudio.jblockly.ui.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

import team.unstudio.jblockly.block.BlockUtils;
import team.unstudio.jblockly.block.BlockDescriber.BlockType;
import team.unstudio.jblockly.ui.block.Block;

public class DefaultBlockLayout implements LayoutManager{

	@Override
	public void layoutContainer(Container parent) {
		if(!(parent instanceof Block)) throw new IllegalArgumentException("Container type must be Block");
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
	
	protected void prePaintBlock(Block block){
		Path2D path = new Path2D.Double();
		List<Line2D> darkShadow = new ArrayList<>();
		List<Area> insertSlotAreas = new ArrayList<>();
	}
	
	protected void prePaintTop(Block block,Path2D path,int x,int y,int width,List<Line2D> darkShadow){
		if(block.getBlockDescriber().getBlockType() == BlockType.INSERT){
			path.moveTo(x+BlockUtils.INSERT_WIDTH-1, y+BlockUtils.INSERT_OFFSET_Y+BlockUtils.INSERT_HEIGHT-1);
			path.lineTo(x, y+BlockUtils.INSERT_OFFSET_Y+BlockUtils.INSERT_HEIGHT-1);
			path.lineTo(x, y+BlockUtils.INSERT_OFFSET_Y-1);
			path.lineTo(x+BlockUtils.INSERT_WIDTH-1, y+BlockUtils.INSERT_OFFSET_Y-1);
			path.lineTo(x+BlockUtils.INSERT_WIDTH-1, y);
			path.lineTo(x+width-1, y);
		}else if(block.getBlockDescriber().getBlockType() == BlockType.NOMRAL||block.getBlockDescriber().getBlockType() == BlockType.END){
			path.moveTo(x, y);
			path.lineTo(x+BlockUtils.NEXT_OFFSET_X-1, y);
			path.lineTo(x+BlockUtils.NEXT_OFFSET_X-1, y+BlockUtils.NEXT_HEIGHT-1);
			path.lineTo(x+BlockUtils.NEXT_OFFSET_X+BlockUtils.INSERT_WIDTH-1, y+BlockUtils.NEXT_HEIGHT-1);
			path.lineTo(x+BlockUtils.NEXT_OFFSET_X+BlockUtils.INSERT_WIDTH-1, y);
			path.lineTo(x+width-1, y);
		}else{
			path.moveTo(x, y);
			path.lineTo(x+width-1, y);
		}
	}
	
	protected void prePaintSub(Path2D path,int x,int y,int width,List<Line2D> darkShadow){
		path.lineTo(x+BlockUtils.SIDE_WIDTH-1, y);
		path.lineTo(x+BlockUtils.SIDE_WIDTH+width-1, y);
	}
	
	protected void prePaintBranch(Path2D path,int x,int y,int width,List<Line2D> darkShadow){
		
	}
	
	protected void prePaintBottom(Path2D path,int x,int y,int width,List<Line2D> darkShadow){
		
	}
	
	protected void prePaintFolded(Path2D path,int x,int y,int width,List<Line2D> darkShadow){
		
	}
	
	protected void prePaintInsertEndSlot(Path2D path,int x,int y,List<Line2D> darkShadow){

	}
	
	protected Area prePaintInsertSlot(int x,int y,List<Line2D> darkShadow){
		Area area = new Area();
		return area;
	}
}
