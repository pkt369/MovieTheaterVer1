package movie;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import movie.data.Movie;
import movie.data.MovieDAO;

public class TicketingUI extends JPanel{
	int width = 1200;
	int height = 900;
	ControlTower ct;
	
	Font f1 = new Font("맑은 돋움", Font.BOLD, 30);
	Color c1 = new Color(233,235,209);
	MovieDAO movieControll = new MovieDAO();
	
	ArrayList<Movie> movieList = null;
	String[] movieName;
	int[] movieAge;

	private final Map<String, ImageIcon> ageImageMap;
	
	
	
	
	TicketingUI(ControlTower ct){
		this.ct = ct;
		setLayout(null);
		
		movieList = movieControll.selectMovie();
		movieName = new String[movieList.size()];
		movieAge = new int[movieList.size()];
		
		//가져온 영화들의 이름, 나이들을 저장할 String 생성
		for(int i = 0; i < movieList.size(); i++) {
			movieName[i] = movieList.get(i).getName();	
			movieAge[i] = movieList.get(i).getAge();
		}
		
		ageImageMap = createAgeMap(movieAge, movieName); //영화나이에 관한 이미지 삽입		

		
		JPanel movie = new JPanel();
		movie.setLayout(null);
		movie.setBounds(0, 0, 300, height);
		
		
		JList jlMovie = new JList(movieName);
		jlMovie.setCellRenderer(new MovieRender());
		
		//jlMovie.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlMovie.setVisibleRowCount(6); //보여줄 행의 갯수
		jlMovie.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		jlMovie.setBackground(c1);
		
		JScrollPane jpMovie = new JScrollPane(jlMovie);
		jpMovie.setBounds(30, 70, 300, 400);
		jpMovie.setBorder(null);
		
		
		
		
		

		
		JPanel whereTheater = new JPanel();
		whereTheater.setLayout(null);
		
		
		
		JPanel PickDay = new JPanel();
		PickDay.setLayout(null);
		
		
		
		JPanel time = new JPanel();
		time.setLayout(null);
		
//		Container container = getContentPane();
//		container.setLayout(null);
//		container.setBackground(c1);
		
		
		JLabel back = new JLabel(new ImageIcon("./images/ticketTitle.jpg"));
		
		back.setBounds(0, 0, width, 50);
		
		add(back);
		add(jpMovie);
		
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
	
}
