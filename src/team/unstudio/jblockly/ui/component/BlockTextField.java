package team.unstudio.jblockly.ui.component;

import javax.swing.JTextField;

import team.unstudio.jblockly.block.IInput;

public class BlockTextField extends JTextField implements IInput{

	private static final long serialVersionUID = 1L;
	
	@Override
	public String value() {
		return getText();
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
