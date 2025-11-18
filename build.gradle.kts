plugins {
    kotlin("jvm") version "1.9.20"
    application
}

group = "racingcar"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.assertj:assertj-core:3.24.2")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("racingcar.ApplicationKt")
}

tasks.run.configure {
    standardInput = System.`in`
}

