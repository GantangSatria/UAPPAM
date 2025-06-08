package com.gsatria.myapplication.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.gsatria.myapplication.R
import com.gsatria.myapplication.domain.model.Plant

class PlantAdapter(
//    private val plants: List<Plant>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {

    private var plants: List<Plant> = emptyList()

    interface OnItemClickListener {
        fun onDeleteClick(plant: Plant)
        fun onDetailClick(plant: Plant)
    }

    inner class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val plantImage: ImageView = itemView.findViewById(R.id.plant_list_img)
        private val plantName: TextView = itemView.findViewById(R.id.plant_list_name)
        private val plantPrice: TextView = itemView.findViewById(R.id.plant_list_price)
        private val deleteButton: AppCompatButton = itemView.findViewById(R.id.plant_list_delete_btn)
        private val detailButton: AppCompatButton = itemView.findViewById(R.id.plant_list_detail_btn)

        fun bind(plant: Plant) {
            plantName.text = plant.plantName
            plantPrice.text = plant.price
            deleteButton.setOnClickListener { listener.onDeleteClick(plant) }
            detailButton.setOnClickListener { listener.onDetailClick(plant) }
            // plantImage.setImageResource(...)

            deleteButton.setOnClickListener {
                listener.onDeleteClick(plant)
            }
            detailButton.setOnClickListener {
                listener.onDetailClick(plant)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.plant_item, parent, false)
        return PlantViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bind(plants[position])
    }

    override fun getItemCount() = plants.size

    fun submitList(newPlants: List<Plant>) {
        plants = newPlants.toList()
        notifyDataSetChanged()
    }
}