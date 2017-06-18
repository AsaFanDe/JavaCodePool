package thread.concurrency.two;
/** * @author  作者 : 范德胜
  * @date 创建时间：2017年6月18日 上午10:32:05
  * @version 1.0 
  */
public class Cinema {
	
	private long vacanciesCinema1;
	
	private long vacanciesCinema2;
	
	private final Object controlCinema1, controlCinema2;

	public Cinema() {
		super();
		this.vacanciesCinema1 = 20;
		this.vacanciesCinema2 = 20;
		this.controlCinema1 = new Object();
		this.controlCinema2 = new Object();
	}
	
	public boolean sellTicket1(int number){
		synchronized (controlCinema1) {
			if (number < vacanciesCinema1) {
				vacanciesCinema1 -= number;
				return true;
			}else {
				return false;
			}
		}
	}
	
	public boolean returnTicket1(int number){
		synchronized (controlCinema1) {
			vacanciesCinema1 += number;
			return true;
		}
	}
	
	public boolean sellTicket2(int number){
		synchronized (controlCinema2) {
			if (number < vacanciesCinema2) {
				vacanciesCinema2 -= number;
				return true;
			}else {
				return false;
			}
		}
	}
	
	public boolean returnTicket2(int number){
		synchronized (controlCinema2) {
			vacanciesCinema2 += number;
			return true;
		}
	}
	
	public long getVacanciesCinema1(){
		return vacanciesCinema1;
	}
	
	public long getVacanciesCinema2(){
		return vacanciesCinema2;
	}

}
