package com.example.noponto

import android.os.Bundle
import com.example.noponto.databinding.ActivityRecordBinding
import com.example.noponto.databinding.AppBarBinding

class RecordActivity : BaseActivity() {

    private lateinit var binding: ActivityRecordBinding
    override val appBarBinding: AppBarBinding
        get() = binding.appBarLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAppBar()

    }
}