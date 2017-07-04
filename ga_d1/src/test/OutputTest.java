package test;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import ga_d1.Chromosome;
import ga_d1.Note;
import static org.junit.Assert.*;

public class OutputTest {

	@Test
	public void test() {
		ArrayList<Note> notes = new ArrayList<>();
		
		notes.add(new Note(0, 0, 0)); //ignorise
		notes.add(new Note(1, 4, 0)); //pauza
		
		notes.add(new Note(5, 3, 76));
		notes.add(new Note(6, 3, 44));
		notes.add(new Note(7, 3, 24));
		
		for (int i = 0; i < 10; i++) {
			Collections.shuffle(notes);
			String output = new Chromosome(notes).toString();
			assertEquals(output, "P(4)E(1)(E(1)G#(1))(E(1)G#(1)C(1))(G#(1)C(1))C(1)");
		}
	}

}
