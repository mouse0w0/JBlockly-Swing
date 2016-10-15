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

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class BlockLabel extends Label implements BlockComponent {

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
	public void dispose() {
		setVisible(false);
		((Pane) getParent()).getChildren().remove(this);
	}
}
