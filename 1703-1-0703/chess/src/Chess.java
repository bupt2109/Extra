package chess;

import java.util.Scanner;


public class Chess {

    private Board board;
    private String player ;
    private int rank1,file1,rank2,file2;
    private int special;
    private char promote;
    private boolean isDraw;
    private boolean gameOver;
    private Scanner sc;
    private String[] inputs ={
            "e2 e4",
            "e7 e5",
            "g1 f3",
            "b8 c6",
            "f1 b5",
            "a7 a6",
            "b5 c6",
            "d7 c6",
            "e1 g1",// (castling)
            "g8 e7",
            "d2 d4",
            "e5 d4",
            "f3 d4",
            "f7 f6",
            "a2 a4",
            "e7 g6",
            "c2 c4",
            "f8 e7",
            "b2 b3",
            "e8 g8" ,//(castling)
            "d1 d3",
            "c6 c5",
            "d4 f5",
            "d8 d3",
            "b1 d2",
            "c8 f5",
            "e4 f5",
            "g6 f4",
            "d2 f3",
            "f4 e2",//(check)
            "g1 h1",
            "e2 g3",// (check)
            "h2 g3",
            "d3 f1",// (check)
            "h1 h2",
            "e7 d6",
            "c1 b2",
            "f1 f2",
            "a1 d1",
            "d6 g3",// (check)
            "h2 h1",
            "f2 b2",
            "d1 d8",
            "f8 d8",
            "f3 e5",
            "b2 c1"//checkmate
    };


    private int count=0;



    public Chess(){
        this.board = new Board();
        sc = new Scanner(System.in);
        init();
    }

    /**
     *
     */
    private void init(){
        board.initialize();
        this.player= "white";
        this.isDraw = false;
        this.gameOver = false;
    }

    /**
     *
     */
    private void playOneMove(){
        System.out.println(board);
        special = -1;
        while(true) {
            System.out.println(player + " make a move: ");
            String input = sc.nextLine();
            //String input = inputs[count++];
            if(dealInput(input)){
                if(special != -1){//resign,draw
                    if(special == 1){//resign
                        System.out.println(player + " resigns");
                        System.out.println(playerChange(player) + " wins the game!");
                        gameOver = true;
                        return;
                    }else if(special ==3){//draw?
                        isDraw = true;
                        break;
                    }else if(special == 2){//draw
                        System.out.println("The game is a draw.");
                        gameOver = true;
                        return;
                    }
                }else{//normal move
                    if(isDraw){
                        isDraw = false;
                    }
                    if(board.validInput(player,rank1,file1,rank2,file2)){
                        break;
                    }
                }
            }
            System.out.println("Illegal move, try again");
        }

        board.move(rank1,file1,rank2,file2);

        if(!board.kingAlive()){
            System.out.println(player + " wins");
            gameOver = true;
            return;
        }
        if(board.isUnderCheck(player) || board.isUnderCheck(playerChange(player))){
            System.out.println("Check!");
            if(board.checkMate(player)){
                System.out.println("Checkmate");
                System.out.println(playerChange(player) + " wins");
                gameOver = true;
                return;
            }
            if(board.checkMate(playerChange(player))){
                System.out.println("Checkmate");
                System.out.println(player + " wins");
                gameOver = true;
                return;
            }
        }else if(board.staleMate(playerChange(player))){
            System.out.println("Stalemate!");
            gameOver = true;
            return;
        }
        player = playerChange(player);
    }

    /**
     *
     */
    private void playOneGame(){
        init();
        while(!gameOver){
            playOneMove();
        }
    }

    /**
     * @param args none
     */
    public static void main(String[] args) {

        Chess game = new Chess();
        //while(true){
            game.playOneGame();
        //}
    }

    /**
     *
     * @param input input from keyboard
     * @return is a valid input or not
     */
    private boolean dealInput(String input){
        if(input==null || input.length()==0)
            return false;
        String[] strs = input.trim().replaceAll(" {2,}", " ").split(" ");
        int len = strs.length;
        if(len == 1){
            if(strs[0].equals("resign")){
                special = 1;
                return true;
            }
            if(strs[0].equals("draw") && isDraw){
                special = 2;
                return true;
            }
        }else if(len==2 || len==3){
            if(strs[0].length()==2 && strs[1].length()==2 &&
               strs[0].charAt(0)<='h' && strs[0].charAt(0)>='a' &&
               strs[1].charAt(0)<='h' && strs[1].charAt(0)>='a' &&
               strs[0].charAt(1)<='8' && strs[0].charAt(1)>='1' &&
               strs[1].charAt(1)<='8' && strs[1].charAt(1)>='1'
            ){
                if(len==2) {
                    toAxis(strs[0], 0);
                    toAxis(strs[1], 1);
                    if(rank1!=rank2 || file1!=file2) {
                        return true;
                    }
                }else{
                    if(strs[2].equals("draw?")){
                        special = 3;
                        toAxis(strs[0], 0);
                        toAxis(strs[1], 1);
                        if(rank1!=rank2 || file1!=file2) {
                            return true;
                        }
                    }else if(strs[2].length()==1){
                        char ch = strs[2].charAt(0);
                        if(ch=='R'||ch=='N'||ch=='B'||ch=='Q'){
                            promote = ch;
                            board.promote = promote;
                            toAxis(strs[0], 0);
                            toAxis(strs[1], 1);
                            if(rank1!=rank2 || file1!=file2) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;

    }

    /**
     *
     * @param player current player
     * @return other player
     */
    private String playerChange(String player){
        if(player.equals("white")){
            return "black";
        }else {
            return "white";
        }
    }

    /**
     * transfer input axis value to the corresponding board array
     * range: 0-7
     */
    private void toAxis(String str, int index){
        char ch1 = str.charAt(0);
        char ch2 = str.charAt(1);
        int[] res = new int[2];
        switch(ch1){
            case 'a': res[1] = 0;break;
            case 'b': res[1] = 1;break;
            case 'c': res[1] = 2;break;
            case 'd': res[1] = 3;break;
            case 'e': res[1] = 4;break;
            case 'f': res[1] = 5;break;
            case 'g': res[1] = 6;break;
            case 'h': res[1] = 7;break;
        }
        res[0] = 8- (ch2-'0');
        if(index==0){
            rank1 = res[0];
            file1 = res[1];
        }else{
            rank2 = res[0];
            file2 = res[1];
        }
    }

}
