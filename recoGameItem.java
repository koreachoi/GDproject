package algo;

import java.util.ArrayList;
import java.util.Scanner;

//<< 정렬, 추천 등 작업이 이루어지는 하위 클래스로 게임을 선택했을 경우의 클래스 >> //
class recoGameItem extends recoItem{

	recoGameItem(ArrayList<Item> c_list) {
		super(c_list);
	}
	
	// << 애플 필터링 메서드 >> // 
	public ArrayList<Item> filteredAppleProduct(ArrayList<Item> c_list){
		ArrayList<Item> r_list = new ArrayList<Item>();
		for (int i = 0; i < c_list.size(); i++) {
			if (c_list.get(i).getBrand().equals("APPLE")) {
				continue;
			}else {
				r_list.add(c_list.get(i));
			}
		}
		
		return r_list;
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
		// c_list = sortGPU(c_list);
		for (int i = 0; i < c_list.size(); i++) {
			if (s == 3) {
				s_item = 21036 * r;
				i_item = c_list.get(i).getGpu_score();
				cal_point = c_list.get(i).calRatio(s_item, i_item);
				c_list.get(i).setTotal_score(cal_point);
			}else {
				if (c_list.get(i).getGpu_type() == 1) {
					s_item = 21036 * r;
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
		// c_list = sortCPU(c_list);
		for (int i = 0; i < c_list.size(); i++) {
			if (c_list.get(i).getGpu_type() == 1) {
				s_item = 35000 * r;
				i_item = c_list.get(i).getCpu_score();
				cal_point =  0.5 * c_list.get(i).calRatio(s_item, i_item);
				c_list.get(i).setTotal_score(c_list.get(i).getTotal_score()+ cal_point);
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
				selected_ratio = 0.35;
				selected_option = 1;
				checked_gpu_type = 1;
				c_list = calGpuscoreToTotal(c_list, selected_ratio, selected_option);
				selected_ratio = 0.7;
				c_list = calCpuscoreToTotal(c_list, selected_ratio);
				setCPUScore = c_list.get(0).getCpu_score() * selected_ratio; 
				minScore.compareCpuScore(setCPUScore);
			}else if(selector2 == 2) {
				selected_ratio = 0.15;
				selected_option = 2;
				checked_gpu_type = 1;
				c_list = calGpuscoreToTotal(c_list, selected_ratio, selected_option);
			}else if(selector2 == 3) {
				selected_ratio = 0.05;
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
		
		c_list = filteredAppleProduct(c_list);
		return c_list;
	}
}