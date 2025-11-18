package racingcar

import kotlin.random.Random

class RacingGame(
    val cars: Cars,
    val tryCount: Int,
    private val moveStrategy: () -> Int = { Random.nextInt(0, 10) }
) {
    init {
        require(tryCount >= 1) { "시도 횟수는 1 이상이어야 합니다" }
    }

    fun play(onRoundFinished: (List<Car>) -> Unit) {
        repeat(tryCount) {
            cars.moveAll { _ ->
                val randomValue = moveStrategy()
                randomValue >= 4
            }
            onRoundFinished(cars.cars)
        }
    }

    fun winners(): List<Car> = cars.findWinners()
}

