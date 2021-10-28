package com.myp.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.junit.Test;

public class ObjectUtilTest {
	
	//static Logger log = Logger.getLogger("com.myp.controller.ObjectUtilTest");
	static Logger log = Logger.getLogger(ObjectUtilTest.class);
	private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	//private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=homedb";
	private static final String url = "jdbc:sqlserver://127.0.0.1:1433;"+
			"databaseName=homedb;";
	private static final String user = "home";
	private static final String pw = "sa";
	
	private static final String url2 = "jdbc:sqlserver://127.0.0.1:1433;"+
			"databaseName=homedb;integratedSecurity=true;";
	@Test
	public void test() {
		log.info("확인!_!");
		
	}
	
	@Test
	public void testConnection()throws Exception{
		Class.forName(driver); //com.microsoft.sqlserver.jdbc.SQLServerDriver JDBC Driver class 로딩
		Connection con = DriverManager.getConnection(url, user, pw); // java.sql.Connection 객체생성

        try{
            System.out.println(con);
             
            System.out.println(con.isClosed()); // connection 닫힘 유무

            Statement stmt = con.createStatement(); // Statement 객체생성

            String sql = "select bno, title, content, writer, regdate, viewcnt"
            		+ "	from"
            		+ "		tbl_board"
            		+ "	where 1 = 1"; // 쿼리문

            ResultSet rs = stmt.executeQuery(sql); // 

            while(rs.next()) {
                System.out.println(rs.getString("bno")+"///"+rs.getString("title")+"///"+rs.getString("content"));
            }

            con.close();

            System.out.println(con.isClosed());
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            con.close();
        }
	}

}
