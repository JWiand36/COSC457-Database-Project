import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import services.Controller;
import services.Display;
import services.Storage;

public class Main extends Application {

    private Storage storage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        BorderPane root = new BorderPane();

        Display display = new Display(root);
        storage = new Storage();

        new Controller(display, storage);

        primaryStage.setTitle("Retail System");
        primaryStage.setScene(new Scene(root, 950, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop(){
        storage.closeDatabase();
    }
}
