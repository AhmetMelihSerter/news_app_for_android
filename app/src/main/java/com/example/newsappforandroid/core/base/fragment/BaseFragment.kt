package com.example.newsappforandroid.core.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.newsappforandroid.core.base.view_model.BaseViewModel
import com.example.newsappforandroid.product.constants.commands.NavigationCommand
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch

abstract class BaseFragment<BINDING : ViewDataBinding, VM : BaseViewModel<BINDING>> : Fragment() {

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
            layoutInflater,
            layoutId,
            container,
            false
        )

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }

        viewModel.setBindingAndKeyboardCallback(binding, ::hideKeyboard)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationListener()
        onViewModelReady(savedInstanceState)
    }

    private fun navigationListener() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.navigation.collect {
                    handleNavigation(it)
                }
            }
        }
    }

    private fun handleNavigation(navCommand: NavigationCommand) {
        when (navCommand) {
            is NavigationCommand.ToDirection -> findNavController().navigate(navCommand.directions)
            is NavigationCommand.Back -> findNavController().navigateUp()
        }
    }

    private fun hideKeyboard() {
        requireActivity().currentFocus?.let {
            val imm = requireActivity().getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE)
                    as InputMethodManager
            if (imm.isAcceptingText) {
                imm.hideSoftInputFromWindow(
                    it.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        }
    }
}
