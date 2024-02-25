package algo;

//<< 중복시 최저 스펙을 담는 클래스  >> //
public class savedMinScore {
	private double cpuScore;
	private double gpuScore;
	
	savedMinScore(){
		this.cpuScore = 0;
		this.gpuScore = 0;
	}
	// << getter setter 메서드 >> //
	public double getCpuScore() {
		return this.cpuScore;
	}
	public double getGpuScore() {
		return this.gpuScore;
	}
	public void setCpuScore(double cpuScore) {
		this.cpuScore = cpuScore;
	}
	public void setGpuScore(double gpuScore) {
		this.gpuScore = gpuScore;
	}
	public void compareCpuScore(double cpuScore) {
		if (this.cpuScore < cpuScore) {
			this.cpuScore = cpuScore;
		}
	}
	public void compareGpuScore(double gpuScore) {
		if (this.gpuScore < gpuScore) {
			this.gpuScore = gpuScore;
		}
	}
	
}
