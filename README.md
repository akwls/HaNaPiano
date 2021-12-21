# HaNaPiano
## 키보드로 피아노를 연주, 녹음할 수 있는 프로그램
### 사용 기술, 언어
Java, Eclipse, MySQL

### 1. 실행 화면

![image](https://user-images.githubusercontent.com/80630432/146872455-a988f90f-8257-4286-b93d-1196b07415fb.png)
메인 화면
![image](https://user-images.githubusercontent.com/80630432/146872466-5d715127-451c-4015-8ce4-b03ee2778323.png)
악보 보기
![image](https://user-images.githubusercontent.com/80630432/146872500-478c9b03-c1b2-4884-8071-41bb880b96dc.png)
녹음 리스트

### 2. 핵심 기능 & 코드
#### 2.1 녹음 기능
녹음 버튼을 누르고 키를 누르면 누른 키와 다음 키를 누르기 까지의 지연 시간이 ArrayList에 저장됩니다.
녹음 재생 시에는 지연 시간만큼 Sleep 후 키에 대응되는 음을 재생합니다.
```
if(this.recording) { // 녹음 중 변수가 true면
	recordKey.add(i); // 녹음 키 리스트 add
	recordOctave.add(octNum); // 녹음 옥타브 리스트 add
	currentSecond = System.currentTimeMillis() - keySecond; // 현재 키가 눌린 시간에서 이전 키가 눌린 시간 빼기
	recordTime.add(currentSecond); // 지연 시간 리스트 add
	keySecond = System.currentTimeMillis(); // 이전 키가 눌린 시간에 현재 키가 눌린 시간 넣기
}
```


#### 2.2 로그인
user 테이블에서 입력받은 아이디가 존재하는지 체크한 후, 비밀번호가 일치하는지 확인합니다. 
```
int login(String id, String pw) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int cnt = 0;
		try {
			String sql = "select * from user where id = ?"; // 아이디 존재 여부 체크
			stmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			rs.last();
			int rowCount = rs.getRow();
			rs.beforeFirst();
			if(rowCount < 1) {
				stmt.close();
				return 1;
			}
			else {
				cnt = 0;
				sql = "select * from user where id = ? and pw = ?"; // 비밀번호 확인
				stmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE);
				stmt.setString(1, id);
				stmt.setString(2, pw);
				rs = stmt.executeQuery();
				rs.last();
				rowCount = rs.getRow();
				rs.beforeFirst();
				if(rowCount == 1) {
					stmt.close();
					return 2;
				}
				else {
					stmt.close();
					return 0;
				}
			}
		} catch(SQLException ee) {
			System.out.println("명령어 전송 실패"+ee.toString());
		}
		return 0;
	}
```

#### 2.3 녹음 파일입출력
로그인했을 경우, 아이디 이름으로 파일을 생성하고 녹음한 내용을 파일에 저장합니다.
사용자 버튼을 누르면 녹음한 목록이 뜨고 클릭 시, 녹음이 재생됩니다.

녹음한 내용 파일에 작성하기
```
public void recordFile() {
		// 눌린 키 문자열로 연결
		String keys = "";
		for(int i=0; i<recordKey.size(); i++) {
			keys += recordKey.get(i).toString() + " ";
		}
		// 지연시간 문자열로 연결
		String times = "";
		for(int i=0; i<recordTime.size(); i++) {
			times += recordTime.get(i).toString() + " ";
		}
		String octave = "";
		for(int i=0; i<recordOctave.size(); i++) {
			octave += recordOctave.get(i).toString() + " ";
		}
		// 아이디로 파일 이름 설정
		 File file=new File("../HaNaPiano/src/record/" + id + ".txt");
	     if(!file.exists()) {
			try {
				file.createNewFile(); // 파일이 존재하지 않으면 새 파일 만들기
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     }
	     System.out.println("파일 생성 완료");
	     try {
	    	 // 파일에 입력
			BufferedWriter bw=new BufferedWriter(new FileWriter(file, true));
			bw.write(keys);
			bw.newLine();
			bw.write(times);
			bw.newLine();
			bw.write(octave);
			bw.newLine();
			bw.flush();
			bw.close();
			
			System.out.println("파일 작성 완료");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
```

### 업무 분담
- 김하진 : DB(로그인, 회원가입 등), 파일입출력(녹음, 재생 등)
- 김유나 : GUI(버튼, 악보 등)
