package com.juco.build_logic.primitive

import com.android.build.gradle.BaseExtension
import com.juco.build_logic.dsl.implementation
import com.juco.build_logic.dsl.library
import com.juco.build_logic.dsl.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class KotlinPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(plugins) {
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            extensions.getByType<BaseExtension>().apply {
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }
            }

            tasks.withType<KotlinCompile> {
                compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
            }

            dependencies{

                implementation(libs.library("kotlinx-immutable"))
                implementation(libs.library("kotlinx-serialization-json"))
            }
        }
    }
}