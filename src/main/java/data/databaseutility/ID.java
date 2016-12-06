/**
 * @author huangxiao
 * @version 2016年12月6日
 */
package data.databaseutility;

public class ID {
	
	public static String idToString(int id, int length) {
		String res = "" + id;
		for (int i = res.length(); i < length; i++) {
			res = '0' + res;
		}
		return res;
	}

}
