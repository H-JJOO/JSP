package com.koreait.server;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class StudentDAO {//데이터 엑세스, 쿼리문 들어갈거임 DATA ACCESS OBJECT 데이터 접근 객체

    public static void main(String[] args) {
        StudentVO vo = new StudentVO();
        vo.setNm("궁예");
        vo.setAge(55);
        vo.setAddr("개성");

        updStudent(vo);


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

    public static int updStudent(StudentVO vo) {

        Connection con = null;
        PreparedStatement ps = null; //쿼리문 실행담당 (+ 쿼리문 완성)
        String sql = "UPDATE t_student2 " +
                    "SET nm = ? , age = ?, addr = ?" +
                    "WHERE sno = 10 ";
        try{
            con = dbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, vo.getNm());
            ps.setInt(2, vo.getAge());
            ps.setString(3, vo.getAddr());
            return ps.executeUpdate();// 영향을 미친 행 수, 0 이 리턴됬다면 업무처리 된게 없다는 뜻
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbUtils.close(con, ps);
        }

        return 0;
    }

}
