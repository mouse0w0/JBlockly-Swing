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

/**
 * 代码块管理器
 */
public class BlockManager {
	/**
	 * Just like name, INSTANCE
	 * 
	 * @since 1.0.0
	 */
	public static final BlockManager INSTANCE = new BlockManager();

	/**
	 * A list for all {@linkplain team.unstudio.jblockly.core.BlockDescriber
	 * BlockDescriber}
	 * 
	 * @since 1.0.0
	 */
	private final List<BlockDescriber> blocks = new ArrayList<BlockDescriber>();

	/**
	 * A list for all {@linkplain team.unstudio.jblockly.core.BlockGroup
	 * BlockGroup}
	 * 
	 * @since 1.0.0
	 */
	private final List<BlockGroup> groups = new ArrayList<BlockGroup>();

	public BlockManager() {
	}

	/**
	 * @return A list for all code blocks
	 */
	public List<BlockDescriber> getBlocks() {
		return blocks;
	}

	/**
	 * @return A list for all blocks group
	 */
	public List<BlockGroup> getGroups() {
		return groups;
	}

	/**
	 * 
	 * @param describer
	 *            add
	 */
	public void addBlock(BlockDescriber describer) {
		blocks.add(describer);
	}

	public void addGroup(BlockGroup group) {
		groups.add(group);
	}
}
