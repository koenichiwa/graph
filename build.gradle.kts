plugins {
    kotlin("multiplatform") version "1.3.72"
    id("com.github.roroche.plantuml") version "1.0.2"
}

group = "com.koenichiwa"
version = "0.2.0"

repositories {
    mavenCentral()
}

kotlin {
    jvm()
    js()
//    macosX64()
    linuxArm64()
    linuxX64()

    sourceSets {
        // Common
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        // Build Types
        // JVM
        jvm().compilations["main"].defaultSourceSet {
            dependsOn(commonMain)
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
        jvm().compilations["test"].defaultSourceSet {
            dependsOn(commonTest)
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }

        // Javascript
        js().compilations["main"].defaultSourceSet {
            dependsOn(commonMain)
            dependencies {
                implementation(kotlin("stdlib-js"))
            }
        }

        js().compilations["test"].defaultSourceSet {
            dependsOn(commonTest)
            dependencies {
                implementation(kotlin("kotlin-test-js"))
            }
        }

        // Mac
//        macosX64().compilations["main"].defaultSourceSet {
//            dependsOn(commonMain)
//        }

        linuxX64().compilations["main"].defaultSourceSet {
            dependsOn(commonMain)
        }
    }
}

classDiagram {
    packageName = "com.koenichiwa.graphs"
    outputFile = project.file("/diagrams/class_diagram.plantuml")
}
