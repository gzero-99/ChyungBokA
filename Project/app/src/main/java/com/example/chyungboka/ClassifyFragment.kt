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

        //대상 특성 어댑터 연결
        var target_data=resources.getStringArray(R.array.target)
        var adapter1=ArrayAdapter<String>(view.context,android.R.layout.simple_list_item_1,target_data)
        view.target_spinner.adapter=adapter1

        //가구유형 어댑터 연결
        var adapter2=ArrayAdapter<String>(view.context,android.R.layout.simple_list_item_1,resources.getStringArray(R.array.household))
        view.house_spinner.adapter=adapter2

        //분야 어댑터 연결
        var adapter3=ArrayAdapter<String>(view.context,android.R.layout.simple_list_item_1,resources.getStringArray(R.array.field))
        view.field_spinner.adapter=adapter3

        return view
    }
}