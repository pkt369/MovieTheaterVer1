package movie;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class FirstUI extends JPanel implements ActionListener, Runnable{
	int width = 1200;
	int height = 900;
	ControlTower ct;
	int count = 0;
	
	JButton ticketing;
	ImageIcon[] adver;
	JLabel ad;
	JLabel background;

	FirstUI(ControlTower ct){
		this.ct = ct;
		setLayout(null);
		
		
		
		adver = new ImageIcon[4];
		for(int i = 0; i < adver.length; i++) {
			adver[i] = ct.imageSetSize(
					new ImageIcon("./images/adver" + i + ".jpg"), 1100, 400);
		}
		ad = new JLabel(adver[0]); //광고
		background = new JLabel(new ImageIcon("./images/Firstbackground.png"));
		
		ad.setBounds(50, 30, 1100, 400);
		background.setBounds(0,0,1200,900);
		
		ticketing = new JButton("예매하기");
		ticketing.setBounds(50, 450, 550, 350);
		
		JButton checking = new JButton();
		checking.setBounds(600, 450, 550, 350);
		
		
		add(ad);
		add(checking);
		add(ticketing);
		add(background);
		
		ticketing.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(ticketing)) {
			ct.changePanel("ticketUi");
		}
		
	}

	@Override
	public void run() {
		while(true) {
			if(count == 4) {
				count = 0;
			}
			try {
				ad.setIcon(adver[count]);
				Thread.sleep(3000);
				count++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
