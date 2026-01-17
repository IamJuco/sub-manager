import com.juco.build_logic.dsl.setNameSpace

plugins {
    id("com.juco.build_logic.convention.feature")
    id("com.juco.build_logic.convention.compose")
    id("com.juco.build_logic.primitive.hilt")
}

android {
    setNameSpace("feature.subscription_edit")
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.common)
    implementation(projects.domain.local)

    implementation(libs.coil.compose)
    implementation(libs.coil.core)

    implementation(libs.androidx.navigation.compose)
}