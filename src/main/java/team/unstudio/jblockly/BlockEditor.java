/*
 * Copyright (c) 2016, Unknown Domain. All rights reserved.
 * GUN GPLv3. Use is subject to license terms.
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
import team.unstudio.jblockly.core.BlockWorkspace;

public class BlockEditor extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(new BlockWorkspace()));
		primaryStage.setTitle("JBlockly Editor");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
