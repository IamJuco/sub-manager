package com.juco.build_logic.primitive

import com.android.build.gradle.BaseExtension
import com.juco.build_logic.dsl.implementation
import com.juco.build_logic.dsl.library
import com.juco.build_logic.dsl.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class CommonAndroidPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        extensions.getByType<BaseExtension>().apply {
            defaultConfig {
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            buildFeatures.apply {
                buildConfig = true
            }
        }
        dependencies {
            implementation(libs.library("androidx-core-ktx"))
        }
    }
}