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

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import team.unstudio.jblockly.old.ui.block.JBlock;
import team.unstudio.jblockly.old.ui.component.JBlockInsert;

public class BlockMenu extends JPopupMenu{

	private static final long serialVersionUID = 1L;
	
	public static final BlockMenu INSTANCE = new BlockMenu();
	
	public BlockMenu() {
		{
			JMenuItem menuItem = new JMenuItem("删除块");
			menuItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Container p = getInvoker().getParent();

					if(getInvoker() instanceof JBlock){
						JBlock c = (JBlock) getInvoker();
						p.remove(c);
						p.revalidate();
						p.repaint();
					}
					else if(getInvoker() instanceof JBlockInsert){
						JBlockInsert c = (JBlockInsert) getInvoker();
						p.remove(c);
						p.revalidate();
						p.repaint();
					}
				}
			});
			add(menuItem);
		}
			
		{
			JMenuItem menuItem = new JMenuItem("添加注释");
			menuItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					((JBlock)getInvoker()).setToolTipText(JOptionPane.showInputDialog(null, "请输入注释.", "注释", JOptionPane.INFORMATION_MESSAGE));
				}
			});
			add(menuItem);
		}	
	}
}
