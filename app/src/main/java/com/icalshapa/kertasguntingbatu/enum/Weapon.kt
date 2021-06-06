package com.icalshapa.kertasguntingbatu.enum

enum class Weapon(val value: Int)  {
    NONE(-1),
    ROCK(0),
    PAPER(1),
    SCISSOR(2);
    companion object {
        fun fromInt(value: Int) = values().first { it.value == value }
    }
}