import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

class BButtonCellRenderer extends JButton implements TableCellRenderer { //J테이블의 버튼을 넣기 위한 클래스
	private JButton button;
    	
    public BButtonCellRenderer() {
    	button = new JButton();
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        button.setText("Sub"); // 버튼의 텍스트를 'X'로 설정
        return button;
    }
}