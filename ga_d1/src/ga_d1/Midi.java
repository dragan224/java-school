package ga_d1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.SynchronousQueue;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class Midi {
	private String file_name;
	
	public static final int NOTE_ON = 0x90;
    public static final int NOTE_OFF = 0x80;

	public Midi(String file_name) {
		super();
		this.file_name = file_name;
	}
	
	ArrayList<Note> parse(int trackIdx, int channelIdx, int note_count) throws Exception {
		Sequence sequence = MidiSystem.getSequence(new File(file_name));
		if (trackIdx < 0 || trackIdx >= sequence.getTracks().length) return null;
		Track track = sequence.getTracks()[trackIdx];
		
		HashMap<Integer, Note> unmatched = new HashMap<>();
		
		ArrayList<Note> result = new ArrayList<>();
		
		for (int i = 0; i < track.size(); i++) {
			MidiEvent event = track.get(i);
			
			MidiMessage message = event.getMessage();
			
			if (message instanceof ShortMessage) {
                ShortMessage sm = (ShortMessage) message;
                
                if (sm.getChannel() != channelIdx) continue;
                
                int velocity = sm.getData2();
                if (sm.getCommand() != NOTE_ON && sm.getCommand() != NOTE_OFF) {
                	continue;
                }
                
                if (sm.getCommand() == NOTE_ON && velocity != 0) {
                	if (note_count == 0) continue;
                	note_count--;
                	Note note = new Note();
                	note.pitch = sm.getData1();
                	note.start = (int) event.getTick();
                    unmatched.put(note.pitch, note);
                } else if (sm.getCommand() == NOTE_OFF || (sm.getCommand() == NOTE_ON && velocity == 0)) { 
                    Note note = unmatched.get(sm.getData1());
                    if (note == null) {
                    	if (note_count == 0) {
                    		continue;
                    	} else {
                    		throw new Exception("Eror matching notes");
                    	}
                    }
                    note.len = (int) (event.getTick() - note.start);
                    result.add(note);
                    unmatched.remove(sm.getData1());
                } else {
                	System.out.println("Parse error:" + sm.getCommand());
                }
			
			}
		}
		assert(unmatched.size() == 0);
		return result;
	}
}
