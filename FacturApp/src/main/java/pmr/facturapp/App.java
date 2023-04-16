package pmr.facturapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pmr.facturapp.controllers.RootController;

public class App extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        App.primaryStage = primaryStage;

        RootController controller = new RootController();

        App.primaryStage.setTitle("FacturApp");
        App.primaryStage.setScene(new Scene(controller.getView()));
        App.primaryStage.setMaximized(true);
        App.primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
