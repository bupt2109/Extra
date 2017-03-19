import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by
 */

//地图类。定义地图及地图逻辑操作
//主要方法：
// bfs() 找到入口到出口的距离表
// printpath() 根据距离表找到最短路径
// init() 初始化地图类，从文件读数据来对坐标|地图|距离表赋初值  调用了maploading类的readObjects方法


public class Maze {
    public static final int SIZE = 10;
    public static final int INF = SIZE*SIZE-1;
    public enum DIR {
        DIR_UP,
        DIR_DOWN,
        DIR_LEFT,
        DIR_RIGHT
    }
    public enum CELL {
        CELL_W,
        CELL_E,
        CELL_T,
        CELL_L
    }

    public Map<Position,CELL> cell;
    public Position startPos;
    public Position endPos;
    public Position transPos;
    public Position landPos;
    public static int[][] distance;
    public Position[] path;
    public int pathlength;

    public void init(){
        cell = new HashMap<Position,CELL>();
        startPos = new Position(0,0);
        endPos = new Position(SIZE-1,SIZE-1);
        char[][] chs = MapLoading.readObjects();
        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                Position p = new Position(i,j);
                switch(chs[i][j]){
                    case 'W':cell.put(p,CELL.CELL_W);break;
                    case 'T':cell.put(p,CELL.CELL_T);
                              transPos = p;
                              break;
                    case 'L':cell.put(p,CELL.CELL_L);
                              landPos = p;
                              break;
                    case 'E':cell.put(p,CELL.CELL_E);break;
                }
            }
        }
        distance = new int[SIZE][SIZE];
        clearDistance();
    }

    public void printpath() {
        int len = pathlength-1;
        path = new Position[len+1];
        path[len] = endPos;
        while (len>0) {
            int i;
            for (i = 0; i < 4; i++) {
                Position p = new Position(path[len]);
                p.move(DIR.values()[i]);
                int xp = p.getX();
                int yp = p.getY();
                if (0 <= xp && xp < SIZE && 0 <= yp && yp < SIZE && distance[xp][yp] == len) {
                    len--;
                    path[len] = new Position(xp, yp);
                    break;
                }
            }
            if(i==4){
                path[len] = transPos;
            }
        }
        displayPath();//测试用，可注释
    }

    //核心：dijkstra算法
    //返回终点到起点的距离大小。若不可达，会返回INF
    public int bfs() {
        clearDistance();
        int xe = endPos.getX();
        int ye = endPos.getY();
        Queue<Position> que = new ConcurrentLinkedQueue<Position>();
        que.add(startPos);
        distance[startPos.getX()][startPos.getY()] = 1;
        while (que.size() > 0) {
            Position point = que.poll();
            if (point.getX() == xe && point.getY() == ye) {
                break;
            }
            for (int i = 0; i < 4; i++) {
                Position p = new Position(point);
                p.move(DIR.values()[i]);
                int xp = p.getX();
                int yp = p.getY();
                Position p1 = new Position(xp,yp);
                if (0 <= xp && xp < SIZE && 0 <= yp && yp < SIZE && getCell(p1) != CELL.CELL_W && distance[xp][yp] == INF) {
                    que.add(p1);
                    distance[xp][yp] = distance[point.getX()][point.getY()] + 1;
                    if(getCell(p1) == CELL.CELL_T && distance[landPos.getX()][landPos.getY()] == INF){
                        que.add(landPos);
                        distance[landPos.getX()][landPos.getY()] = distance[xp][yp];
                    }
                }
            }
        }
        pathlength = distance[xe][ye];
        return pathlength;
    }

    public CELL getCell(Position p ){
        return cell.get(p);
    }


    //清空每个坐标到出口的距离。每次画新图的时候需要调用
    public void clearDistance(){
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                distance[i][j] = INF;
            }
        }
    }

    //测试用，可注释
    public void displayPath(){
        int[][] show = new int[SIZE][SIZE];
        for(int i = 0; i < pathlength; i++){
            show[path[i].getX()][path[i].getY()]=1;
        }

        for (int i = 0; i < SIZE; i++) {
            System.out.println();
            for (int j = 0; j < SIZE; j++) {
                if(getCell(new Position(i,j)) == CELL.CELL_W)
                    System.out.print("W  ");
                else{
                    if(show[i][j]==1){
                        System.out.printf("%2d ",distance[i][j]);
                    }
                    else{
                        System.out.print("   ");
                    }
                }
            }
        }
        System.out.println();
    }

    //测试用，可注释
    public void display(){
        System.out.println("DATA:");
        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                switch (getCell(new Position(i,j))) {
                    case CELL_E: System.out.print("E ");break;
                    case CELL_T: System.out.print("T ");break;
                    case CELL_L: System.out.print("L ");break;
                    case CELL_W: System.out.print("W ");break;
                }
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("DISTANCE:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("%3d",distance[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }


}
