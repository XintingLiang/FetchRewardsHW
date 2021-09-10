package xinting.liang.fetchrewardshomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import xinting.liang.fetchrewardshomework.databinding.ActivityMainBinding
import xinting.liang.fetchrewardshomework.viewModel.DataViewModel

class MainActivity : AppCompatActivity() {

    lateinit var dataViewModel: DataViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataViewModel = ViewModelProvider(this).get(DataViewModel::class.java)

        dataViewModel.data.observe(this, Observer {
        })

    }
}