package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import Remote.Impl.HotelImplForHotel;
import Remote.Impl.OdImplForHotel;
import Remote.Impl.PromImplForHotel;
import Remote.Server.HotelServerForHotel;
import Remote.Server.OdServerForHotel;
import Remote.Server.PromServerForHotel;
import vo.Busyroomvo;
import vo.HotelInforvo;
import vo.Odvo;
import vo.Promvo;

public class dataRemote extends UnicastRemoteObject implements HotelServerForHotel, OdServerForHotel,PromServerForHotel{
	
	private static final long serialVersionUID = 4077329331699640331L;
	private HotelServerForHotel hotelForH;
	private OdServerForHotel OdForH;
	private PromServerForHotel PromForH;
	protected dataRemote() throws RemoteException {
		hotelForH = new HotelImplForHotel();
		OdForH = new OdImplForHotel();
		PromForH = new PromImplForHotel();
	}
	
	@Override
	public String hotelsingup(String password, HotelInforvo inforvo) throws RemoteException{
		// TODO Auto-generated method stub
		return hotelForH.hotelsingup(password, inforvo);
	}
	
	@Override
	public String hotellogin(String id, String password) throws RemoteException{
		// TODO Auto-generated method stub
		return hotelForH.hotellogin(id, password);
	}
	
	@Override
	public void hotellogout(String accredit) throws RemoteException{
		// TODO Auto-generated method stub
		hotelForH.hotellogout(accredit);
	}
	
	@Override
	public HotelInforvo gethotelinfor(String accredit) throws RemoteException{
		// TODO Auto-generated method stub
		return hotelForH.gethotelinfor(accredit);
	}
	
	@Override
	public boolean editinfor(String accredit, HotelInforvo inforvo) throws RemoteException{
		// TODO Auto-generated method stub
		return hotelForH.editinfor(accredit, inforvo);
	}
	
	@Override
	public Busyroomvo getbusyroominfor(String accredit) throws RemoteException{
		// TODO Auto-generated method stub
		return hotelForH.getbusyroominfor(accredit);
	}
	
	@Override
	public boolean editbusyroominfor(String accredit, Busyroomvo busyroomvo) throws RemoteException{
		// TODO Auto-generated method stub
		return hotelForH.editbusyroominfor(accredit, busyroomvo);
	}


	@Override
	public boolean addbusyroominfor(String accredit, Busyroomvo busyroomvo) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelForH.addbusyroominfor(accredit, busyroomvo);
	}

	@Override
	public boolean makeProm(String accredit, Promvo promvo) throws RemoteException {
		// TODO Auto-generated method stub
		return PromForH.makeProm(accredit, promvo);
	}

	@Override
	public Vector<Vector<Promvo>> getPromList(String hotelid) throws RemoteException {
		// TODO Auto-generated method stub
		return PromForH.getPromList(hotelid);
	}

	@Override
	public Promvo getPromInfor(String promid) throws RemoteException {
		// TODO Auto-generated method stub
		return PromForH.getPromInfor(promid);
	}

	@Override
	public boolean deleteProm(String promid) throws RemoteException {
		// TODO Auto-generated method stub
		return PromForH.deleteProm(promid);
	}
	
	@Override
	public Vector<Vector<Odvo>> hotelGetOrder(String accredit) throws RemoteException {
		// TODO Auto-generated method stub
		return OdForH.hotelGetOrder(accredit);
	}

	@Override
	public boolean executeOd(String accredit, String hotelid, String OdID, int value) throws RemoteException {
		// TODO Auto-generated method stub
		return OdForH.executeOd(accredit, hotelid, OdID, value);
	}

	@Override
	public boolean executeOd(String accredit, String hotelid, String OdID, int value, String time) throws RemoteException {
		// TODO Auto-generated method stub
		return OdForH.executeOd(accredit, hotelid, OdID, value, time);
	}
	
	@Override
	public boolean overOd(String accredit, String hotelid, int value, String OdID) throws RemoteException {
		// TODO Auto-generated method stub
		return OdForH.overOd(accredit, hotelid, value, OdID);
	}
}
