package thread.concurrency.two;
/** * @author  作者 : 范德胜
  * @date 创建时间：2017年6月18日 上午10:52:58
  * @version 1.0 
  */
public class TicketOffice1 implements Runnable{
	
	private Cinema cinema;
	
	public TicketOffice1(Cinema cinema) {
		this.cinema = cinema;
	}

	@Override
	public void run() {
		cinema.sellTicket1(3);
		cinema.sellTicket1(2);
		cinema.sellTicket2(2);
		cinema.returnTicket1(3);
		cinema.sellTicket1(5);
		cinema.sellTicket2(2);
		cinema.sellTicket2(2);
		cinema.sellTicket2(2);
	}

}