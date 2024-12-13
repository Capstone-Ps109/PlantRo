package com.example.plantro.ui.history

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingevent.databinding.ItemHistoryBinding
import com.example.plantro.data.remote.response.HistoryItem
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class HistoryAdapter(private val onItemClick: (HistoryItem) -> Unit) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var items: List<HistoryItem> = emptyList()

    fun submitList(newItems: List<HistoryItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

        // Set listener untuk item klik
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = items.size

    class HistoryViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: HistoryItem) {
            // Set data untuk tampilan item
            binding.tvTimeStamp.text = formatTimestamp(item.timestamp)

            // Set predictions pertama
            val prediction1 = item.predictions?.rotasi1?.getOrNull(0)?.let {
                "${it.crop} (${String.format("%.2f", it.confidence ?: 0.0)})"
            } ?: "No Data"

            // Set predictions kedua
            val prediction2 = item.predictions?.rotasi1?.getOrNull(1)?.let {
                "${it.crop} (${String.format("%.2f", it.confidence ?: 0.0)})"
            } ?: "No Data"

            // Set predictions ketiga
            val prediction3 = item.predictions?.rotasi1?.getOrNull(2)?.let {
                "${it.crop} (${String.format("%.2f", it.confidence ?: 0.0)})"
            } ?: "No Data"

            binding.tvPredictions.text = "$prediction1\n$prediction2\n$prediction3"
            val soilQuality = item.soilQuality
            binding.tvSoilQuality.text = "Kondisi tanah: $soilQuality"
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun formatTimestamp(timestamp: String?): String {
            return try {
                // Parse timestamp ke ZonedDateTime
                val zonedDateTime = ZonedDateTime.parse(timestamp)

                // Konversi zona waktu ke GMT+7 (Asia/Jakarta)
                val jakartaTime = zonedDateTime.withZoneSameInstant(java.time.ZoneId.of("Asia/Jakarta"))

                // Format waktu ke bentuk yang diinginkan
                val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm", Locale.getDefault())
                jakartaTime.format(formatter)
            } catch (e: Exception) {
                // Jika parsing gagal, kembalikan timestamp asli
                timestamp ?: "Invalid Date"
            }
        }
    }
}