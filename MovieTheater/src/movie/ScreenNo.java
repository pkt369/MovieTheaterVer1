package movie;

class ScreenNo {
	private int no;
	String[] seat = new String[100];
	
	ScreenNo(int no){
		this.no = no;
		makeSeat();
	}
	
	public void makeSeat() {		
		for(int i = 0; i < 100; i++) {
			char ch = (char)(65 + i / 10);
			seat[i] =  String.valueOf(ch) + ((i % 10) + 1);
			System.out.println(seat[i]);
		}
	}
	
	
}
