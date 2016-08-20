package team.unstudio.jblockly.block;

import java.util.ArrayList;
import java.util.List;

import team.unstudio.jblockly.ui.block.JBlock;

public class BlockGroup {
	
	private String node[] = new String[0];
	private String name;
	private List<Class<? extends JBlock>> blocks = new ArrayList<>();
	
	public String[] getNode() {
		return node;
	}
	
	public void setNode(String ...node) {
		this.node = node;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Class<? extends JBlock>> getBlocks() {
		return blocks;
	}
}
