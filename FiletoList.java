package algo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//<< �������� �����ϴ� Ŭ����  >>
class FiletoList {
	// << ������ �а� ����Ʈ�� �����ѵ� ��ȯ�ϴ� �޼��� >> //
	public ArrayList<Item> createList() throws IOException{
	// < createList > : ������ ����Ʈ�� ����  ##

	// <���� ����>
	// savedList  	: ������ ����Ʈ ����   
	// csv        	: ������ �ҷ��ͼ� �����ϴ� ���� 
	// br        	: ���ڵ� �� ������ �б� ���� ���� 
	// line			: ���ڵ� �� ������ �����ϱ� ���� ���� 
	// aLine		: �� ��(��Ʈ��)�� ����Ʈ�� �����ϱ� ���� ���� 
	// lineArr		: ���� ������ �ɰ��� ���� ���� 

	// <�Լ� ����>
	// return�� ���ֱ� ���� ������ �����ϰ� 
	// ������ �о��ش�. �о��� ������ UTF-8���� �������� ���ڵ��ϰ�
	// while���� ����Ͽ� �� �پ� ������ ,���� ������ �ɰ� �� ����Ʈ�� �����Ѵ�
	// ����� ����Ʈ�� ���� �˸´� Ÿ���� �Ķ���ͷ� ��ȯ�Ͽ� 
	// �״�� ����Ʈ�� �߰����ش� 
	// ��� ���� �۾��� ������ ������ �ݰ� return�� �� �޼��带 �����Ѵ� 

		ArrayList<Item> savedList = new ArrayList<Item>();
      File csv = new File("C:\\JAVA\\algo\\src\\file\\notebook__final.txt");
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csv), "utf-8"));
      String line = "";
       
      while ((line = br.readLine()) != null) { // readLine()�� ���Ͽ��� ����� �� ���� �����͸� �о�´�.
          List<String> aLine = new ArrayList<String>();
          String[] lineArr = line.split(","); // ������ �� ���� ,�� ������ �迭�� ���� �� ����Ʈ�� ��ȯ�Ѵ�.
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
           br.close(); // ��� �� BufferedReader�� �ݾ��ش�.
       	}
       
       	return savedList;
	}
}
