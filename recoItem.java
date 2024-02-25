package algo;

import java.util.ArrayList;
import java.util.Scanner;

//<< ����, ��õ �� �۾��� �̷������ ���� Ŭ���� >> //
public class recoItem {
	// << Ŭ���� ����Ʈ ���� >> // 
	private ArrayList<Item> c_list = new ArrayList<Item>();
	
	// << Ŭ���� ������ >> // 
	recoItem(ArrayList<Item> c_list){
		this.c_list = c_list;
	}
		
	// << CPU�� �����ϴ� Ŭ���� �޼��� >> // 
	public ArrayList<Item> sortCPU(ArrayList<Item> c_list){
	// <CPU SORT> : ��ǰ ���� 
	// <�Լ� ����>
	// �Լ��� ���� �⺻���� ���� �˰������� ����Ͽ�
	// ������������ �����Ѵ�
	// ���� �˰����� ���� �� ȿ������ �˰������� ���� ����
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
		
	// << GPU�� �����ϴ� Ŭ���� �޼��� >> // 
	public ArrayList<Item> sortGPU(ArrayList<Item> c_list) {
		// <GPU SORT> : ��ǰ ����
		// <�Լ� ����>
		// �Լ��� ���� �⺻���� ���� �˰������� ����Ͽ�
		// ������������ �����Ѵ�
		// ���� �˰����� ���� �� ȿ������ �˰������� ���� ����
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
		
	// << TotalScore�� �����ϴ� Ŭ���� �޼��� //
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

	// << GPU ������ total ������ ȯ���ϴ� �޼��� >> //
	public ArrayList<Item> calGpuscoreToTotal() {
		return c_list;
	}

	// << CPU ������ total ������ ȯ���ϴ� �޼��� >> //
	public ArrayList<Item> calCpuscoreToTotal() {
		return c_list;
	}

	// << ��� ������ total ������ ȯ���ϴ� �޼��� >> //
	public ArrayList<Item> calBrightnessscoreToTotal() {
		return c_list;
	}

	// << �ػ� ���ǿ� �´� �������� ���͸��ϴ� �޼��� >> //
	public ArrayList<Item> filteredResolution() {
		return c_list;
	}

	// << ������ ���ϴ� �޼��� >> //
	public ArrayList<Item> effectivenessCost(ArrayList<Item> c_list) {
		// < effectivenessCost > : ������ ����� ���ѵ� ������ �����ϴ� �޼��� //

		// <���� ����>
		// surScore : ������ ���
		// total : �� ����
		// price : ���� ����

		// <�Լ� ����>
		// ������ ����� �����ְ�
		// ������ ������ ����� ���� ���������� ��������־� �����Ѵ�
		// ����Ʈ�� ��ȯ�Ѵ�

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

	// << ���� �� ���� ��û�� ���� ���� �޼��� >> //
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
	
	// << ���� ���� >> //
	public ArrayList<Item> askedCommonQuestion(ArrayList<Item> c_list) {
		// < askedCommonQuestion > : �������� //

		// <���� ����>
		// selector : ȭ�� ũ�⸦ ���ϴ� ����

		// <�Լ� ����>
		// ������� 

		return c_list;
	}
}
