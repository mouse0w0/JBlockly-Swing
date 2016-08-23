package team.unstudio.jblockly.ui.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager2;
import java.util.ArrayList;
import java.util.List;

public class DefaultBlockLayout implements LayoutManager2{
	
	public static final String RIGHT = "RIGHT";
	public static final String LEFT = "LEFT";
	public static final String NEXT = "NEXT";
	public static final String BRANCH = "BRANCH";
	
	private List<BlockRow> blockRows = new ArrayList<>();

	@Override
	public void layoutContainer(Container parent) {
		
	}
	
	@Override
	public void invalidateLayout(Container target) {
		blockRows.clear();
	}
	
	@Override
	public Dimension maximumLayoutSize(Container target) {
		return preferredLayoutSize(target);
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		return preferredLayoutSize(parent);
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		// TODO 自动生成的方法存根
		return null;
	}
	
	@Override
	public void addLayoutComponent(String name, Component comp) {
		// TODO 自动生成的方法存根
		
	}
	
	@Override
	public void addLayoutComponent(Component comp, Object constraints) {
		latest().add(comp);
	}

	@Override
	public void removeLayoutComponent(Component comp) {
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
	
	public BlockRow latest(){
		return blockRows.get(blockRows.size()-1);
	}
	
	public DefaultBlockLayout newline(){
		blockRows.add(new BlockRow());
		return this;
	}
	
	public class BlockRow extends ArrayList<Component>{

		private static final long serialVersionUID = 1L;
		
		/**
		 * 距离上一行垂直间距
		 */
		private int vgap;
		/**
		 * 组件水平间距
		 */
		private int hgap;
		
		public BlockRow() {}
		
		public BlockRow(int vgap,int hgap){
			this.setVgap(vgap);
			this.hgap = hgap;
		}
		
		public BlockRow(int hgap){
			this(5,hgap);
		}
		
		public void doLayout(Container parent,int x,int y){
			for(Component c:this){
				if(c.getParent().equals(parent)){
					c.doLayout();
					c.setLocation(x, y);
					x+=c.getPreferredSize().width;
					x+=hgap;
				}else remove(c);
			}
		}

		public Dimension preferredLayoutSize(Container parent) {
			int width=0,height=0;
			for(Component c:this){
				if(c.getParent().equals(parent)){
					c.doLayout();
					width+=c.getPreferredSize().width;
					width+=hgap;
					if(c.getPreferredSize().height>height)height=c.getPreferredSize().height;
				}else remove(c);
			}
			return new Dimension(width, height-hgap);
		}

		public int getVgap() {
			return vgap;
		}

		public void setVgap(int vgap) {
			this.vgap = vgap;
		}
		
		public int getHgap() {
			return hgap;
		}

		public void setHgap(int hgap) {
			this.hgap = hgap;
		}

	}
}
