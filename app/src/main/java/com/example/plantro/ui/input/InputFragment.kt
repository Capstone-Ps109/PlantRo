package com.example.plantro.ui.input

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.dicodingevent.databinding.FragmentInputBinding
import com.example.plantro.data.pref.UserPreference
import com.example.plantro.data.pref.UserRepository
import com.example.plantro.data.pref.dataStore
import com.example.plantro.ui.detail.DetailActivity

class InputFragment : Fragment() {

    private var _binding: FragmentInputBinding? = null
    private val binding get() = _binding!!
    private val viewModel: InputViewModel by viewModels {
        InputViewModelFactory(UserRepository.getInstance(UserPreference.getInstance(requireContext().dataStore)))
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar = binding.progressBar

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        // Observasi perubahan data dari ViewModel
        // Menggunakan collect untuk StateFlow di dalam Fragment
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.outputResponse.collect { response ->
                if (response != null) {
                    // Setel data yang diterima ke dalam UI
                    binding.soilQualityText.text = response.soilQuality
                    binding.predictionCropText.text = response.predictions?.rotasi1?.get(0)?.crop
                    binding.confidenceText.text = response.predictions?.rotasi1?.get(0)?.confidence.toString()
                }
            }
        }


        // Mengambil data input dari pengguna dan mengirimkan request ke API saat tombol diklik
        binding.checkBtn.setOnClickListener {
            val nitrogen = binding.nitrogenEditText.text.toString().trim().toIntOrNull() ?: 0
            val phosphorus = binding.FosforEditText.text.toString().trim().toIntOrNull() ?: 0
            val potassium = binding.PotassiumEditText.text.toString().trim().toIntOrNull() ?: 0
            val temperature = binding.SuhuEditText.text.toString().trim().toFloatOrNull() ?: 0f
            val humidity = binding.KelembapanEditText.text.toString().trim().toFloatOrNull() ?: 0f
            val pH_Value = binding.pHEditText.text.toString().trim().toFloatOrNull() ?: 0f
            val rainfall = binding.CurahHujanEditText.text.toString().trim().toFloatOrNull() ?: 0f

            if (nitrogen == 0 || phosphorus == 0 || potassium == 0 || temperature == 0f || humidity == 0f || pH_Value == 0f || rainfall == 0f) {
                showToast("Please fill in all fields with valid data")
            } else {
                // Kirim data ke ViewModel untuk diproses
                viewModel.inputData(nitrogen, phosphorus, potassium, temperature, humidity, pH_Value, rainfall)

                // Menunggu data respons API, lalu kirim ke DetailActivity
                viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    viewModel.outputResponse.collect { response ->
                        if (response != null) {
                            // Kirim data ke DetailActivity setelah API berhasil diproses
                            val intent = Intent(activity, DetailActivity::class.java).apply {
                                // Kirimkan semua data input pengguna
                                putExtra("nitrogen", nitrogen)
                                putExtra("phosphorus", phosphorus)
                                putExtra("potassium", potassium)
                                putExtra("temperature", temperature)
                                putExtra("humidity", humidity)
                                putExtra("pH_Value", pH_Value)
                                putExtra("rainfall", rainfall)

                                // Kirimkan data dari respons API
                                putExtra("soilQuality", response.soilQuality)
                                putExtra("predictions", response.predictions?.rotasi1?.get(0)?.crop)
                                putExtra("confidence", response.predictions?.rotasi1?.get(0)?.confidence.toString())
                            }
                            startActivity(intent)
                        }
                    }
                }

            }
        }
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
