plugins{
    id("com.juco.build_logic.convention.application")
    id("com.juco.build_logic.primitive.hilt")
    alias(libs.plugins.gms.google.service)
    alias(libs.plugins.firebase.crashlytics)
}

android {
    namespace = "com.juco.submanager"
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
    implementation(projects.data.local)
    implementation(projects.work)

    implementation(libs.work)
    implementation(libs.hilt.work)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)

    implementation(libs.androidx.appcompat)
    implementation(libs.timber)
}