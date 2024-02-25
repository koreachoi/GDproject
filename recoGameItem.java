package algo;

import java.util.ArrayList;
import java.util.Scanner;

//<< ����, ��õ �� �۾��� �̷������ ���� Ŭ������ ������ �������� ����� Ŭ���� >> //
class recoGameItem extends recoItem{

	recoGameItem(ArrayList<Item> c_list) {
		super(c_list);
	}
	
	// << ���� ���͸� �޼��� >> // 
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
		
		c_list = filteredAppleProduct(c_list);
		return c_list;
	}
}