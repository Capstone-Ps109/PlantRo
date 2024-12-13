package com.example.plantro.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingevent.databinding.FragmentHistoryBinding
import com.example.plantro.data.pref.UserPreference
import com.example.plantro.data.pref.UserRepository
import com.example.plantro.data.pref.dataStore
import com.example.plantro.ui.history.HistoryAdapter
import com.example.plantro.ui.history.HistoryViewModelFactory
import kotlinx.coroutines.launch

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HistoryViewModel by viewModels {
        HistoryViewModelFactory(UserRepository.getInstance(UserPreference.getInstance(requireContext().dataStore)))
    }
    private lateinit var historyAdapter: HistoryAdapter // Adapter untuk RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar = binding.progressBar

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        // Set RecyclerView layout manager
        binding.rvHistoryInput.layoutManager = LinearLayoutManager(requireContext())

        // Inisialisasi adapter
        historyAdapter = HistoryAdapter { item -> }

        binding.rvHistoryInput.adapter = historyAdapter

        // Observasi perubahan data dari ViewModel
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.outputHistory.collect { response ->
                if (response != null) {
                    // Kirim data ke adapter untuk ditampilkan di RecyclerView
                    historyAdapter.submitList(response.history)
                }
            }
        }

        // Meminta data dari ViewModel
        viewModel.historyData()
    }

    private fun showToast(message: String) {
        context?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
