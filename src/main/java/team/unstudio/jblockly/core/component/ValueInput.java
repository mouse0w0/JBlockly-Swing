package team.unstudio.jblockly.core.component;

public interface ValueInput<T> extends BlockComponent{

	String getName();
	void setName(String name);
	T getInput();
	void setInput(Object obj);
	
}
