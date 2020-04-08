package com.sbn.covid19.shared.util

enum class Filter(val key:String) {
    DEFAULT("active"),
    CASE("active"),
    DEATH("deaths"),
    RECOVERED("recovered")
}