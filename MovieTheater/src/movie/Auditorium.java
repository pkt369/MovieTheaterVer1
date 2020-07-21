package movie;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutFocusTraversalPolicy;
import javax.swing.border.MatteBorder;

public class Auditorium extends JPanel implements ActionListener{
	ControlTower ct;
	JLabel background;
	Font f1 = new Font("돋움", Font.PLAIN, 20);
	Font f2 = new Font("돋움", Font.PLAIN, 13);
	Color weakBack = new Color(242, 240, 228);
	
	//선언하기
	JLabel adultText, childText, PreferentialText;
	JButton[] numBu;
	JLabel pickImage; JLabel pickMovie; JLabel TheaterName; JLabel day;
	JLabel pickAu; JLabel seatsLeft;
	JLabel time;
	
	JLabel[] alpha;
	JButton[] seat;
	
	
	
	
	Auditorium(ControlTower ct){
		this.ct = ct;
		setLayout(null);
		
		//백그라운드
		background = new JLabel(ct.imageSetSize(
				new ImageIcon("./images/SeatBackground.PNG"), 1200, 900));
		background.setBounds(0, 0, 1200, 900);
		
		//배열 한번에하기
		numBu = new JButton[27];
		for(int i = 0; i < numBu.length; i++) {
			numBu[i] = new JButton(Integer.toString(i % 9));
			numBu[i].setBounds(100 + (i % 9 * 45), 50 + (i / 9 * 40), 40, 40);
			numBu[i].setFont(f2);
			numBu[i].setBackground(weakBack);
			numBu[i].addActionListener(this);
			numBu[i].setFocusPainted(false);
			add(numBu[i]);
		}
		
		//선언하기
		adultText = new JLabel("어른");
		childText = new JLabel("어린이");
		PreferentialText = new JLabel("우대");
		
		
		
		//위치 지정
		adultText.setBounds(30, 50, 60, 40);
		childText.setBounds(30, 90, 60, 40);
		PreferentialText.setBounds(30, 130, 60, 40);
		
		//폰트 지정
		adultText.setFont(f1);childText.setFont(f1);PreferentialText.setFont(f1);
		//백그라운드 지정
		
		
		
		//추가하기
		add(adultText);add(childText);add(PreferentialText);
		
		//오른쪽 극장영화시간정보
		//선언은 ticketUI의 정보를 받아옴
		pickImage = new JLabel();
		pickMovie = new JLabel();
		TheaterName = new JLabel();
		day = new JLabel();
		pickAu = new JLabel();
		seatsLeft = new JLabel();
		time = new JLabel();
		
		pickImage.setBounds(570, 20, 120, 180);
		pickMovie.setBounds(700, 40, 160, 50); 
		TheaterName.setBounds(860, 40, 120, 50);
		day.setBounds(860, 80, 1000, 50);
		pickAu.setBounds(700, 80, 50, 50);
		time.setBounds(760, 80, 100, 50);
		
		pickImage.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		pickMovie.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		TheaterName.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		day.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		pickAu.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		time.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		
		add(pickImage);add(pickMovie);add(TheaterName);add(day);add(pickAu);add(seatsLeft);
		add(time);
		
		//좌석 배치
		
		alpha = new JLabel[11];
		for(int i = 0; i < 11; i++) {
			char ch = (char)(65 + i);
			alpha[i] = new JLabel(String.valueOf(ch), JLabel.CENTER); //알파벳생성
			alpha[i].setBounds(40, 260 + (i % 11 * 43), 40, 40);
			alpha[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
			alpha[i].setBorder(new MatteBorder(1,0,0,0,Color.black));
			add(alpha[i]);
		}
		
		seat = new JButton[300];
		makeSeat();
		
		add(background);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < numBu.length; i++) {
			if(e.getSource().equals(numBu[i])) {
				//어른, 아이, 우대 3개로 나누기
				if(i / 9 == 0) { //어른
					for(int j = 0; j < 9; j++) {
						numBu[j].setBackground(weakBack);
						numBu[j].setForeground(Color.black);
					}
					numBu[i].setBackground(Color.black);
					numBu[i].setForeground(Color.white);
				}else if(i / 9 == 1) {
					for(int j = 9; j < 18; j++) {
						numBu[j].setBackground(weakBack);
						numBu[j].setForeground(Color.black);
					}
					numBu[i].setBackground(Color.black);
					numBu[i].setForeground(Color.white);
				}else if(i / 9 == 2) {
					for(int j = 18; j < 27; j++) {
						numBu[j].setBackground(weakBack);
						numBu[j].setForeground(Color.black);
					}
					numBu[i].setBackground(Color.black);
					numBu[i].setForeground(Color.white);
				}
				
					
			}	
		}
	}
	
	public void makeSeat() {
		for(int i = 0; i < 220; i++) {
			
			seat[i] = new JButton(new ImageIcon("./images/number/" + Integer.toString(i % 20 + 1) + ".PNG"));
			if(i % 20 < 5) {
				seat[i].setBounds(95 + (i % 20 * 42), 260 + (i / 20 * 43), 40, 40);
			}else if(i % 20 < 15) {
				seat[i].setBounds(125 + (i % 20 * 42), 260 + (i / 20 * 43), 40, 40);
			}else {
				seat[i].setBounds(155 + (i % 20 * 42), 260 + (i / 20 * 43), 40, 40);
			}
			
			if((i >= 200 && i < 205) || (i >= 215 && i < 220)) {
				seat[i].setVisible(false);
			}
				
				
			seat[i].setBorderPainted(false);
			seat[i].setBackground(new Color(247,247,239));
			//seat[i].setFont(new Font("Consolas", Font.PLAIN, 10));
			add(seat[i]);
			
			
			
		}
	}
}
