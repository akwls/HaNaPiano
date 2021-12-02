package pack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class JoinAction implements ActionListener {

	Toast toast = null; // 토스트 객체
	String id, pw, pwcheck, name; // JTextField에서 가져올 아이디, 비밀번호, 비밀번호 확인, 이름 변수
	String message; // 토스트에서 띄울 메시지
	JFrame frame; // 이전 창. 없애는 용도
	
	public JoinAction(JFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// JTextField에서 값 가져오기
		this.id = Join.text_Id.getText();
		this.pw = Join.text_Pwd.getText();
		this.pwcheck = Join.text_PwdCheck.getText();
		this.name = Join.text_Name.getText();
		int code = processJoin(); // 회원가입 실행 후, 실행 결과 코드 리턴.
		toast = new Toast(this.message, 800, 700); // 메시지 토스트 띄우기
		toast.showtoast();
		// 회원가입이 성공적으로 완료되면 로그인 창으로 돌아가기(로그인 창 띄우기)
		if(code == 2) {
			Login login = new Login(frame);
			frame.setVisible(false); // 회원가입 창 닫기
			login.LoginFrame();
		}
		
	}
	public int processJoin() {
		if(!pw.equals(pwcheck)) {
			this.message = "비밀번호가 일치하지 않습니다.";
			return 0;
		}
		// MySQL 클래스 변수를 통해 회원가입 실행.
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