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
import team.unstudio.jblockly.core.BlockWorkspace;
import team.unstudio.jblockly.core.component.BlockLabel;
import team.unstudio.jblockly.core.component.BlockLine;

public class BlockEditor{

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("JBlock Editor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1080, 720);
		BlockWorkspace workspace = new BlockWorkspace();
		
		Block block =new Block();
		BlockLine line = new BlockLine();
		BlockLabel label = new BlockLabel();
		label.setText("滑稽的代码块");
		line.addComponent(label);
		block.addLine(line);
		workspace.addBlock(block);
		block.setLocation(100, 100);
		
		frame.setContentPane(workspace);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
