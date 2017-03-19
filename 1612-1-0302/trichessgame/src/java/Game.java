/**
 * Created by
 */

import java.util.*;

public class Game {
    public static String Name = "三通棋";
    public ChessBoard chessBorad;
    public Map<String, Integer> rankmap = new HashMap<String, Integer>();
    public ArrayList<Position> path;
    public int score; //当前对局分数（赢的人按连通的三角形数量算分，一块三角形一分）
    public Player p1; //玩家1
    public Player p2; //玩家2
    public Position pos;//当前位置
    public Player pl;//当前玩家
    //设定时间限制。level 1 ：1分钟一步棋 level 2：:30秒一步 level3：10秒一步
    //超时即负
    private int level;
    public int step;//步数


    public Game(){
        chessBorad = new ChessBoard();
        p1 = new Player(1); //先下
        p2 = new Player(2); //后下
        rankmap.put("a爱迪生",2);
        rankmap.put("b碧昂斯",11);
        rankmap.put("cC罗",10);
        rankmap.put("d迪迦奥特曼",100);
        rankmap.put("eE-cup",Integer.MAX_VALUE);
        newGame();
    }

    public void newGame(){
        chessBorad.clearBoard();
        score = 0;
        pl = p1;
        path = new ArrayList<Position>();
        level = 1;
        step = 0;
    }


    //悔棋
    public void regretMove(){
        if(path.size()==0)
            return;
        pos.setZ(0);
        if(pl.getXh()==1){
            pl = p2;
        }
        else{
            pl = p1;
        }
        step--;
        path.remove(path.size()-1);
        if(path.size()==0)
            pos = null;
        else
            pos = path.get(path.size()-1);
    }

    //下一步棋后，判断是否赢了
    public boolean isWin(){
        chessBorad.findBlock(pos,pl.getXh());
        if(chessBorad.getEdgeNum()==7){
            return true;
        }
        else{
            return false;
        }
    }

    //下一步棋 -1下子未成功 1下子成功
    public int move(Position pos, Player pl){
        if(!chessBorad.isPosValid(pos))
            return -1;
        pos.setZ(pl.getXh());
        this.pos = pos;
        this.pl = pl;
        path.add(pos);
        step++;
        return 1;
    }

    public void changePlayer() {
        if(pl==p1){
            pl = p2;
        }
        else{
            pl = p1;
        }
    }


    public void login(){
        Scanner sc = new Scanner(System.in);
        String str;
        System.out.print("Input player 1's name: ");
        str = sc.nextLine();
        if(!str.equals(""))
            p1.setName(str);
        System.out.print("Input player 2's name: ");
        str = sc.nextLine();
        if(!str.equals(""))
            p2.setName(str);
    }

    public void updatescore() {
        score = chessBorad.getBlockNum();
        if(rankmap.containsKey(pl.getName())){
            if(score>rankmap.get(pl.getName())){
                rankmap.put(pl.getName(),score);
            }
        }
        else{
            rankmap.put(pl.getName(),score);
        }
    }

    /*public static void main(String[] args) {
        Game g = new Game();
        Position p;
        Player player;
        g.ranklist();
        g.login();
        System.out.println("Welcome! player1: "+ g.p1.getName()+", player2: " + g.p2.getName()+". Game begin!");
        ChessBoard myboard = g.chessBorad;
        Position[][] board = myboard.getBoard();
        myboard.printBoard();
        Scanner sc = new Scanner(System.in);
        while(true) {
            while (g.step != 64) {
                player = g.pl;
                do {
                    System.out.printf("Input player %s move(x,y):", player.getName());
                    String[] str = sc.nextLine().split(",");
                    int x = Integer.parseInt(str[0]);
                    int y = Integer.parseInt(str[1]);
                    p = board[x][y];
                } while (g.move(p, player) == -1);
                myboard.printBoard();
                boolean win = g.isWin();
                System.out.println(myboard.getBlockNum()+" "+ myboard.getEdgeNum());
                myboard.flushState();
                if (win) {
                    System.out.printf("Player %s win! Start a new game?(1/0) :", g.pl.getName());
                    int input = Integer.parseInt(sc.nextLine());
                    if (input == 1) {
                        g.newGame();
                        break;
                    } else {
                        sc.close();
                        System.exit(1);
                    }
                }
                else{
                    if(g.pl == g.p2){
                        System.out.println("你想多下一步吗？(1/0):");
                        int input = Integer.parseInt(sc.nextLine());
                        if (input == 1) {
                            g.newGame();
                            break;
                        } else {
                            sc.close();
                            System.exit(1);
                        }
                    }
                }
            }
            if(g.step == 64){
                System.out.println("No place to move, no one win!\nStart a new game!");
            }
        }
    }*/



}
