package algo;

import java.util.ArrayList;
import java.util.Scanner;

//<< 정렬, 추천 등 작업이 이루어지는 하위 클래스로 그래픽을 선택했을 경우의 클래스 >> //
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
		
		// c_list = sortGPU(c_list);
		
		for (int i = 0; i < c_list.size(); i++) {
			s_item = 21036 * r;
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
		
		// c_list = sortCPU(c_list);
		
		for (int i = 0; i < c_list.size(); i++) {
			s_item = 35000 * r;
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
			selected_ratio = 0.35;
			c_list = calGpuscoreToTotal(c_list, selected_ratio);
		}else if (selector3 == 2) {
			selected_ratio = 0.1;
			c_list = calGpuscoreToTotal(c_list, selected_ratio);
		}
		
		setGPUScore = c_list.get(0).getGpu_score() * selected_ratio;
		minScore.compareGpuScore(setGPUScore);
		
		System.out.println("작업의 속도, 효율성을 얼마나 중요하게 생각하시나요? 1:매우중요 2:중요 3:보통 4:중요하지 않음");
		int selector4 = scanner.nextInt();
		selected_ratio = 0;
		if (selector4 == 1) {
			selected_ratio = 0.7;
			c_list = calCpuscoreToTotal(c_list, selected_ratio);
		}else if(selector4 == 2) {
			selected_ratio = 0.55;
			c_list = calCpuscoreToTotal(c_list, selected_ratio);
		}else if(selector4 == 3) {
			selected_ratio = 0.45;
			c_list = calCpuscoreToTotal(c_list, selected_ratio);
		}else if(selector4 == 4) {
			selected_ratio = 0.45;
			c_list = calCpuscoreToTotal(c_list, selected_ratio);
		}
		
		setCPUScore = c_list.get(0).getCpu_score() * selected_ratio;
		minScore.compareCpuScore(setCPUScore);
		
		return c_list;
	}
}
