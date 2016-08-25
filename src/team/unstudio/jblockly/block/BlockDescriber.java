package team.unstudio.jblockly.block;

import java.awt.Color;

public class BlockDescriber {
	
	public static final int NOMRAL = 0;
	public static final int HAED = 1;
	public static final int END = 2;
	public static final int INSERT = 3;
	public static final int ONLY = 4;

	private String name;
	private Color color = new Color(255, 255, 255);
	private int blockType = NOMRAL;
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

	public int getBlockType() {
		return blockType;
	}

	public void setBlockType(int blockType) {
		this.blockType = blockType;
	}
}
