package com.example.chyungboka

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_classify.*
import kotlinx.android.synthetic.main.fragment_classify.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [ClassifyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ClassifyFragment : Fragment() {
    var mainActivity:MainActivity?=null
    var mContext: Context?=null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity=context as MainActivity
        mContext=context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_classify,container,false)

        var target_data=listOf("- 선택하세요 -","해당없음","여성","임산부","장애","국가유공자 등 보훈 대상자","실업자")
        var adapter1=ArrayAdapter<String>(view.context,android.R.layout.simple_list_item_1,target_data)
        view.target_spinner.adapter=adapter1
        return view
    }
}