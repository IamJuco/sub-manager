import com.juco.build_logic.dsl.setNameSpace
import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.juco.build_logic.convention.feature")
    id("com.juco.build_logic.convention.compose")
    id("com.juco.build_logic.primitive.hilt")
    alias(libs.plugins.gms.google.service)
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(FileInputStream(localPropertiesFile))
}

android {
    setNameSpace("feature.main")
    defaultConfig {
        val admobOpenId = localProperties.getProperty("ADMOB_OPEN_ID")
            ?: "ca-app-pub-3940256099942544/9257395921"
        buildConfigField("String", "ADMOB_OPEN_ID", "\"$admobOpenId\"")
    }
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
    implementation(libs.play.services.ads)

    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
}