package com.example.kirichenko_lessons.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kirichenko_lessons.R
import com.example.kirichenko_lessons.Utils
import com.example.kirichenko_lessons.data.model.Country
import com.example.kirichenko_lessons.data.model.Office
import com.example.kirichenko_lessons.databinding.FragmentOfficesBinding
import com.example.kirichenko_lessons.ui.adapters.OfficeAdapter

class OfficesFragment:Fragment(R.layout.fragment_offices), OfficeAdapter.onItemOfficeClickListener {

    private var _binding: FragmentOfficesBinding? = null
    private val binding get() = requireNotNull(_binding)
    lateinit var rvAdapter: OfficeAdapter
    lateinit var listOfOffices:List<Office>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOfficesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listOfOffices = createOfficeList()
        rvAdapter = OfficeAdapter(listOfOffices,this)

        binding.apply {

            rvOffices.apply {
                adapter = rvAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
    }

    private fun createOfficeList():List<Office>{
        return listOf(
            Office("Gomel", R.drawable.flag_belarus,100,Country.BELARUS,1),
            Office("Moscow", R.drawable.flag_russia,250,Country.RUSSIA,2),
            Office("Vitebsk", R.drawable.flag_belarus,150,Country.BELARUS,1),
            Office("Astana", R.drawable.flag_kazakhstan,450,Country.KAZAKHSTAN,2),
        )
    }

    override fun onVacancyClicked(office: Office, position: Int) {
        Log.d("TAG", office.toString())

        val bundle = Bundle()
        //args.putParcelable("Office", office)
        bundle.putString(Utils.LOCATION, office.location)
        bundle.putInt(Utils.FLAG, office.flag)
        bundle.putInt(Utils.NUMBER_OF_WORKERS, office.numberOfWorkers)
        val country = when(office.country){
            Country.BELARUS -> "Belarus"
            Country.RUSSIA -> "Russia"
            else -> "Kazakhstan"
        }
        bundle.putString(Utils.COUNTRY, country)
        val officeFragmentItem = OfficeFragmentItem()
        officeFragmentItem.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container_main, officeFragmentItem)
            .addToBackStack(null)
            .commit()

        /*childFragmentManager.beginTransaction()
            .replace(R.id.container_fragment, OfficeFragmentItem.newInstance(office))
            .addToBackStack(null)
            .commit()
        requireActivity().setTitle(office.location)*/
    }
}