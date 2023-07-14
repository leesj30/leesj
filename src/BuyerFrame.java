import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class BuyerFrame extends JFrame{
	
	public BuyerFrame(Buyer b) {
		
		super("Funding B");
		setSize(600,650);
		setLayout(null);
		//로그인한 id값받아서 표시
		JLabel idlb = new JLabel();
		idlb.setText(String.format("ID: %s", b.getID()));
		
		JLabel pinlb = new JLabel(); //포인트 값 받아서 표시
		pinlb.setText(String.format("Point: %s", b.getPoint()));
		
		JButton pinbtn = new JButton("Point Charge"); //포인트 충전 버튼
		
		
		// 테이블 생성
        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        
        // 열 추가
        tableModel.addColumn("Goods Name");
        tableModel.addColumn("Price");
        tableModel.addColumn("Count");
        tableModel.addColumn("Sub");
        tableModel.addColumn("Del");

        
        // 모든 Goods 리스트를 순회하면서 데이터 추가
        Manager m = Manager.getInstance();
        m.gfileRead();
        GoodsList gl = m.getForSaleGoods();
        ArrayList<Goods> forSaleGoodsList = gl.getAllGoods();
        for (Goods goods : forSaleGoodsList) {
        	String sellCount = goods.getBuycount() + " / " + goods.getSellcount();
            Object[] rowData = { goods.getName(), goods.getPrice(), sellCount};
            tableModel.addRow(rowData);
        }
        
        table.setModel(tableModel);
		
        TableColumn subbtn = table.getColumnModel().getColumn(3);
        subbtn.setCellRenderer(new BButtonCellRenderer());
        subbtn.setCellEditor(new BButtonCellEditor(table, b));
        
        TableColumn delbtn = table.getColumnModel().getColumn(4);
        delbtn.setCellRenderer(new ButtonCellRenderer());
        delbtn.setCellEditor(new BButtonCellEditor(table, b, 0));
        
        
        int rowHeight = 30; // 원하는 행의 높이 값 (픽셀 단위)
        table.setRowHeight(rowHeight);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 80, 540, 450);
        add(scrollPane);
		
		Font font = idlb.getFont();
		float fontSize = font.getSize() + 5.0f; // 기존 폰트 크기에서 5 픽셀씩 증가
		idlb.setFont(font.deriveFont(fontSize)); // 폰트 크기 조정
		pinlb.setFont(font.deriveFont(fontSize));
		pinbtn.setFont(font.deriveFont(fontSize));
		table.setFont(font.deriveFont(fontSize));
				
		idlb.setBounds(30, 25, 100, 20);
        pinlb.setBounds(220, 25, 150, 20);
        pinbtn.setBounds(400, 25, 150, 23);
        	
		add(idlb);
		add(pinlb);
		add(pinbtn);
			
		pinbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new ChargeFrame(b);
            	dispose();
            }
            
        });
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
}
