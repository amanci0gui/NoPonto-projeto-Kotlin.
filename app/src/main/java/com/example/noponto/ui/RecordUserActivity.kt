package com.example.noponto.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noponto.R
import com.example.noponto.databinding.ActivityRecordUserBinding
import com.example.noponto.databinding.AppBarBinding
import com.example.noponto.databinding.ItemRecordRowBinding

data class ReportEntry(
    val data: String,
    val entryTime: String?,
    val exitTime: String?,
    val pauseCount: Int,
    val registeredHours: String,
    val observations: String?,
    val status: String
)

class RecordUserActivity : BaseActivity() {

    private lateinit var binding: ActivityRecordUserBinding
    override val appBarBinding: AppBarBinding
        get() = binding.appBarLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAppBar()

        val employeeName = intent.getStringExtra("employeeName") ?: getString(R.string.not_informed)
        val employeeRole = intent.getStringExtra("employeeRole") ?: getString(R.string.not_informed)
        val period = intent.getStringExtra("period") ?: "01/01/2024 - 31/01/2024"

        binding.textFuncionario.text = getString(R.string.employee_name_label, employeeName)
        binding.textCargo.text = getString(R.string.employee_role_label, employeeRole)
        binding.textPeriodo.text = "Período: $period"

        val reportEntriesList = listOf(
            ReportEntry("10/06/2025", "08:00", "12:00", 0, "4", null, "Homologado"),
            ReportEntry("11/06/2025", "09:00", "18:00", 1, "8", "Almoço", "Homologado"),
            ReportEntry("12/06/2025", "14:47", "18:00", 1, "-14", "Falta de ponto", "Devendo horas"),
            ReportEntry("15/06/2025", "13:00", "13:15", 0, "0", null, "Devendo horas"),
            ReportEntry("16/06/2025", "07:30", "16:30", 1, "8", null, "Homologado")
        )

        binding.reportRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.reportRecyclerView.adapter = ReportAdapter(reportEntriesList)

        binding.buttonVoltar.setOnClickListener {
            finish()
        }
    }

    inner class ReportAdapter(private val reportEntries: List<ReportEntry>) : RecyclerView.Adapter<ReportAdapter.RowViewHolder>() {

        inner class RowViewHolder(val binding: ItemRecordRowBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
            val binding = ItemRecordRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return RowViewHolder(binding)
        }

        override fun getItemCount() = reportEntries.size

        override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
            val item = reportEntries[position]
            holder.binding.apply {
                reportData.text = item.data
                reportEntrada.text = item.entryTime ?: ""
                reportSaida.text = item.exitTime ?: ""
                reportPausas.text = item.pauseCount.toString()
                reportHorasRegistradas.text = item.registeredHours
                reportObservacoes.text = item.observations ?: ""
                reportHomologacao.text = item.status

                if (item.status == "Devendo horas") {
                    reportHomologacao.setTextColor(Color.RED)
                } else {
                    reportHomologacao.setTextColor(Color.BLACK)
                }
            }
        }
    }
}