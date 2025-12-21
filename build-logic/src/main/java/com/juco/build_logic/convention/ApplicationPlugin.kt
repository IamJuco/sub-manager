package com.juco.build_logic.convention

import com.juco.build_logic.dsl.androidApplicationExtension
import com.juco.build_logic.dsl.configureAndroidLibrary
import com.juco.build_logic.dsl.libs
import com.juco.build_logic.dsl.version
import com.juco.build_logic.primitive.CommonAndroidPlugin
import com.juco.build_logic.primitive.ComposePlugin
import com.juco.build_logic.primitive.KotlinPlugin
import com.juco.build_logic.primitive.TestPlugin
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class ApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            apply<KotlinPlugin>()
            apply<CommonAndroidPlugin>()
            apply<ComposePlugin>()
            apply<TestPlugin>()
            configureAndroidLibrary()

            androidApplicationExtension {
                composeOptions {
                    kotlinCompilerExtensionVersion =
                        libs.findVersion("kotlinCompilerExtensionVersion")
                            .orElseThrow {
                                GradleException(
                                    "libs.versions.toml 에 kotlinCompilerExtensionVersion 이 정의되어 있어야 합니다."
                                )
                            }
                            .requiredVersion
                }

                defaultConfig {
                    applicationId = "com.juco.submanager"
                    versionCode = libs.version("versionCode").toInt()
                    versionName = libs.version("versionName")
                }
            }
        }
    }
}