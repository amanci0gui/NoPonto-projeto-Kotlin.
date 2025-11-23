package com.example.noponto

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.widget.doAfterTextChanged
import com.example.noponto.databinding.ActivityRecordBinding
import com.example.noponto.databinding.AppBarBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class RecordActivity : BaseActivity() {

    private lateinit var binding: ActivityRecordBinding
    override val appBarBinding: AppBarBinding
        get() = binding.appBarLayout

    private var lastClickTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAppBar()

        // Disable confirm button initially
        binding.buttonConfirmar.isEnabled = false
        binding.buttonConfirmar.alpha = 0.5f

        // Create dummy data
        val employeeList = listOf(
            EmployeesActivity.Employee("Pedro Henrique de Lima Franca", "111.222.333-44", "pedro@email.com", "Administrador", "Ativo"),
            EmployeesActivity.Employee("Maria Joaquina", "555.666.777-88", "maria.j@email.com", "Desenvolvedor", "Ativo"),
            EmployeesActivity.Employee("José Carlos", "999.000.111-22", "jose.c@email.com", "Designer", "Inativo")
        )

        val employeeNames = employeeList.map { it.name }

        val adapter = ArrayAdapter(this, R.layout.dropdown_item, employeeNames)
        val autoCompleteTextView = (binding.dropdownUsuario.editText as? AutoCompleteTextView)
        autoCompleteTextView?.setAdapter(adapter)

        // Add text changed listeners
        autoCompleteTextView?.doAfterTextChanged {
            validateInputs()
        }
        binding.periodoEditText.doAfterTextChanged {
            validateInputs()
        }


        binding.periodoEditText.setOnClickListener {
            if (SystemClock.elapsedRealtime() - lastClickTime < 2000) {
                return@setOnClickListener
            }
            lastClickTime = SystemClock.elapsedRealtime()
            showDateRangePicker()
        }

        binding.buttonConfirmar.setOnClickListener {
            val selectedEmployeeName = binding.dropdownUsuario.editText?.text.toString()
            val selectedEmployee = employeeList.find { it.name == selectedEmployeeName }
            val selectedPeriod = binding.periodoEditText.text.toString()

            val intent = Intent(this, RecordUserActivity::class.java).apply {
                putExtra("employeeName", selectedEmployee?.name)
                putExtra("employeeRole", selectedEmployee?.role)
                putExtra("period", selectedPeriod)
            }
            startActivity(intent)
        }
    }

    private fun validateInputs() {
        val isUserSelected = binding.dropdownUsuario.editText?.text?.isNotEmpty() == true
        val isPeriodSelected = binding.periodoEditText.text?.isNotEmpty() == true
        val isEnabled = isUserSelected && isPeriodSelected
        binding.buttonConfirmar.isEnabled = isEnabled
        binding.buttonConfirmar.alpha = if (isEnabled) 1.0f else 0.5f
    }

    private fun showDateRangePicker() {
        val dateRangePicker = MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText("Selecione o período")
            .build()

        dateRangePicker.addOnPositiveButtonClickListener {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val startDate = sdf.format(Date(it.first))
            val endDate = sdf.format(Date(it.second))
            binding.periodoEditText.setText("$startDate - $endDate")
        }

        dateRangePicker.show(supportFragmentManager, "DATE_RANGE_PICKER_TAG")
    }
}