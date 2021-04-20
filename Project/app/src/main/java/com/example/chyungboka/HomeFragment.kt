package com.example.chyungboka

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    //viewpager 설정
    var currentPosition =0
    var mainActivity:MainActivity?=null
    var mContext: Context?=null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity=context as MainActivity
        mContext=context
    }

    //핸들러 설정
    //ui 변경하기
    val handler= Handler(Looper.getMainLooper()){
        setPage()
        true
    }

    //페이지 변경하기
    fun setPage(){
        if(currentPosition==5) currentPosition=0
        pager.setCurrentItem(currentPosition,true)
        currentPosition+=1
    }

    //2초 마다 페이지 넘기기
    inner class PagerRunnable:Runnable{
        override fun run() {
            while(true){
                Thread.sleep(2000)
                handler.sendEmptyMessage(0)
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_home,container,false)
        //어댑터 연결하기
        val adapter=ViewPagerAdapter()
        view.pager.adapter=adapter

        //버튼 클릭시 2page로
        view.nextButton.setOnClickListener {
            currentPosition=2
            view.pager.setCurrentItem(2, true)
        }

        //뷰페이저 넘기는 쓰레드
        val thread=Thread(PagerRunnable())
        thread.start()
        return view
    }
}