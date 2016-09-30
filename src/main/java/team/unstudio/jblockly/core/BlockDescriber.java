package team.unstudio.jblockly.core;

import java.awt.Color;

import team.unstudio.jblockly.old.block.InputDescriber;

public class BlockDescriber {
	
	public enum ConnectionType{
		None,Left,TopAndBottom,Top,Bottom
	}
	
	private final String name;
	private Color color = new Color(255, 255, 255);
	private ConnectionType connectionType = ConnectionType.None;
	private String message;
	private String tooltip;
	private InputDescriber[] inputs;
	
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

	public ConnectionType getConnectionType() {
		return connectionType;
	}

	public String getTooltip() {
		return tooltip;
	}

	public InputDescriber[] getInputs() {
		return inputs;
	}
}
