package team.unstudio.jblockly.core.component;

import javafx.scene.control.ComboBox;

public class InputComboBox extends ComboBox<String> implements ValueInput<String>{

	@Override
	public double getX() {
		return getLayoutX();
	}

	@Override
	public double getY() {
		return getLayoutY();
	}

	@Override
	public void setX(double x) {
		setLayoutX(x);
	}

	@Override
	public void setY(double y) {
		setLayoutY(y);
	}

	@Override
	public void setPoint(double x, double y) {
		setX(x);
		setY(y);	
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public String getInput() {
		return getValue();
	}

	@Override
	public void setInput(Object obj) {
		setValue(obj.toString());
	}
}
