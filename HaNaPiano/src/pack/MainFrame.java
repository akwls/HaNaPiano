package pack;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	
	private ImageIcon icon = new ImageIcon(Main.class.getResource("../image/start.png"));
	private Image background = icon.getImage();
	Graphics g;
	public MainFrame() {
		homeframe();
	}
	public void homeframe() {
		setTitle("하나피아노");//타이틀
		setSize(1600,900);//프레임의 크기
		setResizable(false);//창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);//창이 가운데 나오게
		setLayout(null);//레이아웃을 내맘대로 설정가능하게 해줌.
		setVisible(true);//창이 보이게	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//JFrame이 정상적으로 종료되게
		
	}
	public void paint(Graphics g) {//그리는 함수
		g.drawImage(background, 0, 0, null);//background를 그려줌
	}
	/*

	public void changeImage(String path) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		icon = new ImageIcon(Main.class.getResource(path));
		background = icon.getImage();
		// System.out.println("이미지 변경");
		repaint();
	}
	*/
	
	/*
	public void layoutButton(JPanel pn) {
		JButton btnLogin = new JButton();
		JButton btnStart = new JButton();
		
		pn.setLayout(null);
		
		btnLogin.setBounds(529, 185, 542, 207);
		btnStart.setBounds(529, 478, 542, 207);
		
		pn.add(btnLogin);
		pn.add(btnStart);		
	}
	*/
	
}
