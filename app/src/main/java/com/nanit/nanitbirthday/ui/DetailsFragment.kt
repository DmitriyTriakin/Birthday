package com.nanit.nanitbirthday.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.nanit.nanitbirthday.R
import com.nanit.nanitbirthday.databinding.DetailsFragmentBinding
import com.nanit.nanitbirthday.ui.model.ViewModel

class DetailsFragment : Fragment() {

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ViewModel by activityViewModels()
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
        binding.detailsFragment = this

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel

        initValidation()

        prepareBirthdayLayer()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun goToBirthdayScreen() {
        viewModel.setName(binding.etName.text.toString())
        findNavController().navigate(R.id.action_detailsFragment_to_birthdayFragment)
    }

    private fun initValidation() {
        binding.etName.addTextChangedListener(watcher)
        binding.etBirthday.addTextChangedListener(watcher)
    }

    private fun prepareBirthdayLayer() {
        val builder = MaterialDatePicker.Builder.datePicker().setTitleText(R.string.title_birthday)
        if (viewModel.birthdayInMillis > 0) {
            builder.setSelection(viewModel.birthdayInMillis)
        }
        val picker = builder.build()
        binding.etBirthday.setOnClickListener {
            activity?.supportFragmentManager?.let { manager ->
                picker.addOnPositiveButtonClickListener {
                    viewModel.setBirthday(it)
                }
                picker.show(manager, picker.toString())
            }
        }
    }
}