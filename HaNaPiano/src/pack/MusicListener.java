package pack;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class MusicListener extends KeyAdapter {
	int musicName, musicNum;
	JLabel j1, j2;
	StartAction obj;
	
	public MusicListener() {}
	
	public MusicListener(int musicName, int musicNum) {
		this.musicName = musicName;
		this.musicNum = musicNum;
	}

	public void keyPressed(KeyEvent e) {
		System.out.println(musicNum);
		int keyCode = e.getKeyCode();
		
		switch(keyCode) {
		case KeyEvent.VK_RIGHT:
			musicNum++;
			System.out.println(musicNum);
			//obj = new StartAction(musicNum);
			break;
		}
		
	}
	
	public int getmusicNum() {
		
		return this.musicNum;
	}
	
}
