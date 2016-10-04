package team.unstudio.jblockly.core.component;

import java.util.ArrayList;
import java.util.List;

import team.unstudio.jblockly.core.Block;

public class BlockLine{
	
	public enum AlignType{
		Left,Right
	}
	
	private AlignType align = AlignType.Left;
	private final List<BlockComponent> components = new ArrayList<>();
	private int x,y,height,width;
	private Block parent;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setPoint(int x, int y) {
		setX(x);
		setY(y);
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}
	
	public Block getParent() {
		return parent;
	}

	public void setParent(Block parent) {
		this.parent = parent;
	}

	public List<BlockComponent> getComponents() {
		return components;
	}
	
	public void doLayout(){
		
	}

	public AlignType getAlign() {
		return align;
	}

	public void setAlign(AlignType align) {
		this.align = align;
	}
}
