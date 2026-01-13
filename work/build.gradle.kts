import com.juco.build_logic.dsl.setNameSpace

plugins {
    id("com.juco.build_logic.convention.feature")
    id("com.juco.build_logic.primitive.hilt")
}

android {
    setNameSpace("work")
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.common)
    implementation(projects.domain.local)

    implementation(libs.work)
    implementation(libs.hilt.work)

    implementation(libs.androidx.navigation.compose)
}