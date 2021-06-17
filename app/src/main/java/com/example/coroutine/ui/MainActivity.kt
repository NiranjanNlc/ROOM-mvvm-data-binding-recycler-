package com.example.coroutine.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutine.GirlsApplication
import com.example.coroutine.R
import com.example.coroutine.databinding.ActivityMainBinding
import com.example.coroutine.modal.Repository.GirlsRepository
import com.example.coroutine.modal.database.GirlssDataBase
import com.example.coroutine.viewmodal.SampleViewModal
import com.example.coroutine.viewmodal.ViewModalFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var sampleViewModal: SampleViewModal
    private val adapter: GirlsAdapter = GirlsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding   = DataBindingUtil.setContentView(this, R.layout.activity_main)
         bindData()
        initRecyclerView()
        observeChange()
    }

    private fun initRecyclerView()
    {
        print(" recycler view initiated")
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.setHasFixedSize(true)
        binding.recyclerview.adapter=   adapter
        adapter.submitList(sampleViewModal.girls.value)
     }

    private fun observeChange()
    {
        sampleViewModal.girls.observe(this, Observer {
            println(" Observed "+it.toString())
            adapter.submitList(it)
        })
        println("After observing0")
    }

    fun bindData()
    {
        initialiseSampleViewModal()
        println(" Sample ViewModal  views "+ sampleViewModal.girls.value.toString())
        binding.model = sampleViewModal
        binding.lifecycleOwner=this
    }

    private fun initialiseSampleViewModal() {
        val applicationScope = CoroutineScope(SupervisorJob())
        val database = GirlssDataBase.getDatabase(this, applicationScope)
        val repository =GirlsRepository(database.girldDao())
        sampleViewModal= ViewModalFactory( repository).create(SampleViewModal::class.java)
    }


}
