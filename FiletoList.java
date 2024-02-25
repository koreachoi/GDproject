package algo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//<< 아이템을 저장하는 클래스  >>
class FiletoList {
	// << 파일을 읽고 리스트에 저장한뒤 반환하는 메서드 >> //
	public ArrayList<Item> createList() throws IOException{
	// < createList > : 파일을 리스트로 정렬  ##

	// <변수 설정>
	// savedList  	: 저장할 리스트 변수   
	// csv        	: 파일을 불러와서 저장하는 변수 
	// br        	: 인코딩 후 라인은 읽기 위한 변수 
	// line			: 인코딩 된 라인을 저장하기 위한 변수 
	// aLine		: 한 줄(스트링)을 리스트로 저장하기 위한 변수 
	// lineArr		: 읽은 파일을 쪼개기 위한 변수 

	// <함수 설명>
	// return을 해주기 위한 변수를 선언하고 
	// 파일을 읽어준다. 읽어준 파일은 UTF-8파일 형식으로 인코딩하고
	// while문을 사용하여 한 줄씩 받으며 ,받은 라인을 쪼갠 뒤 리스트로 저장한다
	// 저장된 리스트를 각각 알맞는 타입의 파라미터로 변환하여 
	// 그대로 리스트에 추가해준다 
	// 모든 줄의 작업을 끝내면 파일을 닫고 return한 뒤 메서드를 종료한다 

		ArrayList<Item> savedList = new ArrayList<Item>();
      File csv = new File("C:\\JAVA\\algo\\src\\file\\notebook__final.txt");
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csv), "utf-8"));
      String line = "";
       
      while ((line = br.readLine()) != null) { // readLine()은 파일에서 개행된 한 줄의 데이터를 읽어온다.
          List<String> aLine = new ArrayList<String>();
          String[] lineArr = line.split(","); // 파일의 한 줄을 ,로 나누어 배열에 저장 후 리스트로 변환한다.
          aLine = Arrays.asList(lineArr);
       	String name = aLine.get(0);
       	String brand = aLine.get(1);
      	double size =Double.valueOf(aLine.get(2)); 
      	int resolution = Integer.parseInt(aLine.get(3));
      	int refresh = Integer.parseInt(aLine.get(4));
      	int brightness = Integer.parseInt(aLine.get(5));
      	double battery = Double.valueOf(aLine.get(6));
      	double weight = Double.valueOf(aLine.get(7));
      	int cpu_score = Integer.parseInt(aLine.get(8));
      	int ram = Integer.parseInt(aLine.get(9));
      	int ram_change = Integer.parseInt(aLine.get(10));
      	int gpu_type = Integer.parseInt(aLine.get(11));
      	int gpu_score = Integer.parseInt(aLine.get(12));
      	int price = Integer.parseInt(aLine.get(13));
      	double total_score = Double.valueOf(aLine.get(14));
          savedList.add(new Item(
      	 			name, brand, size, resolution, refresh, 
      	 			brightness, battery, weight, cpu_score, ram,
      	 			ram_change, gpu_type, gpu_score, price, total_score		
          		));
      	}
      
       	if (br != null) { 
           br.close(); // 사용 후 BufferedReader를 닫아준다.
       	}
       
       	return savedList;
	}
}
