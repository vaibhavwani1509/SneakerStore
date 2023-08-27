package com.otta.search.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.otta.Search.R
import com.otta.Search.databinding.FragmentSearchBinding
import com.otta.cart.databinding.FragmentCartBinding
import com.otta.sneakerslist.presentation.Adapter.SearchItemsAdapter
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    /**Data Binding*/
    private lateinit var binding: FragmentSearchBinding

    /**ViewModel*/
    lateinit var viewModel: SearchViewModel

    lateinit var searchRecyclerViewAdapter: SearchItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.searchFragment = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**Init the viewModel*/
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        /**RecyclerView Adapter with select listener*/
        searchRecyclerViewAdapter = SearchItemsAdapter {
            val selectedSneakerId = Bundle()
            selectedSneakerId.putString("sneakerId" , it.id)
            requireView().findNavController().navigate(R.id.action_searchFragment_to_sneaker_details_navigation_graph, selectedSneakerId)
        }

        /**Recyclerview UI Init*/
        binding.searchRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = searchRecyclerViewAdapter
        }

        /**Listen for the Sneakers list*/
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getSneakerList()
                viewModel.sneakersList.collect {
                    searchRecyclerViewAdapter.setList(it)
                }
            }
        }
    }

    fun closeSelf() {
        requireView().findNavController().navigateUp()
    }
}