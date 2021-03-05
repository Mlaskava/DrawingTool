import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;


public class DrawingWindowController {

    @FXML
    private Canvas canvas;

    private double xStartPosition;
    private double yStartPosition;

    EventHandler<MouseEvent> mousePressedHandler = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            xStartPosition = mouseEvent.getSceneX();
            yStartPosition = mouseEvent.getSceneY() - 50;
        }
    };

    EventHandler<MouseEvent> mouseDraggedHandler = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            double xEndPosition = mouseEvent.getSceneX();
            double yEndPosition = mouseEvent.getSceneY() - 50;
            canvas.getGraphicsContext2D().strokeLine(xStartPosition, yStartPosition, xEndPosition, yEndPosition);
            xStartPosition = xEndPosition;
            yStartPosition = yEndPosition;
        }
    };

    @FXML
    void initialize() {
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, mousePressedHandler);
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, mouseDraggedHandler);

    }
}
