package com.koreait.server;

import java.sql.*;

public class DbUtils {

    public static DbUtils dbUtils;//메모리에는 올라가있음, 즉 주소값 넣을 수 있다.

    private DbUtils() {}//다른 클래스에서 객체화 못하게하기

    //싱글톤
    public static DbUtils getInstance() {
        if (dbUtils == null) {
            dbUtils = new DbUtils();//0x1111
        }
        return dbUtils;//0x1111, 계속같은 주소값을 리턴
    }

    public Connection getCon() {//JAVA 랑 DB를 연결하는 다리, 통신을 가능하게 해주는 역할
        final String URL = "jdbc:mysql://localhost:3306/test";
        final String USER_NAME = "root";
        final String USER_PW = "ansghkwo12";
        Connection con = null;

        try {
            con = DriverManager.getConnection(URL, USER_NAME, USER_PW);//객체화 유무, Class 를 객체화 했으면 인스턴스, 안했으면 static, //여기서 오류 발생하면 바로 catch로
            System.out.println("DB 연결 성공!");
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("DB 연결 실패!");
        }

        return con;

    }
    //리소스 엄청 먹어서, 반납해줘야함
    //CRUD
    public void close(Connection con, PreparedStatement ps, ResultSet rs) {//통신을해야하니까(고속도로), //쿼리문 실행(컨트롤쉬프트에프구)담당, //SELECT 결과물 담는 역할

        //닫을때는 반대로 해줘야함

        if (rs != null) {
            try { rs.close();
            } catch (Exception e) { e.printStackTrace(); }
        }

        if (ps != null) {
            try { ps.close();
            } catch (Exception e) { e.printStackTrace(); }
        }

        if (con != null) {
            try { con.close();
            } catch (Exception e) { e.printStackTrace(); }
        }
    }
    //CUD
    public void close(Connection con, PreparedStatement ps) {
        if (ps != null) {
            try { ps.close();
            } catch (Exception e) { e.printStackTrace(); }
        }

        if (con != null) {
            try { con.close();
            } catch (Exception e) { e.printStackTrace(); }
        }
    }

}
