/**
 * @author huangxiao
 * 2016年11月17日
 */
package data.userdata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.userdataservice.MemberDataService;
import po.MemberPO;
import util.ResultMessage;

public class MemberDataServiceMySqlImpl implements MemberDataService {

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
