package pack;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Main {
	// ImageIcon icon = new ImageIcon(Main.class.getResource("../image/start.png"));
	// static Graphics background = icon.getImage();

	public static void main(String[] args) throws Exception {
		
		// setTitle("하나피아노");//타이틀
		JFrame frame = new JFrame("하나피아노");
		frame.setSize(1600,900);//프레임의 크기
		frame.setResizable(false);//창의 크기를 변경하지 못하게
		frame.setLocationRelativeTo(null);//창이 가운데 나오게
		// frame.setLayout(null);//레이아웃을 내맘대로 설정가능하게 해줌.
		//창이 보이게	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//JFrame이 정상적으로 종료되게
		ImageIcon ii = new ImageIcon("F:\\HaNaPiano\\HaNaPiano\\HaNaPiano\\src\\image\\start.png");
		// TODO Auto-generated method stub
		JLabel jl = new JLabel(ii);
		jl.setSize(1600, 900);
		frame.add(jl);
		frame.setLayout(null);
		frame.setVisible(true);
		
		//MainFrame mainFrame = new MainFrame();
		// mainFrame.changeImage("../image/login_start.png");
		// JPanel pn = new JPanel();
		// pn.setLayout(null);
		// mainFrame.layoutButton(pn);
		// mainFrame.setContentPane(pn);
		// frame.paint(background);
		Thread.sleep(2000);
		ImageIcon iii = new ImageIcon("F:\\HaNaPiano\\HaNaPiano\\HaNaPiano\\src\\image\\login_start.png");
		jl.setIcon(iii);
		JButton btnLogin = new JButton();
		JButton btnStart = new JButton();
		btnLogin.setBounds(529, 185, 542, 207);
		btnStart.setBounds(529, 478, 542, 207);
		btnLogin.setOpaque(false);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBorderPainted(true);
		btnLogin.setBorder(new LineBorder(Color.BLUE));
		btnStart.setOpaque(false);
		btnStart.setContentAreaFilled(false);
		btnStart.setBorderPainted(true);
		btnStart.setBorder(new LineBorder(Color.BLUE));
		jl.add(btnLogin);
		jl.add(btnStart);
		
	}
	
	
}
