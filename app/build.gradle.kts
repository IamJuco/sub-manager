plugins{
    id("com.juco.build_logic.convention.application")
    id("com.juco.build_logic.primitive.hilt")
}

android {
    buildTypes {
        release {
            isMinifyEnabled = false
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

    implementation(libs.androidx.appcompat)
    implementation(libs.timber)
}