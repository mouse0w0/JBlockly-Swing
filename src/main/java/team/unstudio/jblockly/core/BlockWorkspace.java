package team.unstudio.jblockly.core;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

public class BlockWorkspace extends Pane{
	
	private final List<Block> blocks = new ArrayList<>();
	private final Canvas canvas = new Canvas();
	
	private Block selectBlock;
	
	public BlockWorkspace() {
		getChildren().add(canvas);
	}

	public List<Block> getBlocks() {
		return blocks;
	}
	
	public void addTopBlock(Block block){
		block.setWorkspace(this);
		blocks.add(block);
	}

	public Block getSelectBlock() {
		return selectBlock;
	}

	public void setSelectBlock(Block selectBlock) {
		this.selectBlock = selectBlock;
	}
}
