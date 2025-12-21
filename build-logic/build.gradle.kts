plugins {
    `kotlin-dsl`
}

group = "com.juco.build_logic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradle)
    compileOnly(libs.kotlin.gradle)
    compileOnly(libs.compose.compiler.gradle)
}

gradlePlugin {
    plugins {
        register("com.juco.build_logic.primitive.CommonAndroidPlugin") {
            id = "com.juco.build_logic.primitive.common"
            implementationClass = "com.juco.build_logic.primitive.CommonAndroidPlugin"
        }

        register("com.juco.build_logic.primitive.ComposePlugin") {
            id = "com.juco.build_logic.primitive.compose"
            implementationClass = "com.juco.build_logic.primitive.ComposePlugin"
        }

        register("com.juco.build_logic.primitive.HiltPlugin") {
            id = "com.juco.build_logic.primitive.hilt"
            implementationClass = "com.juco.build_logic.primitive.HiltPlugin"
        }

        register("com.juco.build_logic.primitive.KotlinPlugin") {
            id = "com.juco.build_logic.primitive.kotlin"
            implementationClass = "com.juco.build_logic.primitive.KotlinPlugin"
        }

        register("com.juco.build_logic.primitive.RetrofitPlugin") {
            id = "com.juco.build_logic.primitive.retrofit"
            implementationClass = "com.juco.build_logic.primitive.RetrofitPlugin"
        }

        register("com.juco.build_logic.primitive.OkHttpPlugin") {
            id = "com.juco.build_logic.primitive.okhttp"
            implementationClass = "com.juco.build_logic.primitive.OkHttpPlugin"
        }

        register("com.juco.build_logic.primitive.TestPlugin") {
            id = "com.juco.build_logic.primitive.test"
            implementationClass = "com.juco.build_logic.primitive.TestPlugin"
        }

        register("com.juco.build_logic.convention.ApplicationPlugin") {
            id = "com.juco.build_logic.convention.application"
            implementationClass = "com.juco.build_logic.convention.ApplicationPlugin"
        }

        register("com.juco.build_logic.convention.ComposePlugin") {
            id = "com.juco.build_logic.convention.compose"
            implementationClass = "com.juco.build_logic.convention.ComposePlugin"
        }

        register("com.juco.build_logic.convention.FeaturePlugin") {
            id = "com.juco.build_logic.convention.feature"
            implementationClass = "com.juco.build_logic.convention.FeaturePlugin"
        }

        register("com.juco.build_logic.convention.KotlinJvmPlugin") {
            id = "com.juco.build_logic.convention.kotlin"
            implementationClass = "com.juco.build_logic.convention.KotlinJvmPlugin"
        }
    }
}
