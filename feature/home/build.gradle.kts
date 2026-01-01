import com.juco.build_logic.dsl.setNameSpace

plugins {
    id("com.juco.build_logic.convention.feature")
    id("com.juco.build_logic.convention.compose")
    id("com.juco.build_logic.primitive.hilt")
}

android {
    setNameSpace("feature.home")
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.common)

    implementation(libs.androidx.navigation.compose)
}