/*
 * Copyright (c) 2016, Unknown Domain. All rights reserved.
 * GUN AGPLv3. Use is subject to license terms.
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

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import team.unstudio.jblockly.core.Block;
import team.unstudio.jblockly.core.BlockUtils;

//TODO:行类型宽度和高度修复
public class BlockLine {

	public enum AlignType {
		Left, Right
	}
	
	public enum LineType{
		None, Branch, Insert
	}

	private AlignType align = AlignType.Left;
	private LineType line = LineType.None;
	private final List<BlockComponent> components = new ArrayList<>();
	private int x, y;
	protected int childX, childY;
	protected Block parent, child = null;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
		doLayout();
	}

	public void setY(int y) {
		this.y = y;
		doLayout();
	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
		doLayout();
	}

	public int getComponentHeight() {
		int height = 0;
		for (BlockComponent component : components)
			if (component.getHeight() > height)
				height = component.getHeight();
		return height;
	}
	
	public int getHeight(){
		switch(line){
			case Branch:
				return getComponentHeight()+(getChild()!=null?getChild().getHeight():0);
			case Insert:
				return getComponentHeight()>=(getChild()!=null?getChild().getHeight():0)?getComponentHeight():getChild().getHeight();
			default:
				return getComponentHeight();
		}
	}

	public int getComponentWidth() {
		int width = BlockUtils.HGAP;
		for (BlockComponent component : components)
			width += component.getWidth() + BlockUtils.HGAP;
		return width;
	}
	
	public int getWidth() {
		switch(line){
			case Branch:
				return getComponentWidth()>=(getChild()!=null?getChild().getWidth():0)?getComponentWidth():getChild().getWidth();
			case Insert:
				return getComponentWidth()+(getChild()!=null?getChild().getWidth():0);
			default:
				return getComponentHeight();
		}
	}

	public Block getParent() {
		return parent;
	}

	public void setParent(Block parent) {
		if(this.parent!=null) removeNotify();
		this.parent = parent;
		if(parent!=null) addNotify();
	}
	
	public void addComponent(BlockComponent component){
		components.add(component);
		if(parent!=null) parent.add((Component) component);
	}
	
	public void removeComponent(BlockComponent component){
		components.remove(component);
		if(parent!=null) parent.remove((Component)component);
	}

	public void doLayout() {
		if(parent==null) return;
		
		if(align==AlignType.Left){
			int y = getY(),x = getX() + BlockUtils.HGAP;
			for (BlockComponent c : components) {
				c.setLocation(x, y);
				((Component)c).doLayout();
				x += c.getWidth() + BlockUtils.HGAP;
			}
		}else if(align == AlignType.Right){
			//TODO: Support align right.
		}
		
		if(child!=null) child.setLocation(childX, childY);
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
		if(this.child!=null){
			this.child.setBlockParent(null);
		}
		this.child = child;
		if(child!=null) {
			child.setBlockParent(parent);
			child.setLocation(childX, childY);
		}
	}

	public int getChildX() {
		return childX;
	}

	public void setChildX(int childX) {
		this.childX = childX;
		if(child!=null) child.setLocation(childX, childY);
	}

	public int getChildY() {
		return childY;
	}

	public void setChildY(int childY) {
		this.childY = childY;
		if(child!=null) child.setLocation(childX, childY);
	}
	
	public void setChildLocation(int childX,int childY){
		this.childX = childX;
		this.childY = childY;
		if(child!=null) child.setLocation(childX, childY);
	}

	public LineType getLineType() {
		return line;
	}

	public void setLineType(LineType line) {
		this.line = line;
	}
	
	public void addNotify(){
		for (BlockComponent component : components) parent.add((Component) component);
		if(getChild()!=null)getParent().add(getChild());
	}
	
	public void removeNotify(){
		for (BlockComponent component : components) parent.remove((Component) component);
		if(getChild()!=null)getParent().remove(getChild());
	}
}
