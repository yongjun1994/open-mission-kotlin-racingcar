package racingcar

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class CarsTest {
    @Test
    fun `전체 자동차가 이동 시도를 진행하면 모두 position이 변화한다`() {
        // given
        val cars = Cars(listOf(
            Car("pobi", 0),
            Car("woni", 0),
            Car("jun", 0)
        ))

        // when
        cars.moveAll { true }

        // then
        assertThat(cars.cars).allMatch { it.getPosition() == 1 }
    }

    @Test
    fun `자동차 목록에서 우승자를 올바르게 찾는다`() {
        // given
        val cars = Cars(listOf(
            Car("pobi", 3),
            Car("woni", 5),
            Car("jun", 5)
        ))

        // when
        val winners = cars.findWinners()

        // then
        assertThat(winners).hasSize(2)
        assertThat(winners.map { it.name }).containsExactlyInAnyOrder("woni", "jun")
    }

    @Test
    fun `모든 자동차가 같은 위치에 있으면 모두 우승자다`() {
        // given
        val cars = Cars(listOf(
            Car("pobi", 2),
            Car("woni", 2),
            Car("jun", 2)
        ))

        // when
        val winners = cars.findWinners()

        // then
        assertThat(winners).hasSize(3)
    }

    @Test
    fun `중복된 이름이 있으면 예외가 발생해야 한다`() {
        // when & then
        assertThatThrownBy {
            Cars(listOf(
                Car("pobi", 0),
                Car("pobi", 0)
            ))
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("중복된 자동차 이름이 있습니다")
    }
}

