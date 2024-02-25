package algo;

import java.util.ArrayList;
import java.util.Scanner;

//<< 정렬, 추천 등 작업이 이루어지는 상위 클래스 >> //
public class recoItem {
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
	public ArrayList<Item> sortGPU(ArrayList<Item> c_list) {
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
	public ArrayList<Item> sortTotalScore(ArrayList<Item> c_list) {
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
	public ArrayList<Item> calGpuscoreToTotal() {
		return c_list;
	}

	// << CPU 점수를 total 점수로 환산하는 메서드 >> //
	public ArrayList<Item> calCpuscoreToTotal() {
		return c_list;
	}

	// << 밝기 점수를 total 점수로 환산하는 메서드 >> //
	public ArrayList<Item> calBrightnessscoreToTotal() {
		return c_list;
	}

	// << 해상도 조건에 맞는 아이템을 필터링하는 메서드 >> //
	public ArrayList<Item> filteredResolution() {
		return c_list;
	}

	// << 가성비를 구하는 메서드 >> //
	public ArrayList<Item> effectivenessCost(ArrayList<Item> c_list) {
		// < effectivenessCost > : 가성비 계수를 구한뒤 점수에 적용하는 메서드 //

		// <변수 설정>
		// surScore : 가성비 계수
		// total : 총 점수
		// price : 가격 점수

		// <함수 설명>
		// 가성비 계수를 구해주고
		// 구해진 가성비 계수를 현재 총합점수에 곱계산해주어 적용한다
		// 리스트를 반환한다

		double surScore = 0;
		double total = 0;
		double cscore = 0;
		double gscore = 0;
		double price = 0;
			
		for (int i = 0 ; i < c_list.size(); i++) {
			cscore = c_list.get(i).getCpu_score();
			gscore = c_list.get(i).getGpu_score();
			total = c_list.get(i).getTotal_score();
			price = c_list.get(i).getPrice();
			if (gscore >= 4000){
				surScore = 	(1 + (cscore / 30000)) * 
						(1 + (gscore / 10000)) *
						(2 - (price / 6000000)) *
						Math.sqrt(total + 10);
			}else if(gscore < 4000) {
				surScore = 	(1 + (cscore / 30000)) * 
						(1 + Math.exp(-(gscore-1000)*(gscore-1000)/30000000)) *
						(Math.pow((4000000/price), 2) / 100) *
						Math.sqrt(total + 10);
			}
			c_list.get(i).setTotal_score(surScore);
		}
		return c_list;
	}

	// << 문서 웹 영상 시청용 점수 측정 메서드 >> //
	public ArrayList<Item> noneSelectedScore(ArrayList<Item> c_list){
		
		double s_item = 0;
		double i_item = 0;
		double cal_point = 0;
		// c_list = sortCPU(c_list);
		
		for (int i = 0; i < c_list.size(); i++) {
			s_item = 35000 * 0.45;
			i_item = c_list.get(i).getCpu_score();
			cal_point = 0.7 * c_list.get(i).calRatio(s_item, i_item);
			c_list.get(i).setTotal_score(c_list.get(i).getTotal_score()+ cal_point);
		}
		
		// c_list = sortGPU(c_list);
		for (int i = 0; i < c_list.size(); i++) {
			s_item = 21036 * 0.05;
			i_item = c_list.get(i).getGpu_score();
			cal_point = 0.3 * c_list.get(i).calRatio(s_item, i_item);
			c_list.get(i).setTotal_score(c_list.get(i).getTotal_score()+ cal_point);
		}
		return c_list;
	}
	
	// << 공통 질문 >> //
	public ArrayList<Item> askedCommonQuestion(ArrayList<Item> c_list) {
		// < askedCommonQuestion > : 공통질문 //

		// <변수 설정>
		// selector : 화면 크기를 구하는 변수

		// <함수 설명>
		// 사이즈와 

		return c_list;
	}
}
