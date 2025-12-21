import com.juco.build_logic.dsl.setNameSpace

plugins {
    id("com.juco.build_logic.convention.feature")
    id("com.juco.build_logic.convention.compose")
}

android {
    setNameSpace("designsystem")
}

dependencies {
    implementation(libs.timber)
}