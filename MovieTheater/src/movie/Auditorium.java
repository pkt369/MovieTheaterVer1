package movie;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutFocusTraversalPolicy;
import javax.swing.border.MatteBorder;

import movie.data.theater;

public class Auditorium extends JPanel implements ActionListener{
	ControlTower ct;
	JLabel background;
	Font f1 = new Font("돋움", Font.PLAIN, 20);
	Font f2 = new Font("돋움", Font.PLAIN, 13);
	Color weakBack = new Color(242, 240, 228);
	
	//선언하기
	JLabel adultText, childText, PreferentialText;
	final JButton[] numBu; int pickCount = 0;
	JLabel pickImage; JLabel pickMovie; JLabel TheaterName; JLabel day;
	JLabel pickAu; JLabel seatsLeft; JLabel pickSeat;
	JLabel time;
	
	JLabel[] alpha;
	JButton[] seat;
	ImageIcon reservation, already, number;
	
	JLabel howMuch; String[] howMany; int total;
	JButton goPaymentButton; ImageIcon[] img;

	
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
		adultText = new JLabel("일반");
		childText = new JLabel("청소년");
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
		pickSeat = new JLabel("선택한 좌석: ");
		
		pickImage.setBounds(570, 20, 120, 180);
		pickMovie.setBounds(700, 40, 160, 50); 
		TheaterName.setBounds(860, 40, 120, 50);
		day.setBounds(860, 80, 1000, 50);
		pickAu.setBounds(700, 80, 50, 50);
		time.setBounds(760, 80, 100, 50);
		seatsLeft.setBounds(960, 40, 200, 50);
		pickSeat.setBounds(700, 120, 500, 50);
		
		pickImage.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		pickMovie.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		TheaterName.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		day.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		pickAu.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		time.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		seatsLeft.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		pickSeat.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		
		add(pickImage);add(pickMovie);add(TheaterName);add(day);add(pickAu);add(seatsLeft);
		add(time);add(pickSeat);
		
		//좌석 배치
		
		alpha = new JLabel[11];
		for(int i = 0; i < 11; i++) {
			char ch = (char)(65 + i);
			alpha[i] = new JLabel(String.valueOf(ch), JLabel.CENTER); //알파벳생성
			alpha[i].setBounds(20, 260 + (i % 11 * 43), 40, 40);
			alpha[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
			alpha[i].setBorder(new MatteBorder(1,0,0,0,Color.black));
			add(alpha[i]);
		}
		
		seat = new JButton[300];
		makeSeat();
		reservation = new ImageIcon("./images/number/reservation.PNG");
		already = new ImageIcon("./images/number/already.PNG");
		
		//오른쪽 리스트
		howMuch = new JLabel("");
		howMuch.setBounds(1010, 250, 200, 200);
		howMuch.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		add(howMuch);
		
		howMany = new String[3];
		howMany[0] = "";howMany[1] = "";howMany[2] = "";
		
		img = new ImageIcon[2];
		img[0] = new ImageIcon("./images/결제선택1.png");
		img[1] = new ImageIcon("./images/결제선택2.png");
		
		goPaymentButton = new JButton(img[0]);	
		goPaymentButton.setBounds(1010, 660, 160, 160);
		goPaymentButton.setBackground(new Color(247,247,239));
		goPaymentButton.addActionListener(this);
		add(goPaymentButton);

		
		add(background);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(goPaymentButton) && goPaymentButton.getIcon().equals(img[1])) {
			System.out.println("실행");
		}
		for(int i = 0; i < seat.length; i++) {
			if(e.getSource().equals(seat[i])) {
				char ch = (char)(65 + i / 20);
				if(pickCount > 0 && !seat[i].getIcon().equals(reservation) &&
						!seat[i].getIcon().equals(already)) {
					seat[i].setIcon(reservation);
					pickCount--;
					//선택한 좌석 글로 보여주기
					pickSeat.setText(pickSeat.getText() + String.valueOf(ch) + (i % 20 + 1) + " ");
					
				}else if(seat[i].getIcon().equals(reservation)) {
					seat[i].setIcon(new ImageIcon("./images/number/" + Integer.toString(i % 20 + 1) + ".PNG"));
					pickCount++;
					//선택 없앤 좌석 없애기
					String addSeat = "";
					String[] splitSeat = pickSeat.getText().split(" ");
					for(int j = 0; j < splitSeat.length; j++) {
						if((String.valueOf(ch) + (i % 20 + 1)).equals(splitSeat[j])) {
							continue;
						}
						addSeat += (splitSeat[j] + " ");
						pickSeat.setText(addSeat);
					}
					
				}
				//버튼 변경
				if(pickCount == 0 && !pickSeat.getText().equals("선택한 좌석: ")) {
					goPaymentButton.setIcon(img[1]);
					
				}else {
					goPaymentButton.setIcon(img[0]);
				}
				return; //리턴을 해주는 이유는 밑에서 pickCount를 초기화해주기 때문이다.
				//만약 좌석선택을 누르지않았다면 초기화되는 방식.
			}
		}
		if(e.getSource().equals(goPaymentButton) && goPaymentButton.getIcon().equals(img[1])) {
			ct.payment.price1.setText(String.valueOf(total));
			ct.payment.price3.setText(String.valueOf(total));
			if(ct.getSellSeats(pickMovie.getText(), day.getText(), time.getText()) == null) {
				ct.payment.sellSeats = pickSeat.getText().substring(
						pickSeat.getText().indexOf(":") + 2);
			}else {
			ct.payment.sellSeats = ct.getSellSeats(pickMovie.getText(), day.getText(), 
					time.getText()) +
					pickSeat.getText().substring(
					pickSeat.getText().indexOf(":") + 2);
			}
			ct.changePanel("payment");
		}
		
		
		
		for(int i = 0; i < numBu.length; i++) {
			if(e.getSource().equals(numBu[i])) {
				pickSeat.setText("선택한 좌석: ");
				goPaymentButton.setIcon(img[0]);
				pickCount = 0;
				for(int j = 0; j < 220; j++) {
					if(seat[j].getIcon().equals(already)) {
						continue;
					}
					seat[j].setIcon(new ImageIcon("./images/number/" + 
				Integer.toString(j % 20 + 1) + ".PNG"));		
				}
				//어른, 아이, 우대 3개로 나누기
				if(i / 9 == 0) { //일반
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

		
		for(int i = 0; i < numBu.length; i++) {
			if(numBu[i].getBackground().equals(Color.black)) {
				//선택할 수 있는 카운트 정하기
				pickCount += Integer.parseInt(numBu[i].getText());
				
			}
		}
		total = 0;
		howMuch.setText("");
		for(int i = 0 ; i < 3; i++) {
			howMany[i] = "";
		}
		for(int i = 0; i < 27; i++) {
			if(numBu[i].getBackground().equals(Color.black) && !numBu[i].getText().equals("0")) {
				if(i / 9 == 0) {
					howMany[0] = "일반 : " + numBu[i].getText() + "명";
					total += (Integer.parseInt(numBu[i].getText()) * 12000);
				}else if(i / 9 == 1) {
					howMany[1] = "청소년 : " + numBu[i].getText() + "명";
					total += (Integer.parseInt(numBu[i].getText()) * 10000);
				}else if(i / 9 == 2){
					howMany[2] = "우대 : " + numBu[i].getText() + "명";
					total += (Integer.parseInt(numBu[i].getText()) * 5000);
				}
			}
		}
		String result = "<html>";
		for(int i = 0; i < 3; i++) {
			if(howMany[i] != "") {
				result += howMany[i] + "<br>";
			}
			if(i == 2 && total != 0) {
				result += "최종 금액: " + total + "원 </html>"; 
				howMuch.setText(result);
			}
		}

		
		
	}
	
	public void makeSeat() {
		for(int i = 0; i < 220; i++) {
			
			seat[i] = new JButton(new ImageIcon("./images/number/" + Integer.toString(i % 20 + 1) + ".PNG"));
			if(i % 20 < 5) {
				seat[i].setBounds(75 + (i % 20 * 42), 260 + (i / 20 * 43), 40, 40);
			}else if(i % 20 < 15) {
				seat[i].setBounds(105 + (i % 20 * 42), 260 + (i / 20 * 43), 40, 40);
			}else {
				seat[i].setBounds(135 + (i % 20 * 42), 260 + (i / 20 * 43), 40, 40);
			}
			
			if((i >= 200 && i < 205) || (i >= 215 && i < 220)) {
				seat[i].setVisible(false);
			}
				
			seat[0].setEnabled(true);	
			seat[i].setBorderPainted(false);
			seat[i].setBackground(new Color(247,247,239));
			seat[i].addActionListener(this);
			//seat[i].setFont(new Font("Consolas", Font.PLAIN, 10));
			add(seat[i]);
			
			
			
		}
	}
	
	public void numBuColorReset() {		
		for(int j = 0; j < 9; j++) {
			numBu[j].setBackground(weakBack);
			numBu[j].setForeground(Color.black);
		}
		numBu[0].setBackground(Color.black);
		numBu[0].setForeground(Color.white);
				
		for(int j = 9; j < 18; j++) {
			numBu[j].setBackground(weakBack);
			numBu[j].setForeground(Color.black);
		}
		numBu[9].setBackground(Color.black);
		numBu[9].setForeground(Color.white);
		
		for(int j = 18; j < 27; j++) {
			numBu[j].setBackground(weakBack);
			numBu[j].setForeground(Color.black);
		}
		numBu[18].setBackground(Color.black);
		numBu[18].setForeground(Color.white);		
	}
}
