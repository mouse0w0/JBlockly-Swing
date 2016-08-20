package team.unstudio.jblockly.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team.unstudio.jblockly.ui.block.JBlock;

public class BlockManager {
	
	private static final BlockManager INSTANCE = new BlockManager();
	
	public static int nextID = 0;
	
	private final Map<Integer, Class<? extends JBlock>> blocks = new HashMap<>();
	private final List<BlockGroup> groups = new ArrayList<>();

	public Map<Integer, Class<? extends JBlock>> getBlocks() {
		return blocks;
	}

	public List<BlockGroup> getGroups() {
		return groups;
	}

	public BlockManager() {}
	
	public void addBlock(int id,Class<? extends JBlock> block){
		if(blocks.containsKey(id)) throw new RuntimeException("ID repeat");
		blocks.put(id, block);	
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
	
	public int getIDformBlock(JBlock block){
		return getIDformBlock(block.getClass());
	}
	
	public int getIDformBlock(Class<? extends JBlock> block){
		for(int i:blocks.keySet()){
			if(blocks.get(i).equals(block))return i;
		}
		return -1;
	}
}
