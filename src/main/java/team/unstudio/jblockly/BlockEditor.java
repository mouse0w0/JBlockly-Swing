package team.unstudio.jblockly;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import team.unstudio.jblockly.core.BlockWorkspace;

public class BlockEditor extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(new BlockWorkspace()));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
