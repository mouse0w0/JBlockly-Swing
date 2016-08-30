package team.unstudio.jblockly.block;

import java.awt.Color;

public class BlockDescriber {

	public enum BlockType {
		NOMRAL,HEAD,END,INSERT,ONLY;
	}

	private String name;
	private Color color = new Color(255, 255, 255);
	private BlockType blockType = BlockType.NOMRAL;
	private String message;
	private String output;
	private String[] args;
	private String help;
	
	public BlockDescriber() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}

	public String getHelp() {
		return help;
	}

	public void setHelp(String help) {
		this.help = help;
	}

	public BlockType getBlockType() {
		return blockType;
	}

	public void setBlockType(BlockType blockType) {
		this.blockType = blockType;
	}
}
