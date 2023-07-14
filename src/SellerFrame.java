import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class SellerFrame extends JFrame {
	public SellerFrame(Seller s){
		super("Funding S");
		setSize(600,650);
		setLayout(null);
		JLabel idlb = new JLabel();
		idlb.setText(String.format("ID: %s", s.getID()));
		JLabel pinlb = new JLabel(); //포인트 값 받아서 표시
		pinlb.setText(String.format("Money: %s", s.getMoney()));
		//표 형식 처럼 만들려면 JTable로 만들어야하며, 테이블모델클래스 필요?
		
		JButton gcbtn = new JButton("Goods Create"); 
		
		// 테이블 생성
        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        
        // 열 추가
        tableModel.addColumn("Goods Name");
        tableModel.addColumn("Price");
        tableModel.addColumn("Count");
        tableModel.addColumn("Purchase");
        tableModel.addColumn("Del");
        
        
        // Seller 객체의 Goods 리스트를 순회하면서 데이터 추가
        for (Goods goods : s.goods) {
        	String sellCount = goods.getBuycount() + " / " + goods.getSellcount();
            Object[] rowData = { goods.getName(), goods.getPrice(), sellCount};
            tableModel.addRow(rowData);
        }
		
        table.setModel(tableModel);
        
        int rowHeight = 30; // 원하는 행의 높이 값 (픽셀 단위)
        table.setRowHeight(rowHeight);
        
        // 버튼 열 추가
        TableColumn pbtn = table.getColumnModel().getColumn(3);
        pbtn.setCellRenderer(new ButtonCellRenderer(0));
        pbtn.setCellEditor(new ButtonCellEditor(table, s));
        
        TableColumn buttonColumn = table.getColumnModel().getColumn(4);
        buttonColumn.setCellRenderer(new ButtonCellRenderer());
        buttonColumn.setCellEditor(new ButtonCellEditor(table, s));
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 80, 540, 450);
        add(scrollPane);
        
		Font font = idlb.getFont();
		float fontSize = font.getSize() + 5.0f; // 기존 폰트 크기에서 5 픽셀씩 증가
		idlb.setFont(font.deriveFont(fontSize)); // 폰트 크기 조정
		pinlb.setFont(font.deriveFont(fontSize));
		gcbtn.setFont(font.deriveFont(fontSize));
		table.setFont(font.deriveFont(fontSize));
		
		idlb.setBounds(30, 25, 100, 20);
        pinlb.setBounds(220, 25, 100, 20);
        gcbtn.setBounds(240, 550, 150 , 30);
        
		add(idlb);
		add(pinlb);
		add(gcbtn);
		
		gcbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new SGoods(s);
            	dispose();
            }
            
        });
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}