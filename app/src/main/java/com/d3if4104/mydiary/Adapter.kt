package com.d3if4104.mydiary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.d3if4104.diariku.Database.Diari
import com.d3if4104.diariku.convertLongToDateString

class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {
    var data = listOf<Diari>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.diari_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = data.size




    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tanggal: TextView = itemView.findViewById(R.id.tvTanggal)
        val diariIsi: TextView = itemView.findViewById(R.id.tvDiari)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
//        val res = holder.itemView.context.resources
        holder.tanggal.text = (convertLongToDateString(item.tanggalDiari))
        holder.diariIsi.text = item.diari

    }

}