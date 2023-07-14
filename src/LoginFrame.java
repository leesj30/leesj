import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
	public LoginFrame() {
		super("Funding Login");
		setSize(400,250);
		setLayout(null);
		
		JLabel idlb = new JLabel("ID : ");
		JLabel pwdlb = new JLabel("PW : ");
		JTextField idtxf = new JTextField(20);
		JPasswordField pwdf = new JPasswordField(20);
		JButton logbtn = new JButton("Login");
		JButton jinbtn = new JButton("Join");
		
		Font font = idlb.getFont();
		float fontSize = font.getSize() + 5.0f; 
		idlb.setFont(font.deriveFont(fontSize));
		pwdlb.setFont(font.deriveFont(fontSize));
		logbtn.setFont(font.deriveFont(fontSize));
		jinbtn.setFont(font.deriveFont(fontSize));
		
		idlb.setBounds(100, 50, 100, 20);
		idtxf.setBounds(140, 50, 150, 20);
		pwdlb.setBounds(90, 80, 100, 20);
		pwdf.setBounds(140, 80, 150, 20);
		logbtn.setBounds(220, 130, 90, 40);
		jinbtn.setBounds(90, 130, 90, 40);
		
		add(idlb);
		add(idtxf);
		add(pwdlb);
		add(pwdf);
		add(logbtn);
		add(jinbtn);
		
		 jinbtn.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                new JoinFrame();
	            }
	        });
		 
		 logbtn.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String id = idtxf.getText();
	                char[] pwd = pwdf.getPassword();
	                Manager m = Manager.getInstance();
	                User u = m.login(id, pwd);
	                if(u != null) {
	                	if (u instanceof Buyer) {
	                        // `u`가 `Buyer` 클래스의 인스턴스인 경우
	                        Buyer b = (Buyer) u;
	                        new BuyerFrame(b);
	                        dispose();
	                	}
	                	else {
	                		Seller s = (Seller) u;
	                		new SellerFrame(s);
	                		dispose();
	                	}
	                }
	                else {
	                	boolean bl = m.userExist(id);
	                	if(bl == true)
	                		JOptionPane.showMessageDialog(null, "pwd가 틀렸습니다");
	                	else
	                		JOptionPane.showMessageDialog(null, "존재하지 않는 ID입니다");
	                }
	            }
	        });
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}