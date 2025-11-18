# 자동차 경주 게임 미션 회고

## 📌 프로젝트 개요

프리코스 자동차 경주 미션을 Kotlin으로 재구현한 프로젝트입니다. TDD 방식으로 개발하며 Kotlin의 언어적 특성을 최대한 활용했습니다.

## 🎯 학습 목표

- [ ] Kotlin 문법에 익숙해지기 (data class, 불변성, 람다, 고차 함수 등)
- [ ] TDD 방식으로 도메인 로직 구현하기
- [ ] Java 버전과의 설계·표현·스타일 차이 비교하기
- [ ] 객체지향 설계 원칙 적용하기

## 💡 주요 학습 내용

### 1. Kotlin 언어 특성 활용

#### Data Class
- Java에서는 getter/setter, equals, hashCode, toString을 직접 작성해야 했지만, Kotlin에서는 data class를 활용하면 더 간결하게 표현 가능
- 이번 프로젝트에서는 일반 class를 사용했지만, 필요 시 data class로 전환 가능

#### 불변성 (Immutability)
- `val` 키워드로 불변 속성 선언
- `require()` 함수로 초기화 시점에 유효성 검증
- 예: `Car` 클래스의 `name`은 `val`로 선언하여 불변성 보장

#### 고차 함수와 람다
- `moveAll(shouldMove: (Car) -> Boolean)` 같은 고차 함수로 전략 패턴을 더 간결하게 표현
- `forEach`, `map`, `filter` 등 컬렉션 함수 활용
- 예: `cars.forEach { car -> car.move(shouldMove(car)) }`

#### Null 안전성
- `?.` 안전 호출 연산자로 NullPointerException 방지
- `?:` 엘비스 연산자로 기본값 제공
- 예: `readLine()?.trim() ?: throw IllegalArgumentException("입력이 없습니다")`

#### 범위 연산자 (Range)
- `in 1..5` 같은 범위 연산자로 가독성 향상
- 예: `require(name.length in 1..5)`

### 2. TDD 개발 경험

#### 테스트 우선 작성
1. **CarTest**: 자동차의 기본 동작과 유효성 검증 테스트
2. **CarsTest**: 자동차 컬렉션의 동작과 우승자 찾기 테스트
3. **RacingGameTest**: 경주 게임의 전체 흐름 테스트

#### 테스트 주도 설계
- 테스트를 먼저 작성하면서 필요한 메서드와 시그니처를 설계
- 테스트가 실패하는 상태에서 구현을 시작하여 점진적으로 통과시키는 과정

### 3. 객체지향 설계

#### 단일 책임 원칙 (SRP)
- `Car`: 자동차의 상태와 이동 로직
- `Cars`: 자동차 컬렉션 관리와 우승자 찾기
- `RacingGame`: 경주 게임의 전체 흐름 제어
- `Application`: 입출력 처리

#### 의존성 역전 원칙 (DIP)
- `RacingGame`의 `moveStrategy`를 함수 타입으로 받아 전략 패턴 구현
- 테스트에서 고정된 전략을 주입하여 테스트 가능성 향상

## 🔍 Java vs Kotlin 비교

### 코드 간결성

**Java:**
```java
public class Car {
    private final String name;
    private int position;
    
    public Car(String name, int position) {
        if (name == null || name.length() < 1 || name.length() > 5) {
            throw new IllegalArgumentException("자동차 이름은 1자 이상 5자 이하여야 합니다");
        }
        if (name.contains(" ")) {
            throw new IllegalArgumentException("자동차 이름에 공백을 포함할 수 없습니다");
        }
        this.name = name;
        this.position = position;
    }
    
    public void move(boolean shouldMove) {
        if (shouldMove) {
            this.position++;
        }
    }
    
    public int getPosition() {
        return position;
    }
}
```

**Kotlin:**
```kotlin
class Car(
    val name: String,
    var position: Int = 0
) {
    init {
        require(name.length in 1..5) { "자동차 이름은 1자 이상 5자 이하여야 합니다" }
        require(!name.contains(" ")) { "자동차 이름에 공백을 포함할 수 없습니다" }
    }
    
    fun move(shouldMove: Boolean) {
        if (shouldMove) position++
    }
    
    fun getPosition(): Int = position
}
```

### 컬렉션 처리

**Java:**
```java
public List<Car> findWinners() {
    int maxPosition = cars.stream()
        .mapToInt(Car::getPosition)
        .max()
        .orElse(0);
    return cars.stream()
        .filter(car -> car.getPosition() == maxPosition)
        .collect(Collectors.toList());
}
```

**Kotlin:**
```kotlin
fun findWinners(): List<Car> {
    val maxPosition = cars.maxOfOrNull { it.position } ?: 0
    return cars.filter { it.position == maxPosition }
}
```

## 🚀 개선 사항 및 리팩토링

### 현재 구현의 장점
- ✅ 도메인 로직과 입출력 로직의 명확한 분리
- ✅ TDD 방식으로 테스트 커버리지 확보
- ✅ Kotlin의 언어적 특성 활용
- ✅ 유효성 검증이 철저함

### 개선 가능한 부분
- [ ] `Car`를 data class로 변경 고려
- [ ] 입력 검증 로직을 별도 클래스로 분리 (예: `InputValidator`)
- [ ] 출력 포맷팅 로직을 별도 클래스로 분리 (예: `OutputFormatter`)
- [ ] 랜덤 값 생성 전략을 인터페이스로 추상화
- [ ] 예외 메시지를 상수로 관리

## 📚 추가 학습 필요 사항

- [ ] Kotlin의 sealed class 활용
- [ ] Kotlin의 inline 함수 활용
- [ ] Kotlin의 확장 함수를 더 적극적으로 활용
- [ ] Kotlin의 코루틴 활용 (비동기 처리)

## 🎓 회고 및 느낀 점

<!-- 여기에 프로젝트를 진행하면서 느낀 점, 어려웠던 점, 배운 점 등을 자유롭게 작성해주세요 -->

### 좋았던 점
- 

### 어려웠던 점
- 

### 배운 점
- 

### 다음에 개선하고 싶은 점
- 

## 📝 참고 자료

- [Kotlin 공식 문서](https://kotlinlang.org/docs/home.html)
- [Kotlin 코딩 컨벤션](https://kotlinlang.org/docs/coding-conventions.html)
- 우아한테크코스 프리코스 자동차 경주 미션

