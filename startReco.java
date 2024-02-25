package algo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
	// < createList > : ����Ʈ ���� �޼���   //

	// <���� ����>
	// 	create_list		: ����Ʈ ���� Ŭ���� �ν��Ͻ� 
	// 	returnList		: ��ȯ���� ��� ����Ʈ

	// <�Լ� ����>
	// ����Ʈ ���� Ŭ���� �ν��Ͻ��� �����ϰ�
	// ��ȯ���ִ� ������ �޼��带 ȣ���Ͽ� �����ϰ�
	// �������ش�
		
		FiletoList create_list = new FiletoList();
		
		ArrayList<Item> returnList = new ArrayList<Item>();
		returnList = create_list.createList();
		return returnList;
	}
	
	// << ����-�ߺ� ���� �޼��� >> //
	public ArrayList<Item> deduplicationList(ArrayList<Item> targetList, ArrayList<Item> totalList){
	// < deduplicationList > :  �ߺ� ���� �޼���  //

	// <���� ����>
	// 	targetName 		: ���� �߰��� ����Ʈ�� ��ǰ �̸�
	// 	totalName		: ������ �ִ� ����Ʈ�� ��ǰ �̸� 
	// 	taList			: ���� �߰��� ����Ʈ
	//	toList			: ������ �ִ� ����Ʈ 
	//	lentotalList	: ���� ����Ʈ�� ����, �ݺ����� ���̷� ���
	//	len2totalList	: ���� ����Ʈ�� ����, �ߺ� ����üũ�� Ȯ�� 

	// <�Լ� ����>
	// �ݺ����� ����Ͽ� ���� �߰��� ����Ʈ�� ��ǰ �̸���
	// ������ �ִ� �� Ȯ���Ѵ�
	// ������δ� �ݺ����� �����ϸ鼭 ���� ��ǰ�� �̸��� ���� ���
	// ���̿��� 1�� ���ش�, ���� �ϳ��� ������ ��� 
	// ���̴� 1�̰�, �� ���� ���� ��� ���̴� 0�� �ȴ�
	// 1�� �Ǵ� ��� ��ǰ�� �״�� �߰����ش�
	
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
	// < filteredMinScore > : ���� ���� ���͸� �޼���  //

	// <���� ����>
	//	filteredList	: ���͵� �������� ��� ����Ʈ 
	// 	minCPU			: �ּҰ��� CPU ������ ��� ���� 
	//	minGPU			: �ּҰ��� GPU ������ ��� ���� 
	// 	itemCPU			: ���� �������� CPU ������ ��� ���� 
	// 	itemGPU			: ���� �������� GPU ������ ��� ���� 

	// <�Լ� ����>
	// CPU�� ��� ����ڰ� �� ���������� 7��,
	// GPU�� ��� ����ڰ� �� ���������� 8�� ���� * 5000�� �̻�
	// �� ������ �������� ���ϴ� ��쿡�� ���͸� �Ǹ�
	// ������ ������ ��� ����Ʈ�� �߰��Ͽ� ��ȯ���ش�
	
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
	
	// << ������ ���ϴ� �޼��� >> //
	public ArrayList<Item> effectivenessCost(ArrayList<Item> c_list, savedMinScore minScore){
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
		
	// << ���� ���� ���� ���� �޼��� >> // 
	public ArrayList<Item> compareItem(ArrayList<Item> game, ArrayList<Item> coding, 
										ArrayList<Item> graphic, ArrayList<Item> none,
										savedMinScore minScore){
	// < compareItem > : ���� ���ý� ���� ���� �޼���  //

	// <���� ����>
	//	totalList	: �ߺ� ����, ���͸� ���� ������ ����� ���� ����Ʈ�� ��� ���� 
	// 	i_CPU		: �������� CPU��
	//	i_GPU		: �������� GPU�� 
	//	s_CPU		: ���� CPU��
	//	s_GPU		: ���� GPU�� 
	//	newCPUScore	: ���� �������� CPU ���ھ� 
	// 	newGPUScore	: ���� �������� GPU ���ھ� 
	// 	newScore	: ���� �������� ��Ż ���� 
		
	// <�Լ� ����>
	// ���� ���õ� ����̱⶧����, �ش� ����Ʈ�� ���� �ƴҰ��
	// �ߺ��������� �켱������ �������ش�
	// �� �� ����� ���� �ּҰ� ���͸��� ����
	// ���������� �������� ���ϴ� �������� ����Ʈ���� �������ְ�
	// �� �����۵��� ������ ����� ���������� ���� �ٽ� ������ �����Ѵ�
	// �� �� ������ ����� ���� �� ��ġ�� �����ص� ����Ʈ�� ��ȯ�Ѵ�
		
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
	
	// << �귣�� ��ǰ �߰� ���� �޼��� >> //
	public ArrayList<Item> addBrandScore(ArrayList<Item> list, ArrayList<String> brandList){
	// < addBrandScore > :  �귣�� ��ǰ �߰� ���� �޼��� //

	// <���� ����>
	// 	TScore 		: �ӽ� ��Ż ��
	//	SScore 		: 2���� �� 
	// 	CScore 		: ������� �� 
	//	tempScore 	: ���� ��
	// 	addScore 	: �߰� ���� ��
	//	resultScore : ������ ��
		

	// <�Լ� ����>
	// ��ǰ�� �ش� �귣�尡 ���õ� �귣�� ����Ʈ �ȿ� �����ϸ� �߰� ������ �ο��Ѵ�
		double TScore;
		double SScore;
		double CScore;
		for (int i = 0; i < list.size(); i ++) {
			if (list.get(i).getBrand().equals("�����") ||
				list.get(i).getBrand().equals("ASUS") ||
				list.get(i).getBrand().equals("���̼�") ||
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
				if (list.get(i).getBrand().equals("�����") ||
						list.get(i).getBrand().equals("ASUS") ||
						list.get(i).getBrand().equals("���̼�") ||
						list.get(i).getBrand().equals("MSI") ||
						list.get(i).getBrand().equals("MSI G")
					){
					addScore = (tempScore * 0.2);
					resultScore = tempScore + addScore;
					list.get(i).setTotal_score(resultScore);
				}else if(
						list.get(i).getBrand().equals("�Ｚ����") ||
						list.get(i).getBrand().equals("LG����") ||
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
	
	// << ���� x�� �α� �޼��� >> //
	public double logB(double x, double base) {
		return Math.log(x) / Math.log(base);
	}
	
	// << ��ǰ ���� ���� �޼��� >> //
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
	
	// << ��ǰ �ֻ��� ���� �޼��� >> //
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
	
	// << ���� ���͸� �޼��� >> //
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
	
	// << �������� : ����� ����� �����ϴ� �޼��� >> //
	public ArrayList<Item> filteredSizeItem(ArrayList<Item> c_list, double minSize) {
	// < filteredSizeItem > : ������ ���͸� �޼��� //

	// <���� ����>
	// mins : ũ���� ������
	// maxs : ũ���� �ִ밪
	// filteredSizeList : ���͸��� ����Ʈ�� ��� ����Ʈ ����

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
		// 1:17 �̻� 2:16 �̻� 3:15 �̻� 4:14 �̻� 5:14 �̸�

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
	
	// << ��� �޼��� >> //
	public void printItem(ArrayList<Item> list) {
	// < printItem > :  ���� �� ������ִ� �޼��� //

	// <���� ����>
	//	temp : �������ֱ� ���� �ӽ� ���� 

	// <�Լ� ����>
	// ������ ���ְ� ������ش� 
		
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
		System.out.println("���� : " + list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getName() + " : �̸�");
			System.out.println(list.get(i).getSize() + " : ũ��");
			System.out.println(list.get(i).getBattery() + ": ���͸�");
			System.out.println(list.get(i).getBrand() + ": �귣��");
			System.out.println(list.get(i).getRefresh() + ": �ֻ���");
			System.out.println(list.get(i).getWeight() + ": ����");
			System.out.println(list.get(i).getResolution() + " : �ػ�");
			System.out.println(list.get(i).getBrightness() + " : ���");
			System.out.println(list.get(i).getCpu_score() + " : CPU");
			System.out.println(list.get(i).getGpu_score() + " : GPU");
			System.out.println(list.get(i).getPrice() + " : ����");
			System.out.println(list.get(i).getTotal_score() + " : ��������");
			System.out.println("-----------------------------------------------");
		}
	}
	
	// << ���� ���� �޼��� >> //
	public void recoItemStart() throws IOException{
	// < recoItemStart > : �����ϰ� �����ϴ� �޼��� //

	// <���� ����>
	//	minScore		: ����� ������ �ּҰ��� ���ϱ� ���� Ŭ������ �ν��Ͻ�
	// 	selectedGame	: ���� ���ý� ������ üũ�� ���� ����
	// 	selectedCoding	: ��
	//	selectedGraphic	: ��
	// 	selectedNone	: ��
	// 	selectedCount	: �ߺ��������� ���ϼ������� �����ϱ� ���� ���� 
		
	// <�Լ� ����>
	// ��� ������ �Է¹����� 
	// ������ �´� ����Ʈ�� Ŭ������ �����ϰ�
	// �ν��Ͻ��� �����ѵ� �ش� Ŭ������ ���� �޼��带 �����Ѵ�
	// �� �� ���������� �����ѵ�, ������ ������ �����ش�
	// ���������δ� ������ ���ش�, ���� ������ ������ �ٽ� �� ��
	// ��� ������ ����� �����ϸ� �״�� ������ ���� ������ְ�
	// ������ ��� ������ ���õ� ��� �ּҰ��� �����ϸ� �� ����� ����
	// ��ǰ�� ���� �� �����ϴ� �޼��带 ȣ���Ͽ� ���� �������ش�

		Scanner scanner = new Scanner(System.in);
		savedMinScore minScore = new savedMinScore();
		
		int selectedGame = 0;
		int selectedCoding = 0;
		int selectedGraphic = 0;
		int selectedNone = 0;
		int selectedCount = 0;
		/*----------------------------------------���� ����----------------------------------------*/
		System.out.println("���ݴ� �Է� ex) 150���� = 1500000 : ");
		double inputPrice = scanner.nextDouble();
		/*----------------------------------------�뵵�� ����----------------------------------------*/
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
				System.out.println("����");
				break;
			}else {
				System.out.println("Error");
			}	
		}
		
		/*----------------------------------------����ó��----------------------------------------*/
		if (selectedCount > 1) {
			comparedList = compareItem(gameList, codingList, graphicList, noSelectList, minScore);
		}else if (selectedCount == 1) {
			if (selectedGame == 1) {comparedList = gameList;}
			else if (selectedCoding == 1) {comparedList = codingList;}
			else if (selectedGraphic == 1) {comparedList = graphicList;}
			else if (selectedNone == 1) {comparedList = noSelectList;}
		}else if (selectedCount == 0) {
			System.out.println("���õ� ������ ����");
		}
		
		/*----------------------------------------ũ�� ���͸�----------------------------------------*/
		System.out.println("ȭ��ũ�� ���� 1:17 �̻� 2:16 �̻� 3:15 �̻� 4:14 �̻� 5:13 �̻�");
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
		
		/*----------------------------------------�귣�� ����ġ ó��----------------------------------------*/
		ArrayList<String> BrandList = new ArrayList<String>();
		while(true) {
			System.out.println("��ȣ�ϴ� �귣�带 ����ּ��� 1:�Ｚ 2:LG 3:APPLE 4:ASUS 5:����� 6:���̼� 7:MSI 8:����");
			int brandselector = scanner.nextInt();
			
			if (brandselector == 1) {if (BrandList.contains("�Ｚ����") == false) {BrandList.add("�Ｚ");}}
			if (brandselector == 2) {if (BrandList.contains("LG����") == false) {BrandList.add("LG");}}
			if (brandselector == 3) {if (BrandList.contains("APPLE") == false) {BrandList.add("APPLE");}}
			if (brandselector == 4) {if (BrandList.contains("ASUS") == false) {BrandList.add("ASUS");}}
			if (brandselector == 5) {if (BrandList.contains("�����") == false) {BrandList.add("�����");}}
			if (brandselector == 6) {if (BrandList.contains("���̼�") == false) {BrandList.add("���̼�");}}
			if (brandselector == 7) {
				if (BrandList.contains("MSI") == false) {BrandList.add("MSI");}
				if (BrandList.contains("MSI G") == false) {BrandList.add("MSI G");}
			}
			if (brandselector == 8) {break;}
		}
		comparedList = addBrandScore(comparedList,BrandList);
		
		/*----------------------------------------���� ����----------------------------------------*/
		System.out.println("��Ʈ�� �޴뼺�� �󸶳� �߿��ϽŰ���?");
		System.out.println("1.��Ʈ���� ������ ������� ������ ��Ʈ���� ��ȣ�Ѵ�.");
		System.out.println("2.���ݴ�� ���ɰ� ���԰� ���������� ���ڴ�. ");
		System.out.println("3.�ʹ� �������� ������ �������.");
		System.out.println("4.�ַ� ���ȿ��� ����Ѵ�.");
		System.out.println("5.���Դ� ���� ����� ����(������ ������ ����)");
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
		
		/*----------------------------------------�ֻ��� ����----------------------------------------*/
		System.out.println("������, �����û, ������ �÷��� �Ͻ� �� �ε巯�� ȭ���� ���Ͻó���?");
		System.out.println("1. ������ ������� ȭ���� �ε巯�� ȭ���� ���Ѵ�  2. �������. ");
		int refreshselector = scanner.nextInt();
		if (refreshselector == 1) {
			comparedList = addRefreshScore(comparedList);
		}
		
		comparedList = filteredPriceItem(comparedList, inputPrice);
		printItem(comparedList);
	}
}