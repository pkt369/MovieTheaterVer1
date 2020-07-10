package movie.manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import movie.ControlTower;
import movie.data.Movie;
import movie.data.MovieDAO;

public class ManagerAdministrateMovie extends JPanel implements ActionListener, MouseListener{
	ArrayList<Movie> list = new ArrayList<Movie>();
	String[] movieList;
	ControlTower ct;
	Color weakBlack;
	Color backColor;
	String menuName;
	public JPanel menu;
	
	//선언하기
	JPanel movieAdministrate;
	JButton AddMinMovie;
	JList<String> movies;
	JScrollPane movieScroll;
	JTextField movieNameField;
	JTextField movieAgeField;
	JButton inputMovie;
	JButton DeleteMovie;
	JLabel result;
	
	//극장 선언하기
	JPanel theaterAdmini;
	JButton theaterBu;
	
	
	
	public ManagerAdministrateMovie(ControlTower ct){
		this.ct = ct;
		setLayout(null);
		Font whiteF1 = new Font("맑은 고딕", Font.BOLD, 30);
		weakBlack = new Color(82,82,82);
		backColor = new Color(230, 230, 216);
		menuName = "AddMinMovie";
		//영화가져오기
		list = ct.admini.selectMovie();
		movieList = new String[list.size()];
		for(int i = 0; i < list.size(); i++) {
			movieList[i] = "영화 제목 : " + list.get(i).getName() + "  나이 제한 : " + list.get(i).getAge();
		}
		
		
		menu = new JPanel();
		menu.setLayout(null);
		menu.setBounds(0, 0, 300, 900);
		menu.setBackground(weakBlack);
		
		
		movieAdministrate = new JPanel();
		movieAdministrate.setLayout(null);
		movieAdministrate.setBounds(300, 0, 900, 900);
		movieAdministrate.setBackground(backColor);
		
		movies = new JList<String>(movieList);
		movies.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		movies.setBackground(backColor);
		
		movieScroll = new JScrollPane(movies);
		movieScroll.setBounds(90, 60, 710, 450);
		
		
		
		//선언하기
		AddMinMovie = new JButton("영화 관리하기");
		movieNameField = new JTextField("영화 제목");
		movieAgeField = new JTextField("나이 제한");
		inputMovie = new JButton("영화 넣기");
		DeleteMovie = new JButton("영화 삭제하기");
		result = new JLabel("입력완료했습니다.");
		
		//임시 저장
		
		//좌표지정
		AddMinMovie.setBounds(30, 50, 250, 90);
		movieNameField.setBounds(110, 580, 300, 50);
		movieAgeField.setBounds(480, 580, 300, 50);
		inputMovie.setBounds(110, 670, 300, 100);
		DeleteMovie.setBounds(480, 670, 300, 100);
		result.setBounds(330, 520, 300, 50);
		
		//폰트 지정
		AddMinMovie.setFont(whiteF1);
		movieNameField.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		movieAgeField.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		inputMovie.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		DeleteMovie.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		result.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		//효과 지정
		AddMinMovie.addActionListener(this);
		AddMinMovie.addMouseListener(this);
		inputMovie.addActionListener(this);
		DeleteMovie.addActionListener(this);
		inputMovie.addMouseListener(this);
		DeleteMovie.addMouseListener(this);
		movies.addListSelectionListener(null);
		
		//배경 지정
		AddMinMovie.setBackground(weakBlack);
		inputMovie.setBackground(backColor);
		DeleteMovie.setBackground(backColor);
		result.setBackground(backColor);
		
		//나머지 세팅
		AddMinMovie.setBorderPainted(false);
		inputMovie.setBorderPainted(false);
		DeleteMovie.setBorderPainted(false);
		
		
		//메뉴에 추가하기
		menu.add(AddMinMovie);
		movieAdministrate.add(movieScroll);
		movieAdministrate.add(result);
		movieAdministrate.add(movieNameField);
		movieAdministrate.add(movieAgeField);
		movieAdministrate.add(inputMovie);
		movieAdministrate.add(DeleteMovie);
		
		
		theaterAdmini = new JPanel();
		theaterAdmini.setLayout(null);
		theaterAdmini.setBounds(300, 0, 900, 900);
		
		//선언하기
		theaterBu = new JButton("극장 관리하기");
		
		//좌표지정
		theaterBu.setBounds(30, 200, 250, 90);
		
		//폰트지정
		theaterBu.setFont(whiteF1);
		
		//효과지정
		theaterBu.addActionListener(this);
		theaterBu.addMouseListener(this);
		
		//배경지정
		theaterBu.setBackground(weakBlack);
		
		//나머지 세팅
		theaterBu.setBorderPainted(false);
		
		//극장관리에 추가하기
		menu.add(theaterBu);
		
		//패널에 추가하기
		
		add(menu);
		add(movieAdministrate);
		
	}

	public void movieLoding() {
		//영화가져오기
		list = ct.admini.selectMovie();
		movieList = new String[list.size()];
		for(int i = 0; i < list.size(); i++) {
			movieList[i] = "영화 제목 : " + list.get(i).getName() + "  나이 제한 : " + list.get(i).getAge();
		}
		movies.setListData(movieList);
	}





	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(inputMovie)) {
			if(movieAgeField.getText().equals("0") || movieAgeField.getText().equals("12")
					|| movieAgeField.getText().equals("15")|| movieAgeField.getText().equals("19")) {
				ct.admini.insertMovie(movieNameField.getText(), Integer.parseInt(movieAgeField.getText()));
				movieLoding();
				result.setText("입력 완료!");
			}else {
				result.setText("나이를 정확히 입력해주세요");
			}
		}else if(e.getSource().equals(DeleteMovie)) {		
			int index = movies.getSelectedValue().indexOf("  나이 제한 : ");
			String findMovie = movies.getSelectedValue().substring(8, index);
			ct.admini.deleteMovie(findMovie);
			movieLoding();
			result.setText("삭제 완료");
		}
		
		if(e.getSource().equals(AddMinMovie)) {
			ct.changePanel("managerAd");
			
		}else if(e.getSource().equals(theaterBu)) {
			ct.changePanel("theaterAdmini");

		}
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) { //마우스가 컴포넌트 위로 올라갈때
		if(e.getSource().equals(AddMinMovie)) {
			AddMinMovie.setBackground(new Color(255,255,255));
		}else if(e.getSource().equals(inputMovie)) {
			inputMovie.setBackground(new Color(200,200,200));
		}else if(e.getSource().equals(DeleteMovie)) {
			DeleteMovie.setBackground(new Color(200,200,200));
		}
		
		if(e.getSource().equals(theaterBu)) {
			theaterBu.setBackground(new Color(255,255,255));
		}
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource().equals(AddMinMovie)) {
			AddMinMovie.setBackground(weakBlack);
		}else if(e.getSource().equals(inputMovie)) {
			inputMovie.setBackground(backColor);
		}else if(e.getSource().equals(DeleteMovie)) {
			DeleteMovie.setBackground(backColor);
		}
		
		if(e.getSource().equals(theaterBu)) {
			theaterBu.setBackground(weakBlack);
		}
	}




	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
