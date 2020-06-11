package movie;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class FirstUI extends JFrame implements ActionListener{
	int width = 1200;
	int height = 900;
	boolean changeDisplay = false;
	ControlTower ct = new ControlTower();
	
	FirstUI(){
		JLabel ad = new JLabel(new ImageIcon("./images/ad.jpg")); //광고
		ad.setBounds(50, 30, 1100, 400);
		
		JButton ticketing = new JButton("예매하기");
		ticketing.setBounds(50, 450, 550, 350);
		
		JButton checking = new JButton();
		checking.setBounds(600, 450, 550, 350);
		
		Container container = getContentPane();
		container.setLayout(null);
		
		
		container.add(ad);
		container.add(checking);
		container.add(ticketing);
		
		ticketing.addActionListener(this);
		
		setTitle("영화예매");
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
		new TicketingUI(ct);
	}
}
