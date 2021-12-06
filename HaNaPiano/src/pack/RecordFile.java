package pack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class RecordFile {
	ArrayList<Integer> recordKey; // 눌린 키 리스트
	ArrayList<Long> recordTime; // 지연시간 리스트
	ArrayList<Integer> recordOctave;
	static String id; // 아이디(파일 이름)
	
	public RecordFile(ArrayList<Integer> recordKey, ArrayList<Long> recordTime, ArrayList<Integer> recordOctave, String id) {
		this.recordKey = recordKey;
		this.recordTime = recordTime;
		this.recordOctave = recordOctave;
		this.id = id;
	}
	
	// 파일입출력
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
	public static void playRecord(int index) {
		File file=new File("../HaNaPiano/src/record/" + id + ".txt");
		ArrayList<String> files = new ArrayList<String>();
		try {
			BufferedReader br=new BufferedReader(new FileReader(file));
			Stream<String> stream = br.lines();
			stream.forEach(arr -> files.add(arr));
			//files = (String[]) stream.toArray();
			String[] recordKey = files.get(index*3).split(" ");
			String[] recordTime = files.get(index*3+1).split(" ");
			String[] recordOctave = files.get(index*3+2).split(" ");
			KeyListener.recordPlay(recordKey, recordTime, recordOctave);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
