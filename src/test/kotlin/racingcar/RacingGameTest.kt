package racingcar

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RacingGameTest {
    @Test
    fun `주어진 시도 횟수만큼 모든 자동차가 이동한다`() {
        // given
        val cars = Cars(listOf(
            Car("pobi", 0),
            Car("woni", 0)
        ))
        val game = RacingGame(cars, tryCount = 3)
        var roundCount = 0

        // when
        game.play { roundCount++ }

        // then
        assertThat(roundCount).isEqualTo(3)
    }

    @Test
    fun `움직임이 없는 전략을 넣으면 모든 자동차가 제자리에 머문다`() {
        // given
        val cars = Cars(listOf(
            Car("pobi", 0),
            Car("woni", 0)
        ))
        val game = RacingGame(cars, tryCount = 5) { 0 } // 항상 0 반환 (이동 안 함)

        // when
        game.play { }

        // then
        assertThat(cars.cars).allMatch { it.getPosition() == 0 }
    }

    @Test
    fun `항상 이동하는 전략이면 모든 자동차가 매 라운드 전진한다`() {
        // given
        val cars = Cars(listOf(
            Car("pobi", 0),
            Car("woni", 0)
        ))
        val game = RacingGame(cars, tryCount = 3) { 4 } // 항상 4 이상 반환 (이동)

        // when
        game.play { }

        // then
        assertThat(cars.cars).allMatch { it.getPosition() == 3 }
    }

    @Test
    fun `우승자를 올바르게 찾는다`() {
        // given
        val cars = Cars(listOf(
            Car("pobi", 3),
            Car("woni", 5),
            Car("jun", 5)
        ))
        val game = RacingGame(cars, tryCount = 1)

        // when
        val winners = game.winners()

        // then
        assertThat(winners.map { it.name }).containsExactlyInAnyOrder("woni", "jun")
    }
}

