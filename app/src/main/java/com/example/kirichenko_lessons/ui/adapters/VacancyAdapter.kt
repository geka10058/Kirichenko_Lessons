package com.example.kirichenko_lessons.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kirichenko_lessons.R
import com.example.kirichenko_lessons.data.model.Vacancy
import com.example.kirichenko_lessons.databinding.ItemVacancyBinding

class VacancyAdapter(
    private var vacancyList: List<Vacancy>,
    private val listener: onItemVacancyClickListener
) : RecyclerView.Adapter<VacancyAdapter.VacancyViewHolder>() {

    fun setData(vacancies: List<Vacancy>) {
        vacancyList = vacancies
        notifyDataSetChanged()
    }

    inner class VacancyViewHolder(private val binding: ItemVacancyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val vacancy = vacancyList[position]
                        listener.onVacancyClicked(vacancy, position)
                    }
                }
            }
        }

        fun bind(vacancy: Vacancy) {
            binding.apply {
                tvTitle.text = vacancy.title
                tvLocation.text = vacancy.location
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        val binding = ItemVacancyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VacancyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return vacancyList.size
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        val currentVacancy = vacancyList[position]
        holder.bind(currentVacancy)
    }

    interface onItemVacancyClickListener {
        fun onVacancyClicked(vacancy: Vacancy, position: Int)
    }
}