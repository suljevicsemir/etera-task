plugins {
    `kotlin-dsl`
}
dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "ethera.android.application"
            implementationClass = "ApplicationPlugin"
        }
        register("androidApplicationCompose") {
            id = "ethera.android.application.compose"
            implementationClass = "ApplicationComposePlugin"
        }
        register("androidFeature") {
            id = "ethera.android.feature"
            implementationClass = "FeaturePlugin"
        }
        register("androidLibraryCompose") {
            id = "ethera.android.library.compose"
            implementationClass = "LibraryComposePlugin"
        }

        register("androidLibrary") {
            id = "ethera.android.library"
            implementationClass = "LibraryPlugin"
        }
        register("androidHilt") {
            id = "ethera.android.hilt"
            implementationClass = "HiltPlugin"
        }
        register("androidBuildType") {
            id = "ethera.android.buildtype"
            implementationClass = "BuildTypePlugin"
        }
        register("androidProjectFlavor") {
            id = "ethera.android.ProjectFlavor"
            implementationClass = "ProjectFlavorPlugin"
        }
    }
}

