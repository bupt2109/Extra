package chess;

import java.util.ArrayList;

public class Board {

    public Piece[][] board = new Piece[8][8];
    public King wKing;
    public King bKing;
    private boolean alive;
    public ArrayList<Piece> bChess;
    public ArrayList<Piece> wChess;

    public char promote = 'Q';

    /**
     *  constructor
     */
    public Board(){
        initialize();
    }

    /**
     * initial the game board
     */
    public void initialize(){
        bChess = new ArrayList<Piece>();
        wChess = new ArrayList<Piece>();
        int i,j;
        for(i = 0; i < 8; i++){
            for(j = 0; j < 8; j++){
                board[i][j] = null;
            }
        }
        // White pawns
        for(i=0; i<8; i++){
            board[1][i] = new Pawn();
        }
        // Black pawns
        for(i=0; i<8; i++){
            board[6][i] = new Pawn();
        }
        //init Rook
        board[0][0] = new Rook();
        board[0][7] = new Rook();
        board[7][0] = new Rook();
        board[7][7] = new Rook();
        //init Knight
        board[0][1] = new Knight();
        board[0][6] = new Knight();
        board[7][6] = new Knight();
        board[7][1] = new Knight();
        //init Bishop
        board[0][2] = new Bishop();
        board[0][5] = new Bishop();
        board[7][2] = new Bishop();
        board[7][5] = new Bishop();
        //init Queen
        board[0][3] = new Queen();
        board[7][3] = new Queen();
        //init King
        board[0][4] = new King();
        board[7][4] = new King();

        for (i = 0; i <= 1; i++) {
            for (j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                piece.setPlayer("black");
                piece.setBoard(board);
                piece.setAxis(i,j);
                bChess.add(piece);
            }
        }
        for (i = 6; i <= 7; i++) {
            for (j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                piece.setPlayer("white");
                piece.setBoard(board);
                piece.setAxis(i,j);
                wChess.add(piece);
            }
        }
        bKing = (King)board[0][4];
        wKing = (King)board[7][4];
        alive = true;
    }

    /**
     * Check if the king of the given player is under check.
     * @param player current player
     * @return whether the king under check
     */
    public boolean isUnderCheck(String player){
        int rank,file;
        ArrayList<Piece> chess;
        if(player.equals("white")){
            rank = wKing.rank;
            file = wKing.file;
            chess = bChess;
        }else{
            rank = bKing.rank;
            file = bKing.file;
            chess = wChess;
        }
        for(Piece piece: chess){
            if(board[piece.rank][piece.file].validMove(rank, file)){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks to see if any moves are possible. If not, then it is either a checkmate or stalemate,
     * depending on whether player is currently under check or not.
     * @return if any moves are possible
     */
    public boolean isNoPieceCanMove(String player){
        int kingrank,kingfile;
        ArrayList<Piece> chess1;
        ArrayList<Piece> chess2;
        if(player.equals("white")){
            kingrank = wKing.rank;
            kingfile = wKing.file;
            chess1 = wChess;
            chess2 = bChess;
        }else{
            kingrank = bKing.rank;
            kingfile = bKing.file;
            chess1 = bChess;
            chess2 = wChess;
        }
        for(Piece piece: chess1){
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if(board[i][j]==null || !board[i][j].getPlayer().equals(player)){
                        if(piece.validMove(i, j)){
                            int tmp;
                            Piece curPiece = piece;
                            Piece targetPiece = board[i][j];
                            int curRank = piece.rank;
                            int curFile = piece.file;
                            if(board[i][j]==null){
                                tmp = 1;
                                board[i][j] = piece;
                                board[i][j].setAxis(i,j);
                            }else{
                                tmp = 2;
                                chess2.remove(board[i][j]);
                            }
                            board[curRank][curFile] = null;
                            boolean flag = true;
                            for(Piece oppopiece: chess2){
                                if(oppopiece.validMove(kingrank, kingfile)){
                                    flag = false;
                                    break;
                                }
                            }
                            if(tmp ==1){
                                board[i][j] = null;
                            }else{
                                chess2.add(targetPiece);
                            }
                            board[curRank][curFile] = curPiece;
                            board[curRank][curFile].setAxis(curRank,curFile);
                            //exists at least one piece one move can let player unchecked
                            if(flag){
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * check if valid for player to move from(rank1,file1) to(rank2,file2)
     * @param player current player
     * @param rank1 rank1
     * @param file1 file1
     * @param rank2 rank2
     * @param file2 file2
     * @return true if its valid
     */
    public boolean validInput(String player, int rank1, int file1, int rank2, int file2){
        if(board[rank1][file1]==null)
            return false;
        if(!board[rank1][file1].getPlayer().equals(player))
            return false;
        if(board[rank2][file2] == null || !board[rank2][file2].getPlayer().equals(player)){
            Piece piece = board[rank1][file1];
            if(piece.validMove(rank2,file2)){
                return true;
            }else if(piece.getRole()=='K' && ((King)piece).validCastling(rank2,file2)){
                return true;
            }
        }
        return false;
    }


    /**
     * move from(rank1,file1) to(rank2,file2)
     * @param rank1 rank1
     * @param file1 file1
     * @param rank2 rank2
     * @param file2 file2
     */
    public void move(int rank1, int file1, int rank2, int file2){
        char role = board[rank1][file1].getRole();
        if(role=='R' && !((Rook)board[rank1][file1]).isHasMoved()){
            ((Rook)board[rank1][file1]).setHasMoved(true);
        }else if(role=='K'){//castling
            King king = (King)board[rank1][file1];
            if(!king.hasMoved){
                king.hasMoved = true;
                if(king.castled!=-1){
                    int indexR = king.castled;
                    int[] x1 = {0,0,7,7};
                    int[] y1 = {0,7,0,7};
                    int[] x2 = {0,0,7,7};
                    int[] y2 = {3,5,3,5};
                    move(x1[indexR],y1[indexR],x2[indexR],y2[indexR]);
                }
            }
        }

        if(board[rank2][file2]!=null){
            King king;
            if(board[rank2][file2].getPlayer().equals("white")){
                king = wKing;
                wChess.remove(board[rank2][file2]);
            }else{
                king = bKing;
                bChess.remove(board[rank2][file2]);
            }
            if(king==board[rank2][file2]){
                alive = false;
            }
        }
        board[rank2][file2] = board[rank1][file1];
        board[rank2][file2].setAxis(rank2,file2);
        board[rank1][file1] = null;

        //promote
        if(board[rank2][file2].getRole()=='p' &&
           ((Pawn)board[rank2][file2]).validPromote(rank2,file2)){

            ArrayList<Piece> pieces;
            if(board[rank2][file2].getPlayer().equals("white")){
                pieces = wChess;
            }else{
                pieces = bChess;
            }
            pieces.remove(board[rank2][file2]);
            ((Pawn)board[rank2][file2]).promote(rank2,file2,promote);
            pieces.add(board[rank2][file2]);
        }

    }


    /**
     * Check if there is a stalemate
     * @return true if stalemated
     */
    public boolean staleMate(String player){
        if(!isUnderCheck(player)){
            if(isNoPieceCanMove(player)){
                return true;
            }
        }
        return false;
    }

    /**
     * Check if there is a checkMate
     * @return true if checkMate
     */
    public boolean checkMate(String player){
        if(isUnderCheck(player)){
            if(isNoPieceCanMove(player)){
                return true;
            }
        }
        return false;
    }


    /**
     * print board to screen
     * @return board
     */
    public String toString(){
        int count = 8;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j]!=null){
                    sb.append(board[i][j]);
                }else{
                    sb.append("  ");
                }
                sb.append(" ");
            }
            sb.append(count--);
            sb.append("\n");
        }
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j]==null){
                    if((i%2==0 && j%2==1)||(i%2==1 && j%2==0)){
                        sb.append("##");
                    }else{
                        sb.append("  ");
                    }
                }else{
                    sb.append(board[i][j]);
                }
                sb.append(" ");
            }
            sb.append(count--);
            sb.append("\n");
        }

        for (int i = 6; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j]!=null){
                    sb.append(board[i][j]);
                }else{
                    sb.append("  ");
                }
                sb.append(" ");
            }
            sb.append(count--);
            sb.append("\n");
        }
        sb.append(" a  b  c  d  e  f  g  h  \n");
        return sb.toString();
    }

    public static void main(String[] args) {
        Board b = new Board();
        System.out.println(b);
    }

    /**
     *
     * @return if the king is alive
     */
    public boolean kingAlive() {
        return alive;
    }
}
