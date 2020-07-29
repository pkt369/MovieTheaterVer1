package movie;

public class Advertising implements Runnable{
	
	private int temp[];
	
	Advertising(){
		temp = new int[10];
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
