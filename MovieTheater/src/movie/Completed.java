package movie;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Completed extends JPanel implements ActionListener{
	ControlTower ct;
	
	Color BackColor = new Color(241,241,229);
	
	JLabel BackImage;
	JLabel finish;					//예매가 완료 되었습니다.
	JLabel pickImage;				//사진
	JLabel pickMovie, movieString; //선택한영화, 영화 : 
	JLabel pickTh, theaterString;		//선택한극장, 극장 :
	JLabel day, dayString;				//날짜 + 시간, 일시:
	JLabel howmany, howmanyString;//인원,인원 :
	JLabel seat, seatString;			//좌석, 좌석 :
	JLabel howmuch, howmuchString; //가격, 가격 :
	JButton completedButton;
	
	Completed(ControlTower ct) {
	this.ct = ct;
	setLayout(null);
	setBackground(BackColor);
	
	BackImage = new JLabel(new ImageIcon("./images/ReservationCompleted.png"));
	finish = new JLabel("예매가 완료 되었습니다.");
	pickImage = new JLabel(ct.imageSetSize(
			new ImageIcon("./images/paymentBase2.png"), 180, 260));
	movieString = new JLabel("영화");
	pickMovie = new JLabel();
	theaterString = new JLabel("극장");
	pickTh = new JLabel();
	dayString = new JLabel("일시");
	day = new JLabel();
	howmanyString = new JLabel("인원");
	howmany = new JLabel();
	seatString = new JLabel("좌석");
	seat = new JLabel();
	howmuchString = new JLabel("가격");
	howmuch = new JLabel();
	completedButton = new JButton(ct.imageSetSize(
			new ImageIcon("./images/finishButton.png"), 230, 90));
	

	BackImage.setBounds(0, 0, 1200, 60);
	finish.setBounds(430, 110, 450, 60);
	pickImage.setBounds(300, 200, 180, 260);
	movieString.setBounds(560, 190, 90, 50);
	pickMovie.setBounds(660, 185, 300, 60);
	theaterString.setBounds(560, 250, 300, 60);
	pickTh.setBounds(660, 250, 300, 60);
	dayString.setBounds(560, 310, 300, 60);
	day.setBounds(660, 310, 300, 60);
	howmanyString.setBounds(560, 370, 300, 60);
	howmany.setBounds(660, 370, 300, 60);
	seatString.setBounds(560, 430, 300, 60);
	seat.setBounds(660, 430, 300, 60);
	howmuchString.setBounds(560, 490, 300, 60);
	howmuch.setBounds(660, 490, 300, 60);
	completedButton.setBounds(475, 650, 230, 90);
	
	
	
	finish.setFont(new Font("맑은 고딕", Font.BOLD, 27));
	movieString.setFont(new Font("맑은 고딕", Font.BOLD, 22));
	pickMovie.setFont(new Font("맑은 고딕", Font.BOLD, 22));
	theaterString.setFont(new Font("맑은 고딕", Font.BOLD, 22));
	pickTh.setFont(new Font("맑은 고딕", Font.BOLD, 22));
	dayString.setFont(new Font("맑은 고딕", Font.BOLD, 22));
	day.setFont(new Font("맑은 고딕", Font.BOLD, 22));
	howmanyString.setFont(new Font("맑은 고딕", Font.BOLD, 22));
	howmany.setFont(new Font("맑은 고딕", Font.BOLD, 22));
	seatString.setFont(new Font("맑은 고딕", Font.BOLD, 22));
	seat.setFont(new Font("맑은 고딕", Font.BOLD, 22));
	howmuchString.setFont(new Font("맑은 고딕", Font.BOLD, 22));
	howmuch.setFont(new Font("맑은 고딕", Font.BOLD, 22));
	
	completedButton.addActionListener(this);
	
	
	
	add(finish); add(pickImage); add(movieString); add(pickMovie); add(theaterString); add(pickTh); add(dayString); add(day); 
	add(howmanyString); add(howmany); add(seatString); add(seat); add(howmuchString); add(howmuch);
	
	add(completedButton);
	add(BackImage);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(completedButton)) {
			ct.dispose();
		}
		
	}
	

}
