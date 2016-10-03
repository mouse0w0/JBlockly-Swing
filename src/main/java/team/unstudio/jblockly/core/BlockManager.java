package team.unstudio.jblockly.core;

import java.util.ArrayList;
import java.util.List;

public class BlockManager {
	
	public static final BlockManager INSTANCE = new BlockManager();
	
	private final List<BlockDescriber> blocks = new ArrayList<>();
	private final List<BlockGroup> groups = new ArrayList<>();

	public BlockManager() {}
	
	public List<BlockDescriber> getBlocks(){
		return blocks;
	}

	public List<BlockGroup> getGroups() {
		return groups;
	}
	
	public void addBlock(BlockDescriber describer){
		blocks.add(describer);
	}
	
	public void addGroup(BlockGroup group){
		groups.add(group);
	}
}
