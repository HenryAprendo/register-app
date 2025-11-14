package com.henrydev.registerapp.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.henrydev.registerapp.RegisterApplication
import com.henrydev.registerapp.ui.home.HomeViewModel
import com.henrydev.registerapp.ui.item.ItemEntryViewModel

object AppViewModelProvider {

    val Factory = viewModelFactory {

//        initializer {
//            val application = (this[AndroidViewModelFactory.APPLICATION_KEY] as RegisterApplication)
//            HomeViewModel(application.container.itemsRepository)
//        }

        /**
         * Initializer for HomeViewModel
         */
        initializer {
            HomeViewModel(registerApplication().container.itemsRepository)
        }

        initializer {
            ItemEntryViewModel(registerApplication().container.itemsRepository)
        }
    }

}


/**
 * Extension function to queries for [Application] object and return an instance
 * of [RegisterApplication]
 */
fun CreationExtras.registerApplication(): RegisterApplication =
(this[AndroidViewModelFactory.APPLICATION_KEY] as RegisterApplication)






