package com.juco.build_logic.primitive

import com.juco.build_logic.dsl.androidTestImplementation
import com.juco.build_logic.dsl.library
import com.juco.build_logic.dsl.libs
import com.juco.build_logic.dsl.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class TestPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                testImplementation(libs.library("junit"))
                testImplementation(libs.library("kotlin-test"))
                androidTestImplementation(libs.library("androidx-junit"))
                androidTestImplementation(libs.library("androidx-espresso-core"))
            }
        }
    }
}