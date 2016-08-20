package team.unstudio.jblockly;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.UIManager;

import team.unstudio.jblockly.ui.BlockWorkspace;

public class BlockEditor extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public BlockEditor() {
		setTitle("积木式代码编辑器");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - 1080) / 2,(Toolkit.getDefaultToolkit().getScreenSize().height - 720) / 2,1080, 720);
		setLayout(new BorderLayout(0, 0));

		//初始化工具条
		JToolBar toolBar = new JToolBar();
		toolBar.setLayout(new FlowLayout(5, 5, 0));
		
		toolBar.add(new JComboBox<>());
		
		add(toolBar, BorderLayout.NORTH);
		
		//初始化组件容器
		JTree tree = new JTree();
		
		JScrollPane scrollPane2 = new JScrollPane(tree);
		add(scrollPane2, BorderLayout.WEST);
		
		//初始化方块编辑区
		JScrollPane scrollPane = new JScrollPane(new BlockWorkspace());
		add(scrollPane, BorderLayout.CENTER);
	}
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel("com.bulenkov.darcula.DarculaLaf");
				} catch (Exception e) {
					e.printStackTrace();
				}
				new BlockEditor().setVisible(true);
			}
		});
	}
}
