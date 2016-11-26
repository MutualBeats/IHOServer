/**
 * @author huangxiao
 * 2016年11月18日
 */
package mysql_test;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.Test;

import data.databaseutility.SqlManager;

public class MySqlConnectionTest {

	private SqlManager sqlManager;
	private Connection connection;
	
	@Test
	public void driveTest() {
		sqlManager = SqlManager.getInstance();
		assertNotNull(sqlManager);
	}
	
	@Test
	public void connectTest() {
		connection = SqlManager.getInstance().getConnection();
		assertNotNull(connection);
	}
	
}
