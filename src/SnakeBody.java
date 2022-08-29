import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SnakeBody {

    private int height;
    private int width;
    private int x;
    private int y;

    public SnakeBody(int x, int y){
        this.height = Init.SNAKEHEIGHT;
        this.width = Init.SNAKEWIDTH;
        this.x = x;
        this.y= y;
    }

    public void drawSnakeBody(GraphicsContext ctx){
        ctx.setFill(Color.rgb(57, 156, 0));
        ctx.fillRect(x, y, width, height);
    }

    public void moveSnakeBody(int x, int y){
        this.x = x;
        this.y = y;
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
}
