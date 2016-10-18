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

	double getX();

	double getY();

	void setX(double x);

	void setY(double y);

	void setPoint(double x, double y);

	double getHeight();

	double getWidth();

	void dispose();
}
