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

package team.unstudio.jblockly.old.block;

import java.awt.Dimension;
import java.awt.Point;

public interface IInput {

	public String value();
	
	public InputDescriber getDescriber();
	
	public Point getTextPoint();
	
	public void setTextPoint(Point point);
	
	public Dimension getPreferredSize();
}
