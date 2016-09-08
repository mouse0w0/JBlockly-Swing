package team.unstudio.jblockly.block;

import java.awt.Dimension;
import java.awt.Point;

public interface IInput {

	public String value();
	
	public InputDescriber getDescriber();
	
	public Point getTextPoint();
	
	public void setTextPoint(Point point);
	
	public Dimension getPreferredSize();
}
