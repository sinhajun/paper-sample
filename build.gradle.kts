plugins {
    id("java")
    kotlin("jvm") version "1.8.21"
}

group = "io.github.sinhajun"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

}

tasks.test {
    useJUnitPlatform()
}

tasks.processResources {
    filesMatching("*.yml") {
        expand(
            "name" to rootProject.name,
            "version" to version
        )
    }
}

/*
1. Edit Configurations
2. Gradle 빌드툴 생성
3. run 부분에 jar copyPlugin 입력
*/
tasks.register("copyPlugin", Copy::class) {
    doFirst { println("copying built plugin ...") }

    from("build/libs/" + "(플러그인 이름)") // 예 : paper-sample-1.0-SNAPSHOT.jar
    into("(서버 플러그인 파일 경로)") // 예: ~/PluginServer/plugins

    doLast { println("copied built plugin!") }
}

kotlin {
    jvmToolchain(11)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}