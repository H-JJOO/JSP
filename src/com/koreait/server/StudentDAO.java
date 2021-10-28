package com.koreait.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {//데이터 엑세스, 쿼리문 들어갈거임 DATA ACCESS OBJECT 데이터 접근 객체

    public static void main(String[] args) {
        List<StudentVO> list = selStudentList();

        for(StudentVO vo : list) {
            System.out.printf("%d - %s\n" , vo.getSno(), vo.getNm());
        }
    }

    public static DbUtils dbUtils = DbUtils.getInstance();

    //레코드 insert 담당 메소드
    public static int insStudent(StudentVO vo) {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null; //쿼리문 실행담당 (+ 쿼리문 완성)
        String sql = "INSERT INTO t_student2(nm, age, addr) VALUES (?, ?, ?)";


        try{
            con = dbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, vo.getNm());
            ps.setInt(2, vo.getAge());
            ps.setString(3, vo.getAddr());
            result = ps.executeUpdate();// 영향을 미친 행 수, 0 이 리턴됬다면 업무처리 된게 없다는 뜻
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbUtils.close(con, ps);
        }

        return result;


    }

    //TODO select
    public static List<StudentVO> selStudentList() {//한곳에 담아서 레코드를 여러개 넘길거다, 파라미터 없다는건 다 데려오겠다는 것
        List<StudentVO> list = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT sno, nm FROM t_student2";//한줄일때는 굳이 띄우기 할 필요 없다 + 로 한줄씩 띄울때는 필수!

        try {
            con = dbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {;//리턴타입 boolean, 줄을 가리키게 할거임, 젤처음에 첫번째줄, 레코드가 있으면 true 리턴 없으면 false 리턴
                StudentVO vo = new StudentVO();//StudentVO 를 객체화
                int sno = rs.getInt("sno");
                String nm = rs.getString("nm");
                vo.setSno(sno);
                vo.setNm(nm);
                list.add(vo);//리스트에 추가
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbUtils.close(con, ps, rs);
        }
        return list;
    }



    public static int updStudent(StudentVO vo) {

        Connection con = null;//통신담당
        PreparedStatement ps = null; //쿼리문 실행담당 (+ 쿼리문 완성)
        //쿼리문(하고자하는거)
        String sql = " UPDATE t_student2 " +
                    " SET nm = ? , age = ?, addr = ? " +
                    " WHERE sno = ? ";
        try{
            con = dbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, vo.getNm());
            ps.setInt(2, vo.getAge());
            ps.setString(3, vo.getAddr());
            ps.setInt(4,vo.getSno());
            return ps.executeUpdate();// 영향을 미친 행 수, 0 이 리턴됬다면 업무처리 된게 없다는 뜻
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbUtils.close(con, ps);
        }
        return 0;
    }

    public static int delStudent(StudentVO vo) {

        Connection con = null;
        PreparedStatement ps = null;
        String sql = " DELETE FROM t_student2 " +
                    " WHERE sno = ? ";

        try {
            con = dbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, vo.getSno());
            return ps.executeUpdate();//SELECT 빼고 이거 쓰면 됨 업뎃
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbUtils.close(con, ps);
        }
        return 0;
    }

}
