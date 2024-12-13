package com.example.plantro.data

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingevent.R

class BarangAdapter (var bList: List<BarangData>) : RecyclerView.Adapter<BarangAdapter.BarangViewHolder>() {

    inner class BarangViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logo : ImageView = itemView.findViewById(R.id.logoIv)
        val titleTv : TextView = itemView.findViewById(R.id.titleTv)
        val tokoTv : TextView =  itemView.findViewById(R.id.tokoTv)
        val hargaTv : TextView =  itemView.findViewById(R.id.hargaTv)
        val linkButton: Button = itemView.findViewById(R.id.linkButton)


    }

    @SuppressLint("NotifyDataSetChanged")
    fun setFilteredList(bList: List<BarangData>){
        this.bList = bList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_barang , parent , false)
        return BarangViewHolder(view)
    }
    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        holder.logo.setImageResource(bList[position].logo)
        holder.titleTv.text = bList[position].title
        holder.tokoTv.text = bList[position].toko
        holder.hargaTv.text = bList[position].harga
        holder.linkButton.setOnClickListener {
            // Aksi untuk membuka tautan atau melakukan sesuatu
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(bList[position].link))
            it.context.startActivity(intent)


        }
    }

    override fun getItemCount(): Int {
        return bList.size
    }

}