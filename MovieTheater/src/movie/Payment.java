package movie;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Payment extends JPanel implements ItemListener, ActionListener{
	ControlTower ct;
	
	JLabel background;
	Color backColor = new Color(242, 240, 228);
	JLabel priceTag; JLabel price1, price2, price3;
	JLabel step1, step2;
	JComboBox<String> sale; 
	String[] saleString = {"카카오 할인(3000원할인)", "SKT 할인(4000원할인)", 
			"LG 할인(1000원할인)", "KT 할인(3000원할인)"};
	JRadioButton[] selectPayment; ButtonGroup payGroup;
	String[] selectString = {"신용카드", "휴대폰결제", "계좌 이체", "카카오 페이", "네이버 페이"};
	JLabel credit; JComboBox<String> creditCard;
	String[] creditString = {"삼성카드", "카카오카드", "sk카드", "농협카드", "국민카드", "SC제일은행", "기업카드"};
	JButton payButton;
	String sellSeats;
	
	public Payment(ControlTower ct) {
		this.ct = ct;
		setLayout(null);
		
		
		//선언하기
		background = new JLabel();
		priceTag = new JLabel(ct.imageSetSize(
				new ImageIcon("./images/paymentBase2.png"), 245, 460));
		step1 = new JLabel(ct.imageSetSize(
				new ImageIcon("./images/step1.png"), 890, 45));
		step2 = new JLabel(ct.imageSetSize(
				new ImageIcon("./images/step2.png"), 890, 45));
		price1 = new JLabel("0"); price2 = new JLabel("0"); price3 = new JLabel("0");
		sale = new JComboBox<String>(saleString);
		selectPayment = new JRadioButton[5];
		for(int i = 0; i < selectPayment.length; i++) {
			selectPayment[i] = new JRadioButton(selectString[i]);
		}
		payGroup = new ButtonGroup(); 
		credit = new JLabel("카드종류");
		creditCard = new JComboBox<String>(creditString);
		payButton = new JButton(ct.imageSetSize(
				new ImageIcon("./images/payButton.png"), 280, 120));
		
		//위치 지정
		background.setBounds(0, 0, 1200, 900);
		priceTag.setBounds(915, 20, 245, 460);
		step1.setBounds(0, 20, 890, 45);
		step2.setBounds(0, 220, 890, 45);
		price1.setBounds(970, 69, 150, 50);
		price2.setBounds(970, 223, 150, 50);
		price3.setBounds(970, 420, 150, 50);
		sale.setBounds(10, 75, 870, 40);
		for(int i = 0; i < selectPayment.length; i++) {
			selectPayment[i].setBounds(30 + (i % 5 * 160), 280, 150, 60);
		}
		credit.setBounds(150, 360, 100, 50);
		creditCard.setBounds(250, 365, 150, 40);
		payButton.setBounds(870, 700, 280, 120);
		
		//백그라운드 지정
		background.setBackground(backColor);
		for(int i = 0; i < selectPayment.length; i++) {
			selectPayment[i].setBackground(backColor);
		}
		creditCard.setBackground(backColor);
		
		//효과 지정
		sale.addItemListener(this);
		for(int i = 0; i < selectPayment.length; i++) {
			selectPayment[i].addItemListener(this);
		}
		payButton.addActionListener(this);
		
		
		//폰트 지정
		price1.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		price2.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		price3.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		sale.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		for(int i = 0; i < selectPayment.length; i++) {
			selectPayment[i].setFont(new Font("맑은 고딕", Font.BOLD, 18));
		}
		credit.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		creditCard.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		//폰트 색상
		price1.setForeground(Color.white);
		price2.setForeground(new Color(127, 208, 232));
		price3.setForeground(new Color(211, 186, 92));
		
		//나머지 지정
		background.setOpaque(true);
		price1.setHorizontalAlignment(JLabel.RIGHT);
		price2.setHorizontalAlignment(JLabel.RIGHT);
		price3.setHorizontalAlignment(JLabel.RIGHT);
		payButton.setBorderPainted(false);
		
		
		
		//추가하기
		add(sale);
		for(int i = 0; i < selectPayment.length; i++) {
			payGroup.add(selectPayment[i]);
			add(selectPayment[i]);
		}
		add(credit);
		add(creditCard);
		add(payButton);
		
		add(price1);
		add(price2);
		add(price3);
		
		add(step1);
		add(step2);
		add(priceTag);
		
		
		add(background);
		
		
	}
	
	public String getPrice(String st) {
		return st.substring(st.indexOf("(") + 1, st.lastIndexOf("원"));
	}

	@Override
	public void itemStateChanged(ItemEvent e) {	
		if(e.getSource().equals(sale)) {
			price2.setText(getPrice(sale.getSelectedItem().toString()));
			String str = String.valueOf(Integer.parseInt(price1.getText()) - 
					Integer.parseInt(price2.getText()));
			price3.setText(str);
		}
		credit.setVisible(false);
		creditCard.setVisible(false);
		if(e.getSource().equals(selectPayment[0])) {
			credit.setVisible(true);
			creditCard.setVisible(true);
		}else if(e.getSource().equals(selectPayment[1])) {
			
		}else if(e.getSource().equals(selectPayment[2])) {
			
		}else if(e.getSource().equals(selectPayment[3])) {
			
		}else if(e.getSource().equals(selectPayment[4])) {
			
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(payButton)) {
			System.out.println("예약되었습니다.");
			ct.thAdmini.updateTheater(ct.auditorium.pickMovie.getText(), ct.ticketUi.pickDa.getText()
					, ct.auditorium.time.getText(), sellSeats);
			ct.completed.pickImage.setIcon(ct.imageSetSize(
					ct.ticketUi.img, 180, 260));
			ct.completed.pickMovie.setText(ct.auditorium.pickMovie.getText());
			ct.completed.pickTh.setText(ct.ticketUi.pickTh.getText());
			ct.completed.day.setText(ct.auditorium.day.getText() + "  " + ct.auditorium.time.getText());
			ct.completed.howmany.setText(getPeople() + "명");
			ct.completed.seat.setText(ct.auditorium.pickSeat.getText().
					substring(ct.auditorium.pickSeat.getText().indexOf(":") + 2));
			ct.completed.howmuch.setText(price3.getText() + "원");
			
			ct.changePanel("completed");
		}
		
	}
	public String getPeople() {
		int people = 0;
		for(int i = 0; i < ct.auditorium.numBu.length;i++) {
			if(ct.auditorium.numBu[i].getBackground().equals(Color.black)) {
				people += Integer.parseInt(ct.auditorium.numBu[i].getText())% 9;
			}
		}
		return String.valueOf(people);
	}
	
	
}
