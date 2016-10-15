/*
 * Copyright (c) 2016, Unknown Domain. All rights reserved.
 * GUN GPLv3. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package team.unstudio.jblockly.core.component;

import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

public class InputComboBox extends ComboBox<String> implements ValueInput<String> {

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

	private String name;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getInput() {
		return getValue();
	}

	@Override
	public void setInput(Object obj) {
		setValue(obj.toString());
	}

	@Override
	public void dispose() {
		setVisible(false);
		((Pane) getParent()).getChildren().remove(this);
	}
}
