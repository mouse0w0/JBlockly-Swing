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

package team.unstudio.jblockly.core.component;

import java.util.ArrayList;
import java.util.List;

import team.unstudio.jblockly.core.Block;
import team.unstudio.jblockly.core.BlockUtils;

public class BlockLine {

	public enum AlignType {
		Left, Right
	}

	private AlignType align = AlignType.Left;
	private final List<BlockComponent> components = new ArrayList<>();
	private int x, y;
	protected int childX,childY;
	protected Block parent, child = null;

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
		double height = 0;
		for (BlockComponent component : components)
			if (component.getHeight() > height)
				height = component.getHeight();
		return height;
	}

	public double getWidth() {
		double width = BlockUtils.HGAP;
		for (BlockComponent component : components)
			width += component.getWidth() + BlockUtils.HGAP;
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

	public void doLayout() {
		int y = this.y+BlockUtils.VGAP,x = this.x + BlockUtils.HGAP;
		for (BlockComponent c : components) {
			c.setPoint(x, y);
			x += c.getWidth() + BlockUtils.HGAP;
		}
		if(child!=null){
			child.setX(childX);
			child.setY(childY);
		}
		//TODO: Support align right.
	}

	public AlignType getAlign() {
		return align;
	}

	public void setAlign(AlignType align) {
		this.align = align;
	}

	public Block getChild() {
		return child;
	}

	public void setChild(Block child) {
		throw new UnsupportedOperationException("Can't add Block in BlockLine");
	}

	public int getChildX() {
		return childX;
	}

	public void setChildX(int childX) {
		this.childX = childX;
	}

	public int getChildY() {
		return childY;
	}

	public void setChildY(int childY) {
		this.childY = childY;
	}

	public void dispose() {
		for (BlockComponent component : components)
			component.dispose();
	}
}
