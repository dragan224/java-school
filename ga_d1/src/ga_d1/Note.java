package ga_d1;

public class Note {
	
	public static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
	
	public int start;
	public int len;
	public int pitch;
	
	Note() {
		
	}
	
	int bitDiff(int A, int B) {
		int cnt = 0;
		for (int i = 0; i < 32; i++) {
			int b1 = (A & 1<<i);
			int b2 = (B & 1<<i);
			if (b1 != b2) cnt++;
		}
		return cnt;
	}
	
	int bitDiff(Note other) {
		return bitDiff(this.start, other.start) + 
			   bitDiff(this.pitch, other.pitch) +
			   bitDiff(this.len, other.len);
	}
	
	int match(Note other) {
		int cnt = 0;
		if (this.start == other.start) ++cnt;
		if (this.pitch == other.pitch) ++cnt;
		if (this.len == other.len) ++cnt;
		return cnt;
	}
	
	int cross(int A, int B) {
		if (A == B) return A;
		
		int max = Math.max(A, B);
		int min = Math.min(A, B);

		return GA.rand.nextInt((max - min) + 1) + min;
	}
	
	int mutate(int A, double p, int lb, int ub) {
		if (GA.rand.nextDouble() > p) return A; //neuspesna mutacija
		return GA.rand.nextInt(ub - lb + 1) + lb;
	}
	
	void cross(Note other) {
		this.start = cross(this.start, other.start);
		this.len = cross(this.len, other.len);
		this.pitch = cross(this.pitch, other.pitch);
		
	}
	
	void mutate(double prob) {
		this.start = mutate(this.start, prob, 0, Main.MAX_START);
		this.len = mutate(this.len, prob, 1, Main.MAX_TICKS);
		this.pitch = mutate(this.pitch, prob, 12, Main.MAX_PITCH);
	}
	

	public Note(int start, int len, int pitch) {
		if (start >= Main.MAX_START) {
			start = Main.MAX_START;
		}
		if (len >= Main.MAX_TICKS) {
			len = Main.MAX_TICKS;
		}
		if (pitch >= Main.MAX_PITCH) {
			pitch = Main.MAX_PITCH;
		}
		
		this.start = start;
		this.len = len;
		this.pitch = pitch;
	}


	@Override
	public String toString() {
		assert(len >= 0);
		if (len == 0) return "";
		
		String name;
		if (pitch != 0) {
			name = NOTE_NAMES[(int) (pitch % 12)] + "" + (pitch/12);
		} else {
			name = "P";
		}
		return name + "(" + len + ")";
	}
	
	public void dbg() {
		System.out.println(start + " " + len + " " + pitch + " ");
	}
}
