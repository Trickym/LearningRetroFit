package com.example.retrofitdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterData(private val dataList : List<DemoDataItem>):RecyclerView.Adapter<AdapterData.ViewHolderData>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterData.ViewHolderData {
        val demoDataItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item,parent,false)
        return  ViewHolderData(demoDataItem)
    }

    override fun onBindViewHolder(holder: AdapterData.ViewHolderData, position: Int) {
        val currentDataItem = dataList[position]
        holder.userId.text = "User Id : ${currentDataItem.userId}"
        holder.dataId.text = "Data Id : ${currentDataItem.id}"
        holder.title.text = "Title : ${currentDataItem.getUniqueName(currentDataItem.id,currentDataItem.title)}"
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolderData(dataItem: View):RecyclerView.ViewHolder(dataItem) {
        val userId:TextView = dataItem.findViewById(R.id.user_id)
        val dataId:TextView = dataItem.findViewById(R.id.data_id)
        val title : TextView = dataItem.findViewById(R.id.title)

    }

}