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

import team.unstudio.jblockly.core.BlockWorkspace;

public class BlockEditor{

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("JBlock Editor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1080, 720);
		BlockWorkspace workspace = new BlockWorkspace();
		
		frame.setContentPane(workspace);
		frame.setVisible(true);
	}

}
