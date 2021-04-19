package com.example.chyungboka

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.banner_image_layout.view.*
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
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    internal lateinit var viewpager :ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        //viewpager = findViewById(R.id.mViewPager) as ViewPager

        //fragment에서 this가 안됨 -> activity()사용!!
       // val pageadapter = ViewPagerAdapter(this)
        val pageadapter = ViewPagerAdapter(this) //tq
        mViewPager.adapter=pageadapter

    }
//https://philosopher-chan.tistory.com/282

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