package clientVo;

import java.util.ArrayList;

public class clientVo {
	/**
	 * @author ����
	 * @see ���û�������ʵ�廯�Ĵ洢��
	 * 			������set/get����
	 */

	//�û�������
	public String name;
	//�û����Ա�
	public String sex;
	//�û����˺�
	public String clientId;
	//�û�������ֵ
	public String vaule;
 
	//�û�����
	public String brithday;
	//�û��绰����
	public String phone;
	
	//�û���Ϣ������������
	public  ArrayList<String> info;
	
	public clientVo(){
		
	}
	/*
	 * @param ������ֵ���Թ�����
	 */
	@SuppressWarnings("unchecked")
	public void setInfo(ArrayList< String> info){
		this.info = (ArrayList<String>) info.clone();
		getRight();
	}
	/*
	 * @param  ����info �������Ϣ
	 */
	private void getRight(){
		name = info.get(0);
		sex = info.get(1);
		//clientId = info.get(2);
		vaule = info.get(2);
		brithday = info.get(3);
		phone = info.get(4);
	}
	/*
	 * @param ����info��������ͬ�������ݿ�
	 */
	 public void creatNewInfo(){
		 info.clear();
		 info.add(name);
		 info.add(sex);
		 info.add(vaule);
		 info.add(brithday);
		 info.add(phone);
	 }
	
}
