package com.juco.build_logic.convention

import com.juco.build_logic.dsl.androidExtension
import com.juco.build_logic.primitive.ComposePlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class ComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply<ComposePlugin>()

            androidExtension.apply {
                buildFeatures {
                    compose = true
                }
            }
        }
    }
}