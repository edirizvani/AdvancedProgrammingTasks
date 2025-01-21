package aud2.cards;

import java.util.*;

public class Deck {
    private ArrayList<PlayingCards> cards;
    HashMap<PlayingCards,Boolean> map;
    private int dealt;

    public Deck(){
        cards=new ArrayList<>(52);
        map =new HashMap<>(52);
        for(int i=0;i<CardType.values().length;i++){
            for(int j=0;j<13;j++){
                PlayingCards card=new PlayingCards(j+2,CardType.values()[i]);
                cards.add(card);
                map.put(card,false);
            }
        }
    }

    public ArrayList<PlayingCards> getCards() {
        return cards;
    }

    public void setCards(ArrayList<PlayingCards> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        StringBuilder s= new StringBuilder();
        for(PlayingCards card: cards){
            s.append(card.toString());
            s.append("\n");
        }
        return s.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck = (Deck) o;
        return Objects.equals(cards, deck.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
    public boolean hasCardsLeft(){
        return (cards.size()-dealt)>0;
    }
//    public PlayingCards[] shuffle(){
//        Arrays.sort(cards);
//        List<PlayingCards> playingCardList=Arrays.asList(cards);
//        Collections.shuffle(playingCardList);
//        return cards;
//    }
    public void shuffle(){
        Collections.shuffle(cards);
    }
//
//    public PlayingCards dealCard(){
//        if(!hasCardsLeft()){
//            return null;
//        }
//        int card= (int)(Math.random() * 52);
//        if(!isDealt[card]){
//            isDealt[card]=true;
//            dealt++;
//            return cards[card];
//        }
//        return dealCard();
//    }
    public PlayingCards dealCard(){
      if(!hasCardsLeft()){
          return null;
      }
      int i=(int)(Math.random()*52);
      if(!map.get(cards.get(i))){
          dealt++;
          map.put(cards.get(i),true);
          return cards.get(i);
      }
      return dealCard();
    }

    public static void main(String[] args) {
        Deck deck=new Deck();
        System.out.println(deck);

        deck.shuffle();
        System.out.println(deck);
        PlayingCards card;
        while((card=deck.dealCard())!=null){
            System.out.println(card);
        }

    }
}
