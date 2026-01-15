import com.juco.build_logic.dsl.setNameSpace

plugins {
    id("com.juco.build_logic.convention.feature")
    id("com.juco.build_logic.convention.compose")
    id("com.juco.build_logic.primitive.hilt")
    alias(libs.plugins.gms.google.service)
}

android {
    setNameSpace("feature.main")
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.common)

    implementation(projects.feature.home)
    implementation(projects.feature.setting)
    implementation(projects.feature.subscriptionAdd)
    implementation(projects.feature.subscriptionDetail)
    implementation(projects.feature.subscriptionEdit)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.config)
    implementation(libs.play.app.update)
    implementation(libs.play.app.update.ktx)

    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
}