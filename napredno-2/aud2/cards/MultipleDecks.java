package aud2.cards;

import java.util.ArrayList;

public class MultipleDecks {
    private ArrayList<Deck> decks;

    public MultipleDecks(int num){
        decks=new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            decks.add(new Deck());
        }
    }

    @Override
    public String toString() {
        StringBuilder str=new StringBuilder();
        int i=0;
        for(Deck deck:decks){
            str.append("Deck number: " + (++i) + " \n");
            str.append(deck.toString());

        }
        return str.toString();
    }

    public static void main(String[] args) {
        MultipleDecks decks1=new MultipleDecks(5);
        System.out.println(decks1 );

    }
}
