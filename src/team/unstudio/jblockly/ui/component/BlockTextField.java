package team.unstudio.jblockly.ui.component;

import javax.swing.JTextField;

import team.unstudio.jblockly.block.IInput;
import team.unstudio.jblockly.block.InputDescriber;

public class BlockTextField extends JTextField implements IInput{

	private static final long serialVersionUID = 1L;
	
	@Override
	public String value() {
		return getText();
	}

	@Override
	public InputDescriber getDescriber() {
		// TODO 自动生成的方法存根
		return null;
	}
	
}
