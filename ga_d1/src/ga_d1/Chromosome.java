package ga_d1;

import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.ArrayList;
import java.util.Collections;

public class Chromosome {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		return result;
	}
	
	// broji koliko se nota poklapa, sluzi samo za ispis za debug
	// ne koristi se nigde u algoritmu
	int match(Chromosome other) {
		int match = 0;
		for (int i = 0; i < notes.size(); i++) {
			match += notes.get(i).match(other.notes.get(i));
		}
		return match;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chromosome other = (Chromosome) obj;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		return true;
	}

	public List<Note> notes = new ArrayList<>();
	
	public Chromosome(Chromosome chromosome) {
		super();
		for (Note note: chromosome.notes) {
			this.notes.add(new Note(note.start, note.len, note.pitch));
		}
	}
	
	public Chromosome(List<Note> notes) {
		super();
		for (Note note: notes) {
			this.notes.add(new Note(note.start, note.len, note.pitch));
		}
	}
	
	void crossOver(Chromosome other) {
		assert(notes.size() == other.notes.size());
		for (int i = 0; i < notes.size(); i++) {
			notes.get(i).cross(other.notes.get(i));
		}
	}
	
	void mutate(double prob) {
		assert(prob >= 0 || prob <= 1);
		for (int i = 0; i < notes.size(); i++) {
			notes.get(i).mutate(prob);
		}
	}
	
	// veci skor je bolji
	double score(Chromosome target) {
		assert(notes.size() == target.notes.size());
		double score = 0;
		int matched = 0;
		
		for (int i = 0; i < notes.size(); i++) {
			Note A = notes.get(i);
			Note B = target.notes.get(i);	
			
			long len_diff = Math.abs(B.len - A.len);
			long pitch_diff = Math.abs(B.pitch - A.pitch);
			long start_diff = Math.abs(B.start - A.start);
			
			int match = A.match(B);
			
			if (match == 2) score += 1e12;
			if (match == 1) score += 1e9;
			if (match == 3) score += 1e15;
			
			score -= 100*pitch_diff + 5*start_diff + len_diff*10;
		}
		return score;
	}
	
	//======Ispis======

	
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();

		Chromosome tmp = new Chromosome(notes);
		Collections.sort(tmp.notes, new NoteComparator());
		
		for (int i = 0; i < tmp.notes.size() - 1; i++) {
			Note curr = new Note(tmp.notes.get(i).start, tmp.notes.get(i).len, tmp.notes.get(i).pitch);
			Note next = new Note(tmp.notes.get(i+1).start, tmp.notes.get(i+1).len, tmp.notes.get(i+1).pitch);
			if (curr.len == 0) continue;
			
			if (curr.start + curr.len <= next.start) {
				res.append(curr);
			} else {
				if (curr.start != next.start) { 
					tmp.notes.get(i).start = next.start;
					tmp.notes.get(i).len -= next.start - curr.start;
					Note note = new Note(curr.start, next.start - curr.start, curr.pitch);
					res.append(note);
					i--;
				} else {
					res.append('(');
					int len = Integer.MAX_VALUE;
					
					for (int j = i + 1; j < tmp.notes.size(); j++) {
						if (tmp.notes.get(i).start != tmp.notes.get(j).start) {
							len = tmp.notes.get(j).start - tmp.notes.get(i).start;
							break;
						}
					}
					
					boolean hit = false;
					for (int j = i; j < tmp.notes.size(); j++) {
						if (curr.start == tmp.notes.get(j).start) {		
							
							int duz;
							if (len < curr.len) {
								duz = len;
								hit = true;
							} else {
								duz = curr.len;
							}
							
							res.append(new Note(curr.start, 
									            duz,
									            tmp.notes.get(j).pitch));
							
							tmp.notes.get(j).start += duz;
							tmp.notes.get(j).len -= duz;
						} else {
							break;
						}
					}
					res.append(')');
					if (hit) i--;
					
				}
			}
		}
		res.append(tmp.notes.get(tmp.notes.size()-1));
		
		return res.toString();
	}
	
	public void dbg() {
		for (int i = 0; i < notes.size(); i++) {
			Note note = notes.get(i);
			System.out.println(note.start + " " + note.len + " " + note.pitch);
		}
	}

}
