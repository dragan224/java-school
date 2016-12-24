package refleksija_anotacija;

public class TestClass {
	@SuppressWarnings("deprecation")
	public TestClass(Integer key) {
		this.key = key;
		date = new java.util.Date(1994, 22, 4);
	}
	
	@Key
	Integer key;
	
	@AfterStore
	void afterStoreMethod() {
		System.out.println("Pozvali smo metodu sa annotacijom AfterStore!");
	}
	
	@BeforeRemove
	void beforeRemoveMethod() {
		System.out.println("Pozvali smo metodu sa annotacijom BeforeRemove!");
	}

	@Date
	private java.util.Date date;
	
	void showDate() {
		System.out.println(date);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + " (key = " + this.key + ")";
	}
}
