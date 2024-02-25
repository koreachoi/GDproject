package algo;
/*
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// << �������� �����ϰ� ������ ���ϴ� Ŭ���� >> //
class Item {
	// << Ŭ���� ���� >> // 
	private String name;
	private double size;
	private int resolution;
	private int brightness;
	private int cpu_score;
	private int ram;
	private int ram_change;
	private int gpu_type;
	private int gpu_score;
	private double gpu_high;
	private double gpu_ultra;
	private double gpu_qhd;
	private int price;
	private int os;
	private double total_score;
	
	// << Ŭ���� ������ >> // 
	Item(
			String name, double size, int resolution, 
			int brightness, int cpu_score, int ram,
			int ram_change, int gpu_type, int gpu_score,
			double gpu_high, double gpu_ultra, double gpu_qhd,
			int price, int os, double total_score
		){
		this.name = name;
		this.size = size;
		this.resolution = resolution;
		this.brightness = brightness;
		this.cpu_score = cpu_score;
		this.ram = ram;
		this.ram_change = ram_change;
		this.gpu_type = gpu_type;
		this.gpu_score = gpu_score;
		this.gpu_high = gpu_high;
		this.gpu_ultra = gpu_ultra;
		this.gpu_qhd = gpu_qhd;
		this.price = price;
		this.os = os;
		this.total_score = total_score;
	}
	
	// << Ŭ���� ������ >> // 
	Item(){
		this.name = " ";
		this.size = 0;
		this.resolution = 0;
		this.brightness = 0;
		this.cpu_score = 0;
		this.ram = 0;
		this.ram_change = 0;
		this.gpu_type = 0;
		this.gpu_score = 0;
		this.gpu_high = 0;
		this.gpu_ultra = 0;
		this.gpu_qhd = 0;
		this.price = 0;
		this.os = 0;
		this.total_score = 0;
	}
	
	// << ���� ��� �޼��� >> // 
	public double calRatio(double s_point, double i_point) {
	// < calRatio > : ���� ���� ��� ��� //

	// <���� ����>
	// s_point  : ���� ���� 
	// i_point  : ���õ� ������ ����
	// t        : ���� ������ ���õ� �������� ��������
	// s        : ���� ������ ���� �������� ����(%)
	// r        : ���� ���� �ٰŷ� ������ �̿��� �������� ū����, �ּ��� ���� ���� �Ҵ�

	// <�Լ� ����>
	// ���� �ٷ� ������ ������ Ȯ���� ���
	// �������� �Ǵ� �������� �������� ���� Ŭ ��� 
	// ����� ������ �� ���� �ȴ�
	// ���� �� ���� ���� : 100 �������� ���� : 90�� ���
	// ���� = (90 / 100) * 100 = 90% ��� ���� ���� �� ������
	// ���� ���� : 100 �������� ���� 110�� ���
	// ���� = (110 / 100) * 100 = 110% ��� ����� ������ ������
	// ���� ���̸� ���ϰ�, �� ���̸� ���� ���������� ������ ����Ѵ�
	// �ٸ�, ���� ���̰� ���� �������� ū ��찡 ������ ��� 
	// ��ǥ���ϴ� �ּҰ� 0, �ִ밪 100���� �ִ밪 100�� �ʰ��ϱ� ������
	// �׷��� ��쿡�� ���� 0���� �Ҵ��Ѵ�

	// < 0 ���� ���>  
	// 1: ������ ������ 0�� ��� 0��
	// 2: �� �������� ���̰� ���� �������� ū ���
		
		double t = 0;
		double s = 0;
		double r = 0;
		if (i_point == 0)
			return 0;
		else {
			if (s_point > i_point)
				t = s_point - i_point;
			else if (s_point < i_point)
				t = i_point - s_point;
			if (s_point < t)
				return 0;
			else
				s = t / s_point;
			
			r = Math.pow(((1 - s) * 10), 2);
				
		}
		return r;
	}
	
	// << �� ������ getter setter �޼���  >> //
	public String getName() {
		return this.name;
	}
	public double getSize() {
		return this.size;
	}
	public int getResolution() {
		return this.resolution;
	}
	public int getBrightness() {
		return this.brightness;
	}
	public double getCpu_score() {
		return this.cpu_score;
	}
	public int getRam() {
		return this.ram;
	}
	public int getRam_change() {
		return this.ram_change;
	}
	public int getGpu_type() {
		return this.gpu_type;
	}
	public double getGpu_score() {
		return this.gpu_score;
	}
	public int getPrice() {
		return this.price;
	}
	public int getOS() {
		return this.os;
	}
	public double getTotal_score() {
		return this.total_score;
	}
	public void setTotal_score(double score) {
		this.total_score = score;
	}
}

//<< �ߺ��� ���� ������ ��� Ŭ����  >> //
class savedMinScore {
	private double cpuScore;
	private double gpuScore;
	
	savedMinScore(){
		this.cpuScore = 0;
		this.gpuScore = 0;
	}
	public double getCpuScore() {
		return this.cpuScore;
	}
	public double getGpuScore() {
		return this.gpuScore;
	}
	public void setCpuScore(double cpuScore) {
		this.cpuScore = cpuScore;
	}
	public void setGpuScore(double gpuScore) {
		this.gpuScore = gpuScore;
	}
	public void compareCpuScore(double cpuScore) {
		if (this.cpuScore < cpuScore) {
			this.cpuScore = cpuScore;
		}
	}
	public void compareGpuScore(double gpuScore) {
		if (this.gpuScore < gpuScore) {
			this.gpuScore = gpuScore;
		}
	}
	
}
// << ����, ��õ �� �۾��� �̷������ ���� Ŭ���� >> //
class recoItem {
	// << Ŭ���� ����Ʈ ���� >> // 
	private ArrayList<Item> c_list = new ArrayList<Item>();
	
	// << Ŭ���� ������ >> // 
	recoItem(ArrayList<Item> c_list){
		this.c_list = c_list;
	}
	
	// << CPU�� �����ϴ� Ŭ���� �޼��� >> // 
	public ArrayList<Item> sortCPU(ArrayList<Item> c_list){
	// <CPU SORT> : ��ǰ ���� 
	// <�Լ� ����>
	// �Լ��� ���� �⺻���� ���� �˰������� ����Ͽ�
	// ������������ �����Ѵ�
	// ���� �˰����� ���� �� ȿ������ �˰������� ���� ����
		Item temp = new Item();
		for (int i = 0; i < c_list.size(); i++) {
			for (int j = i; j < c_list.size(); j++) {
				if (c_list.get(i).getCpu_score() < c_list.get(j).getCpu_score()) {
					temp = c_list.get(i);
					c_list.set(i, c_list.get(j));
					c_list.set(j, temp);
				}
			}
		}
		return c_list;
	}
	
	// << GPU�� �����ϴ� Ŭ���� �޼��� >> // 
	public ArrayList<Item> sortGPU(ArrayList<Item> c_list){
	// <GPU SORT> : ��ǰ ���� 
	// <�Լ� ����>
	// �Լ��� ���� �⺻���� ���� �˰������� ����Ͽ�
	// ������������ �����Ѵ�
	// ���� �˰����� ���� �� ȿ������ �˰������� ���� ����
		Item temp = new Item();
		for (int i = 0; i < c_list.size(); i++) {
			for (int j = i; j < c_list.size(); j++) {
				if (c_list.get(i).getGpu_score() < c_list.get(j).getGpu_score()) {
					temp = c_list.get(i);
					c_list.set(i, c_list.get(j));
					c_list.set(j, temp);
				}
			}
		}
		return c_list;
	}
	
	// << TotalScore�� �����ϴ� Ŭ���� �޼��� //
	public ArrayList<Item> sortTotalScore(ArrayList<Item> c_list){
		Item temp = new Item();
		for (int i = 0; i < c_list.size(); i++) {
			for (int j = i; j < c_list.size(); j++) {
				if (c_list.get(i).getTotal_score() > c_list.get(j).getTotal_score()) {
					temp = c_list.get(i);
					c_list.set(i, c_list.get(j));
					c_list.set(j, temp);
				}
			}
		}
		return c_list;
	}
	
	// << GPU ������ total ������ ȯ���ϴ� �޼��� >> // 
	public ArrayList<Item> calGpuscoreToTotal(){ return c_list;}
	
	// << CPU ������ total ������ ȯ���ϴ� �޼��� >> // 
	public ArrayList<Item> calCpuscoreToTotal(){ return c_list;}
	
	// << ��� ������ total ������ ȯ���ϴ� �޼��� >> // 
	public ArrayList<Item> calBrightnessscoreToTotal(){ return c_list;}
	
	// << �ػ� ���ǿ� �´� �������� ���͸��ϴ� �޼���  >> //
	public ArrayList<Item> filteredResolution(){ return c_list;}
	
	// << �������� : ����� ����� �����ϴ� �޼��� >> // 
	public ArrayList<Item> filteredSizeItem(ArrayList<Item> c_list, double minSize){
	// < filteredSizeItem > : ������ ���͸� �޼���  //

	// <���� ����>
	// mins  				: ũ���� ������ 
	// maxs  				: ũ���� �ִ밪 
	// filteredSizeList    	: ���͸��� ����Ʈ�� ��� ����Ʈ ���� 


	// <�Լ� ����>
	// �������� �޴� ������ ���� �� ũ�⿡ ���� �ִ밪�� �����ش�
	// 17�������� ��� �ִ밪�� 20���� ���ְ�
	// �� ���� ������� ���������� 1�� ���� ������ ���ش�
	// 14 �̸��� �������� �ִ밪�� 14�� �������ش�
	// �� �� �� ����� �´� ���� �� ����Ʈ�� �߰��ϰ�
	// ��ȯ���ش�
		
		ArrayList<Item> filteredSizeList = new ArrayList<Item>();
		double mins = minSize;
		double maxs = 0;
		
		if (mins == 17) {
			maxs = mins + 3;
		}else if (mins >= 14 && mins < 17){
			maxs = mins + 1;
		}else if (mins < 14) {
			maxs = 14;
			mins = 0;
		}
		
		for (int i = 0 ; i < c_list.size(); i++) {
			if (c_list.get(i).getSize() >= mins && c_list.get(i).getSize() < maxs) {
				filteredSizeList.add(c_list.get(i));
			}
		}
		return filteredSizeList;
	}
	
	// << �������� : OS�� ����� �����ϴ� �޼��� >> //
	public ArrayList<Item> filteredOsItem(ArrayList<Item> c_list, int os){
	// < filteredOsItem > : CPU������ ��� �� total ������ �����ϴ� �޼���  //
		
	// <���� ����>
	// o 				: OSŸ��
	// filteredOsList	: ���͵� ����Ʈ 
	
	// <�Լ� ����>
	// WINDOW�� ��� 1
	// MAC�� ��� 0
	// �ݺ����� ���� �ش��ϴ� OS�� �� ����Ʈ�� �߰����ش�
		
		ArrayList<Item> filteredOsList = new ArrayList<Item>();
		int o = os;
	
			for (int i = 0; i < c_list.size(); i++) {
				if(c_list.get(i).getOS() == o) {
					filteredOsList.add(c_list.get(i));
			}
		}
		return filteredOsList;
	}
	
	// << ������ ���ϴ� �޼��� >> //
	public ArrayList<Item> effectivenessCost(ArrayList<Item> c_list){
	// < effectivenessCost > : ������ ����� ���ѵ� ������ �����ϴ� �޼���   //
		
	// <���� ����>
	// surScore	: ������ ��� 
	// total	: �� ����  
	// price	: ���� ����
		
	// <�Լ� ����>
	// ������ ����� �����ְ�
	// ������ ������ ����� ���� ���������� ��������־� �����Ѵ�
	// ����Ʈ�� ��ȯ�Ѵ�
		
		double surScore = 0;
		double total = 0;
		double price = 0;
		
		for (int i = 0 ; i < c_list.size(); i++) {
			total = c_list.get(i).getTotal_score();
			price = c_list.get(i).getPrice();
			surScore = total / (total + (price/10000) + 50);
			c_list.get(i).setTotal_score(c_list.get(i).getTotal_score() * surScore);
		}
		
		return c_list;
	}
	
	// << ���� ���� >> // 
	public ArrayList<Item> askedCommonQuestion(ArrayList<Item> c_list){
	// < effectivenessCost > : ������ ����� ���ѵ� ������ �����ϴ� �޼���   //
		
	// <���� ����>
	// selector		: ȭ�� ũ�⸦ ���ϴ� ���� 
	// selector2	: OS ������ �����ϴ� ���� 
	// OS			: OS Ÿ���� ���� �����ϴ� ���� 
			
	// <�Լ� ����>
	// ������� OS�� ���Ѵ�
		
		System.out.println("ȭ��ũ�� ���� 1:17 �̻� 2:16 �̻� 3:15 �̻� 4:14 �̻� 5:14 �̸�");
		Scanner scanner = new Scanner(System.in);
		int selector = scanner.nextInt();
		double size = 0;
		
		if (selector == 1) {
			size = 17;
			c_list = filteredSizeItem(c_list, size);
		}else if(selector == 2) {
			size = 16;
			c_list = filteredSizeItem(c_list, size);
		}else if(selector == 3) {
			size = 15;
			c_list = filteredSizeItem(c_list, size);
		}else if(selector == 4) {
			size = 14;
			c_list = filteredSizeItem(c_list, size);
		}else if(selector == 5) {
			size = 13;
			c_list = filteredSizeItem(c_list, size);
		}
		
		System.out.println("�ü�� ���� 1:WINDOW 2:MAC");
		int selector2 = scanner.nextInt();
		int OS = 0;
		
		if (selector2 == 1) {				// WINDOW
			OS = 1; 					
			c_list = filteredOsItem(c_list, 1);
		} else if (selector2 == 2) {		// MAC
			OS = 0;
			c_list = filteredOsItem(c_list, 0);
		}

		return c_list;
	}
}

//<< ����, ��õ �� �۾��� �̷������ ���� Ŭ������ ������ �������� ����� Ŭ���� >> //
class recoGameItem extends recoItem{

	recoGameItem(ArrayList<Item> c_list) {
		super(c_list);
	}

	// << GPU ������ total ������ ȯ���ϴ� �޼��� >> // 
	public ArrayList<Item> calGpuscoreToTotal(ArrayList<Item> c_list, double ratio, int selected){
	// < calGpuscoreToTotal > : GPU������ ��� �� total ������ �����ϴ� �޼���  //

	// <���� ����>
	// s_item  		: ������ �Ǵ� �������� ���� * ����
	// i_item  		: ���õ� �������� ���� 
	// cal_point    : �޼���� ���� ���� ��� ���� 
	// r        	: ���� ������ ���� ����(%)
	// s        	: ���õ� �������� �����ϱ� ���� ����

	// <�Լ� ����>
	// ����Ʈ�� ���̸�ŭ �ݺ��ϴµ� MAC OS�� ��츦 �����Ѵ�
	// ���� ���õ� ���� �����(3)�� ��� ����׷����� �����ϰ�
	// �� ���� ��쿡�� ����׷����� �����ϰ� ����Ѵ�
	// �ٸ� ����(1)�� ��� CPU�� ���ɶ��� Ȯ���ϱ� ������
	// ������ ������ 5�ҷ� �����ϰ�, �߰� ����� ��� ���� �����Ѵ�

		double s_item = 0;
		double i_item = 0;
		double cal_point = 0;
		double r = ratio;
		int s = selected;
		c_list = sortGPU(c_list);
		for (int i = 0; i < c_list.size(); i++) {
			if (c_list.get(i).getOS() == 1) {
				if (s == 3) {
					s_item = c_list.get(0).getGpu_score() * r;
					i_item = c_list.get(i).getGpu_score();
					cal_point = c_list.get(i).calRatio(s_item, i_item);
					c_list.get(i).setTotal_score(cal_point);
				}else {
					if (c_list.get(i).getGpu_type() == 1) {
						s_item = c_list.get(0).getGpu_score() * r;
						i_item = c_list.get(i).getGpu_score();
						if (s == 1) {
							cal_point =  0.5 * c_list.get(i).calRatio(s_item, i_item);
						}else if (s == 2) {
							cal_point = c_list.get(i).calRatio(s_item, i_item);
						}
						c_list.get(i).setTotal_score(cal_point);
					}
				}
			}
		}
		return c_list;
	}
	
	// << CPU ������ total ������ ȯ���ϴ� �޼��� >> // 
	public ArrayList<Item> calCpuscoreToTotal(ArrayList<Item> c_list, double ratio){
	// < calCpuscoreToTotal > : CPU������ ��� �� total ������ �����ϴ� �޼���  //

	// <���� ����>
	// s_item  		: ������ �Ǵ� �������� ���� * ����
	// i_item  		: ���õ� �������� ���� 
	// cal_point    : �޼���� ���� ���� ��� ���� 
	// r        	: ���� ������ ���� ����(%)

	// <�Լ� ����>
	// ����Ʈ�� ���̸�ŭ �ݺ��ϴµ� MAC OS�� ��츦 �����Ѵ�
	// CPU�� ����ϴ� ���� ������ ����̹Ƿ�
	// ������ ���� ��ü�� 5�������� �����ش�
		
		double s_item = 0;
		double i_item = 0;
		double cal_point = 0;
		double r = ratio;
		c_list = sortCPU(c_list);
		for (int i = 0; i < c_list.size(); i++) {
			if (c_list.get(i).getOS() == 1) {
				if (c_list.get(i).getGpu_type() == 1) {
					s_item = c_list.get(0).getCpu_score() * r;
					i_item = c_list.get(i).getCpu_score();
					cal_point =  0.5 * c_list.get(i).calRatio(s_item, i_item);
					c_list.get(i).setTotal_score(c_list.get(i).getTotal_score()+ cal_point);
				}
			}
		}
		return c_list;
	}
	
	// << ������ ���õǾ������� Ŭ���� �޼��� >> // 
	public ArrayList<Item> selectedGame(ArrayList<Item> c_list, savedMinScore minScore){
	// < selectedGame > : ������ ���õǾ������� �޼���  //

	// <���� ����>
	// selected_ratio 		: ���õ� ��翡 ���� ������ �����ϴ� ���� 
	// selected_option  	: �������� � ������ üũ�ϴ� ���� 
	// checked_gpu_type    	: ���õ� GPUŸ���� üũ�ϴ� ���� 
	// selector				: ������ 1�� ������ ����� �˰� �ִ��� Ȯ���ϱ� ���� ����  
	// selector2			: ������ 2�� ������ ����� ����� ���� ���� 
	// selector3			: ������ 3�� ������ �����ο� ȯ���� Ȯ���ϱ� ���� ���� 
	// setGPUScore			: GPU �������� �����ϱ� ���� ���� 
	// setCPUScore			: CPU �������� ����ϱ� ���� ���� 
		
	// <�Լ� ����>
	// ù ��° �������� �ڽ��� �ϴ� ������ ����� �˰� �ִ��� ������ �޴´�
	// �˰� �ִ� ��� ����� ����� �ش� ��翡 �´� ������ �����Ѵ�
	// ����� �߰������ ��� ����׷����� �������� ������
	// ������ CPU�������� ����Ѵ�
	// ���������� ������, ���ϴ� ȯ�濡 ���� ������ �ϰ�
	// ������ ���� �信 ���� �߰����� ����ġ�� �ְų�, ��ŵ�ϰ�
	// ����Ʈ�� �����ϴ°����� ������

		Scanner scanner = new Scanner(System.in);
		double selected_ratio = 0;
		int selected_option = 0;
		int checked_gpu_type = 0;
		double setGPUScore = 0;
		double setCPUScore = 0;
		
		System.out.println("������ �ַ� �ϴ� ������ �������� ������� �ľ��ϰ� ��Ű���? 1:O 2:X");
		int selector = scanner.nextInt();
		if (selector == 1) {
			System.out.println("��������� ����� �䱸�Ǵ� ������ �÷����ϰ� ��Ű���? 1:���� 2:�߰���� 3:�����");
			int selector2 = scanner.nextInt();
			if (selector2 == 1) {
				selected_ratio = 0.9;
				selected_option = 1;
				checked_gpu_type = 1;
				c_list = calGpuscoreToTotal(c_list, selected_ratio, selected_option);
				c_list = calCpuscoreToTotal(c_list, selected_ratio);
				setCPUScore = c_list.get(0).getCpu_score() * selected_ratio; 
				minScore.compareCpuScore(setCPUScore);
			}else if(selector2 == 2) {
				selected_ratio = 0.5;
				selected_option = 2;
				checked_gpu_type = 1;
				c_list = calGpuscoreToTotal(c_list, selected_ratio, selected_option);
			}else if(selector2 == 3) {
				selected_ratio = 0.2;
				selected_option = 3;
				checked_gpu_type = 0;
				c_list = calGpuscoreToTotal(c_list, selected_ratio, selected_option);
			}
			setGPUScore = c_list.get(0).getGpu_score() * selected_ratio; 
			minScore.compareGpuScore(setGPUScore);
		}else if (selector == 2) {
			System.out.println("���� ���� �����ֱ�");
		}
		
		System.out.println("������ �÷����� �� �������� �߿�� ����ó���? 1:�߿��ϴ� 2:�����̴� 3:ū�������");
		int selector3 = scanner.nextInt();
		if (selector3 == 1) {
			selected_ratio = selected_ratio + 0.1;
			if (checked_gpu_type == 1) {
				if (selected_option == 1) {
					c_list = calGpuscoreToTotal(c_list, selected_ratio, 1);
					setGPUScore = c_list.get(0).getGpu_score() * selected_ratio; 
					minScore.compareGpuScore(setGPUScore);
					
					c_list = calCpuscoreToTotal(c_list, selected_ratio);
					setCPUScore = c_list.get(0).getCpu_score() * selected_ratio; 
					minScore.compareCpuScore(setCPUScore);
				}else if (selected_option == 2){
					c_list = calGpuscoreToTotal(c_list, selected_ratio, 2);
					setGPUScore = c_list.get(0).getGpu_score() * selected_ratio; 
					minScore.compareGpuScore(setGPUScore);
				}
			}else if(checked_gpu_type == 0){
				c_list = calGpuscoreToTotal(c_list, selected_ratio, 3);
				setGPUScore = c_list.get(0).getGpu_score() * selected_ratio; 
				minScore.compareGpuScore(setGPUScore);
			}
			
		}else if (selector3 == 2) {
			selected_ratio = selected_ratio + 0.05;
			if (checked_gpu_type == 1) {
				if (selected_option == 1) {
					c_list = calGpuscoreToTotal(c_list, selected_ratio, 1);
					setGPUScore = c_list.get(0).getGpu_score() * selected_ratio; 
					minScore.compareGpuScore(setGPUScore);
					
					c_list = calCpuscoreToTotal(c_list, selected_ratio);
					setCPUScore = c_list.get(0).getCpu_score() * selected_ratio; 
					minScore.compareCpuScore(setCPUScore);
				}else {
					c_list = calGpuscoreToTotal(c_list, selected_ratio, 2);
					setGPUScore = c_list.get(0).getGpu_score() * selected_ratio; 
					minScore.compareGpuScore(setGPUScore);
				}
			}else if(checked_gpu_type == 0){
				c_list = calGpuscoreToTotal(c_list, selected_ratio, 3);
				setGPUScore = c_list.get(0).getGpu_score() * selected_ratio; 
				minScore.compareGpuScore(setGPUScore);
			}
		}else if (selector3 == 3) {
			return c_list;
		}
		return c_list;
	}
}

//<< ����, ��õ �� �۾��� �̷������ ���� Ŭ������ �ڵ��� �������� ����� Ŭ���� >> //
class recoCodingItem extends recoItem{

	recoCodingItem(ArrayList<Item> c_list) {
		super(c_list);
		// TODO Auto-generated constructor stub
	}

	// << GPU ������ total ������ ȯ���ϴ� �޼��� >> // 
	public ArrayList<Item> calGpuscoreToTotal(ArrayList<Item> c_list, double ratio, int selected){ 
	// < calGpuscoreToTotal > : GPU������ ��� �� total ������ �����ϴ� �޼���  //

	// <���� ����>
	// s_item  		: ������ �Ǵ� �������� ���� * ����
	// i_item  		: ���õ� �������� ���� 
	// cal_point    : �޼���� ���� ���� ��� ���� 
	// r        	: ���� ������ ���� ����(%)
	// s 			: ���õ� �������� �����ϱ� ���� ����
		
	// <�Լ� ����>
	// ���� GPU�ڿ��� ����ϴ� �������� ����� ����׷����� �����Ѵ�
	// OS�� ������� �ڵ带 �����Ѵ� 
		
			double s_item = 0;
			double i_item = 0;
			double cal_point = 0;
			double r = ratio;
			int s = selected;
			
			c_list = sortGPU(c_list);
			
			if (s == 1) {
				for (int i = 0; i < c_list.size(); i++) {
					if (c_list.get(i).getGpu_type() == 1) {
						s_item = c_list.get(0).getGpu_score() * r;
						i_item = c_list.get(i).getGpu_score();
						cal_point = 0.7 * c_list.get(i).calRatio(s_item, i_item);
						c_list.get(i).setTotal_score(cal_point);
					}
				}
			}else if(s == 2) {
				for (int i = 0; i < c_list.size(); i++) {
					s_item = c_list.get(0).getGpu_score() * r;
					i_item = c_list.get(i).getGpu_score();
					cal_point = 0.7 * c_list.get(i).calRatio(s_item, i_item);
					c_list.get(i).setTotal_score(cal_point);
				}
			}
			return c_list;
	}
	
	// << CPU ������ total ������ ȯ���ϴ� �޼��� >> // 
	public ArrayList<Item> calCpuscoreToTotal(ArrayList<Item> c_list, double ratio){
	// < calCpuscoreToTotal > : CPU������ ��� �� total ������ �����ϴ� �޼���  //

	// <���� ����>
	// s_item  		: ������ �Ǵ� �������� ���� * ����
	// i_item  		: ���õ� �������� ���� 
	// cal_point    : �޼���� ���� ���� ��� ���� 
	// r        	: ���� ������ ���� ����(%)

	// <�Լ� ����>
	// ��� �������� ���鼭 CPU������ �����ѵ� ���� ���� �����ش�
		
			double s_item = 0;
			double i_item = 0;
			double cal_point = 0;
			double r = ratio;
			
			c_list = sortCPU(c_list);
			
			for (int i = 0; i < c_list.size(); i++) {
				s_item = c_list.get(0).getCpu_score() * r;
				i_item = c_list.get(i).getCpu_score();
				cal_point = 0.3 * c_list.get(i).calRatio(s_item, i_item);
				c_list.get(i).setTotal_score(c_list.get(i).getTotal_score()+ cal_point);
			}
			return c_list;
	}
	
	// << �ڵ��� ���õǾ������� Ŭ���� �޼��� >> // 
	public ArrayList<Item> selectedCoding(ArrayList<Item> c_list, savedMinScore minScore){
	// < selectedCoding > : �ڵ��� ���õǾ������� �޼���  //

	// <���� ����>
	// selected_ratio 		: ���õ� ��翡 ���� ������ �����ϴ� ���� 
	// selected_option  	: �������� � ������ üũ�ϴ� ���� 
	// selector				: ������ 1�� ������ GPU�ڿ��� üũ�ϴ� ���� 
	// selector2			: ������ 2�� ������ CPU�ڿ��� ����� üũ�ϴ� ����
	// setGPUScore			: GPU �������� �����ϱ� ���� ���� 
	// setCPUScore			: CPU �������� ����ϱ� ���� ���� 


	// <�Լ� ����>
	// ù ��° �������� GPU �ڿ��� ����ϴ��� ����� 
	// �� ��° �������� ���� ���α׷��� ����ϴ��� ����� 
	// ����Ʈ�� �����ϴ°����� ������
		
		Scanner scanner = new Scanner(System.in);
		double selected_ratio = 0;
		int selected_option = 0;
		double setGPUScore = 0;
		double setCPUScore = 0;
		
		System.out.println("�ӽŷ���, ������, ���Ӱ��ߵ� GPU �ڿ��� ����ϴ� ���α׷����� ����Ͻó���? 1:O 2:X");
		int selector = scanner.nextInt();
		if (selector == 1) {
			selected_ratio = 0.7;
			selected_option = 1;
			System.out.println("� �۾��� ����Ͻó���? 1:������ 2:�ӽŷ��� 3:���� �׷��� ���Ӱ��� 4:��Ÿ(3D �۾�)");
			int selector1_1 = scanner.nextInt();
			c_list = calGpuscoreToTotal(c_list, selected_ratio, selected_option);
		}else if(selector == 2) {
			selected_ratio = 0.2;
			selected_option = 2;
			c_list = calGpuscoreToTotal(c_list, selected_ratio, selected_option);
		}
		setGPUScore = c_list.get(0).getGpu_score() * selected_ratio;
		minScore.compareGpuScore(setGPUScore);
		
		System.out.println("���� Ȥ�� ���� ������ �䱸�ϴ� ���α׷����� ����Ͻó���? 1:O 2:X");
		int selector2 = scanner.nextInt();
		if (selector2 == 1) {
			selected_ratio = 0.7;
			c_list = calCpuscoreToTotal(c_list, selected_ratio);
		}else if (selector2 == 2) {
			selected_ratio = 0.3;
			c_list = calCpuscoreToTotal(c_list, selected_ratio);
		}
		setCPUScore = c_list.get(0).getCpu_score() * selected_ratio;
		minScore.compareCpuScore(setCPUScore);
		
		return c_list;
	}
}

// << ����, ��õ �� �۾��� �̷������ ���� Ŭ������ �׷����� �������� ����� Ŭ���� >> //
class recoGraphicItem extends recoItem{

	recoGraphicItem(ArrayList<Item> c_list) {
		super(c_list);
		// TODO Auto-generated constructor stub
	}
	
	// << ��� ������ total ������ ȯ���ϴ� �޼��� >> // 
	public ArrayList<Item> calBrightnessscoreToTotal(ArrayList<Item> c_list, int score){ 
	// < calCpuscoreToTotal > : CPU������ ��� �� total ������ �����ϴ� �޼���  //

	// <���� ����>
	// s_item  		: ������ �Ǵ� �������� ���� 
	// i_item  		: ���õ� �������� ���� 
	// cal_point    : �޼���� ���� ���� ��� ���� 
	// s			: ��� ������ ������

	// <�Լ� ����>
	// ��� �������� ���鼭 ��� ������ �����ѵ� ���� ���� �����ش�
			
		double s_item = 0;
		double i_item = 0;
		double cal_point = 0;
		int s = score;
				
		for (int i = 0; i < c_list.size(); i++) {
			s_item = s;
			i_item = c_list.get(i).getBrightness();
			cal_point = 0.2 * c_list.get(i).calRatio(s_item, i_item);
			c_list.get(i).setTotal_score(cal_point);
		}
		return c_list;		
	}
	
	// << �ػ� ���ǿ� �´� �������� ���͸��ϴ� �޼���  >> //
	public ArrayList<Item> filteredResolution(ArrayList<Item> c_list, int selectedResolution){
	// < filteredResolution > : �ػ󵵿� ���� ���ǿ� �´� ���鸸 �߷����� �޼���   //

	// <���� ����>
	// filteredList		: ���͵� ���� ��� �� ����Ʈ
	// s				: �ػ󵵼����� 

	// <�Լ� ����>
	// �ػ� �������� ���� �� ���ǿ� �´� �ػ��� ��������
	// ���ο� ����Ʈ�� �߰����ش�
		
		ArrayList<Item> filteredList = new ArrayList<Item>();
		int s = selectedResolution;
		if (s == 1) {
			for (int i = 0; i < c_list.size(); i++) {
				if (c_list.get(i).getResolution() == 1) {
					filteredList.add(c_list.get(i));
				}
			}
		}else if(s == 0) {
			for (int i = 0; i < c_list.size(); i++) {
				if (c_list.get(i).getResolution() == 0) {
					filteredList.add(c_list.get(i));
				}
			}
		}
		return filteredList;
	}
	
	// << GPU ������ total ������ ȯ���ϴ� �޼��� >> //
	public ArrayList<Item> calGpuscoreToTotal(ArrayList<Item> c_list, double ratio){
	// < calGpuscoreToTotal > : GPU������ ��� �� total ������ �����ϴ� �޼���  //

	// <���� ����>
	// s_item  		: ������ �Ǵ� �������� ���� * ����
	// i_item  		: ���õ� �������� ���� 
	// cal_point    : �޼���� ���� ���� ��� ���� 
	// r        	: ���� ������ ���� ����(%)

	// <�Լ� ����>
	// ��� �������� ���鼭 GPU������ �����ѵ� ���� ���� �����ش�
		
		double s_item = 0;
		double i_item = 0;
		double cal_point = 0;
		double r = ratio;
		
		c_list = sortGPU(c_list);
		
		for (int i = 0; i < c_list.size(); i++) {
			s_item = c_list.get(0).getGpu_score() * r;
			i_item = c_list.get(i).getGpu_score();
			cal_point = 0.4 * c_list.get(i).calRatio(s_item, i_item);
			c_list.get(i).setTotal_score(c_list.get(i).getTotal_score()+ cal_point);
		}
		return c_list;
	}
	
	// << CPU ������ total ������ ȯ���ϴ� �޼��� >> //
	public ArrayList<Item> calCpuscoreToTotal(ArrayList<Item> c_list, double ratio){
	// < calCpuscoreToTotal > : CPU������ ��� �� total ������ �����ϴ� �޼���  //

	// <���� ����>
	// s_item  		: ������ �Ǵ� �������� ���� * ����
	// i_item  		: ���õ� �������� ���� 
	// cal_point    : �޼���� ���� ���� ��� ���� 
	// r        	: ���� ������ ���� ����(%)

	// <�Լ� ����>
	// ��� �������� ���鼭 CPU������ �����ѵ� ���� ���� �����ش�
		
		double s_item = 0;
		double i_item = 0;
		double cal_point = 0;
		double r = ratio;
		
		c_list = sortCPU(c_list);
		
		for (int i = 0; i < c_list.size(); i++) {
			s_item = c_list.get(0).getCpu_score() * r;
			i_item = c_list.get(i).getCpu_score();
			cal_point = 0.4 * c_list.get(i).calRatio(s_item, i_item);
			c_list.get(i).setTotal_score(c_list.get(i).getTotal_score()+ cal_point);
		}
		return c_list;
	}
	
	// << �׷��� ���õǾ������� Ŭ���� �޼��� >> // 
	public ArrayList<Item> selectedGraphic(ArrayList<Item> c_list, savedMinScore minScore){
	// < selectedCoding > 	: �׷����� ���õǾ������� �޼���  //

	// <���� ����>
	// selected_ratio 		: ���õ� ��翡 ���� ������ �����ϴ� ���� 
	// selectedResolution  	: ���õ� �ػ󵵸� üũ�ϴ� ����  
	// selector				: ����� �亯�� �޴� ���� 
	// selector2			: �ػ��� �亯�� �޴� ���� 
	// selector3			: GPU�ڿ��� ��� ���θ� �޴� ���� 
	// selector4			: CPU������ �亯�� �޴� ���� 
	// setGPUScore			: GPU �������� �����ϱ� ���� ���� 
	// setCPUScore			: CPU �������� ����ϱ� ���� ���� 


	// <�Լ� ����>
	// ù ��° �������� ��⿡ ���� ���� �ް� ������ �߰��Ѵ� 
	// �� ��° �������� �ػ��� ������ �ް� ���͸��Լ��� ȣ���Ѵ�  
	// �� ��° �������� GPU ��� ���θ� ����� ������ �߰��Ѵ�
	// �� ��° �������� ���ϴ� CPU ������ ����� ������ �߰��ѵ� �����Ѵ� 
		
		Scanner scanner = new Scanner(System.in);
		int selectedResolution = 0;
		double selected_ratio = 0;
		double setGPUScore = 0;
		double setCPUScore = 0;
		
		System.out.println("��Ʈ���� �̿��� �� �ַ� ��𿡼� �̿��ϳ���? 1:�߿�(���� ���) 2:���� �ǳ�(�߰� ������ ���) 3:�ǳ�(���� ���)");
		int selector = scanner.nextInt();
		if (selector == 1) {
			c_list = calBrightnessscoreToTotal(c_list, 700);
		}else if (selector == 2) {
			c_list = calBrightnessscoreToTotal(c_list, 450);
		}else if (selector == 3) {
			c_list = calBrightnessscoreToTotal(c_list, 200);
		}
		
		System.out.println("ȭ���� �������� �߿��ϴٰ� �����ϳ���? 1:O(QHD�̻�) 2:X(FHD)");
		int selector2 = scanner.nextInt();
		if (selector2 == 1) {
			selectedResolution = 1;
			c_list = filteredResolution(c_list, selectedResolution);
		}else if (selector2 == 2) {
			selectedResolution = 0;
			c_list = filteredResolution(c_list, selectedResolution);	
		}
		
		System.out.println("������ �۾��� �Ͻó���? 1:O 2:X");
		int selector3 = scanner.nextInt();
		if (selector3 == 1) {
			selected_ratio = 0.7;
			c_list = calGpuscoreToTotal(c_list, selected_ratio);
		}else if (selector3 == 2) {
			selected_ratio = 0.2;
			c_list = calGpuscoreToTotal(c_list, selected_ratio);
		}
		
		setGPUScore = c_list.get(0).getGpu_score() * selected_ratio;
		minScore.compareGpuScore(setGPUScore);
		
		System.out.println("�۾��� �ӵ�, ȿ������ �󸶳� �߿��ϰ� �����Ͻó���? 1:�ſ��߿� 2:�߿� 3:���� 4:�߿����� ����");
		int selector4 = scanner.nextInt();
		selected_ratio = 0;
		if (selector4 == 1) {
			selected_ratio = 0.9;
			c_list = calCpuscoreToTotal(c_list, selected_ratio);
		}else if(selector4 == 2) {
			selected_ratio = 0.7;
			c_list = calCpuscoreToTotal(c_list, selected_ratio);
		}else if(selector4 == 3) {
			selected_ratio = 0.5;
			c_list = calCpuscoreToTotal(c_list, selected_ratio);
		}else if(selector4 == 4) {
			selected_ratio = 0.3;
			c_list = calCpuscoreToTotal(c_list, selected_ratio);
		}
		
		setCPUScore = c_list.get(0).getCpu_score() * selected_ratio;
		minScore.compareCpuScore(setCPUScore);
		
		return c_list;
	}
}

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
        File csv = new File("C:\\JAVA\\algo\\src\\file\\note_final.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csv), "utf-8"));
        String line = "";
         
        while ((line = br.readLine()) != null) { // readLine()�� ���Ͽ��� ����� �� ���� �����͸� �о�´�.
            List<String> aLine = new ArrayList<String>();
            String[] lineArr = line.split(","); // ������ �� ���� ,�� ������ �迭�� ���� �� ����Ʈ�� ��ȯ�Ѵ�.
            aLine = Arrays.asList(lineArr);
         	String name = aLine.get(0);
        	double size = Double.valueOf(aLine.get(2)); 
        	int resolution = Integer.parseInt(aLine.get(4));
        	int brightness = Integer.parseInt(aLine.get(5));
        	int cpu_score = Integer.parseInt(aLine.get(7));
        	int ram = Integer.parseInt(aLine.get(8));
        	int ram_change = Integer.parseInt(aLine.get(10));
        	int gpu_type = Integer.parseInt(aLine.get(12));
        	int gpu_score = Integer.parseInt(aLine.get(14));
        	double gpu_high = Double.valueOf(aLine.get(15));
        	double gpu_ultra = Double.valueOf(aLine.get(16));
        	double gpu_qhd = Double.valueOf(aLine.get(17));
        	int price = Integer.parseInt(aLine.get(18));
        	int os = Integer.parseInt(aLine.get(19));
        	double total_score = Double.valueOf(aLine.get(20));
            savedList.add(new Item(
        	 			name, size, resolution, brightness, cpu_score, ram,
        	 			ram_change, gpu_type, gpu_score, gpu_high, gpu_ultra,
        	 			gpu_qhd, price, os, total_score		
            		));
        	}
         	if (br != null) { 
             br.close(); // ��� �� BufferedReader�� �ݾ��ش�.
         	}
         
         	return savedList;
	}
}

class startReco {
	// << Ŭ���� ���� >> // 
	private ArrayList<Item> gameList = null;
	private ArrayList<Item> codingList = null;
	private ArrayList<Item> graphicList = null;
	private ArrayList<Item> noSelectList = null;
	private ArrayList<Item> comparedList = null;
	
	// << Ŭ���� ������ >> // 
	startReco(){	
		this.gameList = new ArrayList<Item>();
		this.codingList = new ArrayList<Item>();
		this.graphicList = new ArrayList<Item>();
		this.noSelectList = new ArrayList<Item>();
		this.comparedList = new ArrayList<Item>();
	}
	
	// << ����Ʈ ���� �޼��� >> //
	public ArrayList<Item> createList() throws IOException{
		FiletoList create_list = new FiletoList();
		
		ArrayList<Item> returnList = new ArrayList<Item>();
		returnList = create_list.createList();
		return returnList;
	}
	
	// << ����-�ߺ� ���� �޼��� >> //
	public ArrayList<Item> deduplicationList(ArrayList<Item> targetList, ArrayList<Item> totalList){
		
		String targetName = "";
		String totalName = "";
		
		ArrayList<Item> taList = targetList;
		ArrayList<Item> toList = totalList;

		int lentotalList = 0;
		int len2totalList = 0;
		
		lentotalList = totalList.size();

		for (int i = 0; i < taList.size(); i++) {
			targetName = taList.get(i).getName();
			len2totalList = lentotalList;
			for (int j = 0; j < lentotalList; j++) {
				totalName = totalList.get(j).getName();
				if(targetName.equals(totalName) == false) {
					len2totalList = len2totalList - 1;
				}
			}
			if (len2totalList == 0) {
				totalList.add(taList.get(i));
			}
		}
		
		return toList;
	}
	
	// << ���� ���� �������� ���� �޼��� >> //
	public ArrayList<Item> filteredMinScore(ArrayList<Item> totalList, savedMinScore minScore){
		ArrayList<Item> filteredList = new ArrayList<Item>();
		double minCPU = minScore.getCpuScore();
		double minGPU = minScore.getGpuScore();
		
		minCPU = minCPU * 0.7;
		if (minGPU >= 5000) {
			minGPU = minGPU * 0.8;
		}else if(minGPU < 5000) {
			minGPU = minGPU;
		}
		double itemCPU;
		double itemGPU;
		for(int i = 0; i < totalList.size(); i++) {
			itemCPU = totalList.get(i).getCpu_score();
			itemGPU = totalList.get(i).getGpu_score();
			if ((itemCPU >= minCPU) && (itemGPU >= minGPU)) {
				filteredList.add(totalList.get(i));
			}
		}
		
		return filteredList;
	}
	
	// << ���� ���� ���� ��� �޼��� >> // 
	public double selectedMulCalRatio(double s_point, double i_point) {
	// < calRatio > : ���� ���� ��� ��� //
	
	// <���� ����>
	// s_point  	: ���� ���� 
	// i_point  	: ���õ� ������ ����
	// t        	: ���� ������ ���õ� �������� ��������
	// s        	: ���� ������ ���� �������� ����(%)
	// r        	: ���� ���� �ٰŷ� ������ �̿��� �������� ū����, �ּ��� ���� ���� �Ҵ�\
	// addScore 	: �߰� ������ 
	// biggerSide:	: �� ū ���� �Ǵ� 

	// <�Լ� ����>
	// ���� �ٷ� ������ ������ Ȯ���� ���
	// �������� �Ǵ� �������� �������� ���� Ŭ ��� 
	// ����� ������ �� ���� �ȴ�
	// ���� �� ���� ���� : 100 �������� ���� : 90�� ���
	// ���� = (90 / 100) * 100 = 90% ��� ���� ���� �� ������
	// ���� ���� : 100 �������� ���� 110�� ���
	// ���� = (110 / 100) * 100 = 110% ��� ����� ������ ������
	// ���� ���̸� ���ϰ�, �� ���̸� ���� ���������� ������ ����Ѵ�
	// �ٸ�, ���� ���̰� ���� �������� ū ��찡 ������ ��� 
	// ��ǥ���ϴ� �ּҰ� 0, �ִ밪 100���� �ִ밪 100�� �ʰ��ϱ� ������
	// �׷��� ��쿡�� ���� 0���� �Ҵ��Ѵ�
	// * ���õ� �������� ���� ���ذ����� ū ��쿡�� 10%��
	// * �߰����� �ο��Ͽ� ���� ������ ���� ��ǰ���� ���� ������ �ֵ��� �Ѵ�

	// < 0 ���� ���>  
	// 1: ������ ������ 0�� ��� 0��
	// 2: �� �������� ���̰� ���� �������� ū ���
			
		double t = 0;
		double s = 0;
		double r = 0;
		double addScore = 0;
		int biggerSide = 0;
		if (i_point == 0)
			return 0;
		else {
			if (s_point > i_point) {
				biggerSide = 0;
				t = s_point - i_point;
			}
			else if (s_point < i_point) {
				biggerSide = 1;
				t = i_point - s_point;
			}
			if (s_point < t)
				return 0;
			else
				s = t / s_point;
				
			r = Math.pow(((1 - s) * 10), 2);
			if (biggerSide == 1) {
				r = r + (r * 0.1);
			}
		}
		return r;
	}
	
	// << ������ ���ϴ� �޼��� >> //
	public ArrayList<Item> effectivenessCost(ArrayList<Item> c_list){
	// < effectivenessCost > : ������ ����� ���ѵ� ������ �����ϴ� �޼���   //
			
	// <���� ����>
	// surScore	: ������ ��� 
	// total	: �� ����  
	// price	: ���� ����
			
	// <�Լ� ����>
	// ������ ����� �����ְ�
	// ������ ������ ����� ���� ���������� ��������־� �����Ѵ�
	// ����Ʈ�� ��ȯ�Ѵ�
			
		double surScore = 0;
		double total = 0;
		double price = 0;
			
		for (int i = 0 ; i < c_list.size(); i++) {
			total = c_list.get(i).getTotal_score();
			price = c_list.get(i).getPrice();
			surScore = total / (total + (price/10000) + 50);
			c_list.get(i).setTotal_score(c_list.get(i).getTotal_score() * surScore);
		}
			
		return c_list;
	}
		
	// << ���� ���� ���� ���� �޼��� >> // 
	public ArrayList<Item> compareItem(ArrayList<Item> game, ArrayList<Item> coding, 
										ArrayList<Item> graphic, ArrayList<Item> none,
										int selectedFirst, savedMinScore minScore){
		
		ArrayList<Item> totalList = new ArrayList<Item>();
		Item tempItem = new Item();
		
		// int selected = selectedFirst;
		double i_CPU;
		double i_GPU;
		double s_CPU;
		double s_GPU;
		double newCPUScore;
		double newGPUScore;
		double newScore;
		
		if (game != null) {
			for (int i = 0; i < game.size(); i++) {
				totalList.add(game.get(i));
			}
		}
		if (coding != null) {
			totalList = deduplicationList(coding,totalList);
		}
		if (graphic != null) {
			totalList = deduplicationList(graphic,totalList);
		}
		
		if (none != null) {
			totalList = deduplicationList(none,totalList);
		}
		
		totalList = filteredMinScore(totalList, minScore);
		
		s_CPU = minScore.getCpuScore();
		s_GPU = minScore.getGpuScore();
		for (int i = 0; i < totalList.size(); i++) {
			totalList.get(i).setTotal_score(0);
			i_CPU = totalList.get(i).getCpu_score();
			i_GPU = totalList.get(i).getGpu_score();
			newCPUScore = 0.5 * selectedMulCalRatio(s_CPU, i_CPU);
			newGPUScore = 0.5 * selectedMulCalRatio(s_GPU, i_GPU);
			newScore = newCPUScore + newGPUScore;
			totalList.get(i).setTotal_score(newScore);
		}
		
		totalList = effectivenessCost(totalList);
		comparedList = totalList;
		return comparedList;
	}
	
	// << ��� �޼��� >> // 
	// << ��� �޼��� >> //
	public void printItem(ArrayList<Item> list) {
		
		Item temp = new Item();
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if(list.get(i).getTotal_score() < list.get(j).getTotal_score()) {
					temp = list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
				}
			}
		}
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getName() + " : �̸�");
			System.out.println(list.get(i).getSize() + " : ũ��");
			System.out.println(list.get(i).getResolution() + " : �ػ�");
			System.out.println(list.get(i).getBrightness() + " : ���");
			System.out.println(list.get(i).getCpu_score() + " : CPU");
			System.out.println(list.get(i).getGpu_score() + " : GPU");
			System.out.println(list.get(i).getTotal_score() + " : ��������");
			System.out.println("-----------------------------------------------");
		}
	}
	
	
	// << ���� ���� �޼��� >> //
	public void recoItemStart() throws IOException{
		Scanner scanner = new Scanner(System.in);
		savedMinScore minScore = new savedMinScore();
		
		int selectedGame = 0;
		int selectedCoding = 0;
		int selectedGraphic = 0;
		int selectedNone = 0;
		int selectedCount = 0;
		
		int selectedFirst = 0;
		
		while(true) {
			System.out.println("��� ������ �����ϼ���");
			System.out.println("1.���� 2.�ڵ� 3.����/������ 4.��Ÿ(����) 5.����");
			int selector = scanner.nextInt();
			
			if (selector == 1 && selectedGame == 0) {
				gameList = createList();
				recoGameItem game = new recoGameItem(gameList);
				selectedCount = selectedCount + 1;
				selectedGame = 1;
				gameList = game.selectedGame(gameList, minScore);
				gameList = game.askedCommonQuestion(gameList);
				gameList = game.effectivenessCost(gameList);
				gameList = game.sortTotalScore(gameList);
			}else if (selector == 2 && selectedCoding == 0) {
				codingList = createList();
				recoCodingItem coding = new recoCodingItem(codingList);
				selectedCount = selectedCount + 1;
				selectedCoding = 1;
				codingList = coding.selectedCoding(codingList, minScore);
				codingList = coding.askedCommonQuestion(codingList);
				codingList = coding.effectivenessCost(codingList);
				codingList = coding.sortTotalScore(codingList);
			}else if (selector == 3 && selectedGraphic == 0) {
				graphicList = createList();
				recoGraphicItem graphic = new recoGraphicItem(graphicList);
				selectedCount = selectedCount + 1;
				selectedGraphic = 1;
				graphicList = graphic.selectedGraphic(graphicList, minScore);
				graphicList = graphic.askedCommonQuestion(graphicList);
				graphicList = graphic.effectivenessCost(graphicList);
				graphicList = graphic.sortTotalScore(graphicList);
			}else if (selector == 4 && selectedNone == 0) {
				noSelectList = createList();
				recoItem none = new recoItem(noSelectList);
				selectedCount = selectedCount + 1;
				selectedNone = 1;
				noSelectList = none.askedCommonQuestion(noSelectList);	
			}else if (selector == 5){
				System.out.println("����");
				break;
			}else {
				System.out.println("Error");
			}	
		}
		
		
		if (selectedCount > 1) {
			System.out.println("���� �߿��� ������ �����Դϱ�? 1.���� 2.�ڵ� 3.������/����");
			int selector2 = scanner.nextInt();
			if(selector2 == 1) selectedFirst = 1;
			else if(selector2 == 2) selectedFirst = 2;
			else if(selector2 == 3) selectedFirst = 3;
			comparedList = compareItem(gameList, codingList, graphicList, noSelectList, selectedFirst, minScore);
		}else if (selectedCount == 1) {
			if(selectedGame == 1) comparedList = gameList;
			else if(selectedCoding == 1) comparedList = codingList;
			else if(selectedGraphic == 1) comparedList = graphicList;
			else if(selectedNone == 1) comparedList = noSelectList;
		}else if (selectedCount == 0) {
			System.out.println("���õ� ������ ����");
		}
		printItem(comparedList);
	}
}

public class recoalgo{
	public static void main(String[] args) throws IOException {

		startReco st = new startReco();
		st.recoItemStart();
	}
}

*/