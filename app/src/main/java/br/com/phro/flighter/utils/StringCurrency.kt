package br.com.phro.flighter.utils

import java.text.NumberFormat
import java.util.Locale

class CurrencyFormat {
    companion object {
        fun currencyFormat(value: Double): String {
            val brazilLocale = Locale("pt", "BR")
            val numberFormat = NumberFormat.getCurrencyInstance(brazilLocale)

            return numberFormat.format(value)
        }
    }
}
