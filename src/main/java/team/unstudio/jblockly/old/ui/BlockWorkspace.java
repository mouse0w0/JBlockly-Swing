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

package team.unstudio.jblockly.old.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import team.unstudio.jblockly.old.ui.block.JBlock;
import team.unstudio.jblockly.old.ui.block.JBlockBase;
import team.unstudio.jblockly.old.ui.block.JBlockHead;
import team.unstudio.jblockly.old.ui.block.JBlockInsert;
import team.unstudio.jblockly.old.ui.block.JBlockInsertEndSlot;
import team.unstudio.jblockly.old.ui.block.JBlockInsertSlot;
import team.unstudio.jblockly.old.ui.block.JBlockMulti;
import team.unstudio.jblockly.old.ui.block.JBlockMultiPackage;

public class BlockWorkspace extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private Component dragging;
	private final JPanel displayLayer;
	
	public BlockWorkspace() {
		setSize(2000, 2000);
		setPreferredSize(new Dimension(2000, 2000));
		setLayout(null);
		setBackground(new Color(41,41,41));
		
		displayLayer = new JPanel();
		displayLayer.setOpaque(false);
		displayLayer.setLayout(null);
		displayLayer.setBounds(0, 0, 2000, 2000);
		add(displayLayer,0);
		
		//测试用代码块加载
		JBlockMulti multi = new JBlockHead();
		{
		JLabel label = new JLabel("事件入口");
		label.setForeground(getIdealTextColor(multi.getColor()));
		multi.add(label);
		}
		multi.setLocation(100, 100);
		displayLayer.add(multi);
		
		JBlockMultiPackage mif = new JBlockMultiPackage();
		{
		JLabel label = new JLabel("如果可以跳票");
		label.setForeground(getIdealTextColor(mif.getColor()));
		mif.add(label);
		}
		multi.addBlock(mif);
		
		JBlock codeBlock = new JBlockBase();
		{
		JLabel label = new JLabel("这是一个滑稽的代码块......1");
		label.setForeground(getIdealTextColor(codeBlock.getColor()));
		codeBlock.add(label);
		}
		displayLayer.add(codeBlock);
		
		JBlock codeBlock2 = new JBlockBase();
		codeBlock2.add(new JLabel("这是一个滑稽的代码块......2"));
		codeBlock2.setLocation(200,200);
		displayLayer.add(codeBlock2);
		
		JBlockMultiPackage mif2 = new JBlockMultiPackage();
		{
		JLabel label = new JLabel("如果不能跳票");
		label.setForeground(getIdealTextColor(mif.getColor()));
		mif2.add(label);
		}
		displayLayer.add(mif2);
		
		JBlockInsertSlot insertSlot = new JBlockInsertSlot();
		JBlockInsertEndSlot ie1 = new JBlockInsertEndSlot();
		
		JBlock codeBlock3 = new JBlockBase();
		codeBlock3.add(new JLabel("这是一个滑稽的代码块......3"));
		codeBlock3.add(ie1);
		codeBlock3.add(insertSlot);
		codeBlock3.setLocation(200,200);
		displayLayer.add(codeBlock3);
		
		JBlockInsert i1 = new JBlockInsert();
		i1.add(new JLabel("这是一个滑稽的1号BlockInsert......"));
		displayLayer.add(i1);
		
		JBlockInsert i2 = new JBlockInsert();
		i2.add(new JLabel("这是一个滑稽的2号BlockInsert......"));
		i2.add(new JBlockInsertSlot());
		displayLayer.add(i2);
	}

	public Component getDragging() {
		return dragging;
	}

	public void setDragging(Component dragging) {
		if(dragging!=null)add(dragging,0);
		this.dragging = dragging;
	}

	public JPanel getDisplayLayer() {
		return displayLayer;
	}
	
	private Color getIdealTextColor(Color color) {
		final int nThreshold = 105;
		int bgDelta = (int) ((color.getRed() * 0.299) + (color.getGreen() * 0.587) + (color.getBlue() * 0.114));
		Color foreColor = (255 - bgDelta < nThreshold) ? Color.BLACK : Color.WHITE;
		return foreColor;
	}
}
