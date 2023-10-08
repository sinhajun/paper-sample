plugins {
    id("java")
    kotlin("jvm") version "1.8.21"
}

val pluginPath: String = ".server/plugins" // 예: ~/PluginServer/plugins
group = "io.github.sinhajun"
version = "0.0.1"

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
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

    from("build/libs/" + "${rootProject.name}-${version}.jar") // 예 : paper-sample-0.0.1.jar
    into(pluginPath)

    doLast { println("copied built plugin!") }
}

kotlin {
    jvmToolchain(11)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}