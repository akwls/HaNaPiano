package pack;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LoginAction extends JFrame implements ActionListener {
	
	public JFrame frame; // 이전 창. 없애는 용도
	String id, pw; // JTextField에서 가져올 아이디, 비밀번호 변수
	Toast toast = null; // 토스트 클래스 변수
	public Thread th= null; // 녹음 재생을 위한 스레드
	int recordCnt; // 사용자가 지금까지 녹음한 갯수
	public MusicList mu = new MusicList();
	int recordIndex;
	public static RecordFile recordfile = null;
	public int OctNum = 4;

	public Container c = getContentPane();
	
	public int check_music = 1;
	public LabelList la = new LabelList();
	
	public JLabel imageLabel = new JLabel();
	public int NameInd = 0;
	
	JLabel title = new JLabel(); // 곡 이름
	String path = "../HaNaPiano/src/music/";
	int musicName = 0, musicNum = 1;
	
	public LoginAction(JFrame frame) {
		// 디비 연결 클래스 생성
		super("하나피아노");
		this.frame = frame;
		setLayout(new FlowLayout());
		setSize(1600,900);//프레임의 크기
		setResizable(false);//창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);//창이 가운데 나오게
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//JFrame이 정상적으로 종료되게
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.id = Login.text_Id.getText(); // 아이디 가져오기
		this.pw = Login.text_Pwd.getText(); // 비밀번호 가져오기
		System.out.println("아이디 : " + this.id+", 비밀번호 : " + this.pw);
		int code = processLogin(); // 로그인 후 실행 결과 코드 가져오기
		toast.showtoast(); // 토스트 띄우기
		// 로그인이 성공적으로 완료되면
		if(code == 2) {
			frame.setVisible(false); // 이전 창 없애기
			setVisible(true); // 현재 창 띄우기
			Start_Screen(); // 창 구성하는 메소드
		}
	}

	public void Start_Screen() {
		la.j1.setSize(1600, 188);
		this.add(la.j1);
		this.setLayout(null);
		this.setVisible(true);
		recordfile = new RecordFile(KeyListener.recordKey, KeyListener.recordTime, KeyListener.recordOctave, id); // 녹음 파일입출력 객체
		
		la.j2.setSize(1600, 712);
		la.j2.setBounds(0, 189, 1600, 712);
		this.add(la.j2);
		this.setLayout(null);
		this.setVisible(true);
		
		imageLabel.setSize(1600, 219);
		imageLabel.setBounds(0, 189, 1600, 185);
		this.add(imageLabel);
		imageLabel.setLayout(new FlowLayout());
		this.setVisible(true);
		
		JLabel octave = new JLabel();
		octave.setText("옥타브 "+OctNum);
		octave.setBounds(728, 44, 300, 100);
		octave.setFont(new Font("SansSerif", Font.BOLD, 35));
		la.j1.add(octave);
		la.j1.setLayout(null);
		octave.setVisible(true);	
		
		
		JButton btnMusic = new JButton();
		JButton btnUp = new JButton();
		JButton btnDawn = new JButton();
		JButton btnRecoding = new JButton();
		JButton btnStop = new JButton();
		JButton btnPause = new JButton();
		JButton btnPlay = new JButton();
		JButton btnUser = new JButton();
		
		// 악보보기
		btnMusic.setBounds(20, 40, 100, 110);
		btnMusic.setOpaque(false);
		btnMusic.setContentAreaFilled(false);
		btnMusic.setBorderPainted(true);
		btnMusic.setFocusable(false);
		btnMusic.setBorder(new LineBorder(new Color(255, 231, 110)));
		
		btnMusic.getModel().addChangeListener(new ChangeListener() {
	        @Override
	        public void stateChanged(ChangeEvent e) {
	            ButtonModel model = (ButtonModel) e.getSource();
	            if (model.isRollover()) {
	            	btnMusic.setBorder(new LineBorder(new Color(112, 112, 112)));
	            	btnMusic.setBorderPainted(true); // 테두리 보이게
	            } else {
	            	btnMusic.setBorderPainted(false); // 테두리 안보이게   
	            	btnMusic.setBorderPainted(false); // 테두리 안보이게
	            }
	        }
	    });
		
		la.j1.add(btnMusic);

		btnMusic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(check_music == 1) { // 악보보기
					ImageIcon menu = new ImageIcon("../HaNaPiano/src/image/music_log_menu.png");
					la.j1.setIcon(menu);
					showMusic(la.j1, la.j2);				
				}else {
					la.j1.setIcon(la.menu);
					la.j2.setIcon(la.piano_1);
					la.j2.setBounds(0, 189, 1600, 712);
					check_music = 1;
					title.setVisible(false);
					musicName = 0;
					musicNum = 1;
				}
			}
		});		
		
		// 옥타브 올리기
		btnUp.setBounds(635, 70, 50, 50);
		btnUp.setOpaque(false);
		btnUp.setContentAreaFilled(false);
		btnUp.setBorderPainted(true);
		btnUp.setFocusable(false);
		btnUp.setBorder(new LineBorder(new Color(255, 231, 110)));
		
		btnUp.getModel().addChangeListener(new ChangeListener() {
	        @Override
	        public void stateChanged(ChangeEvent e) {
	            ButtonModel model = (ButtonModel) e.getSource();
	            if (model.isRollover()) {
	            	btnUp.setBorder(new LineBorder(new Color(112, 112, 112)));
	            	btnUp.setBorderPainted(true); // 테두리 보이게
	            } else {
	            	btnUp.setBorderPainted(false); // 테두리 안보이게
	            }
	        }
	    });
		
		la.j1.add(btnUp);
		
		btnUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(OctNum > 3) {
					OctNum--;
					KeyListener.octNum++;
					octave.setText("옥타브 "+OctNum);
					la.j1.add(octave);
					la.j1.setLayout(null);
					octave.setVisible(true);
				}
			}
		});
		
		// 옥타브 내리기
		btnDawn.setBounds(918, 70, 50, 50);
		btnDawn.setOpaque(false);
		btnDawn.setContentAreaFilled(false);
		btnDawn.setBorderPainted(true);
		btnDawn.setFocusable(false);
		btnDawn.setBorder(new LineBorder(new Color(255, 231, 110)));

		btnDawn.getModel().addChangeListener(new ChangeListener() {
	        @Override
	        public void stateChanged(ChangeEvent e) {
	            ButtonModel model = (ButtonModel) e.getSource();
	            if (model.isRollover()) {
	            	btnDawn.setBorder(new LineBorder(new Color(112, 112, 112)));
	            	btnDawn.setBorderPainted(true); // 테두리 보이게
	            } else {
	            	btnDawn.setBorderPainted(false); // 테두리 안보이게
	            }
	        }
	    });
		la.j1.add(btnDawn);
		
		btnDawn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(OctNum < 5) {
					OctNum++;
					KeyListener.octNum--;
					octave.setText("옥타브 "+OctNum);
					la.j1.add(octave);
					la.j1.setLayout(null);
					octave.setVisible(true);
				}
			}
		});
		
		// 녹음하기
		btnRecoding.setBounds(1105, 59, 68, 68);
		btnRecoding.setOpaque(false);
		btnRecoding.setContentAreaFilled(false);
		btnRecoding.setBorderPainted(false);
		btnRecoding.setFocusable(false);
		btnRecoding.setBorder(new RoundedBorder(50));

		btnRecoding.getModel().addChangeListener(new ChangeListener() {
	        @Override
	        public void stateChanged(ChangeEvent e) {
	            ButtonModel model = (ButtonModel) e.getSource();
	            if (model.isRollover()) {
	        		btnRecoding.setBorder(new RoundedBorder(50));
	            	btnRecoding.setBorderPainted(true); // 테두리 보이게
	            } else {
	            	btnRecoding.setBorderPainted(false); // 테두리 안보이게
	            }
	        }
	    });
		la.j1.add(btnRecoding);
		
		// 녹음 멈추기
		btnStop.setBounds(1185, 59, 68, 68);
		btnStop.setOpaque(false);
		btnStop.setContentAreaFilled(false);
		btnStop.setBorderPainted(false);
		btnStop.setFocusable(false);
		btnStop.setBorder(new RoundedBorder(50));

		
		btnStop.getModel().addChangeListener(new ChangeListener() {
	        @Override
	        public void stateChanged(ChangeEvent e) {
	            ButtonModel model = (ButtonModel) e.getSource();
	            if (model.isRollover()) {
	            	btnStop.setBorder(new RoundedBorder(50));
	            	btnStop.setBorderPainted(true); // 테두리 보이게
	            } else {
	            	btnStop.setBorderPainted(false); // 테두리 안보이게
	            }
	        }
	    });
		la.j1.add(btnStop);
		
		// 음악 재생
		btnPlay.setBounds(1265, 59, 68, 68);
		btnPlay.setOpaque(false);
		btnPlay.setContentAreaFilled(false);
		btnPlay.setBorderPainted(false);
		btnPlay.setFocusable(false);
		btnPlay.setBorder(new RoundedBorder(50));
		
		btnPlay.getModel().addChangeListener(new ChangeListener() {
	        @Override
	        public void stateChanged(ChangeEvent e) {
	            ButtonModel model = (ButtonModel) e.getSource();
	            if (model.isRollover()) {
	            	btnPlay.setBorder(new RoundedBorder(50));
	            	btnPlay.setBorderPainted(true); // 테두리 보이게
	            } else {
	            	btnPlay.setBorderPainted(false); // 테두리 안보이게
	            }
	        }
	    });
		la.j1.add(btnPlay);
		
		// 음악 정지
		btnPause.setBounds(1345, 59, 68, 68);
		btnPause.setOpaque(false);
		btnPause.setContentAreaFilled(false);
		btnPause.setBorderPainted(false);
		btnPause.setFocusable(false);
    	btnPause.setBorder(new RoundedBorder(50));
		
		btnPause.getModel().addChangeListener(new ChangeListener() {
	        @Override
	        public void stateChanged(ChangeEvent e) {
	            ButtonModel model = (ButtonModel) e.getSource();
	            if (model.isRollover()) {
	            	btnPause.setBorder(new RoundedBorder(50));
	            	btnPause.setBorderPainted(true); // 테두리 보이게
	            } else {
	            	btnPause.setBorderPainted(false); // 테두리 안보이게
	            }
	        }
	    });
		la.j1.add(btnPause);
		
		// 사용자 버튼
		btnUser.setBounds(1480, 57, 80, 90);
		btnUser.setOpaque(false);
		btnUser.setContentAreaFilled(false);
		btnUser.setBorderPainted(false);
		btnUser.setFocusable(false);
		btnUser.setBorder(new LineBorder(new Color(255, 231, 110)));
		
		btnUser.getModel().addChangeListener(new ChangeListener() {
	        @Override
	        public void stateChanged(ChangeEvent e) {
	            ButtonModel model = (ButtonModel) e.getSource();
	            if (model.isRollover()) {
	            	btnUser.setBorder(new LineBorder(new Color(112, 112, 112)));
	            	btnUser.setBorderPainted(true); // 테두리 보이게
	            } else {
	            	btnUser.setBorderPainted(false); // 테두리 안보이게
	            }
	        }
	    });
		
		btnUser.addActionListener(new MusicList(id));
		// recordIndex = mu.getList();
		
		la.j1.add(btnUser);
		
		Container c = getContentPane();
		c.setFocusable(true);
		c.requestFocus();
		// 키 입력 받기
		
		KeyListener playPiano = new KeyListener(); // 키 리스너 객체 생성
		c.addKeyListener(playPiano);
		
		// 녹음 시작
		btnRecoding.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				KeyListener.recording = true; // 녹음 중 변수 true
				KeyListener.recordKey.clear(); // 녹음 키 리스트 비우기
				KeyListener.recordTime.clear(); // 녹음 시간 리스트 비우기
				KeyListener.keySecond = System.currentTimeMillis(); // 현재 시간 표시
				
			}
			
		});
		
		// 녹음 재생
		btnPlay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				th = new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						KeyListener.recordPlay(); // 스레드로 녹음 재생
					}
				});
				th.start();
				
			}
		});
		
		// 녹음 중지
		btnStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				KeyListener.recording = false; // 녹음 중 변수 false
				recordfile.recordFile(); // 파일입출력 실행
				recordCnt = Main.mysql.getRecordCnt(id); // 녹음 갯수 가져오기
				Main.mysql.setRecordCnt(id, recordCnt+1); // 녹음 갯수 + 1 데이터베이스에 넣기
				
			}
		});
		
		// 재생 정지
		btnPause.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				KeyListener.playing = false; // 연주 중 변수 false
				
			}
		});
		
	}
	public int processLogin() {
		int loginCode = Main.mysql.login(this.id, this.pw); // MySQL 클래스 변수를 통해 로그인하고 결과 코드 가져오기
		String s = "";
		if(loginCode == 1) {
			s = "아이디가 존재하지 않습니다.";
			toast = new Toast(s, 800, 700);
		}
		else if(loginCode == 2) {
			s = "로그인에 성공하였습니다.";
			toast = new Toast(s, 800, 700);
		}
		else if(loginCode == 0){
			s = "비밀번호가 일치하지 않습니다."; 
			toast = new Toast(s, 800, 700);
		}
		// 로그인 결과에 따라 토스트 변수 생성
		return loginCode;
		
	}
	public void showMusic(JLabel j1, JLabel j2) {
		ImageIcon musicImg = new ImageIcon(path+MusicList.music[musicName][musicNum]);
		imageLabel.setIcon(musicImg);		
		
		JButton back = new JButton();
		back.setBounds(640, 141, 49, 39);
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setFocusable(false);
		back.setBorder(new RoundedBorder(24));
		
		back.getModel().addChangeListener(new ChangeListener() {
	        @Override
	        public void stateChanged(ChangeEvent e) {
	            ButtonModel model = (ButtonModel) e.getSource();
	            if (model.isRollover()) {
	            	back.setBorder(new RoundedBorder(24));
	            	back.setBorderPainted(true); // 테두리 보이게
	            } else {
	            	back.setBorderPainted(false); // 테두리 안보이게
	            }
	        }
	    });
		
		JButton next = new JButton();
		next.setBounds(911, 141, 49, 39);
		next.setOpaque(false);
		next.setContentAreaFilled(false);
		next.setBorderPainted(false);
		next.setFocusable(false);
		next.setBorder(new RoundedBorder(24));
		
		next.getModel().addChangeListener(new ChangeListener() {
	        @Override
	        public void stateChanged(ChangeEvent e) {
	            ButtonModel model = (ButtonModel) e.getSource();
	            if (model.isRollover()) {
	            	next.setBorder(new RoundedBorder(24));
	            	next.setBorderPainted(true); // 테두리 보이게
	            } else {
	            	next.setBorderPainted(false); // 테두리 안보이게
	            }
	        }
	    });
		
		JButton select = new JButton();
		select.setBounds(745, 139, 110, 40);
		select.setOpaque(false);
		select.setContentAreaFilled(false);
		select.setBorderPainted(false);
		select.setFocusable(false);
		select.setBorder(new RoundedBorder(24));
		
		select.getModel().addChangeListener(new ChangeListener() {
	        @Override
	        public void stateChanged(ChangeEvent e) {
	            ButtonModel model = (ButtonModel) e.getSource();
	            if (model.isRollover()) {
	            	select.setBorder(new RoundedBorder(24));
	            	select.setBorderPainted(true); // 테두리 보이게
	            } else {
	            	select.setBorderPainted(false); // 테두리 안보이게
	            }
	        }
	    });
		
		
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				musicNum++;
				if(musicNum < MusicList.music[musicName].length) {
					ImageIcon musicImg = new ImageIcon(path+MusicList.music[musicName][musicNum]);
					imageLabel.setIcon(musicImg);
				}else {
					musicNum = MusicList.music[musicName].length - 1;
				}
				
			}
		});
		
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				musicNum--;
				if(musicNum > 0) {
					ImageIcon musicImg = new ImageIcon(path+MusicList.music[musicName][musicNum]);
					imageLabel.setIcon(musicImg);
				}else {
					musicNum = 1;
				}
			}
		});
		
		select.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				musicName++;
				musicNum = 1;
				if(musicName < MusicList.music.length) {
					ImageIcon musicImg = new ImageIcon(path+MusicList.music[musicName][musicNum]);
					imageLabel.setIcon(musicImg);
					title.setText(MusicList.music[musicName][0]);
				}else{
					musicName = 0;
					musicNum = 1;
					ImageIcon musicImg = new ImageIcon(path+MusicList.music[musicName][musicNum]);
					imageLabel.setIcon(musicImg);
					title.setText(MusicList.music[musicName][0]);
				}
			}
		});
		
		add(imageLabel);
		j1.add(back);
		j1.add(next);	
		j1.add(select);
		
		title.setText(MusicList.music[musicName][0]);
		title.setBounds(180, 35, 300, 100);
		title.setFont(new Font("SansSerif", Font.BOLD, 35));
		j1.add(title);
		j1.setLayout(null);
		title.setVisible(true);				
		
		ImageIcon piano_2 = new ImageIcon("../HaNaPiano/src/image/piano_2.png");
		// 피아노 띄우기
		j2.setIcon(piano_2);
		j2.setBounds(0, 373, 1600, 493);
		check_music = 0;
		
	}
	
}

class LabelList{
	ImageIcon menu = new ImageIcon("../HaNaPiano/src/image/login_menu.png");
	JLabel j1 = new JLabel(menu);
	
	ImageIcon piano_1 = new ImageIcon("../HaNaPiano/src/image/piano_1.png");
	JLabel j2 = new JLabel(piano_1);
		
}

