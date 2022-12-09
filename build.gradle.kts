plugins {
    val kotlinVersion = "1.7.10"
    kotlin("jvm") version kotlinVersion
}

java.sourceCompatibility = JavaVersion.VERSION_11

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("kotlin")
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
