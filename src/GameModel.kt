object GameModel {
    val deck = Deck()
    val wastePile: MutableList<Card> = mutableListOf()
    val foundationPiles = arrayOf(
            FoundationPile(clubs),
            FoundationPile(diamonds),
            FoundationPile(hearts),
            FoundationPile(spades))
    val tableauPiles = Array(7, { TableauPile()})

    /** reset game: clear waste pile, reset all foundations, reset deck and fill all tableau piles with cards */
    fun resetGame() {
        wastePile.clear()
        foundationPiles.forEach { it.reset() }
        deck.reset()

        tableauPiles.forEachIndexed { i, tableauPile ->
            val cardsInPile: MutableList<Card> = Array(i + 1, { deck.drawCard() }).toMutableList()
            tableauPiles[i] = TableauPile(cardsInPile)
        }
    }

    /** tap deck: if there are any, pick card from deck, put it on waste pile and face it up,
     * if there are no cards in deck pick cards from waste pile, put them back to deck and clear waste pile */
    fun onDeckTap() {
        if (deck.cardsInDeck.size > 0) {
            val card = deck.drawCard()
            card.faceUp = true
            wastePile.add(card)
        } else {
            deck.cardsInDeck = wastePile.toMutableList()
            wastePile.clear()
        }
    }

    /** tap waste pile: if there are any, pick last card, if playable play card and remove it from waste pile */
    fun onWasteTap() {
        if (wastePile.size > 0) {
            val card = wastePile.last()
            if (playCard(card)) {
                wastePile.remove(card)
            }
        }
    }

    /** tap foundation pile: pick foundation last card, if playable play card and remove it from foundation pile */
    fun onFoundationTap(foundationIndex: Int) {
        val foundationPile = foundationPiles[foundationIndex]
        if (foundationPile.cards.size > 0) {
            val card = foundationPile.cards.last()
            if (playCard(card)) {
                foundationPile.removeCard(card)
            }
        }
    }

    /** tap tableau pile: pick tableau cards starting from cardIndex position till last card, playable play cards and remove them from tableau pile */
    fun onTableauTap(tableauIndex: Int, cardIndex: Int) {
        val tableauPile = tableauPiles[tableauIndex]
        if (tableauPile.cards.size > 0) {
            val cards = tableauPile.cards.subList(cardIndex, tableauPile.cards.lastIndex + 1)
            if (playCards(cards)) {
                tableauPile.removeCards(cardIndex)
            }
        }
    }

    /** play cards */
    private fun playCards(cards: MutableList<Card>): Boolean {
        if (cards.size == 1) {
            return playCard(cards.first())
        } else {
            tableauPiles.forEach {
                if (it.addCards(cards)) {
                    return true
                }
            }
        }
        return false
    }

    /** play card */
    private fun playCard(card: Card): Boolean {
        foundationPiles.forEach {
            if (it.addCard(card)) {
                return true
            }
        }
        tableauPiles.forEach {
            if (it.addCards(mutableListOf(card))) {
                return true
            }
        }
        return false
    }
}