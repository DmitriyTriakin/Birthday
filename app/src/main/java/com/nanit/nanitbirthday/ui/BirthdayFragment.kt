package com.nanit.nanitbirthday.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.nanit.nanitbirthday.R
import com.nanit.nanitbirthday.databinding.BirthdayFragmentBinding
import com.nanit.nanitbirthday.ui.model.ViewModel

class BirthdayFragment : Fragment() {

    private var _binding: BirthdayFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = BirthdayFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setupBirthdayScreen(resources)

        binding.birthdayFragment = this

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun goToDetailsScreen() {
        findNavController().navigate(R.id.action_birthdayFragment_to_detailsFragment)
    }
}