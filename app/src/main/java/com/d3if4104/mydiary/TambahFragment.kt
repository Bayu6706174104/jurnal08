package com.d3if4104.diariku


import android.app.Application
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.d3if4104.diariku.Database.DiariDatabase
import com.d3if4104.diariku.Database.DiariDatabaseDao
import com.d3if4104.diariku.databinding.FragmentAddBinding
import com.d3if4104.mydiary.DiariViewModel
import com.d3if4104.mydiary.DiariViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class TambahFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var application: Application
    private lateinit var dataSource: DiariDatabaseDao
    private lateinit var viewModelFactory: DiariViewModelFactory
    private lateinit var diaryViewModel: DiariViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tambah, container, false)
        application = requireNotNull(this.activity).application
        dataSource = DiariDatabase.getInstance(application).diariDatabaseDao
        viewModelFactory = DiariViewModelFactory(dataSource, application)
        diaryViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DiariViewModel::class.java)

        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_save -> {
                diaryViewModel.onClickTambah(binding.etIsi.text.toString())
                view?.findNavController()?.navigate(R.id.action_addFragment_to_mainFragment)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_check, menu)
    }
}
