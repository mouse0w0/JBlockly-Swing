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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import team.unstudio.jblockly.old.ui.BlockWorkspace;

public class JBlockInsertSlot extends JPanel {

	private static final long serialVersionUID = 1L;

	private JBlockInsert insert;

	public JBlockInsertSlot() {
		setLayout(null);
	}

	@Override
	public void doLayout() {
		if (insert != null) {
			insert.doLayout();
			insert.setLocation(1, 1);
		}
		setPreferredSize(new Dimension(insert != null ? insert.getPreferredSize().width + 2 : 40,
				insert != null ? insert.getPreferredSize().height + 2 : 27));
		setSize(insert != null ? insert.getPreferredSize().width + 2 : 40,
				insert != null ? insert.getPreferredSize().height + 2 : 27);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Color light = new Color(54, 85, 168), dark = new Color(108, 138, 223), normal = new Color(74, 107, 211);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int width = getPreferredSize().width, height = getPreferredSize().height;

		// normal
		{
			g2d.setColor(normal);
			Polygon p = new Polygon();
			p.addPoint(0, (int) (height + 1) / 2 - 1);
			p.addPoint(9, 0);
			p.addPoint(width - 10, 0);
			p.addPoint(width - 1, (int) (height + 1) / 2 - 1);
			p.addPoint(width - 10, height - 1);
			p.addPoint(9, height - 1);
			g2d.fillPolygon(p);
		}

		// light
		{
			g2d.setColor(light);
			Polygon p = new Polygon();
			p.addPoint(0, (int) (height + 1) / 2 - 1);
			p.addPoint(9, 0);
			p.addPoint(width - 10, 0);
			p.addPoint(width - 1, (int) (height + 1) / 2 - 1);
			g2d.drawPolyline(p.xpoints, p.ypoints, p.npoints);
		}

		// dark
		{
			g2d.setColor(dark);
			Polygon p = new Polygon();
			p.addPoint(width - 1, (int) (height + 1) / 2 - 1);
			p.addPoint(width - 10, height - 1);
			p.addPoint(9, height - 1);
			p.addPoint(0, (int) (height + 1) / 2 - 1);
			g2d.drawPolyline(p.xpoints, p.ypoints, p.npoints);
		}
	}

	public BlockWorkspace getWorkspace() {
		Container c = getParent();
		while (c instanceof JPanel) {
			if (c instanceof BlockWorkspace)
				return (BlockWorkspace) c;
			c = c.getParent();
		}
		return null;
	}

	public boolean setInsert(int x, int y, JBlockInsert block) {
		return setInsert(block);
	}

	public boolean setInsert(JBlockInsert block) {
		if (insert != null) {
			int x = getX(), y = getY();
			Container c = getParent();
			while (!(c instanceof BlockWorkspace)) {
				x += c.getX();
				y += c.getY();
				c = c.getParent();
			}
			getWorkspace().getDisplayLayer().add(insert);
			insert.setLocation(x, y);
		}
		add(block, BorderLayout.CENTER);
		insert = block;
		return true;
	}

	public JBlockInsert getInsert() {
		return insert;
	}

	@Override
	public void revalidate() {
		if (insert != null && !insert.getParent().equals(this))
			insert = null;
		super.revalidate();
	}
}
