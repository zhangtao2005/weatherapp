package com.zht.weather.model

data class CityBean(var cityName: String?,var cityId: String?,var cnty: String? ,var location: String? = null
                    ,var parentCity: String? = null,var adminArea: String? ,var isFavor: Boolean = false)
