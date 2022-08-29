import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Window;

import java.util.ArrayList;
import java.util.List;

public class Snake extends Thread {

    private GraphicsContext ctx;
    private Canvas canvas;
    private SnakeHead snakeHead;
    private Scene scene;
    private Food food;
    private List<SnakeBody> snakeBody;
    private boolean moved;

    public Snake(Canvas canvas, GraphicsContext ctx, Scene scene) {
        this.canvas = canvas;
        this.ctx = ctx;
        this.snakeHead = new SnakeHead();
        this.scene = scene;
        this.food = new Food();
        this.snakeBody = new ArrayList();
        this.moved = false;
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
        checkFoodCollision();
        updateSnakeBody();
        snakeHead.moveSnakeHead();
        checkBodyCollision();
        checkWallCollision();

    }

    private void updateSnakeBody() {
        for (int i = snakeBody.size() - 1; i >= 0; i--) {
            if (i == 0) {
                snakeBody.get(i).setX(snakeHead.getX());
                snakeBody.get(i).setY(snakeHead.getY());
            } else {
                snakeBody.get(i).setX(snakeBody.get(i - 1).getX());
                snakeBody.get(i).setY(snakeBody.get(i - 1).getY());
            }
        }
    }

    public void draw() {
        clearCanvas();
        for (SnakeBody body : snakeBody) {
            body.drawSnakeBody(ctx);
        }
        food.drawFood(ctx);
        snakeHead.drawSnakeHead(ctx);
        moved = false;
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
            if (snakeBody.size() == 0) {
                snakeBody.add(new SnakeBody(snakeHead.getX(), snakeHead.getY()));
            } else {
                snakeBody.add(new SnakeBody(snakeBody.get(snakeBody.size() - 1).getX(), snakeBody.get(snakeBody.size() - 1).getY()));
            }
        }
    }

    private void checkBodyCollision() {
        for (SnakeBody body:snakeBody) {
            if (snakeHead.getX() == body.getX() && snakeHead.getY() == body.getY()){
                stop();
            }
        }
    }

    public Snake setBody(SnakeBody body) {
        this.snakeBody.add(body);
        return this;
    }

    private void getKeyPressed() {
        scene.setOnKeyPressed(event -> {
            if (!moved) {
                switch (event.getCode()) {
                    case A:
                        if (!snakeHead.isMoveHori()) {
                            snakeHead.setXChange(-10);
                            snakeHead.setYChange(0);
                            snakeHead.setMoveVerti(false);
                            snakeHead.setMoveHori(true);
                            moved = true;
                        }
                        break;
                    case D:
                        if (!snakeHead.isMoveHori()) {
                            snakeHead.setXChange(10);
                            snakeHead.setYChange(0);
                            snakeHead.setMoveVerti(false);
                            snakeHead.setMoveHori(true);
                            moved = true;
                        }
                        break;
                    case W:
                        if (!snakeHead.isMoveVerti()) {
                            snakeHead.setXChange(0);
                            snakeHead.setYChange(-10);
                            snakeHead.setMoveVerti(true);
                            snakeHead.setMoveHori(false);
                            moved = true;
                        }
                        break;
                    case S:
                        if (!snakeHead.isMoveVerti()) {
                            snakeHead.setXChange(0);
                            snakeHead.setYChange(10);
                            snakeHead.setMoveVerti(true);
                            snakeHead.setMoveHori(false);
                            moved = true;
                        }
                        break;
                    case ESCAPE:
                        System.exit(0);
                }
            }
        });
    }

    public void clearCanvas() {
        ctx.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
