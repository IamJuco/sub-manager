import com.juco.build_logic.dsl.setNameSpace

plugins {
    id("com.juco.build_logic.convention.feature")
    id("com.juco.build_logic.convention.compose")
}

android {
    setNameSpace("core.common")
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
