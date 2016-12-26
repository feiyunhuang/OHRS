package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import dao.Server.OdDataServerForHotel;
import po.orderPo;
import vo.Odvo;

public class OdDataImplForHotel implements OdDataServerForHotel{
	
	private Connection connection = database.getConnection();
	
	//酒店端获取所有本酒店订单信息
	public Vector<Vector<Odvo>> hotelGetOrder(String hotelid){
		// TODO Auto-generated method stub
		String sql = "select *  from orderTable where hotelid =?";
		Vector<orderPo> orderList = new Vector<orderPo>();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, hotelid);
			ResultSet resultSet =preparedStatement.executeQuery();
			while(resultSet.next()){
				orderPo  orderpo = new orderPo();
				orderpo.clientid = resultSet.getString(1);
				orderpo.hotelid = resultSet.getString(2);
				orderpo.inTime = resultSet.getString(3);
				orderpo.outTime = resultSet.getString(4);
				orderpo.money = resultSet.getString(5);
				orderpo.room1N = resultSet.getString(6);
				orderpo.room2N = resultSet.getString(7);
				orderpo.room3N = resultSet.getString(8);
				orderpo.room4N= resultSet.getString(9);
				orderpo.room5N = resultSet.getString(10);
				orderpo.comment = resultSet.getString(11);
				orderpo.starty = resultSet.getString(14);
				orderpo.station = resultSet.getString(12);
				orderpo.room1V =  resultSet.getString(16);
				orderpo.room2V =  resultSet.getString(17);
				orderpo.room3V =  resultSet.getString(18);
				orderpo.room4V =  resultSet.getString(19);
				orderpo.room5V =  resultSet.getString(20);
				orderpo.id = resultSet.getString(15);
				orderList.add(orderpo);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		Vector<Odvo> Odlistvo = new Vector<Odvo>();
		for(int i=0;i<orderList.size();i++){
			Odlistvo.add(orderList.get(i).exchange());
		}
		Vector<Vector<Odvo>> Odvo = new Vector<Vector<Odvo>>();
		Vector<Odvo> OdKind1vo = new Vector<Odvo>();
		Vector<Odvo> OdKind2vo = new Vector<Odvo>();
		Vector<Odvo> OdKind3vo = new Vector<Odvo>();
		for(int i=0;i<Odlistvo.size();i++){
			if(Odlistvo.get(i).getOdstatus().equals("未完成"))
				OdKind1vo.add(Odlistvo.get(i));
			else if(Odlistvo.get(i).getOdstatus().equals("已完成"))
				OdKind2vo.add(Odlistvo.get(i));
			else if(Odlistvo.get(i).getOdstatus().equals("逾期"))
				OdKind3vo.add(Odlistvo.get(i));
		}
		Odvo.add(OdKind1vo);
		Odvo.add(OdKind2vo);
		Odvo.add(OdKind3vo);
		return Odvo;
	}
	
	//酒店处理订单状态，并扣除/增加信用值
	@Override
	public void takeMoneyBack (String clientid,int money,int value,String hotelid,String status,String id,String hotelName,String time) {
		// TODO Auto-generated method stub
		String sql ="updata infotable set value = ? where clientid = ?";
		String setOrder = "updata ordertable set station = ? where id= ?";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			String iString ;
			iString = money+"";
			
			preparedStatement.setString(1,iString );
			preparedStatement.setString(2, clientid);
			preparedStatement.executeUpdate();
			addRecord(clientid, value+"", hotelid, "增加", hotelName, time);
			preparedStatement = connection.prepareStatement(setOrder);
			preparedStatement.setString(1,status);
			preparedStatement.setString(2, id);
			preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	//酒店增加修改信息到修改记录
	private void addRecord(String clientid,String money,String hotelid,String station,String hotel,String time){
		String sql ="insert into changeRecord (setDated,hotelid,clientid,stationd,money,name) "+"  values(?,?,?,?,?,?)";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, time);
			preparedStatement.setString(2, hotelid);
			preparedStatement.setString(3, clientid);
			preparedStatement.setString(4, station);
			preparedStatement.setString(5, money);
			preparedStatement.setString(6, hotel);
			preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public void refushOrder(String clientid,int money,int value,String hotelid,String status,String id,String hotel,String time,String time2 ){
		// TODO Auto-generated method stub
		String sql ="update infotable set value = ? where clientid = ?";
		String setOrder = "update ordertable set station = ? , inTimeid = ? where id= ?";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			PreparedStatement preparedStatement2 = connection.prepareStatement(setOrder);
			String iString ;
			iString = money+"";
			
			preparedStatement.setString(1, iString );
	//		preparedStatement.setString(2, time2);
			preparedStatement.setString(2, clientid);
			preparedStatement.executeUpdate();
			addRecord(clientid, value+"", hotelid, "增加", hotel, time);
			preparedStatement2.setString(1, status);
			preparedStatement2.setString(2, time2);
			preparedStatement2.setString(3, id);
			preparedStatement2.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public void overOd(String clientid,int money,int value,String hotelid,String status,String id,String hotel,String time){
		String sql ="update infotable set value = ? where clientid = ?";
		String setOrder = "update ordertable set station = ?  where id= ?";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			String iString ;
			iString = money+"";
			
			preparedStatement.setString(1,iString );
	//		preparedStatement.setString(2, time2);
			preparedStatement.setString(2, clientid);
			preparedStatement.executeUpdate();
			addRecord(clientid, value+"", hotelid, "减少", hotel, time);
			preparedStatement = connection.prepareStatement(setOrder);
			preparedStatement.setString(1,status);
			
			preparedStatement.setString(2, id);
			preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
//**************************************************************************************************

	//获取用户所有订单
	@Override
	public Vector<orderPo> getAllInfo(String clientid) {
		// TODO Auto-generated method stub
		String sql ="select * from orderTable where id =?";
		Vector<orderPo> orderList = new Vector<orderPo>();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clientid);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				orderPo  orderpo = new orderPo();
					orderpo.clientid = resultSet.getString(1);
					orderpo.hotelid = resultSet.getString(2);
					orderpo.inTime = resultSet.getString(3);
					orderpo.outTime = resultSet.getString(4);
					orderpo.money = resultSet.getString(5);
					orderpo.room1N = resultSet.getString(6);
					orderpo.room2N = resultSet.getString(7);
					orderpo.room3N = resultSet.getString(8);
					orderpo.room4N= resultSet.getString(9);
					orderpo.room5N = resultSet.getString(10);
					orderpo.comment = resultSet.getString(11);
					orderpo.starty = resultSet.getString(14);
					orderpo.station = resultSet.getString(12);
					orderpo.room1V =  resultSet.getString(16);
					orderpo.room2V =  resultSet.getString(17);
					orderpo.room3V =  resultSet.getString(18);
					orderpo.room4V =  resultSet.getString(19);
					orderpo.room5V =  resultSet.getString(20);
					orderpo.id = resultSet.getString(15);
					orderList.add(orderpo);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return orderList;
	}
}

