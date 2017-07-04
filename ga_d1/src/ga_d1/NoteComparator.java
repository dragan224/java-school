package ga_d1;

import java.util.Comparator;

public class NoteComparator implements Comparator<Note> {

	@Override
	public int compare(Note A, Note B) {
		if (A.start < B.start) {
			return -1;
		} else if (A.start > B.start) {
			return 1;
		} else { // jendaki su
			if (A.len < B.len) {
				return -1;
			} else if (A.len > B.len) {
				return 1;
			}
		}
		return 0;
	}

}
