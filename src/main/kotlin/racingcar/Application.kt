package racingcar

fun main() {
    val carNames = readCarNames()
    val tryCount = readTryCount()

    val cars = Cars(carNames.map { Car(it) })
    val game = RacingGame(cars, tryCount)

    println("\n실행 결과")
    game.play { currentCars ->
        currentCars.forEach { car ->
            println("${car.name} : ${"-".repeat(car.getPosition())}")
        }
        println()
    }

    val winners = game.winners()
    val winnerNames = winners.joinToString(", ") { it.name }
    println("최종 우승자 : $winnerNames")
}

fun readCarNames(): List<String> {
    println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)")
    val input = readLine()?.trim() ?: throw IllegalArgumentException("입력이 없습니다")

    val names = input.split(",")
        .map { it.trim() }
        .filter { it.isNotEmpty() }

    require(names.isNotEmpty()) { "자동차 이름이 입력되지 않았습니다" }

    names.forEach { name ->
        require(name.length in 1..5) { "자동차 이름은 1자 이상 5자 이하여야 합니다: $name" }
        require(!name.contains(" ")) { "자동차 이름에 공백을 포함할 수 없습니다: $name" }
    }

    val distinctNames = names.distinct()
    require(distinctNames.size == names.size) { "중복된 자동차 이름이 있습니다" }

    return distinctNames
}

fun readTryCount(): Int {
    println("시도할 회수는 몇회인가요?")
    val input = readLine()?.trim() ?: throw IllegalArgumentException("입력이 없습니다")

    val count = input.toIntOrNull()
        ?: throw IllegalArgumentException("시도 횟수는 숫자여야 합니다: $input")

    require(count >= 1) { "시도 횟수는 1 이상이어야 합니다: $count" }

    return count
}

