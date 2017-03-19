/**
 * Created by
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


//GUI类及主函数入口

//1一开始构造maze类（maze类从文件读迷宫），找到迷宫解，绘制出来。等待鼠标点击
//2鼠标每次点击，重新得到新的迷宫，计算迷宫最短路径，绘制新的迷宫解。
//3循环第二步直到关闭程序。

//内部类：mouseListener implements MouseListener ，添加坐标时添加鼠标监听
//主要方法：
//    paintMaze() 添加坐标panel到frame，更新地图
//    paintPath() 添加路径label到坐标panel，更新地图
//    search() 查找迷宫解法。    调用bfs() printpath() 方法

public class Demo {
    final static Color[] mycolor = {Color.white,Color.black,Color.red,Color.green};
    public Font font = new Font("Dialog", 1, 20);
    public JFrame frame;
    public JPanel panel;
    public JPanel[][] cells ;
    public JLabel[] labels ;

    public Maze maze;
    public int size;


    public Demo() {
        size = Maze.SIZE;
        frame = new JFrame();
        frame.setTitle("Maze");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(size*60,size*60);
        frame.setLocationRelativeTo(null);
        panel = new JPanel();
        maze = new Maze();
        maze.init();
        cells =  new JPanel[size][size];
        panel.setLayout(new GridLayout(size,size));
        frame.setVisible(true);
        paintMaze();
    }

    public void go(){
        search();
        while(true);
    }

    public void search(){
        int bfsres = maze.bfs();
        if (bfsres == maze.INF) {
            JOptionPane.showMessageDialog(null, "The exit position can not be reach !!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            maze.printpath();
            paintPath();
        }
    }

    private void clearMaze(){
        for(int i=0;i<size;i++) {
            for (int j = 0; j < size; j++) {
                panel.remove(cells[i][j]);
            }
        }
        panel.repaint();
    }

    private void paintMaze(){

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                cells[i][j] = new JPanel();
                switch(maze.getCell(new Position(i,j))){
                    case CELL_E: cells[i][j].setBackground(mycolor[0]);break;
                    case CELL_W: cells[i][j].setBackground(mycolor[1]);break;
                    case CELL_T: cells[i][j].setBackground(mycolor[2]);break;
                    case CELL_L: cells[i][j].setBackground(mycolor[3]);break;
                }
                cells[i][j].addMouseListener(new mouseListener());
                panel.add(cells[i][j]);
            }
        }
        frame.add(panel);
        frame.validate();
    }

    private void clearPath() {
        int len = maze.pathlength;
        for(int i=0;i<len;i++){
            Position p = maze.path[i];
            cells[p.getX()][p.getY()].remove(labels[i]);
            cells[p.getX()][p.getY()].repaint();
        }
    }

    private void paintPath() {
        int len = maze.pathlength;
        labels = new JLabel[len];
        for(int i=0;i<len;i++){
            Position p = maze.path[i];
            labels[i] = new JLabel((i+1)+"",JLabel.CENTER);
            labels[i].setFont(font);
            labels[i].setForeground(Color.blue);
            cells[p.getX()][p.getY()].add(labels[i]);
            cells[p.getX()][p.getY()].validate();
        }
    }

    class mouseListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            JPanel jp = (JPanel)e.getSource();
            int x=-1,y=-1;
            for(int i=0;i<size;i++){
                for(int j=0;j<size;j++){
                    if(jp==cells[i][j]){
                        x=i;
                        y=j;
                        i=size;
                        break;
                    }
                }
            }
            Position p = new Position(x,y);
            Maze.CELL c = maze.getCell(p);

            if(e.isShiftDown()){//move the teleporter( CELL_T ) (red)
                int xt = maze.transPos.getX();
                int yt = maze.transPos.getY();
                int xl = maze.landPos.getX();
                int yl = maze.landPos.getY();
                if(!(x==xt&&y==yt) && !(x==xl&&y==yl)){
                    maze.cell.put(maze.transPos, Maze.CELL.CELL_E);
                    maze.cell.put(p, Maze.CELL.CELL_T);
                    maze.transPos = p;
                    clearMaze();
                    paintMaze();
                    search();
                }
            }
            else if(e.isControlDown()){//move teleporter land (green)
                int xt = maze.transPos.getX();
                int yt = maze.transPos.getY();
                int xl = maze.landPos.getX();
                int yl = maze.landPos.getY();
                if( !(x==xt&&y==yt) && !(x==xl&&y==yl)){
                    maze.cell.put(maze.landPos, Maze.CELL.CELL_E);
                    maze.cell.put(p, Maze.CELL.CELL_L);
                    maze.landPos = p;
                    clearMaze();
                    paintMaze();
                    search();
                }
            }
            else {//wall and empty
                if(c== Maze.CELL.CELL_E){
                    maze.cell.put(p, Maze.CELL.CELL_W);
                    clearMaze();
                    paintMaze();
                    search();
                }
                else if(c== Maze.CELL.CELL_W){
                    maze.cell.put(p, Maze.CELL.CELL_E);
                    clearMaze();
                    paintMaze();
                    search();
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static void main(String[] args) {
        Demo demo  = new Demo();
        demo.go();
    }

}
