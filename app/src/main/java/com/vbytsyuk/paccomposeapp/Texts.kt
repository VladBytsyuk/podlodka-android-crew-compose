package com.vbytsyuk.paccomposeapp

object Texts {
    object Title {
        const val APP = "Podlodka Android Crew Сезон #4"

        const val FAVORITES = "Избранное"
        const val SESSIONS = "Сессии"
    }

    object Error {
        object SnackBar {
            const val FAVORITES_OVERFLOW = "Не удалось добавить сессию в избранное"
        }
    }

    object ContentDescription {
        const val FAVORITE_OFF = "Добавить в избранное"
        const val FAVORITE_ON = "Убрать из избранного"
        const val BACK = "Назад"
        const val THEME_DAY = "Сменить тему на тёмную"
        const val THEME_NIGHT = "Сменить тему на светлую"
        const val SESSION_DATE_TIME = "Дата и время сессии"

        fun speakerAvatar(speaker: String) = "Аватар: $speaker"
    }
}
