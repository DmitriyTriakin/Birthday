package com.nanit.nanitbirthday.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailsViewModel : ViewModel() {

    private val _name = MutableLiveData("")
    private val _date = MutableLiveData("")
    private val _url = MutableLiveData("https://as2.ftcdn.net/jpg/02/16/85/19/500_F_216851969_42JnrCBh9acjRk3hkFRzfKLqoA3CpDmk.jpg")

    val name: LiveData<String> = _name
    val date: LiveData<String> = _date
    val url: LiveData<String> = _url

    fun setDate(date: String) {
        _date.value = date
    }
}