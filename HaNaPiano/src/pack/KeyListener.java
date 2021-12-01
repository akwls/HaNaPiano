package pack;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

public class KeyListener extends KeyAdapter {
	public static ArrayList<File> sounds = new ArrayList();
	// File[] sounds = new File[16];
	
	Character[] keys_code = {'a', 'w', 's', 'e', 'd', 'f', 't', 'g', 'y', 'h', 'u', 'j', 'k'};
	ArrayList<Character> keys = new ArrayList<>(Arrays.asList(keys_code));
	String[] sounds_path = {"DO", "DOsharp", "RE", "REsharp", "MI", "FA", "FAsharp", "SOL", "SOLsharp", "LA", "LAsharp", "SI", "DO_high"};
	String path = "../HaNaPiano/src/sound/";
	public static ArrayList<Integer> recordKey = new ArrayList<>();
	
	public static boolean recording = false;
	int keySecond = 0;
	int currentSecond;
	
	public KeyListener() {
		for(int i=0; i<sounds_path.length; i++) {
			sounds.add(new File(path + sounds_path[i] + ".wav"));
		}
	}
	
	public void keyPressed(KeyEvent e) {
		Container contentPane = (Container)e.getSource();
		char c = e.getKeyChar();
		if(keys.contains(c)) {
			int i = keys.indexOf(c);
			AudioInputStream stream;
			try {
				if(this.recording) {
					recordKey.add(i);
				}
				stream = AudioSystem.getAudioInputStream(sounds.get(i));
				Clip clip = AudioSystem.getClip();
	            clip.open(stream);
	            clip.start();
			}
			catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace(); 
			}
            
		}
		
	}
	public static void recordPlay() {
		AudioInputStream stream;
		try {
			for(int i=0; i<recordKey.size(); i++) {
				stream = AudioSystem.getAudioInputStream(sounds.get(recordKey.get(i)));
				Clip clip = AudioSystem.getClip();
	            clip.open(stream);
	            clip.start();
	            Thread.sleep(500);
			}
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace(); 
		}
		
	}
}