package aspekti;

import java.util.Random;

public class InjectionClass {
	public InjectionClass() {
		this.id = new Random().nextInt(100);
	}
	
	public InjectionClass(int id) {
		this.id = id;
	}
	
	private int id;

	public int getId() {
		return id;
	}
}
