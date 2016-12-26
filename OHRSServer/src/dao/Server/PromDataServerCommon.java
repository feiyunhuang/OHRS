package dao.Server;

import java.util.Vector;

import po.Prompo;

public interface PromDataServerCommon {
	
	public Vector<Vector<Prompo>> selectProms(String hotelid);
	
}
