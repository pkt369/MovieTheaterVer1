package movie;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicScrollBarUI;

import movie.data.Movie;
import movie.data.MovieDAO;
import movie.data.theater;

public class TicketingUI extends JPanel implements ListSelectionListener, ActionListener{
	int width = 1200;
	int height = 900;
	ControlTower ct;
	
	Font f1 = new Font("맑은 돋움", Font.BOLD, 30);
	Font f2 = new Font("맑은 돋움", Font.BOLD, 22);
	Color c1 = new Color(242,240,228);
	MovieDAO movieControll = new MovieDAO();
	
	ArrayList<Movie> movieList = null;
	String[] movieName;
	int[] movieAge;
	URL[] movieUrl;
	
	ArrayList<theater> theaterList = null;
	String[] city;
	String[] TheaterName;
	String[] day;

	private final Map<String, ImageIcon> ageImageMap;
	
	//선언
	JList jlMovie;
	JLabel pickImage;
	Image img;
	JLabel pickName;
	JLabel th; JLabel da; JLabel au;
	JLabel pickTh; JLabel pickDa; JLabel pickAu;
	JButton pickSeatButton;
	JButton area;
	JList<String> thList;  JScrollPane thPane;
	String[] thstring = {"종로점", "서울역"};
	JList<String> dayList; JScrollPane dayPane;
	JButton[] timeBu;
	
	
	
	
	TicketingUI(ControlTower ct){
		this.ct = ct;
		setLayout(null);
		
		movieList = movieControll.selectMovie();
		movieName = new String[movieList.size()];
		movieAge = new int[movieList.size()];
		movieUrl = new URL[movieList.size()];
		//가져온 영화들의 이름, 나이들을 저장할 String 생성
		for(int i = 0; i < movieList.size(); i++) {
			movieName[i] = movieList.get(i).getName();	
			movieAge[i] = movieList.get(i).getAge();
			movieUrl[i] = movieList.get(i).getUrl();
		}
		
		//극장정보 가져오기
		theaterList = ct.thAdmini.selectTheater();
		city = new String[theaterList.size()];
		TheaterName = new String[theaterList.size()];
		day = new String[theaterList.size()];
		for(int i = 0; i < theaterList.size(); i++) {
			city[i] = theaterList.get(i).getCity();
			TheaterName[i] = theaterList.get(i).getTheaterName();
			day[i] = theaterList.get(i).getDay();
		}
		
		try {
			img = ImageIO.read(movieUrl[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//날짜 세팅
		SimpleDateFormat format = new SimpleDateFormat("MM월dd일");
		String[] date = new String[10];
		for(int i = 0; i < date.length; i++) {
			Calendar time = Calendar.getInstance();
			time.add(Calendar.DATE, i);
			date[i] = format.format(time.getTime());
		}
		
		ageImageMap = createAgeMap(movieAge, movieName); //영화나이에 관한 이미지 삽입		

		//선언하기
		jlMovie = new JList(movieName);
		JScrollPane jpMovie = new JScrollPane(jlMovie);
		JLabel back = new JLabel(new ImageIcon("./images/ticketTitle.jpg"));
		area = new JButton("서울");
		thList = new JList<String>(thstring);
		thPane = new JScrollPane(thList);
		dayList = new JList<String>(date);
		dayPane = new JScrollPane(dayList);
		timeBu = new JButton[16];
		for(int i = 0; i < timeBu.length; i++) {
			timeBu[i] = new JButton();
		}
		JLabel blackBack = new JLabel();
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
		
		
		//위치 지정
		jpMovie.setBounds(30, 70, 300, 500);
		back.setBounds(0, 0, width, 50);
		area.setBounds(380, 100, 120, 50);
		thPane.setBounds(520, 100, 130, 500);
		dayPane.setBounds(670, 100, 110, 500);
		for(int i = 0; i < timeBu.length; i++) {
			timeBu[i].setBounds(820 + ((i % 2) * 190), 100 + (i / 2)* 60, 160, 40);
		}
		blackBack.setBounds(0, 620, 1200, 280);
		pickImage.setBounds(35, 635, 170, 205);
		pickName.setBounds(230, 650, 250, 50);
		th.setBounds(500, 650, 250, 50);
		da.setBounds(500, 700, 250, 50);
		au.setBounds(500, 750, 250, 50);
		pickTh.setBounds(580, 650, 250, 50);
		pickDa.setBounds(580, 700, 250, 50);
		pickAu.setBounds(580, 750, 250, 50);
		pickSeatButton.setBounds(970, 650, 180, 180);
		
		
		
		//폰트 지정
		jlMovie.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		pickName.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		th.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		da.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		au.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		pickTh.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		pickDa.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		pickAu.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		area.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		thList.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		dayList.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		for(int i = 0; i < timeBu.length; i++) {
			timeBu[i].setFont(new Font("맑은 고딕", Font.BOLD, 18));
		}
		
		//폰트 색깔 지정
		pickName.setForeground(new Color(255, 255, 255));
		th.setForeground(new Color(255, 255, 255));
		da.setForeground(new Color(255, 255, 255));
		au.setForeground(new Color(255, 255, 255));
		pickTh.setForeground(new Color(255, 255, 255));
		pickDa.setForeground(new Color(255, 255, 255));
		pickAu.setForeground(new Color(255, 255, 255));
		
		//배경 지정
		jlMovie.setBackground(c1);
		area.setBackground(c1);
		thList.setBackground(c1);
		dayList.setBackground(c1);
		for(int i = 0; i < timeBu.length; i++) {
			timeBu[i].setBackground(c1);
		}
		blackBack.setBackground(new Color(40, 40, 40));
		pickSeatButton.setBackground(new Color(40, 40, 40));
		
		//효과 지정
		jlMovie.addListSelectionListener(this);
		thList.addListSelectionListener(this);
		dayList.addListSelectionListener(this);
		for(int i = 0; i < timeBu.length; i++) {
			timeBu[i].addActionListener(this);
		}
		pickSeatButton.addActionListener(this);
		
		//나머지 지정
		jlMovie.setCellRenderer(new MovieRender());
		//jlMovie.setVisibleRowCount(6); //보여줄 행의 갯수
		jpMovie.setBorder(null);
		
		jpMovie.getVerticalScrollBar().setBackground(c1); //가로세로 스크롤바 배경변경
		jpMovie.getHorizontalScrollBar().setBackground(c1);
		jpMovie.getVerticalScrollBar().setUI(new BasicScrollBarUI() { 
			protected void configureScrollBarColors() {
		        this.thumbColor = new Color(124,124,124);
			}
		});
		area.setHorizontalAlignment(JLabel.RIGHT);
		thPane.setBorder(null);
		thPane.getVerticalScrollBar().setBackground(c1); //가로세로 스크롤바 배경변경
		thPane.getHorizontalScrollBar().setBackground(c1);
		thPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() { 
			protected void configureScrollBarColors() {
		        this.thumbColor = new Color(124,124,124);
			}
		});
		dayPane.setBorder(null);
		pickSeatButton.setBorder(null);
		blackBack.setOpaque(true); //불투명하게 만듦
		
		
		//관 + 시간 버튼 텍스트 없을때 안보이게 하기
		for(int i = 0; i < timeBu.length; i++) {
			 timeBu[i].setVisible(false);
		 }
		 
		

		//추가하기
		add(area);
		add(thPane);		
		add(back);
		add(jpMovie);
		add(dayPane);
		for(int i = 0; i < timeBu.length; i++) {
			add(timeBu[i]);
		}
		add(pickImage);
		add(pickName);
		add(th);
		add(da);
		add(au);
		add(pickTh);
		add(pickDa);
		add(pickAu);	
		add(pickSeatButton);
		
		add(blackBack);

		//백그라운드
		setBackground(c1);
	}
	
	private Map<String, ImageIcon> createAgeMap(int[] age, String[] name) {
		Map<String, ImageIcon> map = new HashMap<>();
		
		for(int i = 0; i < age.length; i++) {
			map.put(name[i], new ImageIcon("./images/"+ age[i] + ".png"));
		}
		return map;
	}
	
	class MovieRender extends DefaultListCellRenderer {
		
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			
			JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			label.setIcon(ageImageMap.get((String)value));
			
			return label;
		}

	}
	
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(!e.getValueIsAdjusting()) {
			int co = 0;
			pickAu.setText("");
			pickTh.setText(thList.getSelectedValue());
			pickDa.setText(dayList.getSelectedValue());
			pickSeatButton.setIcon(ct.imageSetSize(
					new ImageIcon("./images/pickSeatButton.PNG"), 180, 180));
			
			 
			for(int i = 0; i < movieName.length; i++) {
				if(movieName[i].equals(jlMovie.getSelectedValue())) {
					try {
						img = ImageIO.read(movieUrl[i]);
						pickImage.setIcon(ct.imageSetSize(img, 170, 205));
						pickName.setText(movieName[i]);
						break;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			if(!(pickName.getText() == null) && !(pickTh.getText() == null) &&
					!(pickDa.getText() == null)) {
				 for(int i = 0; i < theaterList.size(); i++) { //비교는 예약과만 비교를 하면된다.
					if(pickName.getText().equals(theaterList.get(i).getMovie()) &&
							pickTh.getText().equals(theaterList.get(i).getTheaterName()) && 
							pickDa.getText().equals(theaterList.get(i).getDay())) {
						
						timeBu[co].setText(theaterList.get(i).getAuditorium()+ " " +
								theaterList.get(i).getStartTime());
						co++;	
					}
				 }
				
				 //예약과 비교가 끝난다면 버튼 생성
				 for(int i = 0; i < timeBu.length; i++) {
					 timeBu[i].setVisible(false);
				 }
				 for(int i = 0; i < co; i++) {
					 timeBu[i].setVisible(true);
				 }
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < timeBu.length; i++) {
			if(e.getSource().equals(timeBu[i])) {
				pickAu.setText(timeBu[i].getText());
				pickSeatButton.setIcon(ct.imageSetSize(
						new ImageIcon("./images/pickSeatButton2.PNG"), 180, 180));
			}
		}	
		if (e.getSource().equals(pickSeatButton)) {
			if(pickAu.getText() == "") {
				JLabel jt = new JLabel("시간을 선택해 주세요");
				jt.setFont(new Font("맑은 고딕", Font.PLAIN, 22));
				JOptionPane.showMessageDialog(null, jt);
			}else{
				ct.auditorium.pickImage.setIcon(ct.imageSetSize(img, 100, 120));
				ct.auditorium.pickMovie.setText(pickName.getText());
				ct.auditorium.TheaterName.setText(pickTh.getText());
				ct.auditorium.day.setText(pickDa.getText());
				ct.auditorium.pickAu.setText(pickAu.getText().substring(0, 2));
				ct.auditorium.time.setText(pickAu.getText().
						substring(pickAu.getText().lastIndexOf(" ") + 1));
				

				for(int i = 0; i < ct.thList.size(); i++) {
					if(ct.thList.get(i).getMovie().equals(pickName.getText()) &&
							ct.thList.get(i).getDay().equals(pickDa.getText()) &&
							ct.thList.get(i).getStartTime().equals(pickAu.getText().
									substring(pickAu.getText().lastIndexOf(" ") + 1))) {
						if(pickAu.getText().substring(0, 2).equals("1관") ||
								pickAu.getText().substring(0, 2).equals("2관") ||
								pickAu.getText().substring(0, 2).equals("3관")) {
							
							String beforeSt = ct.thList.get(i).getSellSeats();
							if(beforeSt == null) {
								ct.auditorium.seatsLeft.setText("남은 좌석 : 293 / 293");
								break;
							}
							String[] st = new String[beforeSt.length() / 3];
							for(int j = 0; j < beforeSt.length() / 3; j++) {	
								st[j] = beforeSt.substring(j*3, j*3+2);
							}
							ct.auditorium.seatsLeft.setText("남은 좌석 : " + Integer.toString(293 - beforeSt.length() / 3) + " / 293");
						}
					}
				}
				ct.changePanel("auditorium");	
			}
		}
		
	}
	
	

}

	
