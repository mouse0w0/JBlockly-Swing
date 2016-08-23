package team.unstudio.jblockly.block;

import java.awt.Color;

public class BlockDescriber {

	private String name;
	private int branchAmount = 0;
	private Color color;
	private String message;
	private String output;
	private String[] args;
	
	public BlockDescriber() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBranchAmount() {
		return branchAmount;
	}

	public void setBranchAmount(int branchAmount) {
		this.branchAmount = branchAmount;
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
}
