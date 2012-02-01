package sep.mdswanson.models.decks;

import sep.mdswanson.R;
import android.content.Context;
import android.content.res.Resources;

public class DeckFactory {

    private final Resources mResources;

    public DeckFactory(Context c) {
        mResources = c.getResources();
    }

    public Deck buildDeck(DeckType type) {
        String[] cards = null;

        switch (type) {
        case IMPRECISE_FIBONACCI:
            cards = mResources.getStringArray(R.array.imprecise_fibonacci_cards);
            break;
        case FIBONACCI:
            cards = mResources.getStringArray(R.array.fibonacci_cards);
            break;
        case TSHIRT_SIZE:
            cards = mResources.getStringArray(R.array.tshirt_size_cards);
            break;
        default:
            break;
        }

        return new Deck(cards);
    }
}
