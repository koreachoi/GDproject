package algo;

import java.util.ArrayList;
import java.util.Scanner;

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
				cal_point = 0.7 * c_list.get(i).calRatio(s_item, i_item);
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
			selected_ratio = 0.35;
			selected_option = 1;
			System.out.println("� �۾��� ����Ͻó���? 1:������ 2:�ӽŷ��� 3:���� �׷��� ���Ӱ��� 4:��Ÿ(3D �۾�)");
			int selector1_1 = scanner.nextInt();
			c_list = calGpuscoreToTotal(c_list, selected_ratio, selected_option);
		}else if(selector == 2) {
			selected_ratio = 0.05;
			selected_option = 2;
			c_list = calGpuscoreToTotal(c_list, selected_ratio, selected_option);
		}
		setGPUScore = c_list.get(0).getGpu_score() * selected_ratio;
		minScore.compareGpuScore(setGPUScore);
		
		System.out.println("���� Ȥ�� ���� ������ �䱸�ϴ� ���α׷����� ����Ͻó���? 1:O 2:X");
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
