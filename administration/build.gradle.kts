plugins {
    alias(libs.plugins.jvm)
}

group = "ru.alex3koval.ktorAuthentication"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.bundles.exposed)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}