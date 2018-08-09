import java.io.Serializable;

public class Skore implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String jmeno;
	private int hour;
	private int min;
	private int sec;
	public Skore(String jmeno, int hour, int min, int sec) {
		super();
		this.jmeno = jmeno;
		this.hour = hour;
		this.min = min;
		this.sec = sec;
	}
	public String getJmeno() {
		return jmeno;
	}
	public void setJmeno(String jmeno) {
		this.jmeno = jmeno;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getSec() {
		return sec;
	}
	public void setSec(int sec) {
		this.sec = sec;
	}
	
	
}
