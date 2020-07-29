package movie;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import movie.data.Movie;
import movie.data.MovieDAO;
import movie.data.TheaterDAO;
import movie.data.theater;
import movie.manager.ManagerAdministrateMovie;
import movie.manager.insertMovieInTheater;

public class ControlTower extends JFrame{
	int width = 1200;
	int height = 900;
	public MovieDAO admini = new MovieDAO();
	public ArrayList<Movie> list = new ArrayList<Movie>();
	public TheaterDAO thAdmini = new TheaterDAO();
	public ArrayList<theater> thList = new ArrayList<theater>();
	public String panelName = null;
	
	
	//화면들
	FirstUI firstUi;
	TicketingUI ticketUi;
	ManagerAdministrateMovie managerAd;
	insertMovieInTheater theaterAdmini;
	public Auditorium auditorium;
	Payment payment;
	Completed completed;

	
	ControlTower(){
		list = admini.selectMovie();
		thList = thAdmini.selectTheater();

	}
	
	public void changePanel(String panelName) {
		if(panelName == "firstUi") {
			getContentPane().removeAll();
			getContentPane().add(firstUi);
			revalidate();
			repaint();
		}else if(panelName == "ticketUi"){
			getContentPane().removeAll();		
			getContentPane().add(ticketUi);	
			revalidate();
			repaint();
		}else if(panelName == "auditorium") {
			getContentPane().removeAll();
			getContentPane().add(auditorium);			
			revalidate();
			repaint();
		}else if(panelName == "payment") {
			getContentPane().removeAll();
			getContentPane().add(payment);
			revalidate();
			repaint();
		}else if(panelName == "completed") {
			getContentPane().removeAll();
			getContentPane().add(completed);
			revalidate();
			repaint();
		}
		
		if(panelName == "managerAd") {
			getContentPane().removeAll();
			getContentPane().add(managerAd.menu); //메뉴패널은 따로 있기때문에 더해줘야 보임
			getContentPane().add(managerAd);
			revalidate();
			repaint();
		}else if(panelName == "theaterAdmini") {
			getContentPane().removeAll();
			getContentPane().add(managerAd.menu);
			getContentPane().add(theaterAdmini);
			revalidate();
			repaint();
		}
		
	}
	public ArrayList<Movie> resetMovieList() {
		list = admini.selectMovie();
		return list;
	}
	public ArrayList<theater> resetTheaterList() {
		thList = thAdmini.selectTheater();
		return thList;
	}
	public ImageIcon imageSetSize(Image icon, int i, int j) {
		ImageIcon ic = new ImageIcon(icon);
		Image img = ic.getImage().getScaledInstance(i, j, java.awt.Image.SCALE_SMOOTH);
		ImageIcon reimg = new ImageIcon(img);
		return reimg;
	}
	//오버로딩
	public ImageIcon imageSetSize(ImageIcon icon, int i, int j) {
		Image img = icon.getImage().getScaledInstance(i, j, java.awt.Image.SCALE_SMOOTH);
		ImageIcon reimg = new ImageIcon(img);
		return reimg;
	}
	public String getSellSeats(String movie, String Day, String Time) {
		for(int i = 0; i < thList.size(); i++) {
			if(thList.get(i).getMovie().equals(movie) && 
					thList.get(i).getDay().equals(Day) && 
					thList.get(i).getStartTime().equals(Time)) {
				return thList.get(i).getSellSeats();
			}
		}
		return "";
	}
	
}
