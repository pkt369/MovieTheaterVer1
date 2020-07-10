package movie;

import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JFrame;

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
//	ArrayList<Movie> movie = new ArrayList<Movie>();
//	ArrayList<ScreenNo> screen = new ArrayList<ScreenNo>();
//	MovieTheaterControl mtc = new MovieTheaterControl();
	public String panelName = null;
	
	
	//화면들
	FirstUI firstUi;
	TicketingUI ticketUi;
	ManagerAdministrateMovie managerAd;
	insertMovieInTheater theaterAdmini;
	
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
}
