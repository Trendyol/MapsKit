plugins {
    id("com.huawei.agconnect")
    id("com.android.library")
    id("kotlin-android")
    id("maven-publish")
}

group = "com.trendyol.mapskit"
version = "1.0"

android {
    namespace = "com.trendyol.mapskit"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
        debug {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(libs.kotlinStd)
    implementation(libs.googleMaps)
    implementation(libs.huaweiMaps)
    implementation(libs.huaweiAgConnect)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.trendyol.mapskit"
                artifactId = "mapskit"
                version = "1.3"
            }
        }
    }
}