package com.koreait.server;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/world")
public class WorldServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String addr = req.getParameter("addr");
        String phone = req.getParameter("phone");
        String gender = req.getParameter("gender");

        System.out.println("addr : " + addr);
        System.out.println("phone : " + phone);
        System.out.println("gender : " + gender);



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //JSON 형태의 값을 문자열 형태로 변환

        String data = Utils.getJson(req);

        Gson gson = new Gson();//객체화

        TestVO vo = gson.fromJson(data, TestVO.class);//data 문자열을 TestVO.class 의 타입으로 변환해줘라

        System.out.println("name : " + vo.getName());
        System.out.println("age : " + vo.getAge());

    }
}
