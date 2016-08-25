package team.unstudio.jblockly.ui.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

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
}
