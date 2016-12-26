package dao.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import po.orderPo;

public class orderForHotel {

	//酒店端获取所有本酒店订单信息
	String hotelOrderAll = "select *  from orderTable where hotelid =?";
	public Vector<orderPo> gethotelOrder(String hotelid) {
		Vector<orderPo> orderList = new Vector<orderPo>();
		try{
			PreparedStatement preparedStatement = infoMain.connection.prepareStatement(hotelOrderAll);
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
		return orderList;
	}
		
	//酒店更改营销策略
	String changeStarty ="update orderTable set starty = ?  where  id =?";
	public void setinfo(String starty,String  id) {
		try{
			PreparedStatement preparedStatement = infoMain.connection.prepareStatement(changeStarty);
			preparedStatement.setString(1, starty);
			preparedStatement.setString(2, id);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	//酒店处理订单状态，并扣除/增加信用值
	String takeMoney ="updata infotable set value = ? where clientid = ?";
	public void takeMoneyBack (String clientid,String money,String hotelid,String station,String hotel,String time) {
		try{
			PreparedStatement preparedStatement = infoMain.connection.prepareStatement(takeMoney);
			String iString ;
			if(station.equals("增加")){
				int i= Integer.parseInt(money)+Integer.parseInt(new infoMain().infoForMoney.getinfo(clientid));
				iString = i+"";
			}
			else{
				int i= Integer.parseInt(money)-Integer.parseInt(new infoMain().infoForMoney.getinfo(clientid));
				iString =i+"";
			}
			
			preparedStatement.setString(1,iString );
			preparedStatement.setString(2, clientid);
			preparedStatement.executeUpdate();
			addRecord(clientid, money, hotelid, station, hotel, time);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	//增加修改信息到修改记录
	String takeBuffer ="insert into changeRecord (setDate,hotelid,clientid,station,money,name) "+"  values(?,?,?,?,?,?)";
	private void addRecord(String clientid,String money,String hotelid,String station,String hotel,String time){
		try{
			PreparedStatement preparedStatement =infoMain.connection.prepareStatement(takeBuffer);
			preparedStatement.setString(1, time);
			preparedStatement.setString(2, hotelid);
			preparedStatement.setString(3, clientid);
			preparedStatement.setString(4, station);
			preparedStatement.setString(5, money);
			preparedStatement.setString(6, hotel);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	//根据id查找单一订单
	String getByID ="select * from orderTable where id =?";
	public orderPo getOneInfo(String id) {
		orderPo orderpo = new orderPo();
		try{
			PreparedStatement preparedStatement = infoMain.connection.prepareStatement(getByID);
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();	
			if(resultSet.next()){
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
			}
			preparedStatement.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return orderpo;
	}
}
