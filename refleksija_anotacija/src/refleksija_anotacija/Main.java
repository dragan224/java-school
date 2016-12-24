package refleksija_anotacija;

public class Main {

	public static void main(String[] args) {

		TestClass o1 = new TestClass(42);
		TestClass o2 = new TestClass(242);
		
		AssocMap mapa = new AssocMap();
		
		mapa.store(o1);
		o1.showDate();
		
		mapa.store(o2);
		o2.showDate();
		
		assert(!mapa.isEmpty());
		//System.out.println("Objekat sa kljucem 42: " + mapa.findByPrimaryKey(42).toString());
		//System.out.println("Objekat sa kljucem 242: " + mapa.findByPrimaryKey(242).toString());
		assert(mapa.findByPrimaryKey(123) == null);
		
		mapa.remove(o2);
		
		assert(mapa.findByPrimaryKey(242) == null);
		
		mapa.remove(o1);
		
		assert(mapa.findByPrimaryKey(42) == null);
	
		assert(mapa.isEmpty());
	}

}
