package pmr.facturapp;

// import java.time.LocalDate;
// import java.time.format.TextStyle;
// import java.time.temporal.WeekFields;
// import java.util.Locale;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import pmr.facturapp.DataBase.MongoDBManager;
import pmr.facturapp.controllers.ARootController;
import pmr.facturapp.controllers.ClientesController;
import pmr.facturapp.controllers.ComprasController;
import pmr.facturapp.controllers.EmpleadosController;
import pmr.facturapp.controllers.InicioController;
import pmr.facturapp.controllers.ProductosController;
import pmr.facturapp.controllers.ProveedoresController;
import pmr.facturapp.controllers.VentasController;

public class App extends Application {

    // Strings alerta conexión
    private final String ALERT_TITLE   = "Error al conectar con la Base de Datos";
    private final String ALERT_CONTENT = "Se ha producido un error durante la conexión con la Base de Datos";

    public static Stage primaryStage;

    public static MongoDBManager dbManager;

    @Override
    public void init() throws Exception {
        super.init();

        // Conexión con BBDD
        if((dbManager = MongoDBManager.connect()) == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(ALERT_TITLE);
            alert.setContentText(ALERT_CONTENT);
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        App.primaryStage = primaryStage;

        // ARootController controller = new ARootController();
        // LoginController loginController = new LoginController();
        // ClientesController controller = new ClientesController();
        // ComprasController controller = new ComprasController();
        // EmpleadosController controller = new EmpleadosController();
        // ProductosController controller = new ProductosController();
        // ProveedoresController controller = new ProveedoresController();
        // VentasController controller = new VentasController();
        InicioController controller = new InicioController();

        App.primaryStage.setTitle("FacturApp");
        App.primaryStage.setScene(new Scene(controller.getView()));
        App.primaryStage.setMaximized(true);
        App.primaryStage.show();

        // System.out.println(LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()));
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

}
