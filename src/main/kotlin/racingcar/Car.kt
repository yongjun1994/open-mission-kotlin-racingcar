package racingcar

class Car(
    val name: String,
    private var position: Int = 0
) {
    init {
        require(name.length in 1..5) { "자동차 이름은 1자 이상 5자 이하여야 합니다" }
        require(!name.contains(" ")) { "자동차 이름에 공백을 포함할 수 없습니다" }
    }

    fun move(shouldMove: Boolean) {
        if (shouldMove) {
            position++
        }
    }

    fun getPosition(): Int = position
}

