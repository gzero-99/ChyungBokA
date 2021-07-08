package com.example.chyungboka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_toolbar.*




class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val fragment_home:HomeFragment= HomeFragment()
    val fragment1:SearchFragment= SearchFragment()
    val fragment2:ClassifyFragment= ClassifyFragment()
    var home:Int=0
    //var state:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(main_layout_toolbar)
        setFragment()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)

        main_navigationView.setNavigationItemSelectedListener(this)


    }

    //툴바에 홈키 만들기기
   override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    //navigation창을 열었을 때 뒤로가기 누를때
    override fun onBackPressed() {
        if(main_drawer_layout.isDrawerOpen(GravityCompat.START)){
            main_drawer_layout.closeDrawers()
        }
        else if(home==1){
            supportFragmentManager.beginTransaction().replace(R.id.home_frame,fragment_home).commit()
            home=0
        }
        else super.onBackPressed()
    }

    //버튼 누르면 네비게이션 열기 및 홈으로
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            //네비게이션 바 열기
            android.R.id.home->{
                main_drawer_layout.openDrawer(GravityCompat.START)
            }
            //home fragment로 이동
            R.id.go_home->{
                home=0
                supportFragmentManager.beginTransaction().replace(R.id.home_frame,fragment_home).commit()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //어떤 메뉴가 선택되었는지 검사
    override fun onNavigationItemSelected(item: MenuItem): Boolean{
        main_drawer_layout.closeDrawers()
        when(item.itemId){
            R.id.menu1->{
                //Log.d("test","item1")
                onFragmentSelected(1)
            }
            R.id.menu2->{
                //Log.d("test","item2")
                onFragmentSelected(2)
            }
        }
        return false
    }

    //화면 전환
    fun onFragmentSelected(position:Int){
        var curFragment: Fragment
        curFragment=fragment_home
        if(position==1){
            curFragment=fragment1
        }
        else if(position==2){
            curFragment=fragment2
        }
        home=1
        supportFragmentManager.beginTransaction().replace(R.id.home_frame,curFragment).commit()
    }

    //초기화면 set
    fun setFragment(){
        val transaction=supportFragmentManager.beginTransaction()
        transaction.add(R.id.home_frame,fragment_home)
        transaction.commit()
    }

    //thread를 main 에서 관리하자 (home fragment의 banner에서 쓰이는 것)




    //복지 api를 호출하는 기본적인 형태.
    //추후에 알맞게 수정하기
    //호출방법 : NetworkThread().start()
    /*
    inner class NetworkThread: Thread(){
        override fun run(){
            try{
                //접속할 페이지의 주소
                var key="jjLFpmSdFQHUJtMORBeBA44kBSZ4y%2FI7d3k8tVGsg%2Fo8uJVgANSPaq%2Bzb6TS17U1O0n59cWKnCVh0wCZCUJWjw%3D%3D"
                var site="http://www.bokjiro.go.kr/openapi/rest/gvmtWelSvc?lifeArray=004&desireArray=7000000&callTp=L&crtiKey="+key
                var url= URL(site)
                var conn=url.openConnection()
                var input=conn.getInputStream()
                Log.d("test","여기까진 됨1")
                var factory=DocumentBuilderFactory.newInstance()
                var builder=factory.newDocumentBuilder()
                //doc : xml 문서를 모두 읽어와서 분석을 끝냄
                var doc=builder.parse(input)

                //root : xml 문서의 모든 데이터들을 갖고있는 객체
                var root = doc.documentElement
                Log.d("test","여기까진 됨2")

                //xml 문서에서 태그 이름이 servList인 태그들이 item_node_list에 리스트로 담김
                var item_node_list=root.getElementsByTagName("servList")

                var item_element=item_node_list.item(1) as Element

                var jurMnofNm_list=item_element.getElementsByTagName("jurMnofNm")

                var jurMnofNm_node=jurMnofNm_list.item(0) as Element

                var jurMnofNm=jurMnofNm_node.textContent
                Log.d("test","${jurMnofNm}")

            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    */
}