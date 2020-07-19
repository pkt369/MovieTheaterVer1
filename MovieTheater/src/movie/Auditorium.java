package movie;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Auditorium extends JPanel implements ActionListener{
	ControlTower ct;
	JLabel background;
	Font f1 = new Font("돋움", Font.PLAIN, 20);
	Font f2 = new Font("돋움", Font.PLAIN, 13);
	Color weakBack = new Color(242, 240, 228);
	
	//선언하기
	JLabel adultText, childText, PreferentialText;
	JButton[] numBu;
	
	
	
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
	
	public void checkColor(JButton[] bu) {
//		if(bu.getBackground().equals(new Color(242,240,228))) {
//			
//		}
	}
}
//효과 지정
		/*
		 * pickImage = ct.pickMovieInfo[0]; pickName = ct.pickMovieInfo[1]; pickTh =
		 * ct.pickMovieInfo[2]; pickDa = ct.pickMovieInfo[3]; pickAu =
		 * ct.pickMovieInfo[4];
		 */
/*
 * //선언하기
	JLabel pickImage;
	JLabel pickName;
	JLabel th; JLabel da; JLabel au;
	JLabel pickTh; JLabel pickDa; JLabel pickAu;
	JButton pickSeatButton;
	//선언하기
	pickImage = new JLabel();
	pickName = new JLabel();
	th = new JLabel("극장");
	da = new JLabel("일시");
	au = new JLabel("상영관");
	pickTh = new JLabel("");
	pickDa = new JLabel("");
	pickAu = new JLabel("");
	pickSeatButton = new JButton(ct.imageSetSize(
			new ImageIcon("./images/pickSeatButton.PNG"), 180, 180));
 * //위치 지정 pickImage.setBounds(35, 635, 170, 205); pickName.setBounds(230, 650,
 * 250, 50); th.setBounds(500, 650, 250, 50); da.setBounds(500, 700, 250, 50);
 * au.setBounds(500, 750, 250, 50); pickTh.setBounds(580, 650, 250, 50);
 * pickDa.setBounds(580, 700, 250, 50); pickAu.setBounds(580, 750, 250, 50);
 * pickSeatButton.setBounds(970, 650, 180, 180);
 * 
 * //폰트 지정 th.setFont(new Font("맑은 고딕", Font.BOLD, 20)); da.setFont(new
 * Font("맑은 고딕", Font.BOLD, 20)); au.setFont(new Font("맑은 고딕", Font.BOLD, 20));
 * pickTh.setFont(new Font("맑은 고딕", Font.BOLD, 22)); pickDa.setFont(new
 * Font("맑은 고딕", Font.BOLD, 22)); pickAu.setFont(new Font("맑은 고딕", Font.BOLD,
 * 22));
 * 
 * //폰트 색상 지정 pickName.setForeground(new Color(255, 255, 255));
 * th.setForeground(new Color(255, 255, 255)); da.setForeground(new Color(255,
 * 255, 255)); au.setForeground(new Color(255, 255, 255));
 * pickTh.setForeground(new Color(255, 255, 255)); pickDa.setForeground(new
 * Color(255, 255, 255)); pickAu.setForeground(new Color(255, 255, 255));
 * 
 * //배경 지정 pickSeatButton.setBackground(new Color(40, 40, 40));
 * 
 * //효과 지정 pickSeatButton.addActionListener(this);
 * 
 * //나머지 지정 pickSeatButton.setBorder(null);
 * 
 * //추가하기 add(pickImage); add(pickName); add(th); add(da); add(au); add(pickTh);
 * add(pickDa); add(pickAu); add(pickSeatButton);
 */
