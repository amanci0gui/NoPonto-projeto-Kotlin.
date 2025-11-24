package com.example.noponto.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.example.noponto.R
import com.example.noponto.databinding.ActivityClockInBinding
import com.example.noponto.databinding.AppBarBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class ClockInActivity : BaseActivity() {

    private lateinit var binding: ActivityClockInBinding
    override val appBarBinding: AppBarBinding
        get() = binding.appBarLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClockInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAppBar()
        setupInitialValues()
        setupInputMasks()
        setupDropdown()
        setupValidation()

        binding.buttonCancelar.setOnClickListener {
            finish()
        }

        binding.buttonConfirmar.setOnClickListener {
            if (validateFinalInputs()) {
                // TODO: Implementar a lógica de confirmação
                Toast.makeText(this, "Ponto registrado com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun setupInitialValues() {
        val brazilTimeZone = TimeZone.getTimeZone("America/Sao_Paulo")
        val sdfDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).apply { timeZone = brazilTimeZone }
        val sdfTime = SimpleDateFormat("HH:mm", Locale.getDefault()).apply { timeZone = brazilTimeZone }

        binding.inputData.editText?.setText(sdfDate.format(Date()))
        binding.inputHora.editText?.setText(sdfTime.format(Date()))
    }

    private fun setupInputMasks() {
        binding.inputData.editText?.addTextChangedListener(Mask.mask("##/##/####", binding.inputData.editText!!))
        binding.inputHora.editText?.addTextChangedListener(Mask.mask("##:##", binding.inputHora.editText!!))
    }

    private fun setupDropdown() {
        val pointTypes = arrayOf("Entrada", "Saída", "Início do intervalo", "Fim do intervalo")
        val adapter = ArrayAdapter(this, R.layout.dropdown_item, pointTypes)
        (binding.dropdownTipoPonto.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    private fun setupValidation() {
        binding.buttonConfirmar.isEnabled = false
        binding.buttonConfirmar.alpha = 0.5f

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validateInputs()
            }
        }

        binding.inputData.editText?.addTextChangedListener(textWatcher)
        binding.inputHora.editText?.addTextChangedListener(textWatcher)
        (binding.dropdownTipoPonto.editText as? AutoCompleteTextView)?.addTextChangedListener(textWatcher)
    }

    private fun validateInputs() {
        val isDateValid = binding.inputData.editText?.text.toString().length == 10
        val isTimeValid = binding.inputHora.editText?.text.toString().length == 5
        val isPointTypeValid = (binding.dropdownTipoPonto.editText as? AutoCompleteTextView)?.text.toString().isNotEmpty()

        val allFieldsValid = isDateValid && isTimeValid && isPointTypeValid

        binding.buttonConfirmar.isEnabled = allFieldsValid
        binding.buttonConfirmar.alpha = if (allFieldsValid) 1.0f else 0.5f
    }

    private fun validateFinalInputs(): Boolean {
        binding.inputData.error = null
        binding.inputHora.error = null
        binding.dropdownTipoPonto.error = null

        var isValid = true
        if (binding.inputData.editText?.text.toString().length != 10) {
            binding.inputData.error = " "
            isValid = false
        }
        if (binding.inputHora.editText?.text.toString().length != 5) {
            binding.inputHora.error = " "
            isValid = false
        }
        if ((binding.dropdownTipoPonto.editText as? AutoCompleteTextView)?.text.toString().isEmpty()) {
            binding.dropdownTipoPonto.error = " "
            isValid = false
        }
        return isValid
    }
}