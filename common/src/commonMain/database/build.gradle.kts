plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("com.squareup.sqldelight")
}
android {
    namespace = "example.Demo.common.database"
}

sqldelight {
    database("demoDatabase") {
        packageName = "example.Demo.database"
        schemaOutputDirectory =
            file("src/commonMain/sqldelight/example/Demo/common/database")
    }
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                // Coroutines
                implementation (org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2)
                implementation (org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2)

                // SQL Delight
                implementation (com.squareup.sqldelight:android-driver:1.5.2)
                implementation (com.squareup.sqldelight:coroutines-extensions-jvm:1.5.2)
            }
        }

        desktopMain {
            dependencies {
                implementation("com.squareup.sqldelight:sqlitedriver:${rootProject.extra["sqlDelightVersion"]}")
            }
        }


    }
}
