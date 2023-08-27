package com.otta.sneakerdetails.presentation

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.otta.sneakerdetails.R
import com.otta.sneakerdetails.databinding.FragmentSneakerDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SneakerDetailsFragment : Fragment() {

    /**Binding*/
    private lateinit var binding: FragmentSneakerDetailsBinding

    /**ViewModel*/
    private lateinit var viewModel: SneakerDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sneaker_details, container, false)
        binding.sneakerDetailsFragment = this
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**Init ViewModel*/
        viewModel = ViewModelProvider(this)[SneakerDetailsViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        /**Getting the Sneaker Id from the arguments Bundle*/
        val sneakerId = arguments?.getString("sneakerId")

        /**Get sneaker details using given ID*/
        sneakerId?.let {
            viewModel.getSneaker(it)
        } ?: run {
            Toast.makeText(requireContext(), "No Details Available.", Toast.LENGTH_SHORT).show()
            closeSelf()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isSneakerInCart.collect { isSneakerInCart ->
                    requireContext().let { context ->
                        binding.addToCartButtonIcon.visibility =
                            if (isSneakerInCart) View.GONE else View.VISIBLE
                        binding.addToCartButtonText.text =
                            if (isSneakerInCart) getString(R.string.remove_to_cart) else getString(R.string.add_to_cart)
                        /**Give haptic feedback for better UX*/
                        viewModel.hapticFeedback(context)
                        /**Show Toast message*/
                        Toast.makeText(
                            context,
                            if (isSneakerInCart) R.string.sneaker_added else R.string.sneaker_removed,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    fun closeSelf() {
        requireView().findNavController().navigateUp()
    }
}