import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class FoundationPileTest {
    @Test
    fun addCard() {
        // arrange
        val foundationPile = FoundationPile(clubs)

        // act
        foundationPile.addCard(Card(0, clubs))

        // assert
        assertEquals(Card(0, clubs), foundationPile.cards[0])
    }

    @Test
    fun removeCard() {
        // arrange
        val foundationPile = FoundationPile(clubs)
        foundationPile.addCard(Card(0, clubs))
        foundationPile.addCard(Card(1, clubs))

        // act
        foundationPile.removeCard(Card(1, clubs))

        // assert
        assertEquals(Card(0, clubs), foundationPile.cards[0])
    }

    @Test
    fun reset() {
        // arrange
        val foundationPile = FoundationPile(clubs)
        foundationPile.addCard(Card(0, clubs))
        foundationPile.addCard(Card(1, clubs))

        // act
        foundationPile.reset()

        // assert
        assertEquals(0, foundationPile.cards.size)
    }
}