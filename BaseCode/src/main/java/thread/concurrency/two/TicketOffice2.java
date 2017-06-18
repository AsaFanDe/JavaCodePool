package thread.concurrency.two;

/** * @author  作者 : 范德胜
  * @date 创建时间：2017年6月18日 上午11:01:01
  * @version 1.0 
  */
public class TicketOffice2 implements Runnable{
	
	private Cinema cinema;
	
	public TicketOffice2(Cinema cinema) {
		this.cinema = cinema;
	}

	@Override
	public void run() {
		cinema.sellTicket2(2);
		cinema.sellTicket2(4);
		cinema.sellTicket1(2);
		cinema.sellTicket1(1);
		cinema.returnTicket2(2);
		cinema.sellTicket1(3);
		cinema.sellTicket2(2);
		cinema.sellTicket1(2);
	}

}
