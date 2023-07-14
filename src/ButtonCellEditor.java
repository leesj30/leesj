import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ButtonCellEditor extends DefaultCellEditor { //J테이블의 버튼을 넣기 위한 클래스
    private JButton button;
    private Seller seller;
    private String goodsName;
    private JTable table;

    public ButtonCellEditor(JTable table, Seller seller) {
        super(new JTextField());
        this.table = table;
        this.seller = seller;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int selectedRow = table.getSelectedRow();
                goodsName = (String) table.getValueAt(selectedRow, 0);
            	Manager m = Manager.getInstance();
            	m.Cancel(seller, goodsName);
            	m.fileWrite(seller);
            	SwingUtilities.getWindowAncestor(button).dispose(); // 현재 창 닫기
                new SellerFrame(seller); // 새로운 SellerFrame 열기
            }
        });
    }
    
    public ButtonCellEditor(JTable table, Seller seller, int a) {
        super(new JTextField());
        this.table = table;
        this.seller = seller;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int selectedRow = table.getSelectedRow();
                goodsName = (String) table.getValueAt(selectedRow, 0);
            	Manager m = Manager.getInstance();
            	seller.Purchase(goodsName);
            	m.fileWrite(seller);
            	SwingUtilities.getWindowAncestor(button).dispose(); // 현재 창 닫기
                new SellerFrame(seller); // 새로운 SellerFrame 열기
            }
        });
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    	this.goodsName = (value == null) ? "" : value.toString();
        button.setText(this.goodsName);
        return button;
    }
}