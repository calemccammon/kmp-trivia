package com.cale.mccammon.kmp.trivia

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform