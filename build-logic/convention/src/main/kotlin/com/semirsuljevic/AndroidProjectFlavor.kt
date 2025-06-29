package com.semirsuljevic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

internal fun Project.configureProjectFlavor(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        flavorDimensions += EtheraFlavorDimension.version.name
        productFlavors {
            EtheraBrandFlavor.values().forEach {
                create(it.name){
                    dimension = it.dimension.name
                }
            }
        }
    }
}
