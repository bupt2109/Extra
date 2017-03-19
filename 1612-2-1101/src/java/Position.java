/**
 * Created by
 */

//坐标定义类
//主要方法：move
public class Position {
    private int x;
    private int y;

    //构造方法，参数坐标值
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //构造方法，参数坐标对象
    public Position(Position p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     * 根据方向移动当前坐标到下一位置
     * @param dir 方向
     */
    public void move(Maze.DIR dir){
        switch (dir) {
            case DIR_UP: y--;break;
            case DIR_DOWN: y++;break;
            case DIR_LEFT: x--;break;
            case DIR_RIGHT: x++;break;
            default: return;
        }
    }

    @Override
    public boolean equals(Object object) {
        if(this == object)
            return true;
        if(object == null)
            return false;
        if(getClass() != object.getClass())
            return false;
        final Position other = (Position)object;
        if(x != other.x){
            return false;
        }
        if(y != other.y){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
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
