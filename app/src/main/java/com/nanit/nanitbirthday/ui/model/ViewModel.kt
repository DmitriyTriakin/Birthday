package com.nanit.nanitbirthday.ui.model

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nanit.nanitbirthday.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ViewModel : ViewModel() {

    companion object {

        private const val DATE_FORMAT = "MM.dd.yyyy"
        private const val IMG_URL_FIRST =
            "https://as2.ftcdn.net/jpg/02/16/85/19/500_F_216851969_42JnrCBh9acjRk3hkFRzfKLqoA3CpDmk.jpg"
        private const val IMG_URL_SECOND =
            "https://as2.ftcdn.net/jpg/02/50/10/03/1000_F_250100321_1hYz6jdwgiKmXz3mC4q0BCnR83jSTSbL.jpg"
    }

    private val _name = MutableLiveData("")
    private val _birthday = MutableLiveData("")
    private val _url = MutableLiveData(IMG_URL_FIRST)
    private val _background
        get() = MutableLiveData(
            when (random) {
                0 -> R.drawable.android_elephant_popup
                1 -> R.drawable.android_fox_popup
                else -> R.drawable.android_pelican_popup
            }
        )
    private val _icCamera
        get() = MutableLiveData(
            when (random) {
                0 -> R.drawable.ic_camera_yellow
                1 -> R.drawable.ic_camera_green
                else -> R.drawable.ic_camera_blue
            }
        )
    private val _isYearsAge = MutableLiveData(false)
    private val _age = MutableLiveData(R.drawable.ic_0)
    private var random = -1
    var birthdayInMillis: Long = -1

    val name: LiveData<String> = _name
    val birthday: LiveData<String> = _birthday
    val url: LiveData<String> = _url
    val background: LiveData<Int> get() = _background
    val icCamera: LiveData<Int> get() = _icCamera
    val isYearsAge: LiveData<Boolean> = _isYearsAge
    val age: LiveData<Int> = _age

    fun setName(name: String) {
        _name.value = name
    }

    fun setBirthday(timeInMillis: Long) {
        birthdayInMillis = timeInMillis
        _birthday.value = SimpleDateFormat(DATE_FORMAT, Locale.US).format(timeInMillis)
    }

    fun setImgUrl() {
        _url.value = IMG_URL_SECOND
    }

    fun setupBirthdayScreen(r: Resources) {
        random = (0..2).random()

        _age.value = r.getIdentifier(
            "ic_${getAge(birthdayInMillis)}", "drawable", "com.nanit.nanitbirthday"
        )
    }

    private fun getAge(timeInMillis: Long): Int {
        val dob: Calendar = Calendar.getInstance()
        val today: Calendar = Calendar.getInstance()
        dob.timeInMillis = timeInMillis
        var age: Int = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)
        var isYearDecrement = false
        if (age > 0) {
            isYearDecrement = if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)) {
                today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)
            } else {
                today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)
            }
            if (isYearDecrement) {
                age--
            }
        }
        _isYearsAge.value = age > 0
        if (age > 0) {
            return if (age > 12) 12 else age
        }
        age = today.get(Calendar.MONTH) - dob.get(Calendar.MONTH)
        if (today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
            if (age == 0 && isYearDecrement) {
                age = 12
            } else if (age > 0) {
                age--
            }
        }
        return if (age < 0) age + 12 else age
    }
}