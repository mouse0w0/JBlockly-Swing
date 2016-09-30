package team.unstudio.jblockly.block;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Area;

import team.unstudio.jblockly.ui.block.Block;

public class BlockInput implements IInput{
	
	public enum InputType{
		BRANCH,INSERT_SLOT,INSERT_END_SLOT,NEXT
	}
	
	private Block block,parent;
	private Area area;
	private Point point;
	private InputType type;

	@Override
	public String value() {
		return null;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public void setPoint(int x, int y){
		this.point = new Point(x, y);
	}

	public Block getParent() {
		return parent;
	}

	public void setParent(Block parent) {
		this.parent = parent;
	}

	public InputType getType() {
		return type;
	}

	public void setType(InputType type) {
		this.type = type;
	}
	
	private final InputDescriber describer;
	
	public BlockInput(InputDescriber describer) {
		this.describer = describer;
	}
	
	@Override
	public InputDescriber getDescriber() {
		return describer;
	}
	
	private Point textPoint;

	@Override
	public Point getTextPoint() {
		return textPoint;
	}

	@Override
	public void setTextPoint(Point point) {
		textPoint = point;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension((int)(block.getFontMetrics(BlockUtils.DEFAULT_FONT).getStringBounds(describer.getDescription(), block.getGraphics()).getWidth())+BlockUtils.MIN_TEXT_GAP+block.getPreferredSize().width,block.getPreferredSize().height);
	}
}
