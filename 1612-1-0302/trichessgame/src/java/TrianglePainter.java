/**
 * Created by
 */
import java.awt.*;
import javax.swing.*;

public class TrianglePainter extends JPanel {
    Game game;
    Player player;
    Position p;
    ChessBoard board;

    private static int xS = 250;
    private static int yS = 10;
    private static int xx = 25;//每个等边三角形长50
    private static int yy = 43;
    public int posX;
    public int posY;
    public int state =0;


    //最顶端的三角形
    private static Triangle startTri = new Triangle(new Point(xS, yS), new Point(xS+xx, yS+yy), new Point(xS-xx, yS+yy));
    //最顶端的倒三角形
    private static Triangle startTri1 = new Triangle(new Point(xS-xx, yS+yy), new Point(xS+xx, yS+yy), new Point(xS, yS+yy+yy));


    public TrianglePainter() {
        game = new Game();
        board = game.chessBorad;
        this.setPreferredSize(new Dimension(500,400));

    }

    //参数为鼠标点击的坐标，返回为第n行第m列{n，m}
    public int[] findPos(int x,int y) {
        x-=xS;
        y-=yS;
        int temp = 0;
        if(y<=0 || y>=yy*8) {
            return null;
        }
        int[] posNow = new int[2];
        posNow[0] = y/yy;
        y %= yy;
        if(x<0) {
            x = -x;
        }
        else{
            temp = 1;
        }
        posNow[1] = x/xx;
        x %= xx;
        if( posNow[1] > posNow[0] || ((posNow[1] == posNow[0]) && ( x>(y/1.73)) ))
            return null;
        if((posNow[0]%2==0 && posNow[1]%2==0) || (posNow[0]%2==1 && posNow[1]%2==1)){
            if(x>(y/1.73)){
                posNow[1]++;
            }
        }
        else{
            if((xx-x)<(y/1.73)){
                posNow[1]++;
            }
        }
        posNow[1] = posNow[0] - posNow[1];
        if(temp==1){
            posNow[1] = posNow[0]*2-posNow[1];
        }
        return posNow;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //System.out.println(state+"+"+posX+"+"+posY);
        if(state==1) {
            Position[][] pos = board.getBoard();
            int z;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j <= i; j++) {
                    startTri.draw(g2d);
                    z = pos[i][2 * j].getZ();
                    if (z == 1) {
                        startTri.fill(g2d, Color.RED);
                    } else if (z == 2) {
                        startTri.fill(g2d, Color.BLUE);
                    }
                    g2d.translate(2 * xx, 0);
                }
                g2d.translate(-(2 * i + 3) * xx, yy);
            }
            g2d.translate(8 * xx, -8 * yy);
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j <= i; j++) {
                    startTri1.draw(g2d);
                    z = pos[i + 1][2 * j + 1].getZ();
                    if (z == 1) {
                        startTri1.fill(g2d, Color.RED);
                    } else if (z == 2) {
                        startTri1.fill(g2d, Color.BLUE);
                    }
                    g2d.translate(2 * xx, 0);
                }
                g2d.translate(-(2 * i + 3) * xx, yy);
            }
            g2d.translate(7 * xx, -7 * yy);
            g2d.setColor(Color.BLACK);
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j <= i; j++) {
                startTri.draw(g2d);
                g2d.translate(2 * xx, 0);
            }
            g2d.translate(-(2 * i + 3) * xx, yy);
        }
    }


}

