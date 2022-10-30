package com.example.pdfreaderrr.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pdfreaderrr.PdfModel
import com.example.pdfreaderrr.R
import com.example.pdfreaderrr.adaptors.VideosAdaptor
import com.example.pdfreaderrr.interfaces.PdfClickedListener
import com.example.pdfreaderrr.ui.PdfShowingScreen
import com.example.pdfreaderrr.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class HomeFragment : Fragment() {
    var videosAdaptor: VideosAdaptor? = null
    lateinit var  layoutManager: LinearLayoutManager
    private val model: MainViewModel by sharedViewModel()
    private var array:ArrayList<PdfModel> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.files?.observe(requireActivity()) {
            layoutManager= LinearLayoutManager( requireContext(), LinearLayoutManager.VERTICAL, false)
            array.addAll(it)
            videosAdaptor = VideosAdaptor(requireContext(), array, object:PdfClickedListener{
                override fun onPdfCLicked(uri: Uri) {
                    val intent = Intent(requireContext(), PdfShowingScreen::class.java)
                    intent.putExtra("Character", uri.toString())
                    startActivity(intent)
                }


            })
      /*      val dividerItemDecoration = DividerItemDecoration(
                videos.getContext(),
                layoutManager.getOrientation()
            )
            videos.addItemDecoration(dividerItemDecoration)*/
            videos.setLayoutManager(
                layoutManager
            )
            videos.adapter=videosAdaptor
            //Log.e("jdkdjf", "${it.size}")
        }



    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        var root = inflater.inflate(R.layout.fragment_home, container, false)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}