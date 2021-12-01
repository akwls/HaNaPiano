package pack;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class MusicListener extends KeyAdapter {
	int musicName, musicNum;
	StartAction sta = new StartAction();
	
	public MusicListener() {}
	
	public MusicListener(int musicName, int musicNum) {
		this.musicName = musicName;
		this.musicNum = musicNum;
	}


	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		switch(keyCode) {
		case KeyEvent.VK_RIGHT:
			musicNum++;
			sta.test(musicNum);
			break;
		}
		
	}
	
	public int getmusicNum() {
		
		return musicNum;
	}
	
}
