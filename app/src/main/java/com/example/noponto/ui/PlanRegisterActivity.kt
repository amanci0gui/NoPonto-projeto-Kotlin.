package com.example.noponto.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.noponto.databinding.ActivityPlanRegisterBinding
import com.example.noponto.databinding.AppBarBinding

class PlanRegisterActivity : BaseActivity() {

    private lateinit var binding: ActivityPlanRegisterBinding
    override val appBarBinding: AppBarBinding
        get() = binding.appBarLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAppBar()
        setupMasks()
        setupValidation()

        binding.buttonCancelarPlano.setOnClickListener {
            finish()
        }

        binding.buttonConfirmarPlano.setOnClickListener {
            // TODO: Implementar a lógica de confirmação do plano
            finish()
        }
    }

    private fun setupMasks() {
        binding.entradaPresencialEditText.addTextChangedListener(Mask.mask("##:##", binding.entradaPresencialEditText))
        binding.saidaPresencialEditText.addTextChangedListener(Mask.mask("##:##", binding.saidaPresencialEditText))
        binding.entradaRemotoEditText.addTextChangedListener(Mask.mask("##:##", binding.entradaRemotoEditText))
        binding.saidaRemotoEditText.addTextChangedListener(Mask.mask("##:##", binding.saidaRemotoEditText))
    }

    private fun setupValidation() {
        binding.buttonConfirmarPlano.isEnabled = false
        binding.buttonConfirmarPlano.alpha = 0.5f

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validateInputs()
            }
        }

        binding.entradaPresencialEditText.addTextChangedListener(textWatcher)
        binding.saidaPresencialEditText.addTextChangedListener(textWatcher)
        binding.entradaRemotoEditText.addTextChangedListener(textWatcher)
        binding.saidaRemotoEditText.addTextChangedListener(textWatcher)

        val checkBoxes = listOf(
            binding.checkPresencialSegunda, binding.checkPresencialTerca, binding.checkPresencialQuarta, binding.checkPresencialQuinta, binding.checkPresencialSexta,
            binding.checkRemotoSegunda, binding.checkRemotoTerca, binding.checkRemotoQuarta, binding.checkRemotoQuinta, binding.checkRemotoSexta
        )

        checkBoxes.forEach { checkBox ->
            checkBox.setOnCheckedChangeListener { _, _ -> validateInputs() }
        }
    }

    private fun validateInputs() {
        val areHorariosFilled = binding.entradaPresencialEditText.text?.length == 5 &&
                binding.saidaPresencialEditText.text?.length == 5 &&
                binding.entradaRemotoEditText.text?.length == 5 &&
                binding.saidaRemotoEditText.text?.length == 5

        val checkBoxes = listOf(
            binding.checkPresencialSegunda, binding.checkPresencialTerca, binding.checkPresencialQuarta, binding.checkPresencialQuinta, binding.checkPresencialSexta,
            binding.checkRemotoSegunda, binding.checkRemotoTerca, binding.checkRemotoQuarta, binding.checkRemotoQuinta, binding.checkRemotoSexta
        )
        val checkedCount = checkBoxes.count { it.isChecked }

        val isEnabled = areHorariosFilled && checkedCount >= 5
        binding.buttonConfirmarPlano.isEnabled = isEnabled
        binding.buttonConfirmarPlano.alpha = if (isEnabled) 1.0f else 0.5f
    }
}