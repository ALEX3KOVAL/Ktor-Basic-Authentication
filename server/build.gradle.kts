val kotlin_version: String by project

plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.ktor)
}

group = "ru.alex3koval.ktorAuthentication"
version = "0.0.1"
application {
    mainClass.set("ru.alex3koval.ktorAuthentication.server.ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":administration"))
    implementation(project(":configure"))

    implementation(libs.bundles.ktor)
    implementation(libs.logback)
    implementation(libs.bundles.exposed)
    implementation(libs.koin.core)
    implementation(libs.hikari)

    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}