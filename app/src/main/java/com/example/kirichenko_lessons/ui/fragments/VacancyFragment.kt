package com.example.kirichenko_lessons.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kirichenko_lessons.R
import com.example.kirichenko_lessons.data.model.Vacancy
import com.example.kirichenko_lessons.databinding.FragmentVacancyBinding
import com.example.kirichenko_lessons.ui.adapters.VacancyAdapter

class VacancyFragment : Fragment(R.layout.fragment_vacancy),
    VacancyAdapter.onItemVacancyClickListener {

    lateinit var rvAdapter: VacancyAdapter
    lateinit var listOfVacancies: List<Vacancy>
    private var _binding: FragmentVacancyBinding? = null
    private val binding get() = requireNotNull(_binding)


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

        //_binding = FragmentVacancyBinding.bind(view)

        listOfVacancies = createVacancyList()
        rvAdapter = VacancyAdapter(listOfVacancies,this)

        binding.apply {
            rvVacancy.apply {
                adapter = rvAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
    }

    private fun createVacancyList(): List<Vacancy> {
        return listOf(
            Vacancy("Senior Python Developer", "Gomel", "description", LANG_PYTHON, LVL_SENIOR),
            Vacancy("AQA Engineer (Python)", "Moskow", "description", LANG_PYTHON, LVL_SENIOR),
            Vacancy("Junior Manual QA Engineer","Sankt-Petersburg","description", LANG_PYTHON, LVL_JUNIOR),
            Vacancy("Middle QA Engineer", "Vitebsk", "description", LANG_PYTHON, LVL_MIDDLE),
            Vacancy("Senior OpenTelemetry Developer", "Minsk", "description", LANG_NET, LVL_SENIOR),
            Vacancy("Senior Python Developer", "Vologda", "description", LANG_PYTHON, LVL_SENIOR),
            Vacancy("Front-end Developer", "Gomel", "description", LANG_JS, LVL_MIDDLE),
            Vacancy("Middle+ Full-Stack Developer", "Gomel", "description", LANG_NET, LVL_MIDDLE),
            Vacancy("Senior / Lead full-stack engineer with strong React", "Moskow", "description", LANG_JS, LVL_SENIOR),
            Vacancy("Senior Python Developer", "Gomel", "description", LANG_PYTHON, LVL_SENIOR),
            Vacancy("Senior Python Developer", "Gomel", "description", LANG_PYTHON, LVL_SENIOR),
            Vacancy("AQA Engineer (Python)", "Moskow", "description", LANG_PYTHON, LVL_SENIOR),
            Vacancy("Junior Manual QA Engineer","Sankt-Petersburg","description", LANG_PYTHON, LVL_JUNIOR),
            Vacancy("Middle QA Engineer", "Vitebsk", "description", LANG_PYTHON, LVL_MIDDLE),
            Vacancy("Senior OpenTelemetry Developer", "Minsk", "description", LANG_NET, LVL_SENIOR),
            Vacancy("Senior Python Developer", "Vologda", "description", LANG_PYTHON, LVL_SENIOR),
            Vacancy("Front-end Developer", "Gomel", "description", LANG_JS, LVL_MIDDLE),
            Vacancy("Middle+ Full-Stack Developer", "Gomel", "description", LANG_NET, LVL_MIDDLE),
            Vacancy("Senior / Lead full-stack engineer with strong React", "Moskow", "description", LANG_JS, LVL_SENIOR),
            Vacancy("Senior Python Developer", "Gomel", "description", LANG_PYTHON, LVL_SENIOR),
        )
    }

    override fun onVacancyClicked(vacancy: Vacancy, position: Int) {
        Toast.makeText(requireContext(), "Vacancy '${vacancy.title}' clicked", Toast.LENGTH_SHORT)
            .show()
    }

    companion object {

        const val LANG_PYTHON = "Python"
        const val LANG_NET = ".NET/C#"
        const val LANG_JS = "Front-end/JS"
        const val LVL_JUNIOR = "Junior"
        const val LVL_MIDDLE = "Middle"
        const val LVL_SENIOR = "Senior"

    }
}