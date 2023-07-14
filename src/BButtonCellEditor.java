import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class BButtonCellEditor extends DefaultCellEditor { //J테이블의 버튼을 넣기 위한 클래스
    private JButton button;
    private Buyer b;
    private String goodsName;
    private JTable table;
    
    public BButtonCellEditor(JTable table, Buyer b) {
        super(new JTextField());
        this.table = table;
        this.b = b;
        button = new JButton();
        button.setOpaque(true);
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int selectedRow = table.getSelectedRow();
                goodsName = (String) table.getValueAt(selectedRow, 0);
                Manager m = Manager.getInstance();
                m.Subscribe(b, goodsName); 
                m.fileWrite(b);
                SwingUtilities.getWindowAncestor(button).dispose(); // 현재 창 닫기
                new BuyerFrame(b); // 새로운 SellerFrame 열기
            }
        });
    }
    
    public BButtonCellEditor(JTable table, Buyer b, int a) {
        super(new JTextField());
        this.table = table;
        this.b = b;
        button = new JButton();
        button.setOpaque(true);
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int selectedRow = table.getSelectedRow();
                goodsName = (String) table.getValueAt(selectedRow, 0);
                Manager m = Manager.getInstance();
                m.Unsubscribe(b, goodsName);
                m.fileWrite(b);
                SwingUtilities.getWindowAncestor(button).dispose(); // 현재 창 닫기
                new BuyerFrame(b); // 새로운 SellerFrame 열기
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