plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.serialization)
}

group = "ru.alex3koval.ktorAuthentication"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":administration"))

    implementation(libs.logback)
    implementation(libs.kotlinx.serialization.core)
    implementation(libs.koin.core)
    implementation(libs.kaml)
    implementation(libs.bundles.exposed)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}