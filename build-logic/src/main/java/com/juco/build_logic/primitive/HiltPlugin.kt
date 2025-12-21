package com.juco.build_logic.primitive

import com.juco.build_logic.dsl.implementation
import com.juco.build_logic.dsl.ksp
import com.juco.build_logic.dsl.library
import com.juco.build_logic.dsl.libs
import com.juco.build_logic.dsl.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(plugins) {
                apply("com.google.devtools.ksp")
                apply("com.google.dagger.hilt.android")
            }

            dependencies {
                implementation(libs.library("hilt-android"))
                implementation(libs.library("hilt-core"))
                implementation(libs.library("hilt-navigation-compose"))
                testImplementation(libs.library("hilt-android-testing"))
                ksp(libs.library("hilt-compiler"))
                ksp(libs.library("hilt-android-compiler"))
                ksp(libs.library("androidx-hilt-compiler"))
            }
        }
    }

}