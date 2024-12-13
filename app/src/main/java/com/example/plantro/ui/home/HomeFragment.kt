package com.example.plantro.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import java.util.Locale
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingevent.R
import com.example.plantro.data.BarangAdapter
import com.example.plantro.data.BarangData


class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var bList = ArrayList<BarangData>()
    private lateinit var adapter: BarangAdapter

    @SuppressLint("DetachAndAttachSameFragment")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.rvBarang)
        searchView = view.findViewById(R.id.searchView)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        addDataToList()
        adapter = BarangAdapter(bList)
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })

        return view
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<BarangData>()
            for (i in bList) {
                if (i.title.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT))) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(requireContext(), "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun addDataToList() {
        bList.add(BarangData("Benih Jagung", R.drawable.bibit_benih_jagung,"BENIH JAGUNG MPM", "Rp 46.000","https://shopee.co.id/product/59245300/21460629106?gads_t_sig=VTJGc2RHVmtYMTlxTFVSVVRrdENkVzBLS2xuUGZzMlQ5NjlFWklmRkZjUXNtc0xXMm9RSjVsQ2x1blNsck04WnIwNkkzcVNQVTZQY083a2FmZWtQQTlPMlYyN0MwM1dZckx3TkxFVGZJVUpYWG1sM2NPUjNydTgyNXdINjZyT08&gad_source=1&gclid=CjwKCAiA6aW6BhBqEiwA6KzDc__KUr1EB9kpZHjD9oy0GSeeC4A3daEGgDv4e8oOnG_iH3_esTXO9BoCJNMQAvD_BwE"))
        bList.add(BarangData("Benih Padi Anti Burung", R.drawable.bibit_benih_padi,"HulwaAyya", "Rp 120.000","https://shopee.co.id/product/222959062/9653093504?gads_t_sig=VTJGc2RHVmtYMTlxTFVSVVRrdENkVzBLS2xuUGZzMlQ5NjlFWklmRkZjUW0rZm1GTDBpRXRUbHB5S0MvRXZUWC92bnNoajczR2o4TFdUTkxtZ1NMY3FuWjFTRlkxQUNSQ3dEUXZLVWZOeXRMY0dtSXZOZGFCdUNobW1ucUdCMjQ&gad_source=1&gclid=CjwKCAiA6aW6BhBqEiwA6KzDc-QQsOvRMf6YtVbcKZ9vQQOrwHkD8O9WZO6L7c6OkZCM0oQWsZL54xoCcLQQAvD_BwE"))
        bList.add(BarangData("Kacang Lentil Hitam", R.drawable.blackgram,"Berkat Foods", "Rp 23.500","https://shopee.co.id/Black-Urad-Whole-Dal-Dhal-Daal-Black-Lentil-Kacang-Hitam-i.115152414.9762539519?sp_atk=66d60c66-d53d-4567-b4c4-975184a65121&xptdk=66d60c66-d53d-4567-b4c4-975184a65121"))
        bList.add(BarangData("Benih Kacang Tanah 1 KG", R.drawable.bibit_benih_kacang_tanah,"Benih Dinda farm", "Rp 72.000","https://shopee.co.id/Benih-Kacang-Tanah-Hibrida-Kulit-Merah-1kg-Benih-Sayuran-Super-i.441018096.19627502425?sp_atk=5b1ce69c-26ea-4490-bfab-9c58d85a780e&xptdk=5b1ce69c-26ea-4490-bfab-9c58d85a780e"))
        bList.add(BarangData("Benih Kacang Hijau 1 KG", R.drawable.bibit_benih_kacang_hijau,"Toko.sukses.tani", "Rp 45.905","https://shopee.co.id/Benih-Kacang-Hijau-Unggul-PERTIWI-Varietas-Vima-5-kemasan-1kg-dari-PERTIWI-i.231758580.21703409585?sp_atk=31c4d991-df0e-43dd-bc72-ca2c08ddbe5a&xptdk=31c4d991-df0e-43dd-bc72-ca2c08ddbe5a"))
        bList.add(BarangData("Benih Kacang Gude 10 butir", R.drawable.bibit_benih_kacang_gude,"Winner Official Store", "Rp 2.500","https://shopee.co.id/5-biji-benih-pigeon-pea-cajanus-cajan-kacang-gude-kayo-kayu-bali-hiris-kance-undis-turis-binatung-i.104438597.6018349762?stm_medium=referral&stm_source=rw"))



    }
}
