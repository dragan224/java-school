public class Customer extends Thread {

  public Customer(GasStation station, int fuelType) {
    this.station = station;
    this.fuelType = fuelType;
  }
  
  void wasteTime(long d) {
	  long tStart = System.currentTimeMillis();
	  while (System.currentTimeMillis() - tStart < d) {
		  ;
	  }
  }

  public void run() {
    // 1. musterija se voza gradom
	  wasteTime((long)(Math.random() * 400));
	  
    // 2. dolazi u na pumpu, izabere svoj terminal za tocenje i staje u red
	  station.getGasPump(fuelType).access(fuelType);
	  
    // 3. toci gorivo
	  wasteTime((long)(Math.random() * 500));
	  
    // 4. napusta pumpu 
	  station.getGasPump(fuelType).leave(fuelType);
  }

  private GasStation station;
  private int fuelType;
}