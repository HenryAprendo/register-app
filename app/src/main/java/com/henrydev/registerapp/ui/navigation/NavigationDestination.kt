package com.henrydev.registerapp.ui.navigation

import androidx.annotation.StringRes

interface NavigationDestination {
    val route: String
    @get:StringRes
    val titleRes: Int
}