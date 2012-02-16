package com.sep.planningpoker.models.decks;

import com.sep.planningpoker.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

public class DeckFactory {

    private final Resources mResources;
    private SharedPreferences mPreferences;

    public DeckFactory(Context context) {
        mResources = context.getResources();
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public Deck getDeck() {
        int deckPref = Integer.parseInt(mPreferences.getString("deckPreference", "0"));
        DeckType selectedType = DeckType.values()[deckPref];
        return buildDeck(selectedType);
    }

    private Deck buildDeck(DeckType type) {
        String[] cards = null;

        switch (type) {
        case STANDARD:
            cards = mResources.getStringArray(R.array.standard_cards);
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
