package aud2.cards;

import java.util.Objects;

public class PlayingCards {
    private int rank;
    private CardType type;

    public PlayingCards(int rank, CardType type) {
        this.rank = rank;
        this.type = type;
    }

    public int getRank() {
        return rank;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("%d %s",rank,type.name());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayingCards that = (PlayingCards) o;
        return rank == that.rank && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, type);
    }
}
