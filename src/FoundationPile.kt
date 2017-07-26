class FoundationPile(val suit: String) {
    val cards: MutableList<Card> = mutableListOf()

    /** add card to foundation: only card with the same suit and with value = lastValue + 1 can be added */
    fun addCard(card: Card): Boolean {
        var nextValue = 0
        if (cards.size > 0) {
            nextValue = cards.last().value + 1
        }
        if (card.suit == suit && card.value == nextValue) {
            cards.add(card)
            return true
        }
        return false
    }

    /** remove last card from foundation */
    fun removeCard(card: Card) {
        cards.remove(card)
    }

    /** clear foundation */
    fun reset() {
        cards.clear()
    }
}