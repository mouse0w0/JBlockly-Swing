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

package team.unstudio.jblockly.core;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import team.unstudio.jblockly.core.component.BlockLine;

/*
 * Code block
 */
public class Block extends JPanel implements Cloneable {

	private static final long serialVersionUID = 1L;

	/**
	 * LayoutType: Automatic: xxxx External: xxxx Inline: xxxx
	 */
	public enum LayoutType {
		Automatic, External, Inline
	}
	
	/**
	 * ConnectionType: None: xxxx Left: xxxx TopAndBottom: xxxx Top: xxxx
	 * Bottom: xxxx
	 */
	public enum ConnectionType {
		None, Left, TopAndBottom, Top, Bottom
	}
	
	public Block() {}

	private BlockWorkspace workspace = null;

	public BlockWorkspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(BlockWorkspace workspace) {
		this.workspace = workspace;
	}

	private Block parent = null;

	public Block getBlockParent() {
		return parent;
	}

	public void setBlockParent(Block parent) {
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

	public void addLine(BlockLine line) {
		line.setParent(this);
		lines.add(line);
	}

	private boolean moveable = true;
	private boolean disable = false;
	private boolean editable = true;
	private boolean folded = false;
	private LayoutType layoutType = LayoutType.Automatic;
	private ConnectionType connectionType = ConnectionType.None;
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

	public ConnectionType getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(ConnectionType connectionType) {
		this.connectionType = connectionType;
	}

	protected Area area;
	private int color = 0X000000;
	private boolean selected = false;
	
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	
	public void setColor(int r,int g,int b) {
		setColor((r<<16)+(g<<8)+b);
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public void dispose() {
		for (BlockLine line : lines)
			line.dispose();
	}

	@Override
	public void doLayout() {
		int x = 0, y = BlockUtils.VGAP;
		for (BlockLine line : lines) {
			line.setLocation(x, y);
			line.doLayout();
			y+=line.getHeight()+BlockUtils.VGAP;
		}
		if(next!=null)next.setLocation(x, y);
	}
	
	@Override
	public boolean contains(int x, int y) {
		return area.contains(x, y);
	}
	
	@Override
	public boolean contains(Point p) {
		return contains(p.x,p.y);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO 自动生成的方法存根
		super.paintComponent(g);
	}
}
