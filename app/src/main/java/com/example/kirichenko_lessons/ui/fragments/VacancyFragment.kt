package com.example.kirichenko_lessons.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kirichenko_lessons.R
import com.example.kirichenko_lessons.data.model.Vacancy
import com.example.kirichenko_lessons.databinding.FragmentVacancyBinding
import com.example.kirichenko_lessons.ui.adapters.VacancyAdapter
import java.util.*

class VacancyFragment : Fragment(R.layout.fragment_vacancy),
    VacancyAdapter.onItemVacancyClickListener {

    lateinit var rvAdapter: VacancyAdapter
    lateinit var listOfVacancies: List<Vacancy>
    private var _binding: FragmentVacancyBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var searchString = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentVacancyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listOfVacancies = createVacancyList()
        rvAdapter = VacancyAdapter(listOfVacancies, this)

        binding.apply {

            rvVacancy.apply {
                adapter = rvAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }

            etFilter.addTextChangedListener {
                searchString = it.toString()
                val newFilteredList = filterVacancyList(listOfVacancies, searchString)
                rvAdapter.setData(newFilteredList!!)
            }
        }
    }

    private fun createVacancyList(): List<Vacancy> {
        return listOf(
            Vacancy("Senior Python Developer", "Gomel", "description", TECH_PYTHON, LVL_SENIOR),
            Vacancy("AQA Engineer (Python)", "Moskow", "description", TECH_PYTHON, LVL_SENIOR),
            Vacancy(
                "Junior Manual QA Engineer",
                "Sankt-Petersburg",
                "description",
                TECH_PYTHON,
                LVL_JUNIOR
            ),
            Vacancy("Middle QA Engineer", "Vitebsk", "description", TECH_PYTHON, LVL_MIDDLE),
            Vacancy("Senior OpenTelemetry Developer", "Minsk", "description", TECH_NET, LVL_SENIOR),
            Vacancy("Senior Python Developer", "Vologda", "description", TECH_PYTHON, LVL_SENIOR),
            Vacancy("Front-end Developer", "Gomel", "description", TECH_JS, LVL_MIDDLE),
            Vacancy("Middle+ Full-Stack Developer", "Gomel", "description", TECH_NET, LVL_MIDDLE),
            Vacancy("Middle Android Developer", "Gomel", "description", TECH_ANDROID, LVL_MIDDLE),
            Vacancy(
                "Senior / Lead full-stack engineer with strong React",
                "Moskow",
                "description",
                TECH_JS,
                LVL_SENIOR
            ),
            Vacancy("Senior Python Developer", "Gomel", "description", TECH_PYTHON, LVL_SENIOR),
            Vacancy("Senior Python Developer", "Gomel", "description", TECH_PYTHON, LVL_SENIOR),
            Vacancy("AQA Engineer (Python)", "Moskow", "description", TECH_PYTHON, LVL_SENIOR),
            Vacancy(
                "Junior Manual QA Engineer",
                "Sankt-Petersburg",
                "description",
                TECH_PYTHON,
                LVL_JUNIOR
            ),
            Vacancy("Middle QA Engineer", "Vitebsk", "description", TECH_PYTHON, LVL_MIDDLE),
            Vacancy("Senior OpenTelemetry Developer", "Minsk", "description", TECH_NET, LVL_SENIOR),
            Vacancy("Senior Python Developer", "Vologda", "description", TECH_PYTHON, LVL_SENIOR),
            Vacancy("Front-end Developer", "Gomel", "description", TECH_JS, LVL_MIDDLE),
            Vacancy("Middle+ Full-Stack Developer", "Gomel", "description", TECH_NET, LVL_MIDDLE),
            Vacancy(
                "Senior / Lead full-stack engineer with strong React",
                "Moskow",
                "description",
                TECH_JS,
                LVL_SENIOR
            ),
            Vacancy("Senior Python Developer", "Gomel", "description", TECH_PYTHON, LVL_SENIOR),
        )
    }

    private fun filterVacancyList(vacancyList: List<Vacancy>, filterBy: String): List<Vacancy>? {
        val list: List<Vacancy> = vacancyList.filter {
            it.technology.uppercase(Locale.getDefault())
                .contains(filterBy.uppercase(Locale.getDefault()))
        }
        return list
    }

    override fun onVacancyClicked(vacancy: Vacancy, position: Int) {
        Toast.makeText(requireContext(), "Vacancy '${vacancy.title}' clicked", Toast.LENGTH_SHORT)
            .show()
    }

    companion object {

        const val TECH_PYTHON = "Python"
        const val TECH_NET = ".NET/C#"
        const val TECH_JS = "Front-end/JS"
        const val TECH_ANDROID = "Android"
        const val LVL_JUNIOR = "Junior"
        const val LVL_MIDDLE = "Middle"
        const val LVL_SENIOR = "Senior"

    }
}