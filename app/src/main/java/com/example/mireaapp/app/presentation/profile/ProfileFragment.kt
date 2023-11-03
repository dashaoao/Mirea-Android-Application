package com.example.mireaapp.app.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.mireaapp.R
import com.example.mireaapp.databinding.FragmentProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel.user) {
            binding.name.text = name
            binding.mail.text = mail
            binding.avatar.load(avatarUri ?: R.drawable.user)
        }
        with(binding) {
            btnSubjects.setOnClickListener { viewModel.onSubjectsClick() }
            btnTeachers.setOnClickListener { viewModel.onTeachersClick() }
            btnSettings.setOnClickListener { viewModel.onSettingsClick() }
        }
    }
}