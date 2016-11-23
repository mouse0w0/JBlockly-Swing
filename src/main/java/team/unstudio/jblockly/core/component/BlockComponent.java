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

public interface BlockComponent {

	int getX();

	int getY();

	void setLocation(int x, int y);

	int getHeight();

	int getWidth();

	void dispose();
	
	boolean isVisible();
	
	void setVisible(boolean visible);
}
