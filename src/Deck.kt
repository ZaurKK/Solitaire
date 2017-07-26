import java.util.*

class Deck {
    val cards = Array(52, { Card(it % 13, getSuit(it)) })
    var cardsInDeck: MutableList<Card> = cards.toMutableList()

    /** pick a card from the deck */
    fun drawCard(): Card = cardsInDeck.removeAt(0)

    /** reset deck: fill it with all cards and shuffle */
    fun reset() {
        cardsInDeck = cards.toMutableList()
        Collections.shuffle(cardsInDeck)
    }

    private fun getSuit(i: Int) = when(i / 13) {
            0 -> clubs
            1 -> diamonds
            2 -> hearts
            else -> spades
        }
}