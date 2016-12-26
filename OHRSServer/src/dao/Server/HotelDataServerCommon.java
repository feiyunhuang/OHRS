package dao.Server;

import po.Busyroompo;
import po.HotelInforpo;
import po.Prompo;

public interface HotelDataServerCommon {
	/*
	 * 根据酒店id获取酒店信息
	 */
	public HotelInforpo selectHotelInfor(String hotelid);
	
	/*
	 * 根据酒店id获取酒店入住和预定客房信息
	 */
	public Busyroompo selectbusyroominfor(String hotelid);

	/*
	 * 根据优惠政策id获取优惠政策信息
	 */
	public Prompo selectProm(String promid);
}
