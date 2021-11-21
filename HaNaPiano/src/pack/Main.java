
package pack;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main {
	// ImageIcon icon = new ImageIcon(Main.class.getResource("../image/start.png"));
	// static Graphics background = icon.getImage();

	public static void main(String[] args) throws Exception {
		
		// 새 창 띄우기
		JFrame frame = new JFrame("하나피아노");
		frame.setSize(1600,900);//프레임의 크기
		frame.setResizable(false);//창의 크기를 변경하지 못하게
		frame.setLocationRelativeTo(null);//창이 가운데 나오게
		frame.setLayout(null);//레이아웃을 내맘대로 설정가능하게 해줌.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//JFrame이 정상적으로 종료되게
		
		// 처음 스플래쉬 화면 띄우기
		ImageIcon ii = new ImageIcon("../HaNaPiano/src/image/start.png");
		JLabel jl = new JLabel(ii);
		jl.setSize(1600, 900);
		frame.add(jl);
		frame.setLayout(null);
		frame.setVisible(true);
		
		Thread.sleep(2000); // 스플래쉬 2초 대기
		// 2초 후 버튼 화면 띄우기
		ImageIcon iii = new ImageIcon("../HaNaPiano/src/image/login_start.png");
		jl.setIcon(iii);
		
		// 로그인, 바로 시작 버튼 만들기
		JButton btnLogin = new JButton();
		JButton btnStart = new JButton();
		
		
		// LineBorder roundedLineBorder = new LineBorder(Color.black, 195, true);
		
		// 로그인 버튼
		btnLogin.setBounds(529, 185, 542, 207); // 좌표, 가로, 세로 설정
		btnLogin.setOpaque(false); 
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBorderPainted(true); // 테두리 투명도 설정
		btnLogin.setBorder(new RoundedBorder(195));
		btnLogin.setFocusable(false);
		// 온클릭 리스너
		btnLogin.getModel().addChangeListener(new ChangeListener() {
	        @Override
	        public void stateChanged(ChangeEvent e) {
	            ButtonModel model = (ButtonModel) e.getSource();
	            if (model.isRollover()) {
	                btnLogin.setBorderPainted(true); // 테두리 보이게
	            } else {
	            	btnLogin.setBorderPainted(false); // 테두리 안보이게
	            }
	        }
	    });
		jl.add(btnLogin);
		
		// 바로 시작 버튼
		btnStart.setBounds(529, 478, 542, 207);
		btnStart.setOpaque(false);
		btnStart.setContentAreaFilled(false);
		btnStart.setBorderPainted(true);
		btnStart.setBorder(new RoundedBorder(195));
		btnStart.getModel().addChangeListener(new ChangeListener() {
	        @Override
	        public void stateChanged(ChangeEvent e) {
	            ButtonModel model = (ButtonModel) e.getSource();
	            if (model.isRollover()) {
	            	btnStart.setBorderPainted(true);
	            } else {
	            	btnStart.setBorderPainted(false);
	            }
	        }
	    });
		jl.add(btnStart);
		
		btnLogin.addActionListener(new Login(frame));
		btnStart.addActionListener(new StartAction(frame));
		
	}
	
	
}
