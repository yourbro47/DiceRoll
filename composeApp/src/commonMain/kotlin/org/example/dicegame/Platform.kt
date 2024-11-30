package org.example.dicegame

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform