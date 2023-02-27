package com.example.kirichenko_lessons.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kirichenko_lessons.data.model.Country
import com.example.kirichenko_lessons.data.model.Office
import com.example.kirichenko_lessons.databinding.ItemOfficeOneBinding

class OfficeAdapter(
    private var officesList: List<Office>,
    private val listener: onItemOfficeClickListener
) : RecyclerView.Adapter<OfficeAdapter.OfficesViewHolder>() {

    fun setData(offices: List<Office>) {
        officesList = offices
        notifyDataSetChanged()
    }

    inner class OfficesViewHolder(private val binding: ItemOfficeOneBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val office = officesList[position]
                        listener.onVacancyClicked(office, position)
                    }
                }
            }
        }

        fun bind(office: Office){
            binding.apply {
                tvLocation.text = office.location
                tvCountry.text = when(office.country){
                    Country.BELARUS -> "Belarus"
                    Country.RUSSIA -> "Russia"
                    else -> "Kazakhstan"
                }
                ivFlag.setImageResource(office.flag)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfficesViewHolder {
        val binding = ItemOfficeOneBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OfficesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return officesList.size
    }

    override fun onBindViewHolder(holder: OfficesViewHolder, position: Int) {
        val currentOffice = officesList[position]
        holder.bind(currentOffice)
    }

    interface onItemOfficeClickListener {
        fun onVacancyClicked(office: Office, position: Int)
    }
}