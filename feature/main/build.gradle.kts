import com.juco.build_logic.dsl.setNameSpace

plugins {
    id("com.juco.build_logic.convention.feature")
    id("com.juco.build_logic.convention.compose")
    id("com.juco.build_logic.primitive.hilt")
}

android {
    setNameSpace("feature.main")
}

dependencies {
    implementation(projects.feature.home)
    implementation(projects.feature.setting)
    implementation(projects.feature.subscriptionAdd)
    implementation(projects.feature.subscriptionDetail)
    implementation(projects.core.designsystem)
    implementation(projects.core.common)

    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
}