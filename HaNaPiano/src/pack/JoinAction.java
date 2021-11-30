package pack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class JoinAction implements ActionListener {

	Toast toast = null;
	String id, pw, pwcheck, name;
	String message;
	JFrame frame;
	
	public JoinAction(JFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.id = Join.text_Id.getText();
		this.pw = Join.text_Pwd.getText();
		this.pwcheck = Join.text_PwdCheck.getText();
		this.name = Join.text_Name.getText();
		int code = processJoin();
		toast = new Toast(this.message, 800, 700);
		toast.showtoast();
		if(code == 2) {
			Login login = new Login(frame);
			frame.setVisible(false);
			login.LoginFrame();
		}
		
	}
	public int processJoin() {
		if(!pw.equals(pwcheck)) {
			this.message = "비밀번호가 일치하지 않습니다.";
			return 0;
		}
		int joinCode = Main.mysql.join(this.id, this.pw, this.name);
		if(joinCode == 1) {
			this.message = "이미 존재하는 아이디입니다. ";
		}
		else if(joinCode == 2) {
			this.message = "회원가입이 완료되었습니다.";
		}
		else {
			this.message = "회원가입에 실패하였습니다.";
		}
		return joinCode;
	}
	
}
