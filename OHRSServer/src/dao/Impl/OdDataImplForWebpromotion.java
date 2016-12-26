package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import dao.Server.OdDataServerForWebpromotion;
import po.orderPo;

public class OdDataImplForWebpromotion implements OdDataServerForWebpromotion{

	private Connection connection = database.getConnection();
	
	//营销端获取所有异常订单信息
	@Override
	public Vector<orderPo> gethotelOrder() {
		// TODO Auto-generated method stub
		String sql = "select *  from orderTable where station =? and check =?";
		Vector<orderPo> orderList = new Vector<orderPo>();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "逾期");
			preparedStatement.setString(2, "0");
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
		} catch(SQLException e){
			e.printStackTrace();
		}
		return orderList;
	}

	//网站营销人员处理订单状态，并扣除/增加信用值
	@Override
	public void takeMoneyBack(String clientid, String money, String station, String time, String id) {
		// TODO Auto-generated method stub
		String takeMoney ="updata infotable set value = ? where clientid = ?";
		String setOrder = "updata ordertable set check = ? where id= ?";
		try{
			String iString ;
			if(station.equals("增加")){
				int i= Integer.parseInt(money)+Integer.parseInt(new ClientDataImplCommon().GetClientValue(clientid));
				iString = i+"";
			}
			else{
				int i= Integer.parseInt(money)-Integer.parseInt(new ClientDataImplCommon().GetClientValue(clientid));
				iString =i+"";
			}
			PreparedStatement preparedStatement = connection.prepareStatement(takeMoney);
		
			
		
			preparedStatement.setString(1,iString );
			preparedStatement.setString(2, clientid);
			preparedStatement.executeUpdate();
			addRecord(clientid, money, station, time);
			preparedStatement = connection.prepareStatement(setOrder);
			preparedStatement.setString(1, "1");
			preparedStatement.setString(2, id);
			preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	//网站营销人员增加修改信息到修改记录
	private void addRecord(String clientid,String money,String station,String time) {
		String sql ="insert into changeRecord (setDate,hotelid,clientid,station,money,name) "+"  values(?,?,?,?,?,?)";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, time);
			preparedStatement.setString(2, "0");
			preparedStatement.setString(3, clientid);
			preparedStatement.setString(4, station);
			preparedStatement.setString(5, money);
			preparedStatement.setString(6, "0");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
