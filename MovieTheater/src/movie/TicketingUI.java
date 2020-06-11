package movie;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import movie.data.Movie;
import movie.data.MovieDAO;

class TicketingUI extends JFrame{
	int width = 1200;
	int height = 900;
	ControlTower ct;
	Font f1 = new Font("맑은 돋움", Font.BOLD, 30);
	ArrayList<Movie> movieList = null;
	MovieDAO movieControll = new MovieDAO();
	Color c1 = new Color(233,235,209);
	
	
	TicketingUI(ControlTower ct){
		this.ct = ct;
		movieList = movieControll.selectMovie();
		
		JPanel movie = new JPanel();
		movie.setLayout(null);
		movie.setBounds(0, 0, 300, height);
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		for(Movie e : movieList) {
			model.addElement(/* new ImageIcon("./images/ad.jpg") + */e.getName());
		}
		
		JList<String> jlMovie = new JList<String>(model);
		
		jlMovie.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlMovie.setVisibleRowCount(10); //보여줄 행의 갯수
		jlMovie.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		jlMovie.setBackground(c1);
		
		JScrollPane jpMovie = new JScrollPane(jlMovie);
		jpMovie.setBounds(30, 70, 300, 600);
		jpMovie.setBorder(null);
		
		
		JButton[] movieName = new JButton[10];
		for(int i = 0; i < movieName.length; i++) {
			movieName[i] = new JButton("영화제목"); //주소값 생성
			movieName[i].setBounds(30, 70 + 50 * i, 200, 50); //배치생성
			movieName[i].setFont(f1); //폰트 변경
			movie.add(movieName[i]); //패널에 넣어줌
		}
		
		
		
		
		JPanel whereTheater = new JPanel();
		whereTheater.setLayout(null);
		
		
		
		JPanel PickDay = new JPanel();
		PickDay.setLayout(null);
		
		
		
		JPanel time = new JPanel();
		time.setLayout(null);
		
		
		
		Container container = getContentPane();
		container.setLayout(null);
		container.setBackground(c1);
		
		JLabel back = new JLabel(new ImageIcon("./images/ticketTitle.jpg"));
		back.setBounds(0, 0, width, 50);
		
		container.add(back);
		container.add(jpMovie);
		
		setTitle("영화예매");
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	public void PickMovie(){
		
	}
}
