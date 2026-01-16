import java.io.FileInputStream
import java.util.Properties

plugins{
    id("com.juco.build_logic.convention.application")
    id("com.juco.build_logic.primitive.hilt")
    alias(libs.plugins.gms.google.service)
    alias(libs.plugins.firebase.crashlytics)
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(FileInputStream(localPropertiesFile))
}

android {
    namespace = "com.juco.submanager"
    defaultConfig {
        val admobAppId = localProperties.getProperty("ADMOB_APP_ID")
            ?: "ca-app-pub-3940256099942544~3347511713"
        manifestPlaceholders["ADMOB_APP_ID"] = admobAppId
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isDebuggable = true
        }

        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(projects.feature.main)
    implementation(projects.core.common)
    implementation(projects.data.local)
    implementation(projects.work)

    implementation(libs.work)
    implementation(libs.hilt.work)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
    implementation(libs.play.services.ads)

    implementation(libs.androidx.appcompat)
    implementation(libs.timber)
}