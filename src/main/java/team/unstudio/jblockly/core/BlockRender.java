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

/*
 * BlockRender
 */
public class BlockRender {

	/**
	 * {@link team.unstudio.jblockly.core.BlockDescriber.ConnectionType} is
	 * None,Left,Bottom's path
	 */
	public static final String BLOCK_TOP = "M 0,0 ";

	/**
	 * {@link team.unstudio.jblockly.core.BlockDescriber.ConnectionType} is
	 * TopAndBottom,Top's path
	 */
	public static final String BLOCK_TOP_CONNECTION = "M 0,0 H 9 V 6 H 20 V 0 ";

	/**
	 * {@link team.unstudio.jblockly.core.BlockDescriber.ConnectionType} is
	 * Left's path
	 */
	public static final String BLOCK_SIDE_INSERT = "V 19 H -5 V 10 H 0 ";

	/**
	 * get block path
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static String getBlockInsertSlot(int x, int y, int width, int height) {
		return new StringBuilder().append("V ").append(y + 9).append(" H ").append(x + width - 6).append(" V ")
				.append(y + 20).append(" H ").append(x + width).append(" ").toString();
	}

	/**
	 * {@link team.unstudio.jblockly.core.BlockDescriber.ConnectionType} is
	 * Bottom,TopAndBottom's path
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static String getBlockBottomConnection(int x, int y, int width, int height) {
		return new StringBuilder().append("V ").append(y).append(" H ").append(x + 19).append(" V ").append(y + 5)
				.append(" H ").append(x + 10).append(" V ").append(y).append(" H ").append(x).append(" Z ").toString();
	}

	/**
	 * {@link team.unstudio.jblockly.core.BlockDescriber.ConnectionType} is
	 * None,Top,Left's path
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static String getBlockBottom(int x, int y, int width, int height) {
		return new StringBuilder().append("V ").append(y).append(" H ").append(x).append(" Z ").toString();
	}

	/**
	 * get block fork's path
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static String getBlockBranch(int x, int y, int width, int height) {
		return new StringBuilder().append("V ").append(y) 
				.append(" H ").append(x + 29).append(" V ").append(y + 5).append(" H ").append(x + 20).append(" V ").append(y)
				.append(" H ").append(x + 10).append(" V ").append(y + height + 20).append(" H ").append(x + width).append(" ").toString();
	}

}