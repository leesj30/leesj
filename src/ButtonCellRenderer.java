import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

class ButtonCellRenderer extends JButton implements TableCellRenderer { //J테이블의 버튼을 넣기 위한 클래스
	private JButton button;
	
    public ButtonCellRenderer() {
    	button = new JButton("Del");
        setOpaque(true);
    }
    
    public ButtonCellRenderer(int a) {
    	button = new JButton("Purchase");
        setOpaque(true);
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return button;
    }
}