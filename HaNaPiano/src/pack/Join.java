package pack;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Join extends JFrame implements ActionListener{
	
	MySQLConnect mysql;  
	public JFrame frame;

	public Join(JFrame frame) {
		// 디비 연결 클래스 생성
		super("하나피아노");
		mysql = new MySQLConnect();
		this.frame = frame;
		setLayout(new FlowLayout());
		setSize(1600,900);//프레임의 크기
		setResizable(false);//창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);//창이 가운데 나오게
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//JFrame이 정상적으로 종료되게
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		mysql.connect();
		frame.setVisible(false);
		setVisible(true);
		JoinFrame();
	}
	
	public void JoinFrame() {
		ImageIcon image = new ImageIcon("../HanaPiano/src/image/join.png");
		JLabel jl = new JLabel(image);
		jl.setSize(1600, 900);
		this.add(jl);
		this.setLayout(null);
		this.setVisible(true);
		
		JTextField text_Id = new JTextField() {
			@Override public void setBorder(Border border) {
		    }
		};
		JTextField text_Pwd = new JTextField() {
			@Override public void setBorder(Border border) {
		    }
		};		JTextField text_PwdCheck = new JTextField() {
			@Override public void setBorder(Border border) {
		    }
		};
		JTextField text_Name = new JTextField() {
			@Override public void setBorder(Border border) {
		    }
		};
		
		JButton btnJoin = new JButton();
		
		// 아이디 입력
		text_Id.setBounds(600, 230, 548, 100);
		text_Id.setText("");
		text_Id.setOpaque(false);
		jl.add(text_Id);

		// 비밀번호 입력
		text_Pwd.setBounds(600, 355, 548, 100);
		text_Pwd.setText("");
		text_Pwd.setOpaque(false);
		jl.add(text_Pwd);
		
		// 비민번호 재입력
		text_PwdCheck.setBounds(600, 477, 548, 100);
		text_PwdCheck.setText("");
		text_PwdCheck.setOpaque(false);
		jl.add(text_PwdCheck);

		// 이름 입력
		text_Name.setBounds(600, 602, 548, 100);
		text_Name.setText("");
		text_Name.setOpaque(false);
		jl.add(text_Name);
		
		// 회원가입 버튼
		btnJoin.setBounds(698, 730, 240, 90);
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
		
		btnJoin.addActionListener(new Login(frame));
	}
	
}