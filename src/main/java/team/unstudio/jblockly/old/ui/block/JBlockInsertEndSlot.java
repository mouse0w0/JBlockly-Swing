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

package team.unstudio.jblockly.old.ui.block;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

public class JBlockInsertEndSlot extends JBlockInsertSlot {

	private static final long serialVersionUID = 1L;

	public JBlockInsertEndSlot() {
		blockColor = new Color(74, 107, 211);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setColor(getColor().darker());
		Polygon p = new Polygon();
		p.addPoint(0, 22);
		p.addPoint(0, 5);
		p.addPoint(10, 8);
		g2d.drawPolyline(p.xpoints, p.ypoints, p.npoints);

		g2d.setColor(getColor().brighter());
		g2d.drawLine(0, 22, 10, 19);
	}

	@Override
	public void doLayout() {
		if (insert != null) {
			insert.doLayout();
			insert.setLocation(1, 1);
			setPreferredSize(new Dimension(insert.getPreferredSize().width + 2, insert.getPreferredSize().height + 2));
			setSize(insert.getPreferredSize().width + 2, insert.getPreferredSize().height + 2);
		} else {
			setPreferredSize(new Dimension(11, 28));
			setSize(11, 28);
		}
	}

	public Polygon getArea(int offsetX, int offsetY) {
		return new Polygon(new int[] { offsetX + 10, offsetX, offsetX, offsetX + 10 },
				new int[] { offsetY + 8, offsetY + 5, offsetY + 22, offsetY + 19 }, 4);
	}
}
