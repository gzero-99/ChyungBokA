package com.example.chyungboka

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //viewpager 설정
    var currentPosition =0
    //핸들러 설정
    //ui 변경하기
    val handler= Handler(Looper.getMainLooper()){
        setPage()
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.fragment_home)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        //어댑터 연결하기
        val adapter=ViewPagerAdapter()
        pager.adapter=adapter

        //버튼 클릭시 2page로
        nextButton.setOnClickListener {
            currentPosition=2
            pager.setCurrentItem(2, true)
        }

        //뷰페이저 넘기는 쓰레드
        val thread=Thread(PagerRunnable())
        thread.start()
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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}