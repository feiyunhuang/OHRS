package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.Server.HotelDataServerCommon;
import po.Busyroompo;
import po.HotelInforpo;
import po.Prompo;

public class HotelDataImplCommon implements HotelDataServerCommon {
	
	private static Connection connect = database.getConnection();
	
	@Override
	public HotelInforpo selectHotelInfor(String hotelid){
		// TODO Auto-generated method stub
		HotelInforpo inforpo = new HotelInforpo();
		String sql = "SELECT * from hotelinfor where hotelid="+hotelid;
		try{
			PreparedStatement ptmt = connect.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			if(rs.next()){
				inforpo.add(rs.getString("name"));
				inforpo.add(rs.getString("hotelid"));
				inforpo.add(rs.getString("tradearea"));
				inforpo.add(rs.getString("level"));
				inforpo.add(rs.getString("tel"));
				inforpo.add(rs.getString("address"));
				inforpo.add(rs.getString("introduction"));
				inforpo.add(rs.getString("LDP"));
				inforpo.add(rs.getString("LDN"));
				inforpo.add(rs.getString("LSP"));
				inforpo.add(rs.getString("LSN"));
				inforpo.add(rs.getString("SDP"));
				inforpo.add(rs.getString("SDN"));
				inforpo.add(rs.getString("SSP"));
				inforpo.add(rs.getString("SSN"));
				inforpo.add(rs.getString("FP"));
				inforpo.add(rs.getString("FN"));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return inforpo;
	}

	@Override
	public Busyroompo selectbusyroominfor(String hotelid) {
		// TODO Auto-generated method stub
		Busyroompo busyroompo = new Busyroompo();
		String sql = "SELECT * from busyroom where hotelid="+hotelid;
		try{
			PreparedStatement ptmt = connect.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			if(rs.next()){
				busyroompo.add(rs.getString("hotelid"));
				busyroompo.add(rs.getString("LDR"));
				busyroompo.add(rs.getString("LDO"));
				busyroompo.add(rs.getString("LSR"));
				busyroompo.add(rs.getString("LSO"));
				busyroompo.add(rs.getString("SDR"));
				busyroompo.add(rs.getString("SDO"));
				busyroompo.add(rs.getString("SSR"));
				busyroompo.add(rs.getString("SSO"));
				busyroompo.add(rs.getString("FR"));
				busyroompo.add(rs.getString("FO"));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return busyroompo;
	}
	
	@Override
	public Prompo selectProm(String promid) {
		// TODO Auto-generated method stub
		Prompo prompo = new Prompo();
		String sql = "SELECT * from promotion where promid="+promid;
		try{
			PreparedStatement ptmt = connect.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			if(rs.next()){
				prompo.add(rs.getString("hotelid"));
				prompo.add(rs.getString("promid"));
				prompo.add(rs.getString("name"));
				prompo.add(rs.getString("kind"));
				prompo.add(rs.getString("discount"));
				prompo.add(rs.getString("introduction"));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return prompo;
	}

}
