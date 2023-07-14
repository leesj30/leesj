import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ChargeFrame extends JFrame{
	public ChargeFrame(Buyer b){
		super("Point Charge");
		setSize(350,200);
		setLayout(null);
		JLabel pinlb = new JLabel("Now Point : ");
		JLabel explb = new JLabel("Charge point>=10000");
		pinlb.setText(String.format("Point: %s", b.getPoint()));
		JLabel cglb = new JLabel("Charge Point : ");
		JTextField pintxf = new JTextField(20);
		JButton cgbtn = new JButton("Charge");
		
		Font font = pinlb.getFont();
		float fontSize = font.getSize() + 3.0f; 
		pinlb.setFont(font.deriveFont(fontSize));
		cglb.setFont(font.deriveFont(fontSize));
		cgbtn.setFont(font.deriveFont(fontSize));
		
		pinlb.setBounds(30, 30, 130, 20);
		cglb.setBounds(30, 60, 130, 20);
		pintxf.setBounds(150, 60, 130, 20);
		explb.setBounds(90, 90, 150, 20);
		cgbtn.setBounds(90, 130, 130, 20);
		
		add(pinlb);
		add(cglb);
		add(pintxf);
		add(cgbtn);
		add(explb);
		
		cgbtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		            int point = Integer.parseInt(pintxf.getText());
		            b.Charge(point);
		            JOptionPane.showMessageDialog(null, String.format("포인트가 %d원 충전되었습니다", point));
		            dispose();
		            
		            Manager m = Manager.getInstance();
		            m.fileWrite(b); // 충전 완료 후 정보 저장
		            
		            new BuyerFrame(b); // 메인 프레임으로 돌아감
		        } catch (NumberFormatException a) {
		            a.printStackTrace();
		        }
		    }
		});
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}