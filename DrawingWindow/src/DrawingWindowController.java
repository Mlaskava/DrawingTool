import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;


public class DrawingWindowController {

    @FXML
    private Canvas canvas;

    @FXML
    private Pane mainPane;


    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Pane propertiesPane;

    private double xStartPosition;
    private double yStartPosition;


    private Circle cursor;


    EventHandler<MouseEvent> mousePressedHandler = mouseEvent -> {

        canvas.getGraphicsContext2D().setStroke(colorPicker.getValue());
        xStartPosition = mouseEvent.getX();
        yStartPosition = mouseEvent.getY() - 80;
        canvas.getGraphicsContext2D().strokeOval(xStartPosition, yStartPosition, 1, 1);
    };

    EventHandler<MouseEvent> mouseDraggedHandler = mouseEvent -> {

        moveCursor(cursor, mouseEvent);
        double xEndPosition = mouseEvent.getX();
        double yEndPosition = mouseEvent.getY() - 80;
        canvas.getGraphicsContext2D().strokeLine(xStartPosition, yStartPosition, xEndPosition, yEndPosition);
        xStartPosition = xEndPosition;
        yStartPosition = yEndPosition;
    };

    EventHandler<MouseEvent> mouseEnteredInCanvasHandler = mouseEvent -> {

        cursor.setOpacity(1);
        mainPane.setCursor(Cursor.NONE);
        moveCursor(cursor, mouseEvent);
    };


    EventHandler<MouseEvent> mouseMovedHandler = mouseEvent -> moveCursor(cursor, mouseEvent);

    EventHandler<MouseEvent> mouseEnteredInPropertiesPaneHandler = mouseEvent -> {
        propertiesPane.setCursor(Cursor.DEFAULT);
        cursor.setOpacity(0);
    };

    void moveCursor(Circle cursor, MouseEvent mouseEvent) {

        cursor.setStroke(colorPicker.getValue());
        cursor.setFill(colorPicker.getValue());
        cursor.setCenterX(mouseEvent.getX());
        cursor.setCenterY(mouseEvent.getSceneY());
    }

    @FXML
    void initialize() {
        cursor = new Circle(0.5, colorPicker.getValue());
        cursor.setOpacity(0);

        mainPane.getChildren().add(cursor);

        propertiesPane.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEnteredInPropertiesPaneHandler);

        cursor.addEventHandler(MouseEvent.MOUSE_PRESSED, mousePressedHandler);
        cursor.addEventHandler(MouseEvent.MOUSE_DRAGGED, mouseDraggedHandler);

        canvas.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEnteredInCanvasHandler);
        canvas.addEventHandler(MouseEvent.MOUSE_MOVED, mouseMovedHandler);


    }
}
