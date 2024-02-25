package algo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
	// < createList > : 리스트 생성 메서드   //

	// <변수 설정>
	// 	create_list		: 리스트 생성 클래스 인스턴스 
	// 	returnList		: 반환값을 담는 리스트

	// <함수 설명>
	// 리스트 생성 클래스 인스턴스를 생성하고
	// 반환해주는 변수에 메서드를 호출하여 저장하고
	// 리턴해준다
		
		FiletoList create_list = new FiletoList();
		
		ArrayList<Item> returnList = new ArrayList<Item>();
		returnList = create_list.createList();
		return returnList;
	}
	
	// << 고사양-중복 제거 메서드 >> //
	public ArrayList<Item> deduplicationList(ArrayList<Item> targetList, ArrayList<Item> totalList){
	// < deduplicationList > :  중복 제거 메서드  //

	// <변수 설정>
	// 	targetName 		: 새로 추가할 리스트의 제품 이름
	// 	totalName		: 기존에 있던 리스트의 제품 이름 
	// 	taList			: 새로 추가할 리스트
	//	toList			: 기존에 있던 리스트 
	//	lentotalList	: 기존 리스트의 길이, 반복문의 길이로 사용
	//	len2totalList	: 기존 리스트의 길이, 중복 여부체크로 확인 

	// <함수 설명>
	// 반복문을 사용하여 새로 추가할 리스트의 제품 이름이
	// 기존에 있는 지 확인한다
	// 방법으로는 반복문을 수행하면서 같은 제품의 이름이 없을 경우
	// 길이에서 1을 빼준다, 만약 하나라도 존재할 경우 
	// 길이는 1이고, 한 개도 없을 경우 길이는 0이 된다
	// 1이 되는 경우 제품을 그대로 추가해준다
	
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
	// < filteredMinScore > : 최저 스펙 필터링 메서드  //

	// <변수 설정>
	//	filteredList	: 필터된 아이템을 담는 리스트 
	// 	minCPU			: 최소값의 CPU 점수를 담는 변수 
	//	minGPU			: 최소값의 GPU 점수를 담는 변수 
	// 	itemCPU			: 비교할 아이템의 CPU 점수를 담는 변수 
	// 	itemGPU			: 비교할 아이템의 GPU 점수를 담는 변수 

	// <함수 설명>
	// CPU의 경우 사용자가 고른 최저스펙의 7할,
	// GPU의 경우 사용자가 고른 최저스펙의 8할 정도 * 5000점 이상
	// 이 기준을 만족하지 못하는 경우에는 필터링 되며
	// 기준을 만족할 경우 리스트에 추가하여 반환해준다
	
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
			else if (s_point == i_point)
				return 100;
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
	public ArrayList<Item> effectivenessCost(ArrayList<Item> c_list, savedMinScore minScore){
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
		double cscore = 0;
		double gscore = 0;
		double price = 0;
		double minGPU = minScore.getGpuScore();
		
		if (minGPU >= 4000) {
			for (int i = 0 ; i < c_list.size(); i++) {
				cscore = c_list.get(i).getCpu_score();
				gscore = c_list.get(i).getGpu_score();
				total = c_list.get(i).getTotal_score();
				price = c_list.get(i).getPrice();
				surScore = 	(1 + (cscore / 30000)) * 
						(1 + (gscore / 10000)) *
						(2 - (price / 6000000)) *
						Math.sqrt(total + 10);
				c_list.get(i).setTotal_score(surScore);
			}
		}else if(minGPU < 4000) {
			for (int i = 0 ; i < c_list.size(); i++) {
				cscore = c_list.get(i).getCpu_score();
				gscore = c_list.get(i).getGpu_score();
				total = c_list.get(i).getTotal_score();
				price = c_list.get(i).getPrice();
				surScore = 	(1 + (cscore / 30000)) * 
						(1 + Math.exp(-(gscore-1000)*(gscore-1000)/30000000)) *
						(Math.pow((4000000/price), 2) / 100) *
						Math.sqrt(total + 10);
				c_list.get(i).setTotal_score(surScore);
			}
		}

		return c_list;
	}
		
	// << 다중 선택 고사양 선택 메서드 >> // 
	public ArrayList<Item> compareItem(ArrayList<Item> game, ArrayList<Item> coding, 
										ArrayList<Item> graphic, ArrayList<Item> none,
										savedMinScore minScore){
	// < compareItem > : 다중 선택시 고사양 선택 메서드  //

	// <변수 설정>
	//	totalList	: 중복 제거, 필터링 등의 과정이 진행된 최종 리스트를 담는 변수 
	// 	i_CPU		: 아이템의 CPU값
	//	i_GPU		: 아이템의 GPU값 
	//	s_CPU		: 기준 CPU값
	//	s_GPU		: 기준 GPU값 
	//	newCPUScore	: 계산된 아이템의 CPU 스코어 
	// 	newGPUScore	: 계산된 아이템의 GPU 스코어 
	// 	newScore	: 계산된 아이템의 토탈 점수 
		
	// <함수 설명>
	// 다중 선택된 경우이기때문에, 해당 리스트가 빈값이 아닐경우
	// 중복아이템을 우선적으로 제거해준다
	// 그 후 사용자 선택 최소값 필터링을 통해
	// 기준점수를 만족하지 못하는 아이템을 리스트에서 제거해주고
	// 각 아이템들의 점수를 사용자 기준점수를 통해 다시 점수를 측정한다
	// 그 후 가성비 계수를 구한 뒤 수치를 곱해준뒤 리스트를 반환한다
		
		ArrayList<Item> totalList = new ArrayList<Item>();

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
		
		totalList = effectivenessCost(totalList, minScore);
		comparedList = totalList;
		return comparedList;
	}
	
	// << 브랜드 제품 추가 점수 메서드 >> //
	public ArrayList<Item> addBrandScore(ArrayList<Item> list, ArrayList<String> brandList){
	// < addBrandScore > :  브랜드 제품 추가 점수 메서드 //

	// <변수 설정>
	// 	TScore 		: 임시 토탈 값
	//	SScore 		: 2할의 값 
	// 	CScore 		: 계산해준 값 
	//	tempScore 	: 기존 값
	// 	addScore 	: 추가 점수 값
	//	resultScore : 더해진 값
		

	// <함수 설명>
	// 제품의 해당 브랜드가 선택된 브랜드 리스트 안에 존재하면 추가 점수를 부여한다
		double TScore;
		double SScore;
		double CScore;
		for (int i = 0; i < list.size(); i ++) {
			if (list.get(i).getBrand().equals("레노버") ||
				list.get(i).getBrand().equals("ASUS") ||
				list.get(i).getBrand().equals("에이서") ||
				list.get(i).getBrand().equals("MSI") ||
				list.get(i).getBrand().equals("MSI G")
				) {
				TScore = list.get(i).getTotal_score();
				SScore = TScore * 0.2;
				CScore = TScore - SScore;
				list.get(i).setTotal_score(CScore);
			}
		}
		double tempScore = 0;
		double addScore = 0;
		double resultScore = 0;
		for (int i = 0; i < list.size(); i++) {
			if (brandList.contains(list.get(i).getBrand())) {
				tempScore = list.get(i).getTotal_score();
				if (list.get(i).getBrand().equals("레노버") ||
						list.get(i).getBrand().equals("ASUS") ||
						list.get(i).getBrand().equals("에이서") ||
						list.get(i).getBrand().equals("MSI") ||
						list.get(i).getBrand().equals("MSI G")
					){
					addScore = (tempScore * 0.2);
					resultScore = tempScore + addScore;
					list.get(i).setTotal_score(resultScore);
				}else if(
						list.get(i).getBrand().equals("삼성전자") ||
						list.get(i).getBrand().equals("LG전자") ||
						list.get(i).getBrand().equals("APPLE")
						) {
					addScore = (tempScore * 0.4);
					resultScore = tempScore + addScore;
					list.get(i).setTotal_score(resultScore);
				}
					
			}
		}
		return list;
	}
	
	// << 밑이 x인 로그 메서드 >> //
	public double logB(double x, double base) {
		return Math.log(x) / Math.log(base);
	}
	
	// << 제품 무게 점수 메서드 >> //
	public ArrayList<Item> mulWeightScore(ArrayList<Item> list, double a, double b, double exp, double base){
		
		double weight = 0;
		double logScore = 0;
		double tempScore = 0;
		double resultScore = 0;
		for (int i = 0; i < list.size(); i++) {
			weight = list.get(i).getWeight();
			logScore = a - (b * logB((weight + exp),base));
			tempScore = list.get(i).getTotal_score();
			resultScore = (tempScore * logScore);
			list.get(i).setTotal_score(resultScore);
		}
		return list;
	}
	
	// << 제품 주사율 점수 메서드 >> //
	public ArrayList<Item> addRefreshScore(ArrayList<Item> list){
		
		double refresh = 0;
		ArrayList<Item> newList = new ArrayList<Item>();
		
		for (int i = 0; i < list.size(); i++) {
			refresh = list.get(i).getRefresh();
			if (refresh >= 90) {
				newList.add(list.get(i));
			}
		}
		return newList;
	}
	
	// << 가격 필터링 메서드 >> //
	public ArrayList<Item> filteredPriceItem(ArrayList<Item> list, double price){
		
		ArrayList<Item> newList = new ArrayList<Item>();
		double minPrice = 0;
		double maxPrice = price;

		for(int i = 0; i < list.size(); i++) {
			if (list.get(i).getPrice() >=minPrice && list.get(i).getPrice() < maxPrice) {
				newList.add(list.get(i));
			}
		}
		
		return newList;
	}
	
	// << 공통질문 : 사이즈를 물어보고 필터하는 메서드 >> //
	public ArrayList<Item> filteredSizeItem(ArrayList<Item> c_list, double minSize) {
	// < filteredSizeItem > : 사이즈 필터링 메서드 //

	// <변수 설정>
	// mins : 크기의 최저값
	// maxs : 크기의 최대값
	// filteredSizeList : 필터링된 리스트를 담는 리스트 변수

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
		// 1:17 이상 2:16 이상 3:15 이상 4:14 이상 5:14 미만

		if (mins == 17) {
			maxs = 20;
		} else if (mins == 16) {
			maxs = 20;
		} else if (mins == 15) {
			maxs = 20;
		} else if (mins == 14) {
			maxs = 20;
		} else if (mins == 13) {
			maxs = 20;
		}

		for (int i = 0; i < c_list.size(); i++) {
			if (c_list.get(i).getSize() >= mins && c_list.get(i).getSize() < maxs) {
				filteredSizeList.add(c_list.get(i));
			}
		}
		return filteredSizeList;
	}
	
	// << 출력 메서드 >> //
	public void printItem(ArrayList<Item> list) {
	// < printItem > :  정렬 및 출력해주는 메서드 //

	// <변수 설정>
	//	temp : 정렬해주기 위한 임시 변수 

	// <함수 설명>
	// 정렬을 해주고 출력해준다 
		
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
		System.out.println("개수 : " + list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getName() + " : 이름");
			System.out.println(list.get(i).getSize() + " : 크기");
			System.out.println(list.get(i).getBattery() + ": 배터리");
			System.out.println(list.get(i).getBrand() + ": 브랜드");
			System.out.println(list.get(i).getRefresh() + ": 주사율");
			System.out.println(list.get(i).getWeight() + ": 무게");
			System.out.println(list.get(i).getResolution() + " : 해상도");
			System.out.println(list.get(i).getBrightness() + " : 밝기");
			System.out.println(list.get(i).getCpu_score() + " : CPU");
			System.out.println(list.get(i).getGpu_score() + " : GPU");
			System.out.println(list.get(i).getPrice() + " : 가격");
			System.out.println(list.get(i).getTotal_score() + " : 총합점수");
			System.out.println("-----------------------------------------------");
		}
	}
	
	// << 설문 시작 메서드 >> //
	public void recoItemStart() throws IOException{
	// < recoItemStart > : 설문하고 실행하는 메서드 //

	// <변수 설정>
	//	minScore		: 사용자 선택의 최소값을 구하기 위한 클래스의 인스턴스
	// 	selectedGame	: 단일 선택시 선택지 체크를 위한 변수
	// 	selectedCoding	: 상동
	//	selectedGraphic	: 상동
	// 	selectedNone	: 상동
	// 	selectedCount	: 중복선택인지 단일선택인지 구분하기 위한 변수 
		
	// <함수 설명>
	// 사용 목적을 입력받은후 
	// 목적에 맞는 리스트와 클래스를 생성하고
	// 인스턴스를 생성한뒤 해당 클래스의 설문 메서드를 수행한다
	// 그 후 공통질문을 수행한뒤, 가성비 점수를 곱해준다
	// 마지막으로는 정렬을 해준다, 위의 과정이 끝나면 다시 한 번
	// 사용 목적을 물어본뒤 종료하면 그대로 설문된 값을 출력해주고
	// 복수의 사용 목적이 선택될 경우 최소값을 만족하며 더 사양이 높은
	// 제품을 필터 및 정렬하는 메서드를 호출하여 값을 수정해준다

		Scanner scanner = new Scanner(System.in);
		savedMinScore minScore = new savedMinScore();
		
		int selectedGame = 0;
		int selectedCoding = 0;
		int selectedGraphic = 0;
		int selectedNone = 0;
		int selectedCount = 0;
		/*----------------------------------------가격 필터----------------------------------------*/
		System.out.println("가격대 입력 ex) 150만원 = 1500000 : ");
		double inputPrice = scanner.nextDouble();
		/*----------------------------------------용도별 설문----------------------------------------*/
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
				gameList = game.effectivenessCost(gameList);
				gameList = game.sortTotalScore(gameList);
			}else if (selector == 2 && selectedCoding == 0) {
				codingList = createList();
				recoCodingItem coding = new recoCodingItem(codingList);
				selectedCount = selectedCount + 1;
				selectedCoding = 1;
				codingList = coding.selectedCoding(codingList, minScore);
				codingList = coding.effectivenessCost(codingList);
				codingList = coding.sortTotalScore(codingList);
			}else if (selector == 3 && selectedGraphic == 0) {
				graphicList = createList();
				recoGraphicItem graphic = new recoGraphicItem(graphicList);
				selectedCount = selectedCount + 1;
				selectedGraphic = 1;
				graphicList = graphic.selectedGraphic(graphicList, minScore);
				graphicList = graphic.effectivenessCost(graphicList);
				graphicList = graphic.sortTotalScore(graphicList);
			}else if (selector == 4 && selectedNone == 0) {
				noSelectList = createList();
				recoItem none = new recoItem(noSelectList);
				selectedCount = selectedCount + 1;
				selectedNone = 1;
				noSelectList = none.noneSelectedScore(noSelectList);
			}else if (selector == 5){
				System.out.println("종료");
				break;
			}else {
				System.out.println("Error");
			}	
		}
		
		/*----------------------------------------다중처리----------------------------------------*/
		if (selectedCount > 1) {
			comparedList = compareItem(gameList, codingList, graphicList, noSelectList, minScore);
		}else if (selectedCount == 1) {
			if (selectedGame == 1) {comparedList = gameList;}
			else if (selectedCoding == 1) {comparedList = codingList;}
			else if (selectedGraphic == 1) {comparedList = graphicList;}
			else if (selectedNone == 1) {comparedList = noSelectList;}
		}else if (selectedCount == 0) {
			System.out.println("선택된 아이템 없음");
		}
		
		/*----------------------------------------크기 필터링----------------------------------------*/
		System.out.println("화면크기 선택 1:17 이상 2:16 이상 3:15 이상 4:14 이상 5:13 이상");
		int sizeselect = scanner.nextInt();
		double size = 0;
		
		if (sizeselect == 1) {
			size = 17;
			comparedList = filteredSizeItem(comparedList, size);
		} else if (sizeselect == 2) {
			size = 16;
			comparedList = filteredSizeItem(comparedList, size);
		} else if (sizeselect == 3) {
			size = 15;
			comparedList = filteredSizeItem(comparedList, size);
		} else if (sizeselect == 4) {
			size = 14;
			comparedList = filteredSizeItem(comparedList, size);
		} else if (sizeselect == 5) {
			size = 13;
			comparedList = filteredSizeItem(comparedList, size);
		}
		
		/*----------------------------------------브랜드 가중치 처리----------------------------------------*/
		ArrayList<String> BrandList = new ArrayList<String>();
		while(true) {
			System.out.println("선호하는 브랜드를 골라주세요 1:삼성 2:LG 3:APPLE 4:ASUS 5:레노버 6:에이서 7:MSI 8:종료");
			int brandselector = scanner.nextInt();
			
			if (brandselector == 1) {if (BrandList.contains("삼성전자") == false) {BrandList.add("삼성");}}
			if (brandselector == 2) {if (BrandList.contains("LG전자") == false) {BrandList.add("LG");}}
			if (brandselector == 3) {if (BrandList.contains("APPLE") == false) {BrandList.add("APPLE");}}
			if (brandselector == 4) {if (BrandList.contains("ASUS") == false) {BrandList.add("ASUS");}}
			if (brandselector == 5) {if (BrandList.contains("레노버") == false) {BrandList.add("레노버");}}
			if (brandselector == 6) {if (BrandList.contains("에이서") == false) {BrandList.add("에이서");}}
			if (brandselector == 7) {
				if (BrandList.contains("MSI") == false) {BrandList.add("MSI");}
				if (BrandList.contains("MSI G") == false) {BrandList.add("MSI G");}
			}
			if (brandselector == 8) {break;}
		}
		comparedList = addBrandScore(comparedList,BrandList);
		
		/*----------------------------------------무게 설문----------------------------------------*/
		System.out.println("노트북 휴대성은 얼마나 중요하신가요?");
		System.out.println("1.노트북의 가격이 비싸져도 가벼운 노트북을 선호한다.");
		System.out.println("2.가격대비 성능과 무게가 적절했으면 좋겠다. ");
		System.out.println("3.너무 무겁지만 않으면 상관없다.");
		System.out.println("4.주로 집안에서 사용한다.");
		System.out.println("5.무게는 별로 상관이 없다(가성비가 좋으면 좋다)");
		int weightselector = scanner.nextInt();
		double exp = 0;
		double base = 0;
		double a = 0;
		double b = 0;
		if (weightselector == 1) {a = 1; b = 1.8; exp = -0.3; base = 4;}
		else if (weightselector == 2) {a = 1; b = 1.5; exp = 0; base = 5;}
		else if (weightselector == 3) {a = 1; b = 2.1; exp = 0; base = 6;}
		else if (weightselector == 4) {a = 2; b = 1; exp = 0.1; base = 10;}
		else if (weightselector == 5) {a = 2; b = 1; exp = 0.3; base = 10;}
		comparedList = mulWeightScore(comparedList,a, b, exp,base);
		
		/*----------------------------------------주사율 설문----------------------------------------*/
		System.out.println("웹서핑, 영상시청, 게임을 플레이 하실 때 부드러운 화면을 원하시나요?");
		System.out.println("1. 가격이 비싸져도 화면이 부드러운 화면을 원한다  2. 상관없다. ");
		int refreshselector = scanner.nextInt();
		if (refreshselector == 1) {
			comparedList = addRefreshScore(comparedList);
		}
		
		comparedList = filteredPriceItem(comparedList, inputPrice);
		printItem(comparedList);
	}
}