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

import javax.swing.JComboBox;

public class InputComboBox<T> extends JComboBox<T> implements ValueInput<T> {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public T getInput() {
		return (T) getSelectedItem();
	}

	@Override
	public void setInput(T obj) {
		setSelectedItem(obj);
	}
}
