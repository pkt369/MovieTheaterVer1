package movie.data;

import java.net.URL;

public class Movie {
	private String name;
	private int age;
	private URL url;
	
	
//	Movie(String name, int age, String Info){
//		this.name = name;
//		this.age = age;
//		this.Info = Info;
//	}

	
	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
}

