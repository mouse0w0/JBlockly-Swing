package team.unstudio.jblockly.core.component;

import javafx.scene.control.TextField;

public class InputTextField extends TextField implements ValueInput<String>{

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
		
	}


	@Override
	public String getInput() {
		return getText();
	}

	@Override
	public void setInput(Object obj) {
		setText(obj.toString());
	}
}
