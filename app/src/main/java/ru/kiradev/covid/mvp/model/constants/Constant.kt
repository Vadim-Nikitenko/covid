package ru.kiradev.covid.mvp.model.constants

class Constant {
    companion object {
        const val BASE_URL: String = "https://covid19-api.org/api/"
        const val CACHE_DIRECTORY_NAME = "cache"
        const val CACHE_SIZE = 10L * 1024L * 1024L
        const val SPLASH_CONTAINER_ID = -1
        const val COMMON_STAT_KEY = "common_stat_key"
    }
}