package com.example.chyungboka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

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