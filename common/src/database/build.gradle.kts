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
    }
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(Deps.Badoo.Reaktive.reaktive)
            }
        }

        desktopMain {
            dependencies {
                implementation(Deps.Squareup.SQLDelight.sqliteDriver)
            }
        }


    }
}
