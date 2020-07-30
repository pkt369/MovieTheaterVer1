package movie;

import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
	JButton checking;

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
		
		ticketing = new JButton(new ImageIcon("./images/goReservation.png"));
		ticketing.setBounds(50, 450, 550, 350);
		ticketing.setBackground(null);
		ticketing.setBorderPainted(false);
		ticketing.setOpaque(false);
		ticketing.setContentAreaFilled(false);
		
		checking = new JButton(new ImageIcon("./images/goReservation2.png"));
		checking.setBounds(600, 450, 550, 350);
		checking.setBackground(null);
		checking.setBorderPainted(false);
		checking.setOpaque(false);
		checking.setContentAreaFilled(false);
		checking.addActionListener(this);
//		

		
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
		}else if(e.getSource().equals(checking)) {
			try {
				Desktop.getDesktop().browse(new URI("http://pkt369.github.io/"));
			} catch (IOException | URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
