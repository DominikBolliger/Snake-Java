import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SnakeHead {

    private int height;
    private int width;
    private int x;
    private int y;
    private int xChange;
    private int yChange;
    private boolean moveHori;
    private boolean moveVerti;

    public SnakeHead() {
        this.height = 10;
        this.width = 10;
        this.x = 100;
        this.y = 100  ;
        this.xChange = 10;
        this.yChange = 0;
        this.moveHori = true;
        this.moveVerti = false;
    }

    public void drawSnake(GraphicsContext ctx) {
        ctx.setFill(Color.GREEN);
        ctx.fillRect(x, y, width, height);
    }

    public void moveSnake() {
        x += xChange;
        y += yChange;
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

    public int getXChange() {
        return xChange;
    }

    public void setXChange(int xChange) {
        this.xChange = xChange;
    }

    public int getYChange() {
        return yChange;
    }

    public void setYChange(int yChange) {
        this.yChange = yChange;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isMoveHori() {
        return moveHori;
    }

    public void setMoveHori(boolean moveHori) {
        this.moveHori = moveHori;
    }

    public boolean isMoveVerti() {
        return moveVerti;
    }

    public void setMoveVerti(boolean moveVerti) {
        this.moveVerti = moveVerti;
    }
}
