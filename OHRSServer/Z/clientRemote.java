package Remote.Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Vector;

import po.orderPo;

public interface clientRemote extends Remote{
	/* 
	 * @author 高翔
	 * @see 所有的远程调用方法汇总
	 */
	//注册用方法
	String creatclientNew(ArrayList<String> info,String password) throws RemoteException;
	//登陆调用接口方法
	String clientlogin(String ID,String passward) throws RemoteException;
	//退出所调用的方法
	void clientlogOut(String ID) throws RemoteException;
	//获取用户信息方法
	ArrayList<String>  getClientInfo(String ID) throws RemoteException;
	//修改用户信息方法
	void setClientInfo(ArrayList<String> clientInfo,String clientid) throws RemoteException;
	
	
	/*
	 * 网站营销人员与网站管理人员所调用的方法
	 */
	//获取用户信息
	ArrayList<String> getclientInfo(String clientid)  throws RemoteException;
	//更改用户信息
	void setclientInfo(ArrayList<String > info, String clientid) throws RemoteException;
	//获取所有用户信息
	Vector<ArrayList<String>> getclientInfo()  throws RemoteException;
	// 获取用户信用值
	String getValue(String clientid) throws RemoteException;
	//更改用户信息值
	void setValue (String value,String clientid) throws RemoteException;
	
	//酒店工作人员获取所有订单
	Vector<orderPo> hotelGetOrder(String hotelId)  throws RemoteException;
	//酒店工作人员获取单一订单
	orderPo hotelGetO(String id)  throws RemoteException;
	//酒店工作人员处理订单 （用户账号、更改金额、酒店账号、状态（逾期、正常、完成）、酒店名称、更改时间
	void hotelTakeMoney(String clientid,String money,String hotelid,String station,String hotel,String time)throws RemoteException;
	
	//网站营销人员获取订单
	Vector< orderPo> webGetOrder()throws RemoteException;
	//网站营销人员处理订单（用户账号、更改金额、状态（逾期、正常、完成）、更改时间、订单id
	 void takemoney (String clientid,String money,String station,String time,String id ) throws RemoteException;
	 //根据id查找单一订单
	 orderPo getOneInfo(String id) throws RemoteException;
}
 