/**
 * Created by
 */


public class Position {
    private int x; //第几排
    private int y; //第几列
    private int z; //谁落子 0未落子 1先下落子 2后下落子
    private int edge; //边缘值

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.edge = setEdge();
    }

    //0 非边缘
    //1 左边缘
    //2 右边缘
    //3 下边缘
    //4 左右边缘
    //5 左下边缘
    //6 右下边缘
    private int setEdge() {
        if(x==0){
            return 4;
        }
        else if(x==7){//最后一排
            if(y==0){
                return 5;
            }
            else if(y==14){
                return 6;
            }
            else if(y%2==1){
                return 0;
            }
            else{
                return 3;
            }
        }
        else if(y==0){
            return 1;
        }
        else if(y==2*x){
            return 2;
        }
        else{
            return 0;
        }
    }

    public int getEdge() {
        return edge;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }
}
