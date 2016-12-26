package ui.hotel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import controller.view_controller_hotelProm;
import vo.Promvo;

public class promotionlistui extends JPanel{
	
	private static view_controller_hotelProm vch = new view_controller_hotelProm();

	/*
	 * 常量值
	 * 用于规定输入框和选择框的大小
	 * 也使用于布局中status的位置确定
	 */
	private final static int frame_width=800;
	private final int frame_height=600;
	private final int label_width=100;
	private final int label_height=30;
	private final int textfield_width = 260;
	private final int textfield_height = 30;
	private final static int heightOfTable = 450;
	
	private JLabel kindOfPromL;
	
	private static JComboBox<String> kindOfPromC;

	private static JTable PromInforT;
	private static Vector<String> kindOfPromInfor;
	private static DefaultTableModel PromInforTM;
	private static JScrollPane scrollPane;
	
	private static Vector<Vector<Promvo>> PromInforvo = new Vector<Vector<Promvo>>();
	private static Vector<String> PromKind1 = new Vector<String>();
	private static Vector<String> PromKind2 = new Vector<String>();
	private static Vector<Vector<String>> PromKind1s = new Vector<Vector<String>>();
	private static Vector<Vector<String>> PromKind2s = new Vector<Vector<String>>();
	
	private JPanel jpanelU = new JPanel();
	private JPanel jpanelC = new JPanel();
	
	private static String accredit = "";
	private static String hotelid = "";
	
	private JButton makeProm;
	private JButton updateB;
	private JButton deleteB;
	
	public promotionlistui(String id){
		hotelid = id;
		initui();
	}
	
	private void initui(){
		Labels();
		ComboBoxs();
		Tables();
		Buttons();
		
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setSize(frame_width, frame_height);
		
		int x = 20;
		int y = 45;
		jpanelU.setLayout(null);
		jpanelU.setSize(frame_width, 90);
		jpanelU.setBackground(Color.white);
		
		jpanelU.add(kindOfPromL);
		jpanelU.add(kindOfPromC);
		jpanelU.add(makeProm);
		jpanelU.add(updateB);
		jpanelU.add(deleteB);
		kindOfPromL.setLocation(x, y);
		kindOfPromC.setLocation(x+label_width, y);
		makeProm.setLocation(jpanelU.getWidth()-x*2-makeProm.getWidth()*2, y);
		updateB.setLocation(jpanelU.getWidth()-x*2-makeProm.getWidth()*3, y);
		deleteB.setLocation(jpanelU.getWidth()-x*2-deleteB.getWidth(), y);
		
		this.add(jpanelU);
		this.add(jpanelC);
		jpanelU.setLocation(0, 0);
		jpanelC.setLocation(0, 90);
	}
	
	private void Labels(){
		kindOfPromL = new JLabel("优惠类型：",JLabel.RIGHT);
		kindOfPromL.setSize(label_width, label_height);
		kindOfPromL.setFont(new Font("华文楷体",1,15));
		kindOfPromL.setForeground(Color.black);	
	}
	
	private void ComboBoxs(){
		String kindOfOd[] = {"折扣优惠","礼品赠送"};
		kindOfPromC = new JComboBox<String>(kindOfOd);
		kindOfPromC.setFont(new Font("华文楷体",1,15));
		kindOfPromC.setSize(textfield_width/2, textfield_height);
		kindOfPromC.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				PromInforT.removeAll();
				if(kindOfPromC.getSelectedItem().toString().equals("折扣优惠")){
					PromInforTM = new DefaultTableModel(PromKind1s, kindOfPromInfor);
					
				}
				else if(kindOfPromC.getSelectedItem().toString().equals("礼品赠送")){
					PromInforTM = new DefaultTableModel(PromKind2s, kindOfPromInfor);	
				}
				PromInforT.setModel(PromInforTM);
				PromInforT.updateUI();
				promotionlistui.setTable();
			}
		});
	}
	
	private void Tables(){
		
		kindOfPromInfor = new Vector<String>();
		kindOfPromInfor.add("酒店ID");
		kindOfPromInfor.add("优惠政策ID");
		kindOfPromInfor.add("优惠名称");
		kindOfPromInfor.add("折扣大小/%");
		kindOfPromInfor.add("优惠介绍");

		PromInforvo = vch.getProms(hotelid);	
		if(PromInforvo.size()==2){
			Vector<Promvo> promvo = new Vector<Promvo>();
			promvo = PromInforvo.get(0);
			for(int j=0;j<promvo.size();j++){
				Promvo promvo2 =promvo.get(j);
				PromKind1 = new Vector<String>();
				PromKind1.add(promvo2.gethotelid());
				PromKind1.add(promvo2.getPromID());
				PromKind1.add(promvo2.getname());
				PromKind1.add(String.valueOf(promvo2.getdiscount()));
				PromKind1.add(promvo2.getintroduction());
				PromKind1s.add(PromKind1);
			}
			promvo = new Vector<Promvo>();
			promvo = PromInforvo.get(1);
			for(int j=0;j<promvo.size();j++){
				Promvo promvo2 =promvo.get(j);
				PromKind2 = new Vector<String>();
				PromKind2.add(promvo2.gethotelid());
				PromKind2.add(promvo2.getPromID());
				PromKind2.add(promvo2.getname());
				PromKind2.add(String.valueOf(promvo2.getdiscount()));
				PromKind2.add(promvo2.getintroduction());
				PromKind2s.add(PromKind2);
			}
		}
		
		PromInforTM = new DefaultTableModel(PromKind1s, kindOfPromInfor);
		PromInforT = new JTable(PromInforTM){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		
		PromInforT.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);	
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();//单元格渲染器  
		tcr.setHorizontalAlignment(JLabel.CENTER);//居中显示  
		PromInforT.setDefaultRenderer(Object.class, tcr);//设置渲染器 
		
		PromInforT.setSize(frame_width, heightOfTable);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, frame_width, heightOfTable);
		scrollPane.getViewport().add(PromInforT);
		PromInforT.setFillsViewportHeight(true);
		
		promotionlistui.setTable();
		
		jpanelC.setLayout(null);
		jpanelC.setSize(frame_width, heightOfTable);
		jpanelC.setBackground(Color.white);
		jpanelC.add(scrollPane);	
	}
	
	private void Buttons(){
		final int button_width=140;
		final int button_height=35;
		
		makeProm = new JButton("制定优惠政策");
		makeProm.setFont(new Font("华文新魏",1,16));
		makeProm.setForeground(Color.white);
		makeProm.setBackground(new Color(0, 191, 255));
		makeProm.setBorderPainted(false);
		makeProm.setFocusPainted(false);
		makeProm.setSize(button_width, button_height);
		makeProm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		makeProm.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				promotionui.setaccredit(accredit);
				vch.makeProm();
			}
		});
		
		updateB = new JButton("刷新");
		updateB.setFont(new Font("华文新魏",1,16));
		updateB.setForeground(Color.white);
		updateB.setBackground(new Color(0, 191, 255));
		updateB.setBorderPainted(false);
		updateB.setFocusPainted(false);
		updateB.setSize(button_width/2, button_height);
		updateB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		updateB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				updatePromlist();
			}
		});
		
		deleteB = new JButton("删除");
		deleteB.setFont(new Font("华文新魏",1,16));
		deleteB.setForeground(Color.white);
		deleteB.setBackground(new Color(0, 191, 255));
		deleteB.setBorderPainted(false);
		deleteB.setFocusPainted(false);
		deleteB.setSize(button_width/2, button_height);
		deleteB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		deleteB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int row = PromInforT.getSelectedRow();
				String promid = PromInforT.getValueAt(row, 1).toString();
				vch.deleteProm(promid);
				updatePromlist();
			}
		});
	}
	
	private static void setTable(){
		
		//设置JTable列宽
		int numOfOdInfor = PromInforT.getColumnCount();
		for(int i=0;i<numOfOdInfor;i++){
		TableColumn Column = PromInforT.getColumnModel().getColumn(i);
		if(i==0||i==3){
			Column.setPreferredWidth(frame_width/numOfOdInfor/2);
			Column.setMaxWidth(frame_width/numOfOdInfor/2);
			Column.setMinWidth(frame_width/numOfOdInfor/2);
		}
		else if(i==1||i==2){
			Column.setPreferredWidth(frame_width/numOfOdInfor);
			Column.setMaxWidth(frame_width/numOfOdInfor);
			Column.setMinWidth(frame_width/numOfOdInfor);
		}
		else{
			Column.setPreferredWidth(frame_width/numOfOdInfor*2);
			Column.setMaxWidth(frame_width/numOfOdInfor*2);
			Column.setMinWidth(frame_width/numOfOdInfor*2);
			}
		}
		//设置行高
		PromInforT.setRowHeight(25);
		JTableHeader tableHeader = PromInforT.getTableHeader(); 
		tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 25));
	}
	
	protected static void setaccredit(String s){
		accredit = s;
	}
	
	protected static void updatePromlist(){
		PromInforvo = new Vector<Vector<Promvo>>();
		PromKind1 = new Vector<String>();
		PromKind2 = new Vector<String>();
		PromKind1s = new Vector<Vector<String>>();
		PromKind2s = new Vector<Vector<String>>();
		PromInforvo = vch.getProms(hotelid);	
		if(PromInforvo.size()==2){
			Vector<Promvo> promvo = new Vector<Promvo>();
			promvo = PromInforvo.get(0);
			for(int j=0;j<promvo.size();j++){
				Promvo promvo2 =promvo.get(j);
				PromKind1 = new Vector<String>();
				PromKind1.add(promvo2.gethotelid());
				PromKind1.add(promvo2.getPromID());
				PromKind1.add(promvo2.getname());
				PromKind1.add(String.valueOf(promvo2.getdiscount()));
				PromKind1.add(promvo2.getintroduction());
				PromKind1s.add(PromKind1);
			}
			promvo = new Vector<Promvo>();
			promvo = PromInforvo.get(1);
			for(int j=0;j<promvo.size();j++){
				Promvo promvo2 =promvo.get(j);
				PromKind2 = new Vector<String>();
				PromKind2.add(promvo2.gethotelid());
				PromKind2.add(promvo2.getPromID());
				PromKind2.add(promvo2.getname());
				PromKind2.add(String.valueOf(promvo2.getdiscount()));
				PromKind2.add(promvo2.getintroduction());
				PromKind2s.add(PromKind2);
			}
		}
		if(kindOfPromC.getSelectedIndex()==0)
			PromInforTM = new DefaultTableModel(PromKind1s, kindOfPromInfor);
		else if(kindOfPromC.getSelectedIndex()==1)
			PromInforTM = new DefaultTableModel(PromKind2s, kindOfPromInfor);
		PromInforT.setModel(PromInforTM);
		PromInforT.updateUI();
		promotionlistui.setTable();
	}
	
	private static void clear(){
		PromInforvo = null;
		PromKind1 = null;
		PromKind2 = null;
		PromKind1s = null;
		PromKind2s = null;
		
		accredit = "";
	}
}
