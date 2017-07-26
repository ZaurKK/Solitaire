import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class TableauPileTest {
    @Test
    fun addCards() {
        // arrange
        val tableauPiles = TableauPile(mutableListOf(Card(12, spades)))
        val cards = mutableListOf(Card(11, hearts))

        // act
        tableauPiles.addCards(cards)

        // assert
        assertEquals(2, tableauPiles.cards.size)
    }

    @Test
    fun removeCards() {
        // arrange
        val tableauPiles = TableauPile(mutableListOf(Card(4, clubs), Card(3, diamonds), Card(2, spades)))

        // act
        tableauPiles.removeCards(1)

        // assert
        assertEquals(mutableListOf(Card(4, clubs, true)), tableauPiles.cards)
    }
}