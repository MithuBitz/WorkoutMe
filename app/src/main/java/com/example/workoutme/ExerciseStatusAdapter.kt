package com.example.workoutme

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutme.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(val item: ArrayList<ExerciseModel>): RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

    class ViewHolder(val itemBinding: ItemExerciseStatusBinding): RecyclerView.ViewHolder(itemBinding.root){
       val tvItem = itemBinding.itemTV
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: ExerciseModel = item[position]
        holder.tvItem.text = model.getId().toString()

        //Change the background of the recyclerView text background on the basis of selected or completed
        //When
        when {
            //model with isSelected is true then set the background of the text
            model.getIsSelected() -> {
                holder.tvItem.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.item_circular_color_thin_background)
                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }
            //model with isCompleted is true then set the background of the text
            model.getIsCompleted() -> {
                holder.tvItem.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.item_circular_accent_background)
                holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
            }
            //or else
            else -> {
                holder.tvItem.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.item_circular_color_grey_background)
                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }
}