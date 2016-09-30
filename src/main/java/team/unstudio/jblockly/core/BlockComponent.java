package team.unstudio.jblockly.core;

public interface BlockComponent {

	public enum AlignType{
		Left,Right,Centre
	}
	
	AlignType getAlignType();
	void setAlignType(AlignType type);
	int getX();
	int getY();
	void setX(int x);
	void setY(int y);
	void setPoint(int x,int y);
	int getHeight();
	int getWidth();
}
