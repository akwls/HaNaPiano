package pack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RecordFile {
	ArrayList<Integer> recordKey; // 눌린 키 리스트
	ArrayList<Long> recordTime; // 지연시간 리스트
	String id; // 아이디(파일 이름)
	
	public RecordFile(ArrayList<Integer> recordKey, ArrayList<Long> recordTime, String id) {
		this.recordKey = recordKey;
		this.recordTime = recordTime;
		this.id = id;
	}
	
	// 파일입출력
	public void recordFile() {
		// 눌린 키 문자열로 연결
		String keys = "";
		for(int i=0; i<recordKey.size(); i++) {
			keys += recordKey.get(i).toString();
		}
		// 지연시간 문자열로 연결
		String times = "";
		for(int i=0; i<recordTime.size(); i++) {
			times += recordTime.get(i).toString() + " ";
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
			BufferedWriter bw=new BufferedWriter(new FileWriter(file));
			bw.write(keys);
			bw.newLine();
			bw.write(times);
			bw.newLine();
			bw.flush();
			bw.close();
			System.out.println("파일 작성 완료");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
