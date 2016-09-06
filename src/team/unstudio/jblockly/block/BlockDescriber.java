package team.unstudio.jblockly.block;

import java.awt.Color;

public class BlockDescriber {

	public enum BlockType {
		NOMRAL,HEAD,END,INSERT,ONLY;
	}

	private final String name;
	private Color color = new Color(255, 255, 255);
	private BlockType blockType = BlockType.NOMRAL;
	private String message;
	private InputDescriber inputs[];
	
	public BlockDescriber(String name) {
		if(name == null||name.isEmpty()) throw new IllegalArgumentException();
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

	public BlockType getBlockType() {
		return blockType;
	}
	
	public static class Builder{
		
		private final String name;
		private Color color = new Color(255, 255, 255);
		private BlockType blockType = BlockType.NOMRAL;
		private String message = "";
		private InputDescriber inputs[] = new InputDescriber[0];
		
		public Builder(String name) {
			if(name == null||name.isEmpty()) throw new IllegalArgumentException();
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

		public Builder setInputs(InputDescriber ...inputs) {
			this.inputs = inputs;
			return this;
		}

		public Builder setBlockType(BlockType blockType) {
			this.blockType = blockType;
			return this;
		}
		
		public BlockDescriber done(){
			BlockDescriber describer = new BlockDescriber(name);
			describer.color = color;
			describer.blockType = blockType;
			describer.message = message;
			describer.inputs = inputs;
			return describer;
		}
	}
}
