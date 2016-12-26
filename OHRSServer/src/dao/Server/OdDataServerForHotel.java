package dao.Server;

import java.util.Vector;

import po.orderPo;
import vo.Odvo;

public interface OdDataServerForHotel{
	
	//酒店端获取所有本酒店订单信息	
	public Vector<Vector<Odvo>> hotelGetOrder(String accredit);
	
	//酒店处理订单状态，并扣除/增加信用值
	public void takeMoneyBack (String clientid,int money,int value,String hotelid,String status,String id,String hotelName,String time);
	
	//获取用户所有订单
	public Vector<orderPo> getAllInfo(String clientid);
	
	public void refushOrder(String clientid,int money,int value,String hotelid,String status,String id,String hotel,String time,String time2 );
	
	public void overOd(String clientid,int money,int value,String hotelid,String status,String id,String hotel,String time);
}
