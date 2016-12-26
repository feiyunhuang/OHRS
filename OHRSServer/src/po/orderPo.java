package po;

import vo.Odvo;

public class orderPo {
	public String clientid = "";
	public String hotelid = "";
	public String inTime = "";
	public String outTime = "";
	public String money = "";
	
	//订单的状态（未完成、逾期、已完成）
	public String station;
	//采用的促销策略
	public String starty;
	//订单id
	public String id;
	//用户的评价
	public String comment;
	
	//房间1的价格
	public String room1V;
	//房间1的数量
	public String room1N;
	
	public String room2V;
	public String room2N;
	
	public String room3V;
	public String room3N;
	
	public String room4V;
	public String room4N;
	
	public String room5V;
	public String room5N;
	
	public Odvo exchange(){
		Odvo odvo = new Odvo();
		odvo.setOdID(this.id);
		odvo.setOdStatus(this.station);
		odvo.setclisentID(this.clientid);
		odvo.sethotelID(this.hotelid);
		odvo.setcomeTime(this.inTime);
		odvo.setdays(this.outTime);
		odvo.setpromotion(this.starty);
		odvo.settotalPrice(Integer.valueOf(this.money));
		odvo.setcomment(this.comment);
		if(Integer.valueOf(this.room1N)>0){
			odvo.setnumOfRoom(this.room1N);
			odvo.setkindOfRoom("豪华双人间");
			odvo.setpriceOfRoom(this.room1V);
		}
		else if(Integer.valueOf(this.room2N)>0){
			odvo.setnumOfRoom(this.room2N);
			odvo.setkindOfRoom("豪华单人间");
			odvo.setpriceOfRoom(this.room2V);
		}
		else if(Integer.valueOf(this.room3N)>0){
			odvo.setnumOfRoom(this.room3N);
			odvo.setkindOfRoom("标准双人间");
			odvo.setpriceOfRoom(this.room3V);
		}
		else if(Integer.valueOf(this.room4N)>0){
			odvo.setnumOfRoom(this.room4N);
			odvo.setkindOfRoom("标准单人间");
			odvo.setpriceOfRoom(this.room4V);
		}
		else if(Integer.valueOf(this.room5N)>0){
			odvo.setnumOfRoom(this.room5N);
			odvo.setkindOfRoom("家庭间");
			odvo.setpriceOfRoom(this.room5V);
		}
		return odvo;
	}
}
