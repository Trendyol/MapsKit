// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://developer.huawei.com/repo/")
            isAllowInsecureProtocol = true
        }
    }
    dependencies {
        classpath(plugin.agp)
        classpath(libs.kotlinGradlePlugin)
        classpath(plugin.huaweiPlugin)
        classpath(plugin.googleMapsPlugin)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://developer.huawei.com/repo/")
            isAllowInsecureProtocol = true
        }
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}