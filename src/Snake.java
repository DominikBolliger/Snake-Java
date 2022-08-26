import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class Snake extends Thread {

    private GraphicsContext ctx;
    private Canvas canvas;
    private SnakeHead snakeHead;
    private Scene scene;
    private Food food;

    public Snake(Canvas canvas, GraphicsContext ctx, Scene scene) {
        this.canvas = canvas;
        this.ctx = ctx;
        this.snakeHead = new SnakeHead();
        this.scene = scene;
        this.food = new Food();
    }

    public void run() {
        while (true) {
            draw();
            update();
            try {
                Thread.sleep(Init.GAMESPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void update() {
        getKeyPressed();
        snakeHead.moveSnake();
        checkWallCollision();
        checkFoodCollision();
    }

    public void draw() {
        clearCanvas();
        snakeHead.drawSnake(ctx);
        food.drawFood(ctx);
    }

    private void checkWallCollision() {
        if (snakeHead.getX() > Init.CANVASWIDTH - snakeHead.getWidth()) {
            snakeHead.setX(0);
        } else if (snakeHead.getX() < 0) {
            snakeHead.setX(Init.CANVASWIDTH - snakeHead.getWidth());
        } else if (snakeHead.getY() > Init.CANVASHEIGHT - snakeHead.getHeight()) {
            snakeHead.setY(0);
        } else if (snakeHead.getY() < 0) {
            snakeHead.setY(Init.CANVASHEIGHT - snakeHead.getHeight());
        }
    }

    private void checkFoodCollision() {
        if (snakeHead.getX() == food.getX() && snakeHead.getY() == food.getY()) {
            food.getPos();
        }
    }

    private void getKeyPressed() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case A:
                        if (!snakeHead.isMoveHori()) {
                            snakeHead.setXChange(-10);
                            snakeHead.setYChange(0);
                            snakeHead.setMoveVerti(false);
                            snakeHead.setMoveHori(true);
                        }
                        break;
                    case D:
                        if (!snakeHead.isMoveHori()) {
                            snakeHead.setXChange(10);
                            snakeHead.setYChange(0);
                            snakeHead.setMoveVerti(false);
                            snakeHead.setMoveHori(true);
                        }
                        break;
                    case W:
                        if (!snakeHead.isMoveVerti()) {
                            snakeHead.setXChange(0);
                            snakeHead.setYChange(-10);
                            snakeHead.setMoveVerti(true);
                            snakeHead.setMoveHori(false);
                        }
                        break;
                    case S:
                        if (!snakeHead.isMoveVerti()) {
                            snakeHead.setXChange(0);
                            snakeHead.setYChange(10);
                            snakeHead.setMoveVerti(true);
                            snakeHead.setMoveHori(false);
                        }
                        break;
                }
            }
        });
    }

    public void clearCanvas() {
        ctx.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
