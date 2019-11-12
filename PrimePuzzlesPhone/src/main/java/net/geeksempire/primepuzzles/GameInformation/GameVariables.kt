/*
 * Copyright (c) 2019.  All Rights Reserved for Geeks Empire.
 * Created by Elias Fazel on 11/11/19 6:09 PM
 * Last modified 11/11/19 6:08 PM
 */

package net.geeksempire.primepuzzles.GameInformation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class GameVariables : ViewModel(){

    companion object {
        val GAME_LEVEL_DIFFICULTY: MutableLiveData<Int> by lazy {
            MutableLiveData<Int>()
        }

        val GAME_LEVEL_DIFFICULTY_COUNTER: MutableLiveData<Int> by lazy {
            MutableLiveData<Int>()
        }


        val CENTER_VALUE: MutableLiveData<Int> by lazy {
            MutableLiveData<Int>()
        }

        val TOP_VALUE: MutableLiveData<Int> by lazy {
            MutableLiveData<Int>()
        }

        val LEFT_VALUE: MutableLiveData<Int> by lazy {
            MutableLiveData<Int>()
        }

        val RIGHT_VALUE: MutableLiveData<Int> by lazy {
            MutableLiveData<Int>()
        }


        val PRIME_NUMBER_DETECTED: MutableLiveData<Boolean> by lazy {
            MutableLiveData<Boolean>()
        }


        val TOGGLE_SNACKBAR: MutableLiveData<Boolean> by lazy {
            MutableLiveData<Boolean>()
        }


        val SHUFFLE_PROCESS_POSITION: MutableLiveData<Int> by lazy {
            MutableLiveData<Int>()
        }

        val SHUFFLE_PROCESS_VALUE: MutableLiveData<Int> by lazy {
            MutableLiveData<Int>()
        }


        val DIVISIBLE_POSITIVE_POINT: MutableLiveData<Int> by lazy {
            MutableLiveData<Int>()
        }

        val PRIME_POSITIVE_POINT: MutableLiveData<Int> by lazy {
            MutableLiveData<Int>()
        }

        val CHANGE_CENTER_RANDOM_POSITIVE_POINT: MutableLiveData<Int> by lazy {
            MutableLiveData<Int>()
        }


        val DIVISIBLE_NEGATIVE_POINT: MutableLiveData<Int> by lazy {
            MutableLiveData<Int>()
        }

        val PRIME_NEGATIVE_POINT: MutableLiveData<Int> by lazy {
            MutableLiveData<Int>()
        }

        val CHANGE_CENTER_RANDOM_NEGATIVE_POINT: MutableLiveData<Int> by lazy {
            MutableLiveData<Int>()
        }
    }
}