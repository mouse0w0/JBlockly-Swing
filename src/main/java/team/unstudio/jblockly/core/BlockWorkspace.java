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

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.Pane;

/*
 * BlockWorkspace
 */
public class BlockWorkspace extends Pane {

	private final List<Block> blocks = new ArrayList<Block>();

	private Block selectBlock;

	public BlockWorkspace() {}

	public List<Block> getBlocks() {
		return blocks;
	}

	public void addTopBlock(Block block) {
		block.setWorkspace(this);
		getChildren().add(block);
		blocks.add(block);
	}

	public Block getSelectBlock() {
		return selectBlock;
	}

	public void setSelectBlock(Block selectBlock) {
		this.selectBlock = selectBlock;
	}

	public void draw() {
		
	}
}
