/*
 * Copyright (c) 2016, Unknown Domain. All rights reserved.
 * GUN GPLv3. Use is subject to license terms.
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

package team.unstudio.jblockly.old.block;

import java.awt.Color;

import team.unstudio.jblockly.old.block.InputDescriber;

public class BlockDescriber {

	public enum HeadType {
		NEXT, INSERT, NONE;
	}

	private final String name;
	private Color color = new Color(255, 255, 255);
	private HeadType headType = HeadType.NEXT;
	private String message;
	private InputDescriber inputs[];

	public BlockDescriber(String name) {
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	public String getMessage() {
		return message;
	}

	public InputDescriber[] getInputs() {
		return inputs;
	}

	public HeadType getHeadType() {
		return headType;
	}

	public static class Builder {

		private final String name;
		private Color color = new Color(255, 255, 255);
		private HeadType blockType = HeadType.NEXT;
		private String message = "";
		private InputDescriber inputs[] = new InputDescriber[0];

		public Builder(String name) {
			if (name == null || name.isEmpty())
				throw new IllegalArgumentException();
			this.name = name;
		}

		public Builder setColor(Color color) {
			this.color = color;
			return this;
		}

		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}

		public Builder setInputs(InputDescriber... inputs) {
			this.inputs = inputs;
			return this;
		}

		public Builder setBlockType(HeadType blockType) {
			this.blockType = blockType;
			return this;
		}

		public BlockDescriber done() {
			BlockDescriber describer = new BlockDescriber(name);
			describer.color = color;
			describer.headType = blockType;
			describer.message = message;
			describer.inputs = inputs;
			return describer;
		}
	}
}
