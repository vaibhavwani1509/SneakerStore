package com.otta.cart.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.otta.cart.R
import com.otta.cart.databinding.FragmentCartBinding
import com.otta.sneakerslist.presentation.Adapter.CartItemsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment : Fragment() {

    /**Data Binding*/
    private lateinit var binding: FragmentCartBinding

    /**ViewModel*/
    lateinit var viewModel: CartViewModel

    /**RecyclerView Adapter*/
    lateinit var cartRecyclerViewAdapter: CartItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        binding.cartFragment = this
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**Init the viewModel*/
        viewModel = ViewModelProvider(this)[CartViewModel::class.java]

        /**RecyclerView Adapter with select listener*/
        cartRecyclerViewAdapter = CartItemsAdapter {
            viewModel.removeCartItem(it.id)
            Toast.makeText(requireContext(), "'${it.name}' removed from the cart", Toast.LENGTH_SHORT).show()
        }

        /**Recyclerview UI Init*/
        binding.cartItemsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = cartRecyclerViewAdapter
        }

        /**Listen for the Sneakers list*/
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getCartItemsList()
                viewModel.cartItemsList.collect {
                    if (it.isNotEmpty())
                        cartRecyclerViewAdapter.setList(it)
                    else {
                        Toast.makeText(requireContext(), "No Item in the cart", Toast.LENGTH_SHORT).show()
                        closeSelf()
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.orderDetails.collect {
                    binding.subtotal.text = getString(R.string.subtotal) + " : $" + it.SubTotal.toString()
                    binding.taxAnCharges.text = getString(R.string.taxes_charges) + " : $" + it.TaxesAndCharges.toString()
                    binding.total.text = "$ ${it.Total.toString()}"
                }
            }
        }

        binding.checkoutButton.setOnClickListener {
            viewModel.getFinalOrderDetails()
        }
    }

    fun closeSelf() {
        requireView().findNavController().navigateUp()
    }
}