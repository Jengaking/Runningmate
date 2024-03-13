package main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import controllers.DB_Connector;


public class TestDriver {

	public static void main(String[] args) {
		DB_Connector.connectToDB("test");
		Connection con =  DB_Connector.getConnection();
		try {
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery("select * from 주문");
			 ResultSetMetaData schema = rs.getMetaData();
			 System.out.println("주문 테이블 컬럼 수 : " + schema.getColumnCount());
			 for(int i = 1; i <= schema.getColumnCount(); i++) {
				 System.out.println("column "+i+": " + schema.getColumnName(i)
						 			+ " " + schema.getColumnTypeName(i) + "(" + schema.getPrecision(i) + ", "+ schema.getScale(i)+")" + schema.getColumnClassName(i));
			 }
			 rs.next();
			 int sd = rs.getDate(6).getYear();
			 System.out.println(sd);
			 rs.close();
			 stmt.close();
			 DB_Connector.closeConnection();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
