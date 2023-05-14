package pmr.facturapp;

// import java.time.LocalDate;
// import java.time.format.TextStyle;
// import java.time.temporal.WeekFields;
// import java.util.Locale;

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

        // System.out.println(LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()));
        // System.out.println(LocalDate.now().get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear()));
    }

    public static void main(String[] args) {
        launch(args);
    }

}
