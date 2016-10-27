/*
 * Copyright (c) 2016, Unknown Domain. All rights reserved.
 * GUN AGPLv3. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package team.unstudio.jblockly;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import team.unstudio.jblockly.core.Block;
import team.unstudio.jblockly.core.BlockWorkspace;

public class BlockEditor extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BlockWorkspace workspace = new BlockWorkspace();
		
		Block block1 = new Block();
		block1.setLocation(100, 100);
		block1.setColor(0XFF0000);
		workspace.addTopBlock(block1);
		
		Block block2 = new Block();
		block2.setLocation(300, 300);
		block2.setColor(0X00FF00);
		workspace.addTopBlock(block2);
		
		primaryStage.setScene(new Scene(workspace));
		primaryStage.setTitle("JBlockly Editor");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
