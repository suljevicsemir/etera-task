package com.semirsuljevic

enum class EtheraBrandFlavor (val dimension: EtheraFlavorDimension, val applicationId: String? = null) {
    DEV(EtheraFlavorDimension.version,"com.semirsuljevic.ethera.dev"),
    PROD(EtheraFlavorDimension.version,"ba.semirsuljevic.ethera")
}
