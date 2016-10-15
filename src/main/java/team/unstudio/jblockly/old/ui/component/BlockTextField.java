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

package team.unstudio.jblockly.old.ui.component;

import java.awt.Point;

import javax.swing.JTextField;

import team.unstudio.jblockly.old.block.IInput;
import team.unstudio.jblockly.old.block.InputDescriber;

public class BlockTextField extends JTextField implements IInput {

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

	private Point textPoint;

	@Override
	public Point getTextPoint() {
		return textPoint;
	}

	@Override
	public void setTextPoint(Point point) {
		textPoint = point;
	}

}
