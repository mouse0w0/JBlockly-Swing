package team.unstudio.jblockly.core;

public abstract class BlockInputDescriber {

	private final String type;
	private String name;
	private String defaultValue = null;
	
	public BlockInputDescriber(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public abstract BlockInput<?> newInput();
}
