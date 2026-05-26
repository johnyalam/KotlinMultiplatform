package fi.developer.kotlinmultiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform