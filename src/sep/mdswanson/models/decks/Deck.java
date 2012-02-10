package sep.mdswanson.models.decks;

public class Deck {

    private final String[] mCards;

    public Deck(String[] cards) {
        mCards = cards;
    }
    
    public int size() {
        return mCards.length;
    }
    
    public String getCard(int index) {
        return mCards[index];
    }
    
    public int getStartingCardIndex() {
        return Math.max(mCards.length / 2 - 1, 0);
    }
}
