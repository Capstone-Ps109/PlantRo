package com.example.plantro.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.dicodingevent.databinding.FragmentProfileBinding
import com.example.plantro.data.pref.UserPreference
import com.example.plantro.data.pref.UserRepository
import com.example.plantro.data.pref.dataStore
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel

        val userRepository = UserRepository.getInstance(
            UserPreference.getInstance(requireContext().dataStore)
        )

        profileViewModel = ViewModelProvider(this, ProfileViewModelFactory(userRepository))
            .get(ProfileViewModel::class.java)
        binding.buttonLogout.setOnClickListener {
            profileViewModel.logout()
        }

        profileViewModel.email.observe(viewLifecycleOwner) { email ->
            binding.textviewEmail.text = email
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}