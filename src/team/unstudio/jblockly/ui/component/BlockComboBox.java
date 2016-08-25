package team.unstudio.jblockly.ui.component;

import javax.swing.JComboBox;

import team.unstudio.jblockly.block.IInput;

public class BlockComboBox extends JComboBox<String> implements IInput{

	private static final long serialVersionUID = 1L;

	@Override
	public String value() {
		return (String) getSelectedItem();
	}

	private String desc;
	
	@Override
	public String getDescription() {
		return desc;
	}

	@Override
	public void setDescription(String desc) {
		this.desc = desc;
	}
}
