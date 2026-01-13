plugins{
    id("com.juco.build_logic.convention.application")
    id("com.juco.build_logic.primitive.hilt")
}

android {
    namespace = "com.juco.submanager"
    buildTypes {
        debug {
            isMinifyEnabled = false
            isDebuggable = true
            applicationIdSuffix = ".debug"
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

    implementation(libs.androidx.appcompat)
    implementation(libs.timber)
}