package team.unstudio.jblockly.ui.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager2;

public class BlockRowLayout implements LayoutManager2{
	
	/**
	 * 距离上一行垂直间距
	 */
	private int vgap;
	/**
	 * 组件水平间距
	 */
	private int hgap;
	
	public BlockRowLayout() {}
	
	public BlockRowLayout(int vgap,int hgap){
		this.vgap = vgap;
		this.hgap = hgap;
	}
	
	public BlockRowLayout(int hgap){
		this(5,hgap);
	}

	@Override
	public void addLayoutComponent(String name, Component comp) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void layoutContainer(Container parent) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void removeLayoutComponent(Component comp) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void addLayoutComponent(Component comp, Object constraints) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public float getLayoutAlignmentX(Container target) {
		return 0.5f;
	}

	@Override
	public float getLayoutAlignmentY(Container target) {
		return 0.5f;
	}

	@Override
	public void invalidateLayout(Container target) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public Dimension maximumLayoutSize(Container target) {
		// TODO 自动生成的方法存根
		return null;
	}
}

