/**
 * @author huangxiao
 * 2016年11月17日
 */
package data.userdata;

import java.rmi.RemoteException;

import dataservice.userdataservice.MarketerDataService;
import po.MarketerPO;
import util.ResultMessage;

public class MarketerDataServiceMySqlImpl implements MarketerDataService {

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.MarketerDataService#findData(java.lang.String)
	 */
	@Override
	public MarketerPO findData(String MarketerID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage updateData(MarketerPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage insert(MarketerPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
