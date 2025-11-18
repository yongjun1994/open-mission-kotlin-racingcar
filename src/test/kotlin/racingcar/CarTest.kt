package racingcar

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class CarTest {
    @Test
    fun `전진 조건이 참이면 한 칸 전진한다`() {
        // given
        val car = Car("pobi", 0)

        // when
        car.move(shouldMove = true)

        // then
        assertThat(car.getPosition()).isEqualTo(1)
    }

    @Test
    fun `전진 조건이 거짓이면 위치가 그대로다`() {
        // given
        val car = Car("pobi", 0)

        // when
        car.move(shouldMove = false)

        // then
        assertThat(car.getPosition()).isEqualTo(0)
    }

    @Test
    fun `자동차 이름이 1자 미만이면 예외가 발생해야 한다`() {
        // when & then
        assertThatThrownBy { Car("", 0) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("자동차 이름은 1자 이상 5자 이하여야 합니다")
    }

    @Test
    fun `자동차 이름이 5자 초과이면 예외가 발생해야 한다`() {
        // when & then
        assertThatThrownBy { Car("abcdef", 0) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("자동차 이름은 1자 이상 5자 이하여야 합니다")
    }

    @Test
    fun `자동차 이름에 공백이 포함되면 예외가 발생해야 한다`() {
        // when & then
        assertThatThrownBy { Car("po bi", 0) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("자동차 이름에 공백을 포함할 수 없습니다")
    }
}

