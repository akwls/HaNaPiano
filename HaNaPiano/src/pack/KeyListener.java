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
	
	// 오디오 파일 arrayList
	public static ArrayList<File> sounds = new ArrayList();
	// 키 배열
	Character[] keys_code = {'a', 'w', 's', 'e', 'd', 'f', 't', 'g', 'y', 'h', 'u', 'j', 'k'};
	// 키 배열로 arrayList 생성
	ArrayList<Character> keys = new ArrayList<>(Arrays.asList(keys_code));
	// 오디오 파일 이름
	String[] sounds_path = {"DO", "DOsharp", "RE", "REsharp", "MI", "FA", "FAsharp", "SOL", "SOLsharp", "LA", "LAsharp", "SI", "DO_high"};
	// 오디오 파일 경로
	String path = "../HaNaPiano/src/sound/";
	// 녹음 - 누른 키 arrayList
	public static ArrayList<Integer> recordKey = new ArrayList<>();
	// 녹음 - 누른 지연 시간 arrayList
	public static ArrayList<Long> recordTime = new ArrayList<>();
	
	public static boolean recording = false; // 녹음 중 변수
	public static boolean playing = false; // 재생 중 변수
	public static long keySecond = 0; // 이전 키를 누른 시간
	public static long currentSecond = 0; // 현재 키를 누른 시간
	
	public KeyListener() {
		for(int i=0; i<sounds_path.length; i++) {
			sounds.add(new File(path + sounds_path[i] + ".wav"));
			// 오디오 파일 생성하기
		}
	}
	
	// 키가 눌렸을 때 피아노 음 재생하기
	public void keyPressed(KeyEvent e) {
		Container contentPane = (Container)e.getSource();
		char c = e.getKeyChar();
		if(keys.contains(c)) { // 눌린 키가 리스트에 있으면
			int i = keys.indexOf(c); // 눌린 키의 인덱스
			AudioInputStream stream;
			try {
				stream = AudioSystem.getAudioInputStream(sounds.get(i)); // 눌린 키에 해당하는 오디오 파일 stream 연결
				Clip clip = AudioSystem.getClip();
	            clip.open(stream);
	            clip.start();
	            if(this.recording) { // 녹음 중 변수가 true면
					recordKey.add(i); // 녹음 키 리스트 add
					currentSecond = System.currentTimeMillis() - keySecond; // 현재 키가 눌린 시간에서 이전 키가 눌린 시간 빼기
					recordTime.add(currentSecond); // 지연 시간 리스트 add
					keySecond = System.currentTimeMillis(); // 이전 키가 눌린 시간에 현재 키가 눌린 시간 넣기
				}
			}
			catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace(); 
			}
            
		}
		
	}
	
	// 녹음 재생
	public static void recordPlay() {
		playing = true; // 연주 중 변수 true
		AudioInputStream stream;
		try {
			for(int i=0; i<recordKey.size(); i++) {
				if(!playing) return; // 연주 중 변수가 false면 break
				Thread.sleep(recordTime.get(i)); // 지연시간 리스트의 i번째 방만큼 지연하기
				stream = AudioSystem.getAudioInputStream(sounds.get(recordKey.get(i))); // 눌린 키 리스트에 해당하는 오디오 파일
				Clip clip = AudioSystem.getClip();
	            clip.open(stream);
	            clip.start();
	            
			}
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace(); 
		}
		
	}
	public static void recordPlay(String[] recordKey, String[] recordTime) { // int, long
		AudioInputStream stream;
		try {
			for(int i=0; i<recordKey.length; i++) {
				// if(!playing) return; // 연주 중 변수가 false면 break
				Thread.sleep(Long.parseLong(recordTime[i])); // 지연시간 리스트의 i번째 방만큼 지연하기
				stream = AudioSystem.getAudioInputStream(sounds.get(Integer.parseInt(recordKey[i]))); // 눌린 키 리스트에 해당하는 오디오 파일
				Clip clip = AudioSystem.getClip();
	            clip.open(stream);
	            clip.start();
	            
			}
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace(); 
		}
	}
}
