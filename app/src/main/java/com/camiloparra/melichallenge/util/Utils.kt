package com.camiloparra.melichallenge.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

/**
 * You can find standard utils if you needed
 *
 * Created by Camilo Parra on 9/06/2022.
 */
class Utils {

    fun setFormatPrice(price: Int): String {
        //val format = DecimalFormat("#.###")
        val locale = Locale("es", "419")
        val format = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency = Currency.getInstance(Locale.getDefault())
        return format.format(price)
    }

}