package team.unstudio.jblockly.core;

public interface BlockInput<T> extends BlockComponent{

	String getName();
	T getValue();
	
}
