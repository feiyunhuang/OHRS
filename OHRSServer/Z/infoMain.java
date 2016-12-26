package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import dao.Impl.database;

public class infoMain {
	protected static Connection connection = database.getConnection();

	public infoForWeb infoForWeb;
	public infoForMoney infoForMoney;
//	public orderMain orderMain;

	// 注册调用的方法
	public String addInfo = "insert into infotable(name,sex,clientid,password,value,birthday,phone,station) "+"  values(?,?,?,?,?,?,?,?)";
	public String creatNewClient(ArrayList<String> info,String password) {
		String clientid = "";
		try{	
			clientid = this.brinpassword();
			PreparedStatement preparedStatement = connection.prepareStatement(addInfo);
			preparedStatement.setString(1, info.get(0));
			preparedStatement.setString(2, info.get(1));
			preparedStatement.setString(3, clientid);
			preparedStatement.setString(4, password);
			preparedStatement.setString(5, info.get(2));
			preparedStatement.setString(6, info.get(3));
			preparedStatement.setString(7, info.get(4));
			preparedStatement.setString(8,"0");
			preparedStatement.executeUpdate();
			preparedStatement.close();
			return clientid;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return clientid;
	}
	
	//比对账号密码的方法
	String loginSuccess = "update infotable set station = 1 where clientid = ?";
	String getByID ="select station from infotable where clientid = ?";
	String getPassID ="select password from infotable where clientid = ?";
	public String login(String clientid,String password) {
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(getByID);
			preparedStatement.setString(1, clientid);
			ResultSet resultSet = preparedStatement.executeQuery();
			String result; 
		
			if(!resultSet.next()){
				preparedStatement.close();
				return "账号不存在";
			}
			else {	
				//result = resultSet.getString("password");
				int station = resultSet.getInt("station");
				//System.out.println(result);
				if(station == 0){
					preparedStatement.close();
					preparedStatement =connection.prepareStatement(getPassID);
					preparedStatement.setString(1, clientid);
					resultSet =preparedStatement.executeQuery();
					resultSet.next();
					result = resultSet.getString("password");
					if(result.equals(password)){
						preparedStatement.close();
						preparedStatement= connection.prepareStatement(loginSuccess);
						preparedStatement.setString(1, clientid);
						preparedStatement.executeUpdate();
						return "成功";
					}
					else{
						return "密码错误";
					}
				}
					
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return "账号已登录";
	}
	
	//用户退出登陆
	String loginOut = "update infotable set station = 0  where clientid = ?";
	public void loginOut(String clientid) {
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(loginOut);
			preparedStatement.setString(1, clientid);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		}catch(SQLException e){
				e.printStackTrace();
		}
	}
	
	//根据clientID获取用户信息
	String getInfo ="select name,sex,value,birthday,phone  from infotable where clientid = ?";
	public ArrayList<String> getInfo(String clientid) {
		ArrayList<String> infoList = new ArrayList<String>();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(getInfo);
			preparedStatement.setString(1, clientid);
			ResultSet resultSet = preparedStatement.executeQuery();
			//preparedStatement.close();
			resultSet.next();
			for(int i =0;i<5;i++){
				infoList.add(resultSet.getString(i+1));
			}
			preparedStatement.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return infoList;
	}
	
	//修改用户信息的方法
	String changeInfo = "update infotable set name =? , sex =?, birthday = ?,phone =?   where  clientid =?";
	public void setInfo(ArrayList<String> info,String clientid) {
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(changeInfo);
			preparedStatement.setString(1, info.get(0));
			preparedStatement.setString(2, info.get(1));
			preparedStatement.setString(3, info.get(2));
			preparedStatement.setString(4, info.get(3));
			preparedStatement.setString(5, clientid);
			preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	//随机生成账号的方法
	private String brinpassword() {
		StringBuilder stringBuilder = new StringBuilder();
		try{
			Random random = new Random();
			for(int i =0;i<9;i++){
				stringBuilder.append(random.nextInt(10));	
			}
			while(getRepeat(stringBuilder.toString())){
				stringBuilder = new StringBuilder();
				for(int i =0;i<9;i++){
					stringBuilder.append((int)Math.random()*10);	
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}
	
	//检查账号是否重复
	String avidrepeat ="select name  from infotable where clientid = ?";
	private boolean getRepeat(String clientid) throws SQLException{
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(avidrepeat);
			preparedStatement.setString(1, clientid);
			ResultSet resultSet = preparedStatement.executeQuery();
			if( resultSet.next()){
				return true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
}
