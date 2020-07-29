package movie;

import javax.swing.JFrame;

import movie.manager.ManagerAdministrateMovie;
import movie.manager.insertMovieInTheater;

public class MovieMain {
	public static void main(String[] args) {
		ControlTower ct = new ControlTower();
		ct.firstUi = new FirstUI(ct);
		ct.ticketUi = new TicketingUI(ct);
		ct.managerAd = new ManagerAdministrateMovie(ct);
		ct.theaterAdmini = new insertMovieInTheater(ct);
		ct.auditorium = new Auditorium(ct);
		ct.payment = new Payment(ct);
		ct.completed = new Completed(ct);
		ct.thread = new Thread(ct.firstUi);
		
		//스레드시작
		ct.thread.start();
		//화면
		ct.add(ct.firstUi);
		
		ct.setTitle("managerAd");
		ct.setSize(1200, 900);
		ct.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ct.setVisible(true);
		ct.setLocationRelativeTo(null);
	}
}
