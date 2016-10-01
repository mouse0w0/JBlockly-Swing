package team.unstudio.jblockly.core;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BlockWorkspace extends Pane{
	
	private final List<Block> blocks = new ArrayList<>();
	private final Canvas canvas = new Canvas();
	
	private Block selectBlock;
	
	public BlockWorkspace() {
		getChildren().add(canvas);
		canvas.setOnMousePressed(event->{
			
		});
		canvas.setOnMouseReleased(event->{
			
		});
		canvas.setOnMouseMoved(event->{
			
		});
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
	
	public void draw(){
		GraphicsContext graphics = canvas.getGraphicsContext2D();
		graphics.setFill(Color.WHITESMOKE);
		graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		blocks.forEach(block->block.draw(graphics));
	}
}
