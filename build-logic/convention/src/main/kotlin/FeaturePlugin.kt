import com.android.build.gradle.LibraryExtension
import com.semirsuljevic.configureBuildType
import com.semirsuljevic.configureProjectFlavor
import com.semirsuljevic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class FeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("hechimdemo.android.library")
                apply("hechimdemo.android.hilt")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                configureBuildType(this)
                configureProjectFlavor(this)

                packaging {
                    resources.excludes.add("META-INF/*")
                }
            }
            dependencies {
                add("implementation", libs.findLibrary("kotlinx.serialization.json").get())
                add("implementation", libs.findLibrary("datastore").get())
                add("implementation", libs.findLibrary("material3").get())
                add("testImplementation", libs.findLibrary("mockk").get())
                add("testImplementation", libs.findLibrary("testCoroutine").get())
                add("androidTestImplementation", libs.findLibrary("jupiter").get())
                add("androidTestImplementation", libs.findLibrary("mockAndroid").get())
                add("androidTestImplementation", libs.findLibrary("truth").get())
                add("androidTestImplementation", libs.findLibrary("androidx.test.ext.junit").get())
                add("androidTestImplementation", libs.findLibrary("androidx-espresso-core").get())
            }
        }
    }
}
