/*
 * Copyright (c) 2016, Unknown Domain. All rights reserved.
 * GUN AGPLv3. Use is subject to license terms.
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

import javax.swing.JTextField;

public class InputTextField extends JTextField implements ValueInput<String> {

	private static final long serialVersionUID = 1L;

	@Override
	public void dispose() {
		getParent().remove(this);
	}

	@Override
	public String getInput() {
		return getText();
	}

	@Override
	public void setInput(String obj) {
		setText(obj);
	}
}
