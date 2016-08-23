package team.unstudio.jblockly.block;

import java.util.ArrayList;
import java.util.List;

public class BlockManager {
	
	private static final BlockManager INSTANCE = new BlockManager();
	
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
	
//	public JBlock loadBlock(JSONObject json){
//		if(blocks.containsKey(json.getInteger("id"))){
//			try {
//				return blocks.get(json.getInteger("id")).newInstance();
//			} catch (InstantiationException e) {
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			}
//		}
//		return null;
//	}
	
	public void addGroup(BlockGroup group){
		groups.add(group);
	}
}
