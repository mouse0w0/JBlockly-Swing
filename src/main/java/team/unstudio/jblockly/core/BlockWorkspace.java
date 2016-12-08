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

package team.unstudio.jblockly.core;

import javax.swing.JPanel;

/*
 * BlockWorkspace
 */
public class BlockWorkspace extends JPanel {

	private static final long serialVersionUID = 1L;

	private Block selectBlock;

	public BlockWorkspace() {
		setOpaque(false);
		setLayout(null);
		setBorder(null);
	}

	public void addBlock(Block block) {
		block.setWorkspace(this);
		add(block);
	}
	
	public void removeBlock(Block block) {
		block.setWorkspace(null);
		remove(block);
	}

	public Block getSelectBlock() {
		return selectBlock;
	}

	public void setSelectBlock(Block selectBlock) {
		this.selectBlock = selectBlock;
	}
}
