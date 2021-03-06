package pack;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Login implements ActionListener {
	
	JFrame jframe; // 자기 자신 창
	JFrame frame; // 호출 전 창. 없애는 용도
	public static JTextField text_Id; // 아이디 입력
	public static JTextField text_Pwd; // 비밀번호 입력

	public Login(JFrame frame) {
		System.out.println("Login.java 실행");
		jframe = new JFrame("하나피아노"); // 새로운 창 생성
		this.frame = frame;
		jframe.setLayout(new FlowLayout());
		jframe.setSize(1600,900);//프레임의 크기
		jframe.setResizable(false);//창의 크기를 변경하지 못하게
		jframe.setLocationRelativeTo(null);//창이 가운데 나오게
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//JFrame이 정상적으로 종료되게
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(frame != null) frame.setVisible(false); // 이전 창 없애기
		jframe.setVisible(true); // 자기 자신 띄우기
		LoginFrame();
	}
	
	public void LoginFrame() {
		ImageIcon image = new ImageIcon("../HaNaPiano/src/image/login.png");
		JLabel jl = new JLabel(image);
		jl.setSize(1600, 900);
		jframe.add(jl);
		jframe.setLayout(null);
		jframe.setVisible(true);
		
		// 아이디, 비밀번호 입력 JTextField 생성
		text_Id = new JTextField() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override public void setBorder(Border border) {
		    }
		};
		text_Pwd = new JTextField() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override public void setBorder(Border border) {
		    }
		};
		
		JButton btnLogin = new JButton();
		JButton btnJoin = new JButton();
		
		// 아이디 입력
		text_Id.setBounds(550, 310, 548, 100);
		text_Id.setText("");
		text_Id.setOpaque(false);
		text_Id.setEditable(true);
		jl.add(text_Id);
		
		// 비밀번호 입력
		text_Pwd.setBounds(550, 450, 548, 100);
		text_Pwd.setText("");
		text_Pwd.setOpaque(false);
		jl.add(text_Pwd);
		
		// 로그인하기
		btnLogin.setBounds(840, 595, 273, 103);
		btnLogin.setOpaque(false);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBorderPainted(false);
		btnLogin.setBorder(new RoundedBorder(100));
		btnLogin.setFocusable(false);
		
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
		
		// 회원가입 하기
		btnJoin.setBounds(509, 595, 273, 103);
		btnJoin.setOpaque(false);
		btnJoin.setContentAreaFilled(false);
		btnJoin.setBorderPainted(false);
		btnJoin.setBorder(new RoundedBorder(100));
		btnJoin.setFocusable(false);
				
		btnJoin.getModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ButtonModel model = (ButtonModel) e.getSource();
			    if (model.isRollover()) {
			      	btnJoin.setBorderPainted(true); // 테두리 보이게
			    } else {
			        btnJoin.setBorderPainted(false); // 테두리 안보이게
			    }
			}
		});		
		jl.add(btnJoin);
		jframe.add(jl);
		Container cc = jframe.getContentPane();
		cc.setFocusable(true);
		text_Id.requestFocus();		
		btnJoin.addActionListener(new Join(jframe));
		btnLogin.addActionListener(new LoginAction(jframe));
		
				
	}
}