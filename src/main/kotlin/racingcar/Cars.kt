package racingcar

class Cars(val cars: List<Car>) {
    init {
        val names = cars.map { it.name }
        require(names.size == names.distinct().size) { "중복된 자동차 이름이 있습니다" }
    }

    fun moveAll(shouldMove: (Car) -> Boolean) {
        cars.forEach { car ->
            car.move(shouldMove(car))
        }
    }

    fun findWinners(): List<Car> {
        val maxPosition = cars.maxOfOrNull { it.getPosition() } ?: 0
        return cars.filter { it.getPosition() == maxPosition }
    }
}

