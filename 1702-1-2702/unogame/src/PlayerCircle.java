
/**
 * @name :
 * @e-mail:
 */


public class PlayerCircle {
    private Player firstPlayer = null;
    private int MAX;
    private int size;
    private Player[] players;

    /**
     * Constructor
     */
    public PlayerCircle() {
        this(5);//default there are always 5 players in a uno game.
    }

    /**
     * Constructor with specific size
     *
     * @param size
     */
    public PlayerCircle(int size) {
        MAX = size;
        this.size = 0;
        players = new Player[MAX];
    }

    /**
     * add a player to the circle
     *
     * @param p player
     */
    public void addToCircle(Player p) {
        if (size == MAX) {
            return;
        }
        if (size == 0) {
            players[0] = p;
            firstPlayer = p;
            firstPlayer.setNextPlayer(null);
            firstPlayer.setPrevPlayer(null);
            size++;
        } else {
            int i;
            for (i = 0; i < size; i++) {
                if (p.getName().compareTo(players[i].getName()) <= 0) {
                    break;
                }
            }
            for (int j = size; j > i; j--) {
                players[j] = players[j - 1];
            }
            players[i] = p;
            if (i == size) {
                players[i].setNextPlayer(players[0]);
                players[i].setPrevPlayer(players[i - 1]);
                players[i - 1].setNextPlayer(players[i]);
                players[0].setPrevPlayer(players[i]);

            } else if (i == 0) {
                firstPlayer = players[0];
                players[i].setNextPlayer(players[i + 1]);
                players[i].setPrevPlayer(players[size]);
                players[size].setNextPlayer(players[i]);
                players[i + 1].setPrevPlayer(players[i]);

            } else {
                players[i].setNextPlayer(players[i + 1]);
                players[i].setPrevPlayer(players[i - 1]);
                players[i - 1].setNextPlayer(players[i]);
                players[i + 1].setPrevPlayer(players[i]);
            }
            size++;
        }
    }

    //returns the first person in the circle.

    /**
     * @return returns the first person in the circle.
     */
    public Player getFirstPlayer() {
        return firstPlayer;
    }

    //returns number of players in the circle.

    /**
     * @return returns number of players in the circle.
     */
    public int getSize() {
        return size;
    }

    //print all the players in the circle

    /**
     * print all the players in the circle
     *
     * @return all the players
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PlayerCircle{" + size + "}");
        //sb.append('\n');
        for (int i = 0; i < size; i++) {
            sb.append(players[i].getName() + ",");
            //sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * remove one player from circle
     * attention: the circle must be full before this method
     *
     * @param p player to move
     */
    public void removeFromCircle(Player p) {
        Player pl = firstPlayer;
        Player pl1 = pl;
        Player pl2 = pl;
        int index = -1;
        for (int i = 0; i < 5; i++) {
            if (pl.equals(p)) {
                index = i;
                pl1 = pl.getPrevPlayer();
                pl2 = pl.getNextPlayer();
                pl.setPrevPlayer(null);
                pl.setNextPlayer(null);
                break;
            }
            pl = pl.getNextPlayer();
        }
        if (index == -1) {
            throw new IndexOutOfBoundsException("Can't not find player in circle!");
        }
        for (int i = index; i < 4; i++) {
            players[i] = players[i + 1];
        }
        pl1.setNextPlayer(pl2);
        pl2.setPrevPlayer(pl1);
        firstPlayer = players[0];
        players[4] = null;
        size--;
    }
}
