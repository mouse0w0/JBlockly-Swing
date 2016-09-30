package team.unstudio.jblockly.old;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;

import team.unstudio.jblockly.old.ui.BlockWorkspace;

public class BlockEditor extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public BlockEditor() {
		setTitle("JBlockly Editor");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - 1080) / 2,(Toolkit.getDefaultToolkit().getScreenSize().height - 720) / 2,1080, 720);
		setLayout(new BorderLayout(0, 0));
		
		JTree tree = new JTree();
		
		JScrollPane scrollPane2 = new JScrollPane(tree);
		add(scrollPane2, BorderLayout.WEST);
		
		JScrollPane scrollPane = new JScrollPane(new BlockWorkspace());
		add(scrollPane, BorderLayout.CENTER);
	}
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel("com.bulenkov.darcula.DarculaLaf");
					new BlockEditor().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
