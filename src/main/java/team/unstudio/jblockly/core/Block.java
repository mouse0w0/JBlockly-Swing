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

package team.unstudio.jblockly.core;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import team.unstudio.jblockly.core.component.BlockLine;

/*
 * Code block
 */
public class Block implements Cloneable{
	
	/**
	 * LayoutType:
	 *   Automatic: xxxx
	 *   External: xxxx
	 *   Inline: xxxx
	 */
	public enum LayoutType{
		Automatic,External,Inline
	}
	
	public final BlockDescriber describer;
	
	public Block(BlockDescriber describer) {
		this.describer = describer;
	}
	
	private BlockWorkspace workspace = null;
	
	public BlockWorkspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(BlockWorkspace workspace) {
		this.workspace = workspace;
	}
	
	private Block parent = null;

	public Block getParent() {
		return parent;
	}

	public void setParent(Block parent) {
		this.parent = parent;
	}
	
	private Block next = null;
	
	public Block getNext() {
		return next;
	}

	public void setNext(Block next) {
		this.next = next;
	}
	
	private String message;
	private final List<BlockLine> lines = new ArrayList<>();
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void addLine(BlockLine line){
		line.setParent(this);
		lines.add(line);
	}

	private boolean moveable = true;
	private boolean disable = false;
	private boolean editable = true;
	private boolean folded = false;
	private LayoutType layoutType = LayoutType.Automatic;
	private String tooltip;

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public boolean isMoveable() {
		return moveable;
	}

	public void setMoveable(boolean moveable) {
		this.moveable = moveable;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}
	
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isFolded() {
		return folded;
	}

	public void setFolded(boolean folded) {
		this.folded = folded;
	}

	public LayoutType getLayoutType() {
		return layoutType;
	}

	public void setLayoutType(LayoutType layoutType) {
		this.layoutType = layoutType;
	}

	private int width,height,x,y;
	private String path,darkPath,lightPath;
	private boolean selected = false;
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setLocation(int x,int y){
		this.x = x;
		this.y = y;
	}

	public String getPath() {
		return path;
	}
	
	public String getDarkPath() {
		return darkPath;
	}

	public String getLightPath() {
		return lightPath;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void dispose(){
		for(BlockLine line:lines)line.dispose();
	}
	
	public void doLayout(){
		int x=0,y=0;
		for(BlockLine line:lines){
			
		}
	}
	
	public void draw(GraphicsContext graphics){
		graphics.translate(x, y);
	}
}
