package com.koreait.server;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")//이 주소로 요청들어오면(URL)
public class HelloServlet extends HttpServlet {//HttpServlet 상속받으면 tomcat 에게서 요청과 응답을 받을수 있는 권한 받음
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//요청, 응답
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        System.out.println("name : " + name);
        System.out.println("age : " + age);

        PrintWriter out = response.getWriter();

        TestVO vo = new TestVO();//객체화
        vo.setName("haha");//값담기
        vo.setAge(45);//값담기

        //수동(복잡)
        String result = String.format("{ \"name\": \"%s\", \"age\": %s }", vo.getName(), vo.getAge());//JSON 형태로 표현한거
        System.out.println("result: " + result);
        out.print(result);

        //라이브러리(간편)
        Gson gson = new Gson();
        String result2 = gson.toJson(vo);

        //반대로 파싱 가능
        TestVO vo2 = gson.fromJson(result2, TestVO.class);

        System.out.println("result2: " + result2);
        out.print(result);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String data = Utils.getJson(request);

        Gson gson = new Gson();//Gson 사용하기위해 객체화(사용하기 면하기 위해서)
        TestVO vo = gson.fromJson(data, TestVO.class);//fromJson : 문자열을 잘라서 객체화해서 값을 넣어줌

        System.out.println("name : " + vo.getName());
        System.out.println("age : " + vo.getAge());
        System.out.println(data);
    }
}
