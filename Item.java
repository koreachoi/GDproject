package algo;

//<< �������� �����ϰ� ������ ���ϴ� Ŭ���� >> //
public class Item {
	// << Ŭ���� ���� >> // 
		private String name;
		private String brand;
		private double size;
		private int resolution;
		private int refresh;
		private int brightness;
		private double battery;
		private double weight;
		private int cpu_score;
		private int ram;
		private int ram_change;
		private int gpu_type;
		private int gpu_score;
		private int price;
		private double total_score;
		
		// << Ŭ���� ������ >> // 
		Item(
				String name, String brand, double size, 
				int resolution, int refresh,int brightness, 
				double battery, double weight, int cpu_score, 
				int ram, int ram_change, int gpu_type, 
				int gpu_score, int price, double total_score
			){
			this.name = name;
			this.brand = brand;
			this.size = size;
			this.resolution = resolution;
			this.refresh = refresh;
			this.brightness = brightness;
			this.battery = battery;
			this.weight = weight;
			this.cpu_score = cpu_score;
			this.ram = ram;
			this.ram_change = ram_change;
			this.gpu_type = gpu_type;
			this.gpu_score = gpu_score;
			this.price = price;
			this.total_score = total_score;
		}
		
		// << Ŭ���� ������ >> // 
		Item(){
			this.name = " ";
			this.brand = " ";
			this.size = 0;
			this.resolution = 0;
			this.refresh = 0;
			this.brightness = 0;
			this.battery = 0;
			this.weight = 0;
			this.cpu_score = 0;
			this.ram = 0;
			this.ram_change = 0;
			this.gpu_type = 0;
			this.gpu_score = 0;
			this.price = 0;
			this.total_score = 0;
		}
		
		// << ���� ��� �޼��� >> // 
		public double calRatio(double s_point, double i_point) {
		// < calRatio > : ���� ���� ��� ��� //

		// <���� ����>
		// s_point  : ���� ���� 
		// i_point  : ���õ� ������ ����
		// t        : ���� ������ ���õ� �������� ��������
		// s        : ���� ������ ���� �������� ����(%)
		// r        : ���� ���� �ٰŷ� ������ �̿��� �������� ū����, �ּ��� ���� ���� �Ҵ�

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

		// < 0 ���� ���>  
		// 1: ������ ������ 0�� ��� 0��
		// 2: �� �������� ���̰� ���� �������� ū ���
			
			double t = 0;
			double s = 0;
			double r = 0;
			if (i_point == 0)
				return 0;
			else {
				if (s_point > i_point)
					t = s_point - i_point;
				else if (s_point < i_point)
					t = i_point - s_point;
				else if (s_point == i_point)
					return 100;
				if (s_point < t)
					return 0;
				else
					s = t / s_point;
				
				r = Math.pow(((1 - s) * 10), 2);
					
			}
			return r;
		}
		
		// << �� ������ getter setter �޼���  >> //
		public String getName() {
			return this.name;
		}
		public String getBrand() {
			return this.brand;
		}
		public double getSize() {
			return this.size;
		}
		public int getResolution() {
			return this.resolution;
		}
		public int getRefresh() {
			return this.refresh;
		}
		public int getBrightness() {
			return this.brightness;
		}
		public double getBattery() {
			return this.battery;
		}
		public double getWeight() {
			return this.weight;
		}
		public double getCpu_score() {
			return this.cpu_score;
		}
		public int getRam() {
			return this.ram;
		}
		public int getRam_change() {
			return this.ram_change;
		}
		public int getGpu_type() {
			return this.gpu_type;
		}
		public double getGpu_score() {
			return this.gpu_score;
		}
		public int getPrice() {
			return this.price;
		}
		public double getTotal_score() {
			return this.total_score;
		}
		public void setTotal_score(double score) {
			this.total_score = score;
		}
}
