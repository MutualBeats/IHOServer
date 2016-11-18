/**
 * @author huangxiao
 * 2016年11月17日
 */
package data.userdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import data.databaseutility.SqlManager;
import dataservice.userdataservice.MemberDataService;
import po.MemberPO;
import util.ResultMessage;

public class MemberDataServiceMySqlImpl extends UnicastRemoteObject implements MemberDataService {

	private static final long serialVersionUID = 2L;
	
	private SqlManager sqlManager = SqlManager.getInstance();
	
	protected MemberDataServiceMySqlImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.MemberDataService#businessRegister(po.MemberPO)
	 */
	@Override
	public ResultMessage businessRegister(MemberPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.MemberDataService#originalRegister(po.MemberPO)
	 */
	@Override
	public ResultMessage originalRegister(MemberPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.MemberDataService#update(java.lang.String, po.MemberPO)
	 */
	@Override
	public MemberPO update(String name, MemberPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.MemberDataService#levelUpdate(java.util.ArrayList, int)
	 */
	@Override
	public ResultMessage levelUpdate(ArrayList<Integer> levelList, int level) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.MemberDataService#query(java.lang.String, po.MemberPO)
	 */
	@Override
	public MemberPO query(String name, MemberPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
