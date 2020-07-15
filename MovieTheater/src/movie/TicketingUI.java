package movie;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicScrollBarUI;

import movie.data.Movie;
import movie.data.MovieDAO;
import movie.data.theater;

public class TicketingUI extends JPanel implements ListSelectionListener{
	int width = 1200;
	int height = 900;
	ControlTower ct;
	
	Font f1 = new Font("맑은 돋움", Font.BOLD, 30);
	Font f2 = new Font("맑은 돋움", Font.BOLD, 22);
	Color c1 = new Color(233,235,209);
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
		JLabel blackBack = new JLabel();
		try {
			img = ImageIO.read(movieUrl[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pickImage = new JLabel();
		pickName = new JLabel();
		th = new JLabel("극장");
		da = new JLabel("일시");
		au = new JLabel("상영관");
		pickTh = new JLabel("");
		pickDa = new JLabel("");
		pickAu = new JLabel("");
		area = new JButton("서울");
		thList = new JList<String>(thstring);
		thPane = new JScrollPane(thList);
		dayList = new JList<String>(date);
		dayPane = new JScrollPane(dayList);
		
		
		//위치 지정
		jpMovie.setBounds(30, 70, 300, 500);
		back.setBounds(0, 0, width, 50);
		blackBack.setBounds(0, 620, width, 280);
		pickImage.setBounds(35, 610, 170, 250);
		pickName.setBounds(230, 650, 250, 50);
		th.setBounds(500, 650, 250, 50);
		da.setBounds(500, 700, 250, 50);
		au.setBounds(500, 750, 250, 50);
		pickTh.setBounds(580, 650, 250, 50);
		pickDa.setBounds(580, 700, 250, 50);
		pickAu.setBounds(580, 750, 250, 50);
		area.setBounds(380, 100, 120, 50);
		thPane.setBounds(520, 100, 130, 500);
		dayPane.setBounds(670, 100, 110, 500);
		
		
		
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
		
		
		//배경 지정
		jlMovie.setBackground(c1);
		blackBack.setBackground(new Color(40, 40, 40));
		area.setBackground(c1);
		thList.setBackground(c1);
		dayList.setBackground(c1);
		
		
		//효과 지정
		jlMovie.addListSelectionListener(this);
		thList.addListSelectionListener(this);
		dayList.addListSelectionListener(this);
		
		//나머지 지정
		jlMovie.setCellRenderer(new MovieRender());
		//jlMovie.setVisibleRowCount(6); //보여줄 행의 갯수
		jpMovie.setBorder(null);
		blackBack.setOpaque(true); //불투명하게 만듦
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
		
		
		
		//폰트 색깔 지정
		pickName.setForeground(new Color(255, 255, 255));
		th.setForeground(new Color(255, 255, 255));
		da.setForeground(new Color(255, 255, 255));
		au.setForeground(new Color(255, 255, 255));
		pickTh.setForeground(new Color(255, 255, 255));
		pickDa.setForeground(new Color(255, 255, 255));
		pickAu.setForeground(new Color(255, 255, 255));
		
		//추가하기
		add(pickImage);
		add(pickName);
		add(th);
		add(da);
		add(au);
		add(pickTh);
		add(pickDa);
		add(pickAu);
		add(area);
		add(thPane);		
		add(back);
		add(jpMovie);
		add(dayPane);
		
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
	ImageIcon imageSetSize(Image icon, int i, int j) {
		ImageIcon ic = new ImageIcon(icon);
		Image img = ic.getImage().getScaledInstance(i, i, java.awt.Image.SCALE_SMOOTH);
		ImageIcon reimg = new ImageIcon(img);
		return reimg;
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(!e.getValueIsAdjusting()) {
			pickTh.setText(thList.getSelectedValue());
			pickDa.setText(dayList.getSelectedValue());
			 
			for(int i = 0; i < movieName.length; i++) {
				if(movieName[i].equals(jlMovie.getSelectedValue())) {
					try {
						img = ImageIO.read(movieUrl[i]);
						pickImage.setIcon(imageSetSize(img, 170, 250));
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
				 for(int i = 0; i < movieName.length; i++) { //영화비교
					
				 }
			}
		}
	}
}

	
