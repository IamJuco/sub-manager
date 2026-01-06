import com.juco.build_logic.dsl.setNameSpace

plugins {
    id("com.juco.build_logic.convention.feature")
    id("com.juco.build_logic.primitive.hilt")
    id("com.juco.build_logic.primitive.room")
}

android {
    setNameSpace("data.local")
}

dependencies {
    implementation(libs.androidx.datastore)
    implementation(projects.domain.local)
}