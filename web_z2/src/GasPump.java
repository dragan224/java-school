import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class GasPump {
  private BlockingQueue<Integer> q = new LinkedBlockingQueue<>();
  
  public GasPump() {
	counter = new AtomicInteger(0);
  }

  /**
   * Staje u red.
   */
  public void access(int fuelType) {
	  int cnt = counter.incrementAndGet();
	  q.add(cnt);
	  
	  while (q.peek() != cnt) {
		  ;
	  }
	  System.out.println("usao " + fuelType);
  }

  /**
   * Odlazi.
   */
  public void leave(int fuelType) {
	  q.poll();
	  System.out.println("izasao " + fuelType);
  }

  /**
   * broj musterija koje stoje u redu, ukljucujuci
   * i onoga ko trenutno koristi terminal za tocenje.
   */
  public AtomicInteger counter;
}
