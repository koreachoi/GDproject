package algo;

import java.util.ArrayList;
import java.util.Scanner;

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
			
			// c_list = sortGPU(c_list);
			
			if (s == 1) {
				for (int i = 0; i < c_list.size(); i++) {
					if (c_list.get(i).getGpu_type() == 1) {
						s_item = 21036 * r;
						i_item = c_list.get(i).getGpu_score();
						cal_point = 0.3 * c_list.get(i).calRatio(s_item, i_item);
						c_list.get(i).setTotal_score(cal_point);
					}
				}
			}else if(s == 2) {
				for (int i = 0; i < c_list.size(); i++) {
					s_item = 21036 * r;
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
			
			// c_list = sortCPU(c_list);
			
			for (int i = 0; i < c_list.size(); i++) {
				s_item = 35000 * r;
				i_item = c_list.get(i).getCpu_score();
				cal_point = 0.7 * c_list.get(i).calRatio(s_item, i_item);
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
			selected_ratio = 0.35;
			selected_option = 1;
			System.out.println("어떤 작업을 사용하시나요? 1:딥러닝 2:머신러닝 3:고사양 그래픽 게임개발 4:기타(3D 작업)");
			int selector1_1 = scanner.nextInt();
			c_list = calGpuscoreToTotal(c_list, selected_ratio, selected_option);
		}else if(selector == 2) {
			selected_ratio = 0.05;
			selected_option = 2;
			c_list = calGpuscoreToTotal(c_list, selected_ratio, selected_option);
		}
		setGPUScore = c_list.get(0).getGpu_score() * selected_ratio;
		minScore.compareGpuScore(setGPUScore);
		
		System.out.println("현재 혹은 향후 고사양을 요구하는 프로그램등을 사용하시나요? 1:O 2:X");
		int selector2 = scanner.nextInt();
		if (selector2 == 1) {
			selected_ratio = 0.55;
			c_list = calCpuscoreToTotal(c_list, selected_ratio);
		}else if (selector2 == 2) {
			selected_ratio = 0.4;
			c_list = calCpuscoreToTotal(c_list, selected_ratio);
		}
		setCPUScore = c_list.get(0).getCpu_score() * selected_ratio;
		minScore.compareCpuScore(setCPUScore);
		
		return c_list;
	}
}
