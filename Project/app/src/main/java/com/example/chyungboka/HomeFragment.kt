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
// 데이터 리소스로부터 페이지뷰를 생성하는 코드 (PagerAdapger 상속)
class ViewPagerAdapter(private val context:Context):PagerAdapter(){
    private var layoutInflater :LayoutInflater?=null

    val Image = arrayOf(
        R.drawable.p1,
        R.drawable.p2,
        R.drawable.p3,
        R.drawable.p4
    )

    //페이지 뷰가 특정 키 객체와 연관되는 지 여부
    override fun isViewFromObject(view:View,`object`: Any):Boolean{
        return view === `object`
    }

    //사용 가능한 뷰의 개수를 리턴 (뷰 페이저의 전체 페이지 수 결정)
    override fun getCount(): Int {
        return Image.size
    }

    //position에 해당하는 페이지 생성 (화면에 표시할 페이지뷰 생성)
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        //xml 에 미리 정의해둔 틀을 실제 메모리에 올려준다 (부플린다는 뜻)
        //xml에 정의된 리소스를 view객체로 반환해줌
        layoutInflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
        val v = layoutInflater!!.inflate(R.layout.banner_image_layout,null)//페이지 레이아웃 리소스
        val image = v.findViewById<View>(R.id.imageView) as ImageView

        image.setImageResource(Image[position])
        val vp = container as ViewPager
        vp.addView(v,0) //뷰페이저에 추가

        return v
    }

    //position 위치의페이지 제거
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val v = `object` as View
        vp.removeView(v)
    }
}
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