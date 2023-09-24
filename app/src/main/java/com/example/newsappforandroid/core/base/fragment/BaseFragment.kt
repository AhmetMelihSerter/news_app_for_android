package com.example.newsappforandroid.core.base.fragment

import com.example.newsappforandroid.BR
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.newsappforandroid.core.constants.Extensions.hideKeyboard
import com.example.newsappforandroid.core.constants.Extensions.launchAndRepeatWithViewLifecycle
import com.example.newsappforandroid.core.base.view_model.BaseViewModel
import com.example.newsappforandroid.product.constants.navigation.NavigationCommand
import kotlinx.coroutines.launch

abstract class BaseFragment<BINDING : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    @get:LayoutRes
    protected abstract val layoutId: Int

    protected abstract val viewModel: VM

    protected lateinit var binding: BINDING

    protected open fun onViewModelReady(savedInstanceState: Bundle?) {
        viewModel.initialize()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            layoutId,
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.viewModel, viewModel)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideKeyboardListener()
        navigationListener()
        onViewModelReady(savedInstanceState)
    }

    private fun hideKeyboardListener() {
        launchAndRepeatWithViewLifecycle {
            viewModel.keyboardStatus.collect {
                hideKeyboard()
                binding.root.clearFocus()
            }
        }
    }

    private fun navigationListener() {
        launchAndRepeatWithViewLifecycle {
            viewModel.navigation.collect {
                handleNavigation(it)
            }
        }
    }

    private fun handleNavigation(navCommand: NavigationCommand) {
        when (navCommand) {
            is NavigationCommand.ToDirection -> findNavController().navigate(navCommand.directions)
            is NavigationCommand.Back -> findNavController().navigateUp()
        }
    }
}
