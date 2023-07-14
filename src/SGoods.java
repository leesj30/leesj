import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SGoods extends JFrame {
	public SGoods(Seller s){
		super("Goods Create");
		setLayout(null);
		setSize(400, 250);
		
		JLabel namelb = new JLabel("Goods Name: ");
		JTextField nametxf = new JTextField(20);
		JLabel pricelb = new JLabel("Goods Price: ");
		JTextField pricetxf = new JTextField(20);
		JLabel cntlb = new JLabel("Goods Sell Count: ");
		JTextField cnttxf = new JTextField(20);
		JLabel numlb = new JLabel("Sell Count >= 5");
		
		JButton gcbtn = new JButton("Goods Create"); 
		
		Font font = namelb.getFont();
		float fontSize = font.getSize() + 5.0f; // 기존 폰트 크기에서 5 픽셀씩 증가
		namelb.setFont(font.deriveFont(fontSize)); // 폰트 크기 조정
		pricelb.setFont(font.deriveFont(fontSize));
		cntlb.setFont(font.deriveFont(fontSize));
		gcbtn.setFont(font.deriveFont(fontSize));
		
		
		//테이블은 일단 건너뛰기
		
		namelb.setBounds(70, 25, 150, 20);
        nametxf.setBounds(190, 25, 150, 20);
        pricelb.setBounds(70, 55, 150, 20);
        pricetxf.setBounds(190, 55, 150, 20);
        cntlb.setBounds(60, 85, 200, 20);
        cnttxf.setBounds(220, 85, 120, 20);
        numlb.setBounds(150, 105, 150, 20);
        gcbtn.setBounds(125, 150, 150 , 30);
        
        
       	
        
		add(namelb);
		add(nametxf);
		add(pricelb);
		add(pricetxf);
		add(cntlb);
		add(cnttxf);
		add(gcbtn);
		add(numlb);
		
		
		gcbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Manager m = Manager.getInstance();
            	Goods g = m.Registration(s);
            	String name = nametxf.getText();
            	g.setName(name);
                int price = Integer.parseInt(pricetxf.getText());
                g.setPrice(price);
                int cnt = Integer.parseInt(cnttxf.getText());
                g.setSellCount(cnt);
                m.gfileWrite(g);
                m.fileWrite(s);
                dispose();
                new SellerFrame(s);
                
            }
            
        });
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}