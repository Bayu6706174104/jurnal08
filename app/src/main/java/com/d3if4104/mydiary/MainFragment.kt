package com.d3if4104.mydiary


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.d3if4104.diariku.Database.DiariDatabase
//import com.d3if4104.diariku.databinding.FragmentMainBinding
import com.d3if4104.mydiary.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater,R.layout.fragment_main, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = DiariDatabase.getInstance(application).diariDatabaseDao
        val viewModelFactory = DiariViewModelFactory(dataSource, application)
        val adapter = Adapter()

        binding.diariList.adapter = adapter


        val diariViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(DiariViewModel::class.java)

        binding.diariViewModel = diariViewModel

        binding.setLifecycleOwner(this)

        diariViewModel.diari.observe(viewLifecycleOwner, Observer { hasil ->
            hasil?.let {
                adapter.data = hasil

            }
        })


        binding.floatingActionButton.setOnClickListener{ view : View ->
        view.findNavController().navigate(R.id.action_mainFragment_to_addFragment)
        }

        setHasOptionsMenu(true)
        return binding.root



    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)
    }




}
