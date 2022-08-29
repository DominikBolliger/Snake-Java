import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Food {

    private int width;
    private int height;
    private int x;
    private int y;
    private boolean visible;


    public Food() {
        this.width = 10;
        this.height = 10;
        getPos();
        this.visible = true;
    }

    public void drawFood(GraphicsContext ctx) {
        if (visible) {
            ctx.setFill(Color.YELLOW);
            ctx.fillRect(x, y, width, height);
        }
    }

    public void getPos() {
        Random rnd = new Random();
        x = rnd.nextInt(Init.CANVASWIDTH);
        x = x - (x % 10);
        y = rnd.nextInt(Init.CANVASHEIGHT);
        y = y - (y % 10);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
