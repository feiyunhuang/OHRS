package dao.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class infoForMoney {
	
	//网站营销人员获取用户信息的方法
	String controller = "select value  from infotable where clientid = ?";
	public String getinfo(String clientid){
		try{
			PreparedStatement preparedStatement = infoMain.connection.prepareStatement(controller);
			preparedStatement.setString(1, clientid);
			ResultSet resultSet =preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getString(1);
			}	
		}catch(SQLException e){
			e.printStackTrace();
		}
		return "找不到用户";
	}	
			
	//网站营销人员更改用户信息的方法
	String changeInfo ="update infotable set value = ?  where  clientid =?";
	public void setinfo(String value,String  clientid){
		try{
			PreparedStatement preparedStatement = infoMain.connection.prepareStatement(changeInfo);
			preparedStatement.setString(1, value);
			preparedStatement.setString(2, clientid);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
