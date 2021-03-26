package com.nanit.nanitbirthday.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.nanit.nanitbirthday.R
import com.nanit.nanitbirthday.databinding.DetailsFragmentBinding
import java.text.SimpleDateFormat
import java.util.Locale

class DetailsFragment : Fragment() {

    companion object {

        private const val DATE_FORMAT = "MM.dd.yyyy"

        fun newInstance() = DetailsFragment()
    }

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }
    private val watcher = object : TextWatcher {

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val name = binding.etName.text.toString().trim()
            val birthday = binding.etBirthday.text.toString().trim()
            binding.btnShowBirthdayScreen.isEnabled = name.isNotEmpty() && birthday.isNotEmpty()
        }

        override fun afterTextChanged(s: Editable) {}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel

        initValidation()

        prepareBirthdayLayer()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initValidation() {
        binding.etName.addTextChangedListener(watcher)
        binding.etBirthday.addTextChangedListener(watcher)
    }

    private fun prepareBirthdayLayer() {
        val picker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(R.string.title_birthday)
            .build()
        binding.etBirthday.setOnClickListener {
            activity?.supportFragmentManager?.let { manager ->
                picker.addOnPositiveButtonClickListener {
                    val date = SimpleDateFormat(DATE_FORMAT, Locale.US).format(it)
                    viewModel.setDate(date)
                }
                picker.show(manager, picker.toString())
            }
        }
    }
}