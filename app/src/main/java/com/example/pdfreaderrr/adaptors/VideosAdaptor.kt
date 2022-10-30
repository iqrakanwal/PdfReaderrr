package com.example.pdfreaderrr.adaptors

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pdfreaderrr.PdfModel
import com.example.pdfreaderrr.R
import com.example.pdfreaderrr.interfaces.ButtonClick
import com.example.pdfreaderrr.interfaces.PdfClickedListener

class VideosAdaptor(var context: Context, var arrayList: ArrayList<PdfModel>, var buttonClick: PdfClickedListener) :
    RecyclerView.Adapter<VideosAdaptor.VideoItem>() {
    inner class VideoItem(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.name)
        var size: TextView = view.findViewById(R.id.size)
        var container: View? = view

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoItem {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.pdf_items, parent, false)
        return VideoItem(view)
    }

    override fun onBindViewHolder(holder: VideoItem, position: Int) {
        holder.name.text = arrayList.get(position).name
        holder.size.text = arrayList.get(position).size.toString()
        holder.name?.setOnClickListener {
            buttonClick.onPdfCLicked(Uri.parse(arrayList?.get(position).path))
        }


    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}