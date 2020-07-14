package movie;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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

public class TicketingUI extends JPanel implements ListSelectionListener{
	int width = 1200;
	int height = 900;
	ControlTower ct;
	
	Font f1 = new Font("맑은 돋움", Font.BOLD, 30);
	Color c1 = new Color(233,235,209);
	MovieDAO movieControll = new MovieDAO();
	
	ArrayList<Movie> movieList = null;
	String[] movieName;
	int[] movieAge;
	URL[] movieUrl;

	private final Map<String, ImageIcon> ageImageMap;
	
	//선언
	JList jlMovie;
	JLabel pickImage;
	Image img;
	JLabel pickName;
	JLabel th; JLabel da; JLabel au;
	
	
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
		
		//위치 지정
		jpMovie.setBounds(30, 70, 300, 500);
		back.setBounds(0, 0, width, 50);
		blackBack.setBounds(0, 620, width, 280);
		pickImage.setBounds(60, 630, 150, 200);
		
		//이미지 크기 조정
		
		
		//폰트 지정
		jlMovie.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		
		//배경 지정
		jlMovie.setBackground(c1);
		blackBack.setBackground(new Color(0, 0, 0));
		
		//효과 지정
		jlMovie.addListSelectionListener(this);
		
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
		
//		UIManager.getLookAndFeelDefaults().put( "ScrollBar:\"ScrollBar.button\".size", 0);
//		UIManager.getLookAndFeelDefaults().put("ScrollBar.decrementButtonGap", 0);
//		UIManager.getLookAndFeelDefaults().put( "ScrollBar.incrementButtonGap", 0);
		
		
		//추가하기
		add(pickImage);
		
		add(back);
		add(jpMovie);
		
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
			for(int i = 0; i < movieName.length; i++) {
				if(movieName[i].equals(jlMovie.getSelectedValue())) {
					try {
						img = ImageIO.read(movieUrl[i]);
						pickImage.setIcon(imageSetSize(img, 150, 200));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
		
		
	}
}

	
