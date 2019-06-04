package com.edicasoft.journeyreport.dynamicFeature

interface IDynamicFeature {
    fun run(name: Int, callback: DynamicFeatureCallback)
    fun run(name: String, callback: DynamicFeatureCallback)
    fun cancelRun(name: String)
    fun uninstall(name: String)
}