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

package team.unstudio.jblockly;

import javax.swing.JFrame;

import team.unstudio.jblockly.core.Block;
import team.unstudio.jblockly.core.Block.ConnectionType;
import team.unstudio.jblockly.core.BlockWorkspace;
import team.unstudio.jblockly.core.component.BlockLabel;
import team.unstudio.jblockly.core.component.BlockLine;

public class BlockEditor{

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("JBlockly Editor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1080, 720);
		BlockWorkspace workspace = new BlockWorkspace();
		workspace.setSize(1080, 720);
		
		{Block block =new Block();
		BlockLine line = new BlockLine();
		BlockLabel label = new BlockLabel();
		label.setText("滑稽的代码块");
		line.addComponent(label);
		block.addLine(line);
		workspace.addBlock(block);
		block.setLocation(100, 100);
		block.setConnectionType(ConnectionType.Bottom);}
		
		{Block block =new Block();
		BlockLine line = new BlockLine();
		BlockLabel label = new BlockLabel();
		label.setText("滑稽的代码块");
		line.addComponent(label);
		block.addLine(line);
		workspace.addBlock(block);
		block.setLocation(100, 200);
		block.setConnectionType(ConnectionType.Top);}
		
		{Block block =new Block();
		BlockLine line = new BlockLine();
		BlockLabel label = new BlockLabel();
		label.setText("滑稽的代码块");
		line.addComponent(label);
		block.addLine(line);
		workspace.addBlock(block);
		block.setLocation(100, 300);
		block.setConnectionType(ConnectionType.TopAndBottom);}
		
		{Block block =new Block();
		BlockLine line = new BlockLine();
		BlockLabel label = new BlockLabel();
		label.setText("滑稽的代码块");
		line.addComponent(label);
		block.addLine(line);
		workspace.addBlock(block);
		block.setLocation(300, 100);
		block.setConnectionType(ConnectionType.Left);}
		
		{Block block =new Block();
		BlockLine line = new BlockLine();
		BlockLabel label = new BlockLabel();
		label.setText("滑稽的代码块");
		line.addComponent(label);
		block.addLine(line);
		workspace.addBlock(block);
		block.setLocation(300, 200);
		block.setConnectionType(ConnectionType.None);}
		
		frame.setContentPane(workspace);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
