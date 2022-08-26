import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;


public class GameWindow extends Application {

    public static Canvas canvas;
    public static GraphicsContext graphics_context;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Snake");

        canvas = new Canvas(Init.CANVASWIDTH, Init.CANVASHEIGHT);
        graphics_context = canvas.getGraphicsContext2D();

        // create a Group
        Group group = new Group(canvas);
        // create a scene
        Scene scene = new Scene(group, Init.CANVASWIDTH, Init.CANVASHEIGHT);

        // set the scene
        stage.setScene(scene);
        stage.show();
        Snake game = new Snake(canvas, graphics_context, scene);
        game.start();
    }
}
