class TableauPile(var cards: MutableList<Card> = mutableListOf()) {
    /** initialize tableau pile and face up the last card */
    init {
        if (cards.size > 0)
            cards.last().faceUp = true
    }

    /** add cards to tableau pile:
    the first card's value should be less by 1 than the last card in tableau pile and suit color should be different */
    fun addCards(newCards: MutableList<Card>): Boolean {
        if (cards.size > 0) {
            if (newCards.first().value == cards.last().value - 1 &&
                    suitCheck(newCards.first(), cards.last())) {
                cards.addAll(newCards)
                return true
            }
        }  else if (newCards.first().value == 12) {
            cards.addAll(newCards)
            return true
        }
        return false
    }

    /** remove cards from tableau pile:
    all cards starting from tappedIndex and greater should be removed and if there are still cards left need to face up the last one */
    fun removeCards(tappedIndex: Int) {
        for (i in tappedIndex..cards.lastIndex) {
            cards.removeAt(tappedIndex)
        }
        if (cards.size > 0) {
            cards.last().faceUp = true
        }
    }

    /** check if suits of two cards have different colors */
    private fun suitCheck(c1: Card, c2: Card): Boolean {
        if ((redSuits.contains(c1.suit) && blackSuits.contains(c2.suit)) ||
                (blackSuits.contains(c1.suit) && redSuits.contains(c2.suit))) {
            return true
        }
        return false
    }
}