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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;

public class JBlockInsert extends JBlock {

	private static final long serialVersionUID = 1L;

	public JBlockInsert() {
		blockColor = new Color(74, 107, 211);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int width = getPreferredSize().width, height = getPreferredSize().height;

		g2d.setColor(getColor());
		Polygon p = new Polygon();
		p.addPoint(10, 0);
		p.addPoint(width - 1, 0);
		p.addPoint(width - 1, height - 1);
		p.addPoint(10, height - 1);
		p.addPoint(10, 17);
		p.addPoint(0, 20);
		p.addPoint(0, 5);
		p.addPoint(10, 8);
		g2d.fill(p);

		g2d.setColor(getColor().brighter());
		p = new Polygon();
		p.addPoint(0, 20);
		p.addPoint(0, 5);
		p.addPoint(10, 8);
		p.addPoint(10, 0);
		p.addPoint(width - 1, 0);
		g2d.drawPolyline(p.xpoints, p.ypoints, p.npoints);
		g2d.drawLine(10, 17, 10, height - 1);

		g2d.setColor(getColor().darker());
		p = new Polygon();
		p.addPoint(width - 1, 0);
		p.addPoint(width - 1, height - 1);
		p.addPoint(10, height - 1);
		g2d.drawPolyline(p.xpoints, p.ypoints, p.npoints);
		g2d.drawLine(0, 20, 10, 17);
	}

	@Override
	public void doLayout() {
		int width = 15, height = 26;
		for (Component c : getComponents()) {
			c.doLayout();
			Dimension d = c.getPreferredSize();
			c.setBounds(width, 5, d.width, d.height);
			width += d.width + 5;
			if (d.height + 10 > height)
				height = d.height + 10;
		}
		if (width < 45)
			width = 45;
		setPreferredSize(new Dimension(width + 5, height));
		setSize(width + 5, height);
		setRealSize(width + 5, height);
	}

	@Override
	public Point getInsertPoint() {
		return new Point(getX(), getY() + getHeight() / 2);
	}

	@Override
	public boolean addBlock(int x, int y, JBlock block) {
		Component b = getComponentAt(x, y);
		if (b != null && !b.equals(this) && b instanceof JBlock)
			return ((JBlock) b).addBlock(x - b.getX(), y - b.getY(), block);
		else
			return false;
	}

	@Override
	public boolean addBlock(JBlock block) {
		return false;
	}

	@Override
	public boolean onBeInserted(JBlock block) {
		return block instanceof JBlockInsertSlot;
	}
}
