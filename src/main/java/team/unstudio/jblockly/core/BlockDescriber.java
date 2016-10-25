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

import team.unstudio.jblockly.core.Block.ConnectionType;

/*
 * BlockDescriber
 */
public class BlockDescriber {

	private final String name;
	private int color = 0X666666;
	private ConnectionType connectionType = ConnectionType.None;
	private String message;
	private String tooltip;
	private BlockInputDescriber[] inputs;

	public BlockDescriber(String name) {
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getColor() {
		return color;
	}

	public String getMessage() {
		return message;
	}

	public ConnectionType getConnectionType() {
		return connectionType;
	}

	public String getTooltip() {
		return tooltip;
	}

	public BlockInputDescriber[] getInputs() {
		return inputs;
	}
}
