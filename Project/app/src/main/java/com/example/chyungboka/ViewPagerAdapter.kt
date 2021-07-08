package com.example.chyungboka

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.banner_page.view.*

class ViewPagerAdapter :PagerAdapter(){
    private var mContext: Context?=null

    fun ViewPagerAdapter(context: Context){
        mContext=context;
    }

    //position에 해당하는 페이지 생성
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view=LayoutInflater.from(container.context).inflate(R.layout.banner_page,container,false)
        //app:srcCompat="@drawable/banner01"
        //var name = "banner0"+position;
        //view.banner_image.setImageResource(R.drawable.name);
        val p :Int = when(position){
            1->R.drawable.b01;
            2->R.drawable.b02;
            3->R.drawable.b03;
            else->R.drawable.b04;
        }//banner prac file : banner01~04.png

        view.banner_image.setImageResource(p);
        container.addView(view)
        return view
    }

    //position에 위치한 페이지 제거거
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }


    //사용가능한 뷰 개수 리턴
    override fun getCount(): Int {
        return 5
    }

    //페이지뷰가 특정 키 객체(key object)와 연관 되는지 여부
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return (view==`object`)
    }
}