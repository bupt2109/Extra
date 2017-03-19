/**
 * @name :
 * @e-mail:
 */
public class Player {
    private String name;
    private Player nextPlayer = null;
    private Player prevPlayer = null;
    private SinglyLinkedList<UnoCard> hand;

    /**
     * Constructor
     *
     * @param name player name
     */
    public Player(String name) {
        this.name = name;
        hand = new SinglyLinkedList<UnoCard>();
    }

    /**
     * @param c card add to hand
     */
    public void addToHand(UnoCard c) {
        //you have to implement this
        hand.regularInsert(c);
    }

    //the method signature has changed, this should return the item at that index that was removed.

    /**
     * @param index this should return the item at that index that was removed.
     * @return the UnoCard that was removed
     */
    public UnoCard removeFromHand(int index) {
        //you have to implement this
        //returns the UnoCard that was removed
        UnoCard res = hand.removeIndex(index);
        return res;
    }

    /**
     * @return return true when your hand has nothing left.
     */
    public boolean winner() {
        // return true when your hand has nothing left.
        // you have to implement this
        if (hand.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public Player getPrevPlayer() {
        return prevPlayer;
    }

    public void setPrevPlayer(Player prevPlayer) {
        this.prevPlayer = prevPlayer;
    }

    public String toString() {
        return "Player [name=" + name + "],[hand=" + hand + "]";
    }


    public String getName() {
        return name;
    }

    public SinglyLinkedList<UnoCard> getHand() {
        return hand;
    }
}
