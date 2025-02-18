import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.30"
}

group = "de.stckoverflw"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://repo.codemc.io/repository/maven-snapshots/")
}

dependencies {
    // PaperMC Dependency
    compileOnly("io.papermc.paper", "paper-api", "1.17.1-R0.1-SNAPSHOT") // Only used on compile time because we have a PaperMC Server so we don't need it in the final jar

    // KSpigot dependency
    implementation("net.axay", "kspigot", "1.17.2")

    // Gson dependency
    implementation("com.google.code.gson", "gson", "2.8.7")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "16"
        }
    }
    withType<JavaCompile> {
        options.release.set(16)
    }
}
