import java.awt.*;
import java.awt.geom.GeneralPath;

/**
 * Author:
 */
// 定义一个三角形类
public class Triangle {
    private Point p1;
    private Point p2;
    private Point p3;

    private GeneralPath path;

    // 使用三个点构建一个三角形
    public Triangle(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.path = buildPath();
    }

    // 绘制三角形边
    public void draw(Graphics2D g2d) {
        g2d.draw(path);
    }

    // 填充三角形
    public void fill(Graphics2D g2d, Color color) {
        g2d.setColor(color);
        g2d.fill(path);
    }

    // 创建三角形外形的路径
    private GeneralPath buildPath() {
        path = new GeneralPath();
        path.moveTo(p1.x, p1.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(p3.x, p3.y);
        path.closePath();
        return path;
    }
}