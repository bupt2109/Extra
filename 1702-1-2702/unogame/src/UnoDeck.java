
/**
 * Class for the Uno Deck
 * The cards have already been created for you in the constructor, you just have to add them
 * to your linked list that
 * @name :
 * @e-mail:
 */
public class UnoDeck {
	private static final String[] REGULAR_COLORS = {"red", "yellow", "blue", "green"};
	//uncomment once you make your deck, then add all the cards in the constructor to your deck
	private SinglyLinkedList<UnoCard> deck; // initialize this in your constructor
	private SinglyLinkedList<UnoCard> discard; // initialize this in your constructor
	private UnoCard lastDiscarded;
	
	//http://play-k.kaserver5.org/Uno.html
	// There are 108 cards in a Uno deck. 
	// There are four suits, Red, Green, Yellow and Blue, 
	// each consisting of one 0 card, two 1 cards, two 2s, 3s, 4s, 5s, 6s, 7s, 8s and 9s; 
	// two Draw Two cards; two Skip cards; and two Reverse cards. 
	// In addition there are four Wild cards and four Wild Draw Four cards.

    /**
     * Your job is just to add the new Card’s to the deck. Make sure you add
     them with the randomInsert method, so that the deck is shuffled when it’s first used.
     */
	public UnoDeck(){
		// Initialized as having all 108 cards, as described above
        deck = new SinglyLinkedList<UnoCard>();
        discard = new SinglyLinkedList<UnoCard>();
        lastDiscarded = null;
		
		for (String color : REGULAR_COLORS){
            deck.randomInsert(new UnoCard(color, 0)); // add one of your color in zero
			for (int i = 0; i<2; i++){
				// add numbers 1-9
				for (int cardNumber = 1; cardNumber<=9; cardNumber++){
					//new UnoCard(color, cardNumber); // Add this to deck - NOTE this line has changed
                    deck.randomInsert(new UnoCard(color, cardNumber));
				}
				// add 2 of each of the special card for that color
                deck.randomInsert(new UnoCard(color, true, false, false)); // add to deck
                deck.randomInsert(new UnoCard(color, false, true, false)); // add to deck
                deck.randomInsert(new UnoCard(color, false, false, true)); // add to deck
			}
			
		}
		// add 4 wild cards, and 4 draw 4 wild cards
		for (int i = 0; i<4; i++){
            deck.randomInsert(new UnoCard(false)); // add to deck
            deck.randomInsert(new UnoCard(true)); // add to deck
		}


	}

    /**
     * draw a card from the deck. If there are no more cards in the deck,
     it moves all of the cards from the discard pile to the deck
     (the discard pile will already be shuffled, as described below).
     * @return first card in deck
     */
    public UnoCard drawCard(){
        if(deck.size()==0){
            refreshDeck();
        }
        UnoCard res = deck.getHead().getData();
        deck.removeIndex(0);
        return res;
    }


    /**
     * adds c to the discard pile, and sets it as the last discarded card.
     * Will throw an error if an invalid card is placed on the deck.
     You can choose how to throw the error, but make sure in the client code
     that no invalid cards will be placed. When calling this,
     use the randomInsert method, so that the discard pile will be randomly shuffled.
     * @param c put c into discardCard
     */
    void discardCard(UnoCard c){
        if(lastDiscarded!=null && !c.canBePlacedOn(lastDiscarded)){
            throw new UnsupportedOperationException("Invalid card!");
        }
        lastDiscarded = c;
        discard.randomInsert(c);
    }



    /**
     * gets the last card which was discarded use this to compare to the card you’re about to put down.
     * @return last card
     */
    public UnoCard getLastDiscarded(){
        return lastDiscarded;
    }

    @Override
    public String toString(){
        return "unodeck";
    }


    /**
     *
     * @return deck
     */
    public SinglyLinkedList<UnoCard> getDeck() {
        return deck;
    }

    /**
     *
     * @return discard
     */
    public SinglyLinkedList<UnoCard> getDiscard() {
        return discard;
    }

    /**
     * put all cards in discard area back to deck
     */
    public void refreshDeck(){
        SinglyLinkedNode<UnoCard> tmp;
        do{
            tmp = discard.getHead();
            deck.regularInsert(tmp.getData());
            discard.removeIndex(0);
        }while(discard.size()>0);
        lastDiscarded = null;
    }
}
