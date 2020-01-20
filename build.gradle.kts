plugins {
    kotlin("jvm") version "1.3.61"
    `maven-publish`
}

group = "io.hugogu"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.gitlab.arturbosch.detekt:detekt-api:1.4.0")
}

publishing {
    publications {
        create<MavenPublication>("DetektSmells") {
            from(components["java"])
            artifactId = "detekt-smells"
            pom {
                description.set("Static code analysis extension for Detekt")
                name.set("detekt-smells")
                url.set("https://github.com/hugogu/detekt-smells")
                licenses {
                    license {
                        name.set("The Apache Software License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        distribution.set("repo")
                    }
                }
                developers {
                    developer {
                        id.set("Hugo Gu")
                        name.set("Hugo Gu")
                        email.set("hugogu@outlook.com")
                    }
                }
                scm {
                    url.set("https://github.com/hugogu/detekt-smells")
                }
            }
        }
    }
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
