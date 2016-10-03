package team.unstudio.jblockly.core;

import team.unstudio.jblockly.core.component.BlockComponent;

public interface ValueInput<T> extends BlockComponent{

	String getName();
	T getValue();
	
}
