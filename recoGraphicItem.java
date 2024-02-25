package algo;

import java.util.ArrayList;
import java.util.Scanner;

//<< ����, ��õ �� �۾��� �̷������ ���� Ŭ������ �׷����� �������� ����� Ŭ���� >> //
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
		
		// c_list = sortGPU(c_list);
		
		for (int i = 0; i < c_list.size(); i++) {
			s_item = 21036 * r;
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
		
		// c_list = sortCPU(c_list);
		
		for (int i = 0; i < c_list.size(); i++) {
			s_item = 35000 * r;
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
			selected_ratio = 0.35;
			c_list = calGpuscoreToTotal(c_list, selected_ratio);
		}else if (selector3 == 2) {
			selected_ratio = 0.1;
			c_list = calGpuscoreToTotal(c_list, selected_ratio);
		}
		
		setGPUScore = c_list.get(0).getGpu_score() * selected_ratio;
		minScore.compareGpuScore(setGPUScore);
		
		System.out.println("�۾��� �ӵ�, ȿ������ �󸶳� �߿��ϰ� �����Ͻó���? 1:�ſ��߿� 2:�߿� 3:���� 4:�߿����� ����");
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
