package team.unstudio.jblockly.core;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class BlockMenu extends JPopupMenu{

	private static final long serialVersionUID = 1L;
	
	public static final BlockMenu INSTANCE = new BlockMenu();
	
	public BlockMenu() {
		{
			JMenuItem menuItem = new JMenuItem("删除块");
			menuItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(getInvoker() instanceof Block){
						Container p = getInvoker().getParent();
						Block c = (Block) getInvoker();
						p.remove(c);
						p.revalidate();
						p.repaint();
					}
				}
			});
			add(menuItem);
		}
	}
}
