val sqldelightVersion = "1.5.4"

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.squareup.sqldelight").version("1.5.4")
}

sqldelight {
    database("demoDatabase") {
        packageName = "com.myapplication.common"
        sourceFolders = listOf("sqldelight")
        schemaOutputDirectory =
            file("src/commonMain/sqldelight/com/myapplication/common/outputs")
    }
}

kotlin {
    android()
    jvm("desktop")

    sourceSets {
        named("commonMain") {
            dependencies {
                api(compose.runtime)
                api(compose.ui)
                api(compose.foundation)
                api(compose.material)
                // Needed only for preview.
                implementation(compose.preview)
                // SQL Delight
                implementation("com.squareup.sqldelight:runtime:$sqldelightVersion")
                implementation("com.squareup.sqldelight:coroutines-extensions:$sqldelightVersion")
            }
        }
        named("androidMain") {
            dependencies {
                api("androidx.appcompat:appcompat:1.5.1")
                api("androidx.core:core-ktx:1.8.0")
                // SQL Delight
                implementation("com.squareup.sqldelight:android-driver:$sqldelightVersion")
            }
        }

        named("desktopMain") {
            dependencies {
                implementation("com.squareup.sqldelight:sqlite-driver:$sqldelightVersion")
            }
        }
    }
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 26
        targetSdk = 33
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    sourceSets {
        named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res")
        }
    }
    namespace = "com.myapplication.common"
}
/*dependencies {
    implementation("androidx.compose.material:material:1.3.1")
    implementation("androidx.compose.material:material-icons-core:1.3.1")
    implementation("androidx.annotation:annotation-jvm:+")
    implementation("androidx.compose.ui:ui-unit:1.3.3")
    implementation("androidx.compose.ui:ui:1.3.3")
    implementation("androidx.compose.foundation:foundation:1.3.1")
}*/
