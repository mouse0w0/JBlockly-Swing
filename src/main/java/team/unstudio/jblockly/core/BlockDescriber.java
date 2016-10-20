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
 * BlockDescriber
 */
public class BlockDescriber {

	/**
	 * ConnectionType: None: xxxx Left: xxxx TopAndBottom: xxxx Top: xxxx
	 * Bottom: xxxx
	 */
	public enum ConnectionType {
		None, Left, TopAndBottom, Top, Bottom
	}

	private final String name;
	private int color = 0XFFFFFF;
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
