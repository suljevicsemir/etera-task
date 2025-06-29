package com.semirsuljevic

enum class EtheraBuildType(val applicationIdSuffix:String? = null) {
    DEBUG(".debug"),
    BETA(".qa"),
    STAGING(".staging"),
    RELEASE
}
