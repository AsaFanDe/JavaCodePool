package thread.concurrency.three;
/** * @author  作者 : 范德胜
  * @date 创建时间：2017年6月19日 下午5:36:54
  * @version 1.0 
  */
public class Results {
	
	private int data[];
	
	public Results(int size) {
		data = new int[size];
	}
	
	public void setData(int position, int value){
		data[position] = value;
	}
	
	public int[] getData(){
		return data;
	}

}
