package pmr.facturapp;

import java.net.URL;

// import java.time.LocalDate;
// import java.time.format.TextStyle;
// import java.time.temporal.WeekFields;
// import java.util.Locale;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pmr.facturapp.controllers.LoginController;
import pmr.facturapp.controllers.RootController;
import pmr.facturapp.database.MongoDBManager;

public class App extends Application {

    /*
     * Tareas del programa
     */
    public static Task<Void> tareaRegistro;
    public static Task<Void> tareaBorrado;

    /*
     * Variables URL
     */
    private static final URL URL_LOGO = App.class.getResource("/images/PNG/logo/F_logo.png");

    /*
     * Variables alfanuméricas
     */
    // Strings alerta conexión
    private final String ALERT_TITLE = "Error al conectar con la Base de Datos";
    private final String ALERT_CONTENT = "Se ha producido un error durante la conexión con la Base de Datos";
    public static String USERNAME = "";

    /*
     * Variables Image
     */
    public static Image LOGO;

    // Titulo de la aplicación
    private final String APP_TITLE = "FacturApp";

    public static Stage primaryStage;
    public static RootController rootController;

    public static MongoDBManager dbManager;

    @Override
    public void init() throws Exception {
        super.init();

        // Conexión con BBDD
        if ((dbManager = MongoDBManager.connect()) == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(ALERT_TITLE);
            alert.setContentText(ALERT_CONTENT);
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        App.primaryStage = primaryStage;
        App.LOGO = new Image(URL_LOGO.toString());

        LoginController controller = new LoginController();

        App.primaryStage.setTitle(APP_TITLE);
        App.primaryStage.setScene(new Scene(controller.getView(), 450, 650));
        App.primaryStage.getIcons().setAll(LOGO);
        App.primaryStage.show();

        // System.out.println(LocalDate.now().get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear()));

    }

    @Override
    public void stop() throws Exception {
        super.stop();
        dbManager.disconnect();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /*
     * Funciones
     */
    public static void loadApp() {
        App.primaryStage.close();

        // Apertura de la nueva ventana con el contenido de la App
        App.primaryStage.setScene(new Scene(new RootController().getView()));
        App.primaryStage.setMaximized(true);
        App.primaryStage.show();
    }

}
