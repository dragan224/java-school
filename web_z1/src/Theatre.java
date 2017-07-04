public class Theatre {
  public Theatre(String name, int counterCount) {
    this.name = name;
    counters = new boolean[counterCount];
  }

  /** zauzimanje saltera ako je slobodan */
  public synchronized int approachCounter(Guest guest) {
    int idx = getCounter();
    if (idx != -1) counters[idx] = true;
    return idx;
  }

  /** oslobadjanje saltera */
  public synchronized void leaveCounter(Guest guest, int index) {
    counters[index] = false;
  }

  /** vraca redni broj prvog slobodnog saltera; vraca -1 ako nema slobodnih saltera */
  private int getCounter() {
    for (int i = 0; i < counters.length; i++)
      if (counters[i] == false)
        return i;
    return -1;
  }

  /** naziv pozorista */
  public String name;

  /** niz saltera kojima pristupaju gosti; ako je vrednost i-tog elementa niza false, i-ti salter je slobodan  */
  public boolean[] counters;
}