import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.21"
    id("org.jetbrains.compose") version "1.0.0-alpha3"
    id("com.squareup.sqldelight") version "1.4.4"
}

group = "ru.magomedcoder"
version = "1.0"

sqldelight {
    database("Database") {
        packageName = "models"
    }
}

repositories {
    google()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("com.squareup.sqldelight:sqlite-driver:1.4.4")
    implementation("com.squareup.sqldelight:coroutines-extensions-jvm:1.4.4")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "notes-kotlin-desktop"
            packageVersion = "1.0.0"
        }
    }
}