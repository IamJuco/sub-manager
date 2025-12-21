package com.juco.build_logic.primitive

import com.juco.build_logic.dsl.implementation
import com.juco.build_logic.dsl.library
import com.juco.build_logic.dsl.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class RetrofitPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                implementation(libs.library(("retrofit")))
                implementation(libs.library(("retrofit2-kotlinx-serialization-converter")))
            }
        }
    }
}