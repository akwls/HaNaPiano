package pack;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Music extends JFrame implements ActionListener{
	
	public JFrame frame;

	public Music(JFrame frame) {
		// 디비 연결 클래스 생성
		super("하나피아노");
		this.frame = frame;
		//setLayout(new FlowLayout());
		setSize(1600,900);//프레임의 크기
		setResizable(false);//창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);//창이 가운데 나오게
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//JFrame이 정상적으로 종료되게
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		/*frame.setVisible(false);
		setVisible(true);*/
		MusicFrame();
	}
	
	public void MusicFrame() {
		ImageIcon image = new ImageIcon("../HaNaPiano/src/image/action_music.png");
		JLabel jl = new JLabel(image);
		jl.setSize(1600, 900);
		this.add(jl);
		this.setLayout(null);
		this.setVisible(true);
	}
	
}
