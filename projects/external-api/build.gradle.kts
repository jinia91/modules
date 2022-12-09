plugins {
    val kotlinVersion = "1.7.10"
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    id("org.springframework.boot") version "2.7.6"
}


dependencies {
    implementation(project(":projects:external-api:adapter:in_comming:http"))
    implementation(project(":projects:external-api:adapter:out_going:mysql"))
    implementation(project(":projects:external-api:core:domain"))
    implementation(project(":projects:external-api:core:application"))
    implementation(project(":projects:external-api:port"))
}

allprojects {
    apply {
        plugin("kotlin")
        plugin("kotlin-spring")
        plugin("kotlin-jpa")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
    }

    java.sourceCompatibility = JavaVersion.VERSION_11
    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.jetbrains.kotlin:kotlin-allopen")
        implementation("org.jetbrains.kotlin:kotlin-noarg")
        implementation("javax.validation:validation-api:2.0.1.Final")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-integration")
        implementation("org.springframework.integration:spring-integration-ip")
        implementation("io.github.microutils:kotlin-logging-jvm:3.0.2")

        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
        testImplementation("org.jetbrains.kotlin:kotlin-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
        testImplementation("org.springframework.integration:spring-integration-test")
    }
}

subprojects {
    tasks {
        bootJar {
            enabled = false
        }

        jar {
            enabled = true
        }
    }
}

project(":projects:external-api:adapter:in_comming:http"){
    dependencies{
        api(project(":projects:external-api:port"))
    }
}

project(":projects:external-api:adapter:out_going:mysql"){
    dependencies{
        api(project(":projects:external-api:core:domain"))
        runtimeOnly("mysql:mysql-connector-java")
    }
}


project(":projects:external-api:core:application"){
    dependencies{
        implementation(project(":projects:external-api:port"))
        api(project(":projects:external-api:core:domain"))
    }
}