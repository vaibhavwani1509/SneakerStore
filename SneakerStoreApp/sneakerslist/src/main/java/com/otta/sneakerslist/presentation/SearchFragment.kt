package com.otta.sneakerslist.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.otta.sneakerslist.R
import com.otta.sneakerslist.databinding.FragmentSearchBinding
import com.otta.sneakerslist.presentation.Adapter.SneakersListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    /**Data Binding*/
    private lateinit var binding: FragmentSearchBinding

    /**ViewModel*/
    lateinit var viewModel: SearchViewModel

    /**Adapter*/
    lateinit var searchRecyclerViewAdapter: SneakersListAdapter

    /**Text Change Listener*/
    lateinit var textWatcher: TextWatcher

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

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        /**RecyclerView Adapter with select listener*/
        searchRecyclerViewAdapter = SneakersListAdapter {
            val selectedSneakerId = Bundle()
            selectedSneakerId.putString("sneakerId", it.id)
            requireView().findNavController().navigate(
                R.id.action_searchFragment_to_sneaker_details_navigation_graph,
                selectedSneakerId
            )
        }

        /**Recyclerview UI Init*/
        binding.searchRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
            adapter = searchRecyclerViewAdapter
        }

        /**Listen for the Sneakers list*/
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.sneakersList.collect {
                    searchRecyclerViewAdapter.setList(it)
                }
            }
        }

        /**Text Change listener*/
        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val searchKeyword = viewModel.getValidInputValue(s.toString())
                viewModel.search(searchKeyword)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.searchField.addTextChangedListener(textWatcher)
        viewModel.search(binding.searchField.text.toString())
    }

    override fun onPause() {
        super.onPause()
        binding.searchField.removeTextChangedListener(textWatcher)
    }

    fun closeSelf() {
        requireView().findNavController().navigateUp()
    }
}