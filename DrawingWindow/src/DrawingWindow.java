import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DrawingWindow extends Application {

    private static DrawingWindow drawingWindowInstance;


    public DrawingWindow(){
        drawingWindowInstance = this;
    }


    public static DrawingWindow getDrawingWindowInstance(){
        return drawingWindowInstance;
    }



    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("DrawingWindow.fxml"));
        Pane pane = loader.load();

        stage.setScene(new Scene(pane));
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
