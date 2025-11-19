package com.example.noponto

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.noponto.databinding.FragmentMenuBinding

class MenuDialogFragment : DialogFragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the style for the dialog to be fullscreen
        setStyle(STYLE_NORMAL, R.style.Theme_NoPonto_FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // --- Menu Navigation ---

        binding.menuHome.setOnClickListener {
            // Navigate to WelcomeActivity, clearing other activities on top of it.
            val intent = Intent(requireContext(), WelcomeActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
            dismiss()
        }

        binding.menuRegistrarPonto.setOnClickListener {
            val intent = Intent(requireContext(), RegisterActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
            dismiss()
        }

        binding.menuFuncionarios.setOnClickListener {
            val intent = Intent(requireContext(), EmployeesActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
            dismiss()
        }

        binding.menuRelatorios.setOnClickListener {
            val intent = Intent(requireContext(), RecordActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
            dismiss()
        }

        // Set up the close button
        binding.menuFechar.setOnClickListener {
            dismiss() // Close the dialog
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
