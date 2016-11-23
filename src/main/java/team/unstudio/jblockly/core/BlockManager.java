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
 * BlockManager
 */
public class BlockManager {
	/**
	 * Just like name, INSTANCE
	 * 
	 * @since 1.0.0
	 */
	public static final BlockManager INSTANCE = new BlockManager();

	/**
	 * A list for all {@linkplain team.unstudio.jblockly.core.BlockGroup
	 * BlockGroup}
	 * 
	 * @since 1.0.0
	 */
	private final List<BlockGroup> groups = new ArrayList<>();

	public BlockManager() {
	}
	/**
	 * @return A list for all blocks group
	 */
	public List<BlockGroup> getGroups() {
		return groups;
	}

	public void addGroup(BlockGroup group) {
		groups.add(group);
	}
}
