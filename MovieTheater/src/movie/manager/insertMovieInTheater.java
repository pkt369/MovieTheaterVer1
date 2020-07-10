package movie.manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.print.attribute.standard.Severity;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import movie.ControlTower;

public class insertMovieInTheater extends JPanel implements ActionListener{
	ControlTower ct;
	Font f1 = new Font("맑은 고딕", Font.BOLD, 22);
	Color backColor;
	String[] movieName;
	String[] auditorium;
	String[] reservation;
	Font f2 = new Font("맑은 고딕", Font.BOLD, 18);
	
	
	
	//선언하기
	JLabel whatTheater;
	JTextField movieTextField;
	JTextField dateTextField;
	JTextField timeTextField;
	JList<String> adminiTheater;
	JComboBox<String> movieBox;
	JComboBox<String> dateBox;
	JTextField hourField;
	JTextField minField;
	JComboBox<String> audBox;
	JButton insertButton;
	JList<String> reserList;
	JScrollPane reserScroll;
	JButton deleteBu;
	
	

	
	
	
	
	public insertMovieInTheater(ControlTower ct){
		this.ct = ct;
		setLayout(null);
		setBounds(300, 0, 900, 900);
		backColor = new Color(230, 230, 216);
		movieName = new String[ct.list.size()];
		for(int i = 0; i < ct.list.size(); i++) {
			movieName[i] = ct.list.get(i).getName();
		}
		
		//날짜 세팅
		SimpleDateFormat format = new SimpleDateFormat("MM월dd일");
//		Date time = new Date();
		String[] date = new String[5];
		for(int i = 0; i < 5; i++) {
			Calendar time = Calendar.getInstance();
			time.add(Calendar.DATE, i);
			date[i] = format.format(time.getTime());
		}
		
		//상영관 관리
		auditorium = new String[7];
		for(int i = 0; i < auditorium.length; i++) { // 먼저 7개관 세팅
			auditorium[i] = i+1 + "관";
		}
		

		//선언하기
		whatTheater = new JLabel("종로점");
		adminiTheater = new JList<String>();
		movieBox = new JComboBox<String>(movieName);
		JLabel dayText = new JLabel("날짜");
		dateBox = new JComboBox<String>(date);
		JLabel timeText = new JLabel("시간");
		JLabel hourText = new JLabel("시");
		JLabel minText = new JLabel("분");
		hourField = new JTextField();
		minField = new JTextField();
		audBox = new JComboBox<String>(auditorium);
		insertButton = new JButton(new ImageIcon("./images/insert.png"));
		reserList = new JList<String>(reservation);
		reserScroll = new JScrollPane(reserList);
		deleteBu = new JButton("삭제하기");
		reservationRoding();
				
		//좌표지정
		whatTheater.setBounds(1000, 10, 200, 50);
		movieBox.setBounds(330,600,250,40);
		dayText.setBounds(655, 550, 150, 50);
		dateBox.setBounds(605, 600, 150, 40);
		timeText.setBounds(645, 650, 150, 50);
		hourField.setBounds(620, 700, 100, 40);
		minField.setBounds(620, 750, 100, 40);
		hourText.setBounds(725, 700, 100, 40);
		minText.setBounds(725, 750, 100, 40);
		audBox.setBounds(790, 600, 100, 40);
		insertButton.setBounds(930, 610, 180, 180);
		reserScroll.setBounds(330, 50, 840, 440);
		deleteBu.setBounds((330 + 840) / 2, 500, 150, 40);
				
		//폰트지정
		whatTheater.setFont(f1);
		movieBox.setFont(f2);
		dayText.setFont(f1);
		dateBox.setFont(f2);
		timeText.setFont(f1);
		hourText.setFont(f1);
		minText.setFont(f1);
		audBox.setFont(f2);
		reserList.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		deleteBu.setFont(f1);
		
		//효과지정
		movieBox.addActionListener(this);
		dateBox.addActionListener(this);
		hourField.addActionListener(this);
		minField.addActionListener(this);
		audBox.addActionListener(this);
		insertButton.addActionListener(this);
		reserList.addListSelectionListener(null);
		deleteBu.addActionListener(this);
				
		//배경지정
		setBackground(backColor);
		insertButton.setBackground(null);
				
		//나머지 세팅
		
				
		//극장관리에 추가하기
		add(whatTheater);
		add(movieBox);
		add(dayText);
		add(dateBox);
		add(timeText);
		add(hourField);
		add(minField);
		add(hourText);
		add(minText);
		add(audBox);
		add(insertButton);
		add(reserScroll);
		add(deleteBu);
		
		
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(insertButton)) {
			if(hourField.getText().trim().isEmpty() || minField.getText().trim().isEmpty()) {
				return; // 칸에 아무것도 없으면 스트링값으로 밑에 들어가서 오류가 걸린다.
			}
			if(!(Integer.parseInt(hourField.getText()) >= 0 
					&& Integer.parseInt(hourField.getText()) < 24 
					&& Integer.parseInt(minField.getText()) >= 0
					&& Integer.parseInt(minField.getText()) < 60)) {
				return;
			}
			String te = hourField.getText() + "시" + minField.getText() + "분";
			ct.thAdmini.insertTheater("서울","종로점",movieBox.getSelectedItem().toString(),
					dateBox.getSelectedItem().toString(), te, 
					audBox.getSelectedItem().toString());
//			reservation = movieBox.getSelectedItem().toString();
			reservationRoding();
		}
		
		//삭제하기
		if(e.getSource().equals(deleteBu)) {
			String checkReser = reserList.getSelectedValue().toString();
			String[] array = checkReser.split("---"); // 띄워쓰기로 나눈다.
			// 제목, 날짜, 시간 순이다.
			ct.thAdmini.deleteTheater(array[0], array[1], array[2]);
			reservationRoding();
		}
	}
	public void reservationRoding() {
		ct.thList = ct.thAdmini.selectTheater();
		reservation = new String[ct.thList.size()];
		for(int i = 0; i < ct.thList.size(); i++) {
			reservation[i] = ct.thList.get(i).getMovie() + "---" + ct.thList.get(i).getDay() +
					"---" + ct.thList.get(i).getStartTime() + "---" + ct.thList.get(i).getAuditorium();
		}
		reserList.setListData(reservation);
	}
}
