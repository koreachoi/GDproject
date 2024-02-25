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

// << 아이템을 저장하고 총점을 구하는 클래스 >> //
class Item {
	// << 클래스 변수 >> // 
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
	
	// << 클래스 생성자 >> // 
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
	
	// << 클래스 생성자 >> // 
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
	
	// << 점수 계산 메서드 >> // 
	public double calRatio(double s_point, double i_point) {
	// < calRatio > : 점수 비율 계산 방법 //

	// <변수 설정>
	// s_point  : 기준 점수 
	// i_point  : 선택된 아이템 점수
	// t        : 기준 점수와 선택된 아이템의 점수차이
	// s        : 기준 점수에 대한 점수차이 비율(%)
	// r        : 계산된 값을 근거로 제곱을 이용해 가까울수록 큰값을, 멀수록 작은 값을 할당

	// <함수 설명>
	// 값을 바로 나누어 비율을 확인할 경우
	// 기준점이 되는 변수보다 아이템의 값이 클 경우 
	// 제대로 측정할 수 없게 된다
	// 예를 들어서 기준 점수 : 100 아이템의 점수 : 90의 경우
	// 비율 = (90 / 100) * 100 = 90% 라는 값을 얻을 수 있지만
	// 기준 점수 : 100 아이템의 점수 110의 경우
	// 비율 = (110 / 100) * 100 = 110% 라는 결과가 나오기 때문에
	// 값의 차이를 구하고, 그 차이를 기준 점수에서의 비율로 계산한다
	// 다만, 값의 차이가 기준 점수보다 큰 경우가 존재할 경우 
	// 목표로하는 최소값 0, 최대값 100에서 최대값 100을 초과하기 때문에
	// 그러한 경우에는 값을 0으로 할당한다

	// < 0 점인 경우>  
	// 1: 아이템 점수가 0인 경우 0점
	// 2: 두 점수간의 차이가 기준 점수보다 큰 경우
		
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
	
	// << 각 변수의 getter setter 메서드  >> //
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

//<< 중복시 최저 스펙을 담는 클래스  >> //
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
// << 정렬, 추천 등 작업이 이루어지는 상위 클래스 >> //
class recoItem {
	// << 클래스 리스트 변수 >> // 
	private ArrayList<Item> c_list = new ArrayList<Item>();
	
	// << 클래스 생성자 >> // 
	recoItem(ArrayList<Item> c_list){
		this.c_list = c_list;
	}
	
	// << CPU를 정렬하는 클래스 메서드 >> // 
	public ArrayList<Item> sortCPU(ArrayList<Item> c_list){
	// <CPU SORT> : 상품 정렬 
	// <함수 설명>
	// 함수를 가장 기본적인 정렬 알고리즘을을 사용하여
	// 내림차순으로 정렬한다
	// 정렬 알고리즘은 차후 더 효율적인 알고리즘으로 수정 가능
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
	
	// << GPU를 정렬하는 클래스 메서드 >> // 
	public ArrayList<Item> sortGPU(ArrayList<Item> c_list){
	// <GPU SORT> : 상품 정렬 
	// <함수 설명>
	// 함수를 가장 기본적인 정렬 알고리즘을을 사용하여
	// 내림차순으로 정렬한다
	// 정렬 알고리즘은 차후 더 효율적인 알고리즘으로 수정 가능
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
	
	// << TotalScore로 정렬하는 클래스 메서드 //
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
	
	// << GPU 점수를 total 점수로 환산하는 메서드 >> // 
	public ArrayList<Item> calGpuscoreToTotal(){ return c_list;}
	
	// << CPU 점수를 total 점수로 환산하는 메서드 >> // 
	public ArrayList<Item> calCpuscoreToTotal(){ return c_list;}
	
	// << 밝기 점수를 total 점수로 환산하는 메서드 >> // 
	public ArrayList<Item> calBrightnessscoreToTotal(){ return c_list;}
	
	// << 해상도 조건에 맞는 아이템을 필터링하는 메서드  >> //
	public ArrayList<Item> filteredResolution(){ return c_list;}
	
	// << 공통질문 : 사이즈를 물어보고 필터하는 메서드 >> // 
	public ArrayList<Item> filteredSizeItem(ArrayList<Item> c_list, double minSize){
	// < filteredSizeItem > : 사이즈 필터링 메서드  //

	// <변수 설정>
	// mins  				: 크기의 최저값 
	// maxs  				: 크기의 최대값 
	// filteredSizeList    	: 필터링된 리스트를 담는 리스트 변수 


	// <함수 설명>
	// 최저값을 받는 변수를 받은 뒤 크기에 따라 최대값을 정해준다
	// 17사이즈의 경우 최대값을 20으로 해주고
	// 그 외의 사이즈는 최저값에서 1을 더한 값으로 해준다
	// 14 미만의 값에서는 최대값을 14로 설정해준다
	// 그 후 각 사이즈에 맞는 값만 새 리스트에 추가하고
	// 반환해준다
		
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
	
	// << 공통질문 : OS를 물어보고 필터하는 메서드 >> //
	public ArrayList<Item> filteredOsItem(ArrayList<Item> c_list, int os){
	// < filteredOsItem > : CPU점수를 계산 후 total 점수로 적용하는 메서드  //
		
	// <변수 설정>
	// o 				: OS타입
	// filteredOsList	: 필터된 리스트 
	
	// <함수 설명>
	// WINDOW의 경우 1
	// MAC의 경우 0
	// 반복문을 돌며 해당하는 OS를 새 리스트에 추가해준다
		
		ArrayList<Item> filteredOsList = new ArrayList<Item>();
		int o = os;
	
			for (int i = 0; i < c_list.size(); i++) {
				if(c_list.get(i).getOS() == o) {
					filteredOsList.add(c_list.get(i));
			}
		}
		return filteredOsList;
	}
	
	// << 가성비를 구하는 메서드 >> //
	public ArrayList<Item> effectivenessCost(ArrayList<Item> c_list){
	// < effectivenessCost > : 가성비 계수를 구한뒤 점수에 적용하는 메서드   //
		
	// <변수 설정>
	// surScore	: 가성비 계수 
	// total	: 총 점수  
	// price	: 가격 점수
		
	// <함수 설명>
	// 가성비 계수를 구해주고
	// 구해진 가성비 계수를 현재 총합점수에 곱계산해주어 적용한다
	// 리스트를 반환한다
		
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
	
	// << 공통 질문 >> // 
	public ArrayList<Item> askedCommonQuestion(ArrayList<Item> c_list){
	// < effectivenessCost > : 가성비 계수를 구한뒤 점수에 적용하는 메서드   //
		
	// <변수 설정>
	// selector		: 화면 크기를 구하는 변수 
	// selector2	: OS 종류를 결정하는 변수 
	// OS			: OS 타입의 값을 저장하는 변수 
			
	// <함수 설명>
	// 사이즈와 OS를 구한다
		
		System.out.println("화면크기 선택 1:17 이상 2:16 이상 3:15 이상 4:14 이상 5:14 미만");
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
		
		System.out.println("운영체제 선택 1:WINDOW 2:MAC");
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

//<< 정렬, 추천 등 작업이 이루어지는 하위 클래스로 게임을 선택했을 경우의 클래스 >> //
class recoGameItem extends recoItem{

	recoGameItem(ArrayList<Item> c_list) {
		super(c_list);
	}

	// << GPU 점수를 total 점수로 환산하는 메서드 >> // 
	public ArrayList<Item> calGpuscoreToTotal(ArrayList<Item> c_list, double ratio, int selected){
	// < calGpuscoreToTotal > : GPU점수를 계산 후 total 점수로 적용하는 메서드  //

	// <변수 설정>
	// s_item  		: 기준이 되는 아이템의 점수 * 비율
	// i_item  		: 선택된 아이템의 점수 
	// cal_point    : 메서드로 계산된 값을 담는 변수 
	// r        	: 기준 점수에 대한 비율(%)
	// s        	: 선택된 선택지를 구분하기 위한 변수

	// <함수 설명>
	// 리스트의 길이만큼 반복하는데 MAC OS의 경우를 제외한다
	// 만약 선택된 값이 저사양(3)의 경우 내장그래픽을 포함하고
	// 그 외의 경우에는 내장그래픽을 제외하고 계산한다
	// 다만 고사양(1)의 경우 CPU의 성능또한 확인하기 때문에
	// 점수의 비율을 5할로 제한하고, 중간 사양의 경우 전부 포함한다

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
	
	// << CPU 점수를 total 점수로 환산하는 메서드 >> // 
	public ArrayList<Item> calCpuscoreToTotal(ArrayList<Item> c_list, double ratio){
	// < calCpuscoreToTotal > : CPU점수를 계산 후 total 점수로 적용하는 메서드  //

	// <변수 설정>
	// s_item  		: 기준이 되는 아이템의 점수 * 비율
	// i_item  		: 선택된 아이템의 점수 
	// cal_point    : 메서드로 계산된 값을 담는 변수 
	// r        	: 기준 점수에 대한 비율(%)

	// <함수 설명>
	// 리스트의 길이만큼 반복하는데 MAC OS의 경우를 제외한다
	// CPU를 계산하는 경우는 고사양의 경우이므로
	// 더해진 값을 전체의 5할정도로 더해준다
		
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
	
	// << 게임이 선택되었을때의 클래스 메서드 >> // 
	public ArrayList<Item> selectedGame(ArrayList<Item> c_list, savedMinScore minScore){
	// < selectedGame > : 게임이 선택되었을때의 메서드  //

	// <변수 설정>
	// selected_ratio 		: 선택된 사양에 따른 비율을 결정하는 변수 
	// selected_option  	: 선택지가 어떤 것인지 체크하는 변수 
	// checked_gpu_type    	: 선택된 GPU타입을 체크하는 변수 
	// selector				: 선택지 1의 변수로 사양을 알고 있는지 확인하기 위한 변수  
	// selector2			: 선택지 2의 변수로 사양을 물어보기 위한 변수 
	// selector3			: 선택지 3의 변수로 여유로운 환경을 확인하기 위한 변수 
	// setGPUScore			: GPU 최저값을 저장하기 위한 변수 
	// setCPUScore			: CPU 최저값을 계산하기 위한 변수 
		
	// <함수 설명>
	// 첫 번째 질문으로 자신이 하는 게임의 사양을 알고 있는지 질문을 받는다
	// 알고 있는 경우 사양을 물어본뒤 해당 사양에 맞는 점수를 측정한다
	// 고사양과 중간사양의 경우 내장그래픽은 포함하지 않으며
	// 고사양은 CPU점수까지 고려한다
	// 점수측정이 끝나면, 원하는 환경에 대한 질문을 하고
	// 질문에 대한 답에 따라 추가적인 가중치를 주거나, 스킵하고
	// 리스트를 리턴하는것으로 끝난다

		Scanner scanner = new Scanner(System.in);
		double selected_ratio = 0;
		int selected_option = 0;
		int checked_gpu_type = 0;
		double setGPUScore = 0;
		double setCPUScore = 0;
		
		System.out.println("본인이 주로 하는 게임의 권장사양을 어느정도 파악하고 계신가요? 1:O 2:X");
		int selector = scanner.nextInt();
		if (selector == 1) {
			System.out.println("어느정도의 사양이 요구되는 게임을 플레이하고 계신가요? 1:고사양 2:중간사양 3:저사양");
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
			System.out.println("게임 종류 보여주기");
		}
		
		System.out.println("게임을 플레이할 시 프레임을 중요시 여기시나요? 1:중요하다 2:보통이다 3:큰상관없다");
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

//<< 정렬, 추천 등 작업이 이루어지는 하위 클래스로 코딩을 선택했을 경우의 클래스 >> //
class recoCodingItem extends recoItem{

	recoCodingItem(ArrayList<Item> c_list) {
		super(c_list);
		// TODO Auto-generated constructor stub
	}

	// << GPU 점수를 total 점수로 환산하는 메서드 >> // 
	public ArrayList<Item> calGpuscoreToTotal(ArrayList<Item> c_list, double ratio, int selected){ 
	// < calGpuscoreToTotal > : GPU점수를 계산 후 total 점수로 적용하는 메서드  //

	// <변수 설정>
	// s_item  		: 기준이 되는 아이템의 점수 * 비율
	// i_item  		: 선택된 아이템의 점수 
	// cal_point    : 메서드로 계산된 값을 담는 변수 
	// r        	: 기준 점수에 대한 비율(%)
	// s 			: 선택된 선택지를 구분하기 위한 변수
		
	// <함수 설명>
	// 만약 GPU자원을 사용하는 선택지를 고를경우 내장그래픽은 제외한다
	// OS에 상관없이 코드를 동작한다 
		
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
	
	// << CPU 점수를 total 점수로 환산하는 메서드 >> // 
	public ArrayList<Item> calCpuscoreToTotal(ArrayList<Item> c_list, double ratio){
	// < calCpuscoreToTotal > : CPU점수를 계산 후 total 점수로 적용하는 메서드  //

	// <변수 설정>
	// s_item  		: 기준이 되는 아이템의 점수 * 비율
	// i_item  		: 선택된 아이템의 점수 
	// cal_point    : 메서드로 계산된 값을 담는 변수 
	// r        	: 기준 점수에 대한 비율(%)

	// <함수 설명>
	// 모든 아이템을 돌면서 CPU점수를 측정한뒤 기존 값에 더해준다
		
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
	
	// << 코딩이 선택되었을때의 클래스 메서드 >> // 
	public ArrayList<Item> selectedCoding(ArrayList<Item> c_list, savedMinScore minScore){
	// < selectedCoding > : 코딩이 선택되었을때의 메서드  //

	// <변수 설정>
	// selected_ratio 		: 선택된 사양에 따른 비율을 결정하는 변수 
	// selected_option  	: 선택지가 어떤 것인지 체크하는 변수 
	// selector				: 선택지 1의 변수로 GPU자원을 체크하는 변수 
	// selector2			: 선택지 2의 변수로 CPU자원의 사양을 체크하는 변수
	// setGPUScore			: GPU 최저값을 저장하기 위한 변수 
	// setCPUScore			: CPU 최저값을 계산하기 위한 변수 


	// <함수 설명>
	// 첫 번째 질문으로 GPU 자원을 사용하는지 물어본다 
	// 두 번째 질문으로 고사양 프로그램을 사용하는지 물어본다 
	// 리스트를 리턴하는것으로 끝난다
		
		Scanner scanner = new Scanner(System.in);
		double selected_ratio = 0;
		int selected_option = 0;
		double setGPUScore = 0;
		double setCPUScore = 0;
		
		System.out.println("머신러닝, 딥러닝, 게임개발등 GPU 자원을 사용하는 프로그램등을 사용하시나요? 1:O 2:X");
		int selector = scanner.nextInt();
		if (selector == 1) {
			selected_ratio = 0.7;
			selected_option = 1;
			System.out.println("어떤 작업을 사용하시나요? 1:딥러닝 2:머신러닝 3:고사양 그래픽 게임개발 4:기타(3D 작업)");
			int selector1_1 = scanner.nextInt();
			c_list = calGpuscoreToTotal(c_list, selected_ratio, selected_option);
		}else if(selector == 2) {
			selected_ratio = 0.2;
			selected_option = 2;
			c_list = calGpuscoreToTotal(c_list, selected_ratio, selected_option);
		}
		setGPUScore = c_list.get(0).getGpu_score() * selected_ratio;
		minScore.compareGpuScore(setGPUScore);
		
		System.out.println("현재 혹은 향후 고사양을 요구하는 프로그램등을 사용하시나요? 1:O 2:X");
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

// << 정렬, 추천 등 작업이 이루어지는 하위 클래스로 그래픽을 선택했을 경우의 클래스 >> //
class recoGraphicItem extends recoItem{

	recoGraphicItem(ArrayList<Item> c_list) {
		super(c_list);
		// TODO Auto-generated constructor stub
	}
	
	// << 밝기 점수를 total 점수로 환산하는 메서드 >> // 
	public ArrayList<Item> calBrightnessscoreToTotal(ArrayList<Item> c_list, int score){ 
	// < calCpuscoreToTotal > : CPU점수를 계산 후 total 점수로 적용하는 메서드  //

	// <변수 설정>
	// s_item  		: 기준이 되는 아이템의 점수 
	// i_item  		: 선택된 아이템의 점수 
	// cal_point    : 메서드로 계산된 값을 담는 변수 
	// s			: 밝기 기준의 점수값

	// <함수 설명>
	// 모든 아이템을 돌면서 밝기 점수를 측정한뒤 기존 값에 더해준다
			
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
	
	// << 해상도 조건에 맞는 아이템을 필터링하는 메서드  >> //
	public ArrayList<Item> filteredResolution(ArrayList<Item> c_list, int selectedResolution){
	// < filteredResolution > : 해상도에 따라서 조건에 맞는 값들만 추려내는 메서드   //

	// <변수 설정>
	// filteredList		: 필터된 값을 담는 새 리스트
	// s				: 해상도선택지 

	// <함수 설명>
	// 해상도 선택지에 따라서 각 조건에 맞는 해상도의 아이템을
	// 새로운 리스트에 추가해준다
		
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
	
	// << GPU 점수를 total 점수로 환산하는 메서드 >> //
	public ArrayList<Item> calGpuscoreToTotal(ArrayList<Item> c_list, double ratio){
	// < calGpuscoreToTotal > : GPU점수를 계산 후 total 점수로 적용하는 메서드  //

	// <변수 설정>
	// s_item  		: 기준이 되는 아이템의 점수 * 비율
	// i_item  		: 선택된 아이템의 점수 
	// cal_point    : 메서드로 계산된 값을 담는 변수 
	// r        	: 기준 점수에 대한 비율(%)

	// <함수 설명>
	// 모든 아이템을 돌면서 GPU점수를 측정한뒤 기존 값에 더해준다
		
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
	
	// << CPU 점수를 total 점수로 환산하는 메서드 >> //
	public ArrayList<Item> calCpuscoreToTotal(ArrayList<Item> c_list, double ratio){
	// < calCpuscoreToTotal > : CPU점수를 계산 후 total 점수로 적용하는 메서드  //

	// <변수 설정>
	// s_item  		: 기준이 되는 아이템의 점수 * 비율
	// i_item  		: 선택된 아이템의 점수 
	// cal_point    : 메서드로 계산된 값을 담는 변수 
	// r        	: 기준 점수에 대한 비율(%)

	// <함수 설명>
	// 모든 아이템을 돌면서 CPU점수를 측정한뒤 기존 값에 더해준다
		
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
	
	// << 그래픽 선택되었을때의 클래스 메서드 >> // 
	public ArrayList<Item> selectedGraphic(ArrayList<Item> c_list, savedMinScore minScore){
	// < selectedCoding > 	: 그래픽이 선택되었을때의 메서드  //

	// <변수 설정>
	// selected_ratio 		: 선택된 사양에 따른 비율을 결정하는 변수 
	// selectedResolution  	: 선택된 해상도를 체크하는 변수  
	// selector				: 밝기의 답변을 받는 변수 
	// selector2			: 해상도의 답변을 받는 변수 
	// selector3			: GPU자원의 사용 여부를 받는 변수 
	// selector4			: CPU성능의 답변을 받는 변수 
	// setGPUScore			: GPU 최저값을 저장하기 위한 변수 
	// setCPUScore			: CPU 최저값을 계산하기 위한 변수 


	// <함수 설명>
	// 첫 번째 질문으로 밝기에 대한 답을 받고 점수를 추가한다 
	// 두 번째 질문으로 해상도의 조건을 받고 필터링함수를 호출한다  
	// 세 번째 질문으로 GPU 사용 여부를 물어보고 점수를 추가한다
	// 네 번째 질문으로 원하는 CPU 성능을 물어보고 점수를 추가한뒤 종료한다 
		
		Scanner scanner = new Scanner(System.in);
		int selectedResolution = 0;
		double selected_ratio = 0;
		double setGPUScore = 0;
		double setCPUScore = 0;
		
		System.out.println("노트북을 이용할 때 주로 어디에서 이용하나요? 1:야외(가장 밝기) 2:밝은 실내(중간 정도의 밝기) 3:실내(낮은 밝기)");
		int selector = scanner.nextInt();
		if (selector == 1) {
			c_list = calBrightnessscoreToTotal(c_list, 700);
		}else if (selector == 2) {
			c_list = calBrightnessscoreToTotal(c_list, 450);
		}else if (selector == 3) {
			c_list = calBrightnessscoreToTotal(c_list, 200);
		}
		
		System.out.println("화질의 선명함이 중요하다고 생각하나요? 1:O(QHD이상) 2:X(FHD)");
		int selector2 = scanner.nextInt();
		if (selector2 == 1) {
			selectedResolution = 1;
			c_list = filteredResolution(c_list, selectedResolution);
		}else if (selector2 == 2) {
			selectedResolution = 0;
			c_list = filteredResolution(c_list, selectedResolution);	
		}
		
		System.out.println("렌더링 작업을 하시나요? 1:O 2:X");
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
		
		System.out.println("작업의 속도, 효율성을 얼마나 중요하게 생각하시나요? 1:매우중요 2:중요 3:보통 4:중요하지 않음");
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
        File csv = new File("C:\\JAVA\\algo\\src\\file\\note_final.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csv), "utf-8"));
        String line = "";
         
        while ((line = br.readLine()) != null) { // readLine()은 파일에서 개행된 한 줄의 데이터를 읽어온다.
            List<String> aLine = new ArrayList<String>();
            String[] lineArr = line.split(","); // 파일의 한 줄을 ,로 나누어 배열에 저장 후 리스트로 변환한다.
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
             br.close(); // 사용 후 BufferedReader를 닫아준다.
         	}
         
         	return savedList;
	}
}

class startReco {
	// << 클래스 변수 >> // 
	private ArrayList<Item> gameList = null;
	private ArrayList<Item> codingList = null;
	private ArrayList<Item> graphicList = null;
	private ArrayList<Item> noSelectList = null;
	private ArrayList<Item> comparedList = null;
	
	// << 클래스 생성자 >> // 
	startReco(){	
		this.gameList = new ArrayList<Item>();
		this.codingList = new ArrayList<Item>();
		this.graphicList = new ArrayList<Item>();
		this.noSelectList = new ArrayList<Item>();
		this.comparedList = new ArrayList<Item>();
	}
	
	// << 리스트 생성 메서드 >> //
	public ArrayList<Item> createList() throws IOException{
		FiletoList create_list = new FiletoList();
		
		ArrayList<Item> returnList = new ArrayList<Item>();
		returnList = create_list.createList();
		return returnList;
	}
	
	// << 고사양-중복 제거 메서드 >> //
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
	
	// << 다중 선택 최저스펙 필터 메서드 >> //
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
	
	// << 다중 선택 점수 계산 메서드 >> // 
	public double selectedMulCalRatio(double s_point, double i_point) {
	// < calRatio > : 점수 비율 계산 방법 //
	
	// <변수 설정>
	// s_point  	: 기준 점수 
	// i_point  	: 선택된 아이템 점수
	// t        	: 기준 점수와 선택된 아이템의 점수차이
	// s        	: 기준 점수에 대한 점수차이 비율(%)
	// r        	: 계산된 값을 근거로 제곱을 이용해 가까울수록 큰값을, 멀수록 작은 값을 할당\
	// addScore 	: 추가 가산점 
	// biggerSide:	: 더 큰 쪽을 판단 

	// <함수 설명>
	// 값을 바로 나누어 비율을 확인할 경우
	// 기준점이 되는 변수보다 아이템의 값이 클 경우 
	// 제대로 측정할 수 없게 된다
	// 예를 들어서 기준 점수 : 100 아이템의 점수 : 90의 경우
	// 비율 = (90 / 100) * 100 = 90% 라는 값을 얻을 수 있지만
	// 기준 점수 : 100 아이템의 점수 110의 경우
	// 비율 = (110 / 100) * 100 = 110% 라는 결과가 나오기 때문에
	// 값의 차이를 구하고, 그 차이를 기준 점수에서의 비율로 계산한다
	// 다만, 값의 차이가 기준 점수보다 큰 경우가 존재할 경우 
	// 목표로하는 최소값 0, 최대값 100에서 최대값 100을 초과하기 때문에
	// 그러한 경우에는 값을 0으로 할당한다
	// * 선택된 아이템의 값이 기준값보다 큰 경우에는 10%의
	// * 추가점을 부여하여 같은 차이의 낮은 제품보다 높은 점수를 주도록 한다

	// < 0 점인 경우>  
	// 1: 아이템 점수가 0인 경우 0점
	// 2: 두 점수간의 차이가 기준 점수보다 큰 경우
			
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
	
	// << 가성비를 구하는 메서드 >> //
	public ArrayList<Item> effectivenessCost(ArrayList<Item> c_list){
	// < effectivenessCost > : 가성비 계수를 구한뒤 점수에 적용하는 메서드   //
			
	// <변수 설정>
	// surScore	: 가성비 계수 
	// total	: 총 점수  
	// price	: 가격 점수
			
	// <함수 설명>
	// 가성비 계수를 구해주고
	// 구해진 가성비 계수를 현재 총합점수에 곱계산해주어 적용한다
	// 리스트를 반환한다
			
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
		
	// << 다중 선택 고사양 선택 메서드 >> // 
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
	
	// << 출력 메서드 >> // 
	// << 출력 메서드 >> //
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
			System.out.println(list.get(i).getName() + " : 이름");
			System.out.println(list.get(i).getSize() + " : 크기");
			System.out.println(list.get(i).getResolution() + " : 해상도");
			System.out.println(list.get(i).getBrightness() + " : 밝기");
			System.out.println(list.get(i).getCpu_score() + " : CPU");
			System.out.println(list.get(i).getGpu_score() + " : GPU");
			System.out.println(list.get(i).getTotal_score() + " : 총합점수");
			System.out.println("-----------------------------------------------");
		}
	}
	
	
	// << 설문 시작 메서드 >> //
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
			System.out.println("사용 목적을 선택하세요");
			System.out.println("1.게임 2.코딩 3.영상/디자인 4.기타(없음) 5.종료");
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
				System.out.println("종료");
				break;
			}else {
				System.out.println("Error");
			}	
		}
		
		
		if (selectedCount > 1) {
			System.out.println("가장 중요한 목적은 무엇입니까? 1.게임 2.코딩 3.디자인/영상");
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
			System.out.println("선택된 아이템 없음");
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