package aspekti;

public class TestClass {

	public TestClass() {
		
	}
	
	@ManagedBean
	private InjectionClass prvi;
	
	int blabla;
	
	private InjectionClass drugi;
	
	@ManagedBean
	private InjectionClass treci;
	
	String blablabla;

	void test() {
		assert(prvi != null);
		System.out.println(prvi.getId());
		
		assert(drugi == null);
		
		assert(treci != null);
		System.out.println(treci.getId());
	}
}
