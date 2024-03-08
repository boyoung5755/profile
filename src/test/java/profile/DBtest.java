package profile;


import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import profile.stack.dao.StackDAO;

/**
 * 프로그램 설명
 * @date        : 2024. 3. 8.
 * @author      : boyoung
 * @version	: 1.0
 * <PRE>
 * ----------------------------
 * 개정이력
 * 2024. 3. 8. boyoung : 최초작성
 * </PRE>
 */

class DBtest {
	
	StackDAO dao;
  final String DRIVER = "com.mysql.jdbc.Driver";
  final String URL = "jdbc:mysql://localhost/pofol";
  final String USER = "root";
  final String PASSWORD = "0000";

	//@Test
	void test() {
		fail("Not yet implemented");
	}

	
	@Test
	  public void mariaTest() throws Exception{
	   Class.forName(DRIVER);
	   
	   try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
	    System.out.println(con);
	   } catch(Exception e) {
	    e.printStackTrace();
	   }
	  }
	
}
