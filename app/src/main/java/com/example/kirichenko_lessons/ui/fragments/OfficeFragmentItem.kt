package com.example.kirichenko_lessons.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kirichenko_lessons.R
import com.example.kirichenko_lessons.Utils
import com.example.kirichenko_lessons.data.model.Country
import com.example.kirichenko_lessons.data.model.Office
import com.example.kirichenko_lessons.databinding.FragmentHomeBinding
import com.example.kirichenko_lessons.databinding.FragmentOfficeDescriptionBinding

class OfficeFragmentItem : Fragment(R.layout.fragment_office_description) {

    private var _binding: FragmentOfficeDescriptionBinding? = null
    private val binding get() = requireNotNull(_binding)
    var officeArgs: Office? = null
    var location = ""
    var flag = 0
    var numOfWor = 0
    var country = ""
    lateinit var args: Bundle


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        /*if (arguments != null) {
            val args = arguments
            val country = when(args?.getString(Utils.COUNTRY)) {
                "Belarus" -> Country.BELARUS
                "Russia" -> Country.RUSSIA
                else -> Country.KAZAKHSTAN
            }
            if (args != null) {
                officeArgs = Office(
                    args.getString(Utils.LOCATION, ""),
                    args.getInt(Utils.FLAG, R.drawable.flag_belarus),
                    args.getInt(Utils.NUMBER_OF_WORKERS, 0),
                    country, 1)
            }
        }*/
        _binding = FragmentOfficeDescriptionBinding.inflate(inflater, container, false)
        args = this.requireArguments()
        location = args.getString(Utils.LOCATION)!!
        flag = args.getInt(Utils.FLAG)
        numOfWor = args.getInt(Utils.NUMBER_OF_WORKERS)
        country = args.getString(Utils.COUNTRY)!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*officeArgs.let {office ->
            binding.apply {
                val country = when(officeArgs!!.country){
                    Country.BELARUS -> "Belarus"
                    Country.RUSSIA -> "Russia"
                    else -> "Kazakhstan"
                }
                tvCountry.text = "Country: $country"
                tvLocation.text =" Location: ${officeArgs?.location}"
                tvNumbersOfWorkers.text = "Numbers of workers: ${officeArgs?.numberOfWorkers}"
            }
        }*/

        binding.apply {
            tvCountry.text = "Country: $country"
            tvLocation.text = " Location: $location"
            tvNumbersOfWorkers.text = "Numbers of workers: $numOfWor"
            ivFlag.setImageResource(flag)
        }

    }

    companion object {

        /*fun newInstance(office: Office): OfficeFragmentItem {
            val arguments = Bundle().apply {
                putAll(Bundle())
            }
            val fragment = OfficeFragmentItem()
            fragment.arguments = arguments
            return fragment
        }*/
    }
}