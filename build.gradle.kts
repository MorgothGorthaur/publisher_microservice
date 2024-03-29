plugins {
    java
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.3"
    id("io.freefair.lombok") version "8.4"
}


allprojects {
    group = "executor.service"
    version = "0.0.1-SNAPSHOT"
    repositories {
        mavenCentral()
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "io.freefair.lombok")

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        implementation("jakarta.validation:jakarta.validation-api:3.1.0-M1")

    }
    tasks.bootJar {
        enabled = false
    }

}

