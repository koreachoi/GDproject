package algo;

//<< 아이템을 저장하고 총점을 구하는 클래스 >> //
public class Item {
	// << 클래스 변수 >> // 
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
		
		// << 클래스 생성자 >> // 
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
		
		// << 클래스 생성자 >> // 
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
		
		// << 점수 계산 메서드 >> // 
		public double calRatio(double s_point, double i_point) {
		// < calRatio > : 점수 비율 계산 방법 //

		// <변수 설정>
		// s_point  : 기준 점수 
		// i_point  : 선택된 아이템 점수
		// t        : 기준 점수와 선택된 아이템의 점수차이
		// s        : 기준 점수에 대한 점수차이 비율(%)
		// r        : 계산된 값을 근거로 제곱을 이용해 가까울수록 큰값을, 멀수록 작은 값을 할당

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

		// < 0 점인 경우>  
		// 1: 아이템 점수가 0인 경우 0점
		// 2: 두 점수간의 차이가 기준 점수보다 큰 경우
			
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
		
		// << 각 변수의 getter setter 메서드  >> //
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
