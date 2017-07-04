public class Guest extends Thread {

  /** konstruktor */
  public Guest(String name, Theatre theatre) {
    this.name = name;
    this.theatre = theatre;
  }
  
  void wasteTime(long d) {
	  long tStart = System.currentTimeMillis();
	  while (System.currentTimeMillis() - tStart < d) {
		  ;
	  }
  }
  
  /** opisuje ponasanje gosta */
  public void run() {
	 try {
		//1. Hoda pre ulaska
		wasteTime((long)(Math.random() * 600));
		
		//2. Pokusava da dodje na red na prazan kiosk
		long tStart = System.currentTimeMillis();
		long duration = (long)(Math.random() * 600);
		
		int idx = -1;
		while (System.currentTimeMillis() - tStart < duration) {
			idx = theatre.approachCounter(this);
			if (idx != -1) break;
		}
		System.out.println("Musterija " + this.name + " zauzela je " + idx);
		
		//3. Kupuje kartu na kiosku
		wasteTime((long)(Math.random() * 600));
		
		//4. Ako je zauzeo neki kiosk, napusta ga
		if (idx != -1) theatre.leaveCounter(this, idx);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

  /** ime gosta */
  public String name;

  /** pozoriste u koje se ide */
  private Theatre theatre;

}