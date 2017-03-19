import java.util.Scanner;

/**
 * @name :
 * @e-mail:
 */
public class UnoGame {

    private UnoDeck unoDeck;
    private PlayerCircle circle;
    private Queue<Player> playerQueue;

    private Player playerNow;
    private UnoCard cardNow;

    private Scanner scanner;

    private int numberofPlayer;
    private int numberofCircle = 5;

    private int[] cardCanbePlace;

    private boolean clockwise;
    private int roundCount;

    //String[] playerNames;//测试的时候这个关闭，交的时候打开
    String[] playerNames = {//测试的时候这个打开，交的时候删了
            "sbs cd6",
            "kbs cd5",
            "ebs cd4",
            "dbs cd3",
            "abs cd1",
            //"cbs cd2",
            //"zbs cd8",
            //"wbs cd7"
    };

    /**
     * Construct method
     * Start amazing Uno game with friends!
     */
    public UnoGame() {
        scanner = new Scanner(System.in);
        initGame();
        while (true) {
            playOneGame();
        }
    }

    /**
     * play one game
     */
    private void playOneGame() {
        System.out.println("A new game begin: " + circle);
        playerNow = circle.getFirstPlayer();
        clockwise = true;
        Player pl = playerNow;
        for (int i = 0; i < numberofCircle; i++) {
            for (int j = 0; j < 1; j++) {
                cardNow = unoDeck.drawCard();
                pl.addToHand(cardNow);
            }
            pl = pl.getNextPlayer();
        }

        unoDeck.discardCard(unoDeck.drawCard());
        cardNow = unoDeck.getLastDiscarded();
        roundCount = 0;


        while (true) {// each player play his turn until someone win the game
            UnoCard card;
            System.out.println("The card in discard pile is: " + cardNow);
            System.out.println(playerNow);
            if (isHandValid()) {
                int input;
                boolean inputflag = false;
                while (true) {//wait for user entering a valid card to place;
                    System.out.println("You have " + cardCanbePlace.length + " cards can be placed down, " + cardscanbePlace() + " choose one card: ");
                    String str = scanner.nextLine();
                    if (!isNumeric(str)) {
                        continue;
                    }
                    input = Integer.parseInt(str);
                    for (int i = 0; i < cardCanbePlace.length; i++) {
                        if (input == cardCanbePlace[i]) {
                            inputflag = true;
                            break;
                        }
                    }
                    if (inputflag)
                        break;
                }
                card = playerNow.removeFromHand(input);
                unoDeck.discardCard(card);
                System.out.println("Player " + playerNow.getName() + " place down: " + card);
                if (playerNow.winner()) {// the game is over
                    break;
                } else {// the game is not over
                    cardNow = card;
                    if (card.isSkip()) {
                        if (clockwise) {
                            playerNow = playerNow.getNextPlayer().getNextPlayer();
                        } else {
                            playerNow = playerNow.getPrevPlayer().getPrevPlayer();
                        }
                        roundCount++;
                    } else {
                        if (card.isReverse()) {
                            if (clockwise == true) {
                                clockwise = false;
                            } else {
                                clockwise = true;
                            }
                        }
                        if (clockwise) {
                            playerNow = playerNow.getNextPlayer();
                        } else {
                            playerNow = playerNow.getPrevPlayer();
                        }
                    }
                }
            } else {//player has no card to place, so skip his turn
                System.out.println("You have no card can be placed down!");
                if (cardNow.isDrawTwo()) {
                    for (int i = 0; i < 2; i++) {
                        card = unoDeck.drawCard();
                        playerNow.addToHand(card);
                    }
                    System.out.println("Draw two cards and pass!");
                } else if (cardNow.isWildDrawFour()) {
                    for (int i = 0; i < 4; i++) {
                        card = unoDeck.drawCard();
                        playerNow.addToHand(card);
                    }
                    System.out.println("Draw four cards and pass!");
                } else {
                    card = unoDeck.drawCard();
                    playerNow.addToHand(card);
                    System.out.println("Pass!");
                }
                if (clockwise) {
                    playerNow = playerNow.getNextPlayer();
                } else {
                    playerNow = playerNow.getPrevPlayer();
                }
            }
            roundCount++;
        }

        Player loser = getLsoer();
        System.out.println("Game over! The winner is: " + playerNow.getName());
        System.out.println("This game goes to " + (roundCount / 5 +1)+ " round!");
        System.out.println("The loser is " + loser.getName());
        pl = playerNow;
        SinglyLinkedList<UnoCard> discard = unoDeck.getDiscard();
        for (int i = 0; i < numberofCircle; i++) {
            int handsize = pl.getHand().size();
            for (int j = 0; j < handsize; j++) {
                UnoCard card = pl.removeFromHand(0);
                discard.randomInsert(card);
            }
            pl = pl.getNextPlayer();
        }
        unoDeck.refreshDeck();
        if(numberofPlayer>5) {
            circle.removeFromCircle(loser);
            Player playerinqueue = playerQueue.dequeue();
            playerQueue.enqueue(loser);
            circle.addToCircle(playerinqueue);
            System.out.println(playerinqueue.getName() + " has added to play circle.");
        }

        System.out.println("\n\n\n");

    }

    /**
     * init the whole uno game,includes player circle, deck,players,etc..
     */
    private void initGame() {

        circle = new PlayerCircle();
        unoDeck = new UnoDeck();
        System.out.println("Input player number(>=5): ");
        //测试的时候这个关闭，交的时候这个打开
//        while (true) {
//            String str = scanner.nextLine();
//            if (!isNumeric(str)) {
//                continue;
//            }
//            numberofPlayer = Integer.parseInt(str);
//            if (numberofPlayer > numberofCircle) {
//                break;
//            }
//        }
        //测试的时候这个打开，交的时候这个关闭
        numberofPlayer = playerNames.length;

        //测试的时候这个关闭，交的时候这个打开
//        playerNames = new String[numberofPlayer];

        //测试的时候这个关闭，交的时候这个打开
//        for (int i = 0; i < numberofPlayer; i++) {
//            System.out.println("Input player" + (i+1) + "'s name: ");
//            playerNames[i] = scanner.nextLine();
//        }



        for (int i = 0; i < numberofCircle; i++) {
            Player pl = new Player(playerNames[i]);
            circle.addToCircle(pl);
        }

        if(numberofPlayer>5) {
            playerQueue = new Queue<>(numberofPlayer - numberofCircle);
            for (int i = numberofCircle; i < numberofPlayer; i++) {
                Player pl = new Player(playerNames[i]);
                playerQueue.enqueue(pl);
            }
        }
    }

    /**
     * whether user input is a digit number
     *
     * @param str user input from keyboard
     * @return true if user input is a digit number
     * @runtime T = O(N)    N: length of args string
     */
    private boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * is there a valid card in current player's hand
     *
     * @return true if current player's hand has valid card to place
     * @runtime T = O(N)    N: bard number in player's hand
     */
    private boolean isHandValid() {
        SinglyLinkedList hand = playerNow.getHand();
        SinglyLinkedNode node = hand.getHead();
        int size = hand.size();
        int[] tmp = new int[size];
        int count = 0;
        for (int i = 0; i < size; i++) {
            UnoCard card = (UnoCard) node.getData();
            if (card.canBePlacedOn(cardNow)) {
                tmp[count++] = i;
            }
            node = node.getNext();
        }
        if (count == 0) {
            return false;
        } else {
            cardCanbePlace = new int[count];
            for (int i = 0; i < count; i++) {
                cardCanbePlace[i] = tmp[i];
            }
            return true;
        }
    }

    /**
     * @return string value of cards can be placed
     */
    public String cardscanbePlace() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cardCanbePlace.length; i++) {
            sb.append(cardCanbePlace[i]);
            sb.append(',');
        }
        return sb.toString();
    }

    /**
     * find loser of this game
     *
     * @return loser
     */
    private Player getLsoer() {
        Player pl = playerNow;//winner, so he has least card
        Player res = pl;
        int mostHandRemain = 0;
        for (int i = 0; i < numberofCircle; i++) {
            int tmp = pl.getHand().size();
            if (mostHandRemain < tmp) {
                mostHandRemain = tmp;
                res = pl;
            }
            pl = pl.getNextPlayer();
        }
        return res;
    }


    /**
     * the main function
     *
     * @param args
     */
    public static void main(String[] args) {
        UnoGame game = new UnoGame();
    }


}
