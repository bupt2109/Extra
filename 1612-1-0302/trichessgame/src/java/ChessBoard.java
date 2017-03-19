/**
 * Created by
 */


public class ChessBoard {
    public static final int ROW = 8;
    public static final int COLUMN[] = {1,3,5,7,9,11,13,15};

    private Position[][] board;   //棋盘
    private int[][] visit;       //查找一整块棋时标记是否找过
    private int edgeNum;         //一块棋的边缘数，3即赢
    private int blockNum;        //一块棋的大小，记录分数用

    public ChessBoard(){
        board = new Position[ROW][];
        visit = new int[ROW][];
        for(int i=0;i<ROW;i++){
            board[i] = new Position[COLUMN[i]];
            visit[i] = new int[COLUMN[i]];
            for(int j=0;j<COLUMN[i];j++){
                board[i][j] = new Position(i,j);
            }
        }
        clearBoard();
    }

    //根据一个三角形，找到与之相连的所有三角形
    public void findBlock(Position p,int z){
        if(p==null)
            return;
        if(p.getZ()!=z || visit[p.getX()][p.getY()]==1){
            return;
        }
        blockNum++;
        switch(p.getEdge()){
            case 1:edgeNum |= 1; break;//001
            case 2:edgeNum |= 2; break;//010
            case 3:edgeNum |= 4; break;//100
            case 4:edgeNum |= 3; break;//011
            case 5:edgeNum |= 5; break;//101
            case 6:edgeNum |= 6; break;//110
        }
        visit[p.getX()][p.getY()]=1;

        findBlock(getLeft(p),z);
        findBlock(getRight(p),z);
        if(UporDown(p)==0)
        {
            findBlock(getDown(p),z);
        }
        else{
            findBlock(getUp(p),z);
        }
        return;
    }

    //清空棋盘。每次画新图的时候需要调用
    public void clearBoard(){
        for(int i=0;i<ROW;i++){
            for(int j=0;j<COLUMN[i];j++){
                board[i][j].setZ(0);
            }
        }
        edgeNum=0;
        blockNum=0;
    }

    //每下一步棋，更新棋盘状态
    public void flushState(){
        edgeNum=0;
        blockNum=0;
        for(int i=0;i<ROW;i++){
            for(int j=0;j<COLUMN[i];j++){
                visit[i][j]=0;
            }
        }
    }

    //判断三角形是正三角还是倒三角
    //0 正三角 每一行的奇数个三角形
    //1 倒三角 每一行的偶数个三角形
    public int UporDown(Position p){
        return p.getY()%2;
    }

    //判断一个三角形可不可以落子
    public boolean isPosValid(Position p){
        return p.getZ()==0;
    }

    //找到棋盘当前位置下面一个三角形
    public Position getDown(Position p){
        if(p.getX() == (ROW-1))
            return null;
        return board[p.getX()+1][p.getY()+1];
    }

    //找到棋盘当前位置上面一个三角形
    public Position getUp(Position p){
        if(p.getX() == 0 || p.getY()==0 || p.getY()== (COLUMN[p.getX()]-1))
            return null;
        return board[p.getX()-1][p.getY()-1];
    }

    //找到棋盘当前位置左边一个三角形
    public Position getLeft(Position p){
        if(p.getY()==0)
            return null;
        return board[p.getX()][p.getY()-1];
    }

    //找到棋盘当前位置右边一个三角形
    public Position getRight(Position p){
        if(p.getY()== (COLUMN[p.getX()]-1))
            return null;
        return board[p.getX()][p.getY()+1];
    }

    public Position[][] getBoard() {
        return board;
    }

    public int getEdgeNum() {
        return edgeNum;
    }

    public int getBlockNum() {
        return blockNum;
    }

    public void printBoard(){
        for(int i=0;i<ROW;i++){
            for(int k=ROW-i-1;k>0;k--){
                System.out.print("  ");
            }
            for(int j=0;j<COLUMN[i];j++){
                System.out.print( board[i][j].getZ()+" ");
            }
            System.out.println();
        }
    }

}
