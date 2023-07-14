import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JoinFrame extends JFrame{
	public JoinFrame() {
		super("Funding Join");
		setSize(400,270);
		setLayout(null);
		
		JLabel idlb = new JLabel("ID : ");
		JLabel pwdlb = new JLabel("PW : ");
		JLabel keylb = new JLabel("Key : ");
		JTextField idtxf = new JTextField(20);
		JPasswordField pwdf = new JPasswordField(20);
		
		JRadioButton Sradiobtn = new JRadioButton("Seller");
		JRadioButton Bradiobtn = new JRadioButton("Buyer");
		
		ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(Sradiobtn);
        buttonGroup.add(Bradiobtn);
		
	    JPanel pan = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pan.add(Sradiobtn);
		pan.add(Bradiobtn);

		JButton jinbtn = new JButton("Join");
		JButton chkbtn = new JButton("Check");
		
		Font font = idlb.getFont();
		float fontSize = font.getSize() + 5.0f; 
		idlb.setFont(font.deriveFont(fontSize));
		pwdlb.setFont(font.deriveFont(fontSize));
		keylb.setFont(font.deriveFont(fontSize));
		Sradiobtn.setFont(font.deriveFont(fontSize));
		Bradiobtn.setFont(font.deriveFont(fontSize));
		jinbtn.setFont(font.deriveFont(fontSize));
		
		
		idlb.setBounds(100, 50, 100, 20);
		idtxf.setBounds(140, 50, 150, 20);
		pwdlb.setBounds(90, 80, 100, 20);
		pwdf.setBounds(140, 80, 150, 20);
		keylb.setBounds(88, 110, 100, 30);
		pan.setBounds(140, 110, 200, 30);
		jinbtn.setBounds(140, 170, 80, 30);
		chkbtn.setBounds(300, 50, 80, 30);
		
		add(idlb);
		add(idtxf);
		add(pwdlb);
		add(pwdf);
		add(keylb);
		add(pan);
		add(jinbtn);
		add(chkbtn);
		
		jinbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Sradiobtn.isSelected()) {
                	String id = idtxf.getText();
    				char[] pwd = pwdf.getPassword();
    				Manager m = Manager.getInstance();
    				Seller s = new Seller(id, pwd);
    				m.join(s);
    				dispose();
                } else if (Bradiobtn.isSelected()) {
              
                	String id = idtxf.getText();
    				char[] pwd = pwdf.getPassword();
    				Manager m = Manager.getInstance();
    				Buyer b = new Buyer(id, pwd);
    				m.join(b);
    				dispose();
                }
            }
        });
		
		chkbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String id = idtxf.getText();
                Manager m = Manager.getInstance();
                boolean bl = m.idCheck(id);
                if(bl == false)
                	JOptionPane.showMessageDialog(null, "중복된 ID입니다");
                else 
                	JOptionPane.showMessageDialog(null, "사용 가능한 ID입니다");
            }
        });
		 
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}