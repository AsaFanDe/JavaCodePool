package thread.concurrency.two;
/** * @author  作者 : 范德胜
  * @date 创建时间：2017年6月18日 下午6:37:56
  * @version 1.0 
  */
public class FileMock {

	private String content[];
	
	private int index;
	
	public FileMock(int size, int length) {
		content = new String[size];
		for(int i = 0; i < size; i++){
			StringBuffer buffer = new StringBuffer();
			for (int j = 0; j < length; j++) {
				int indice = (int) (Math.random() * 255);
				buffer.append((char)indice);
			}
			content[i] = buffer.toString();
		}
		index = 0;
	}
	
	public boolean hasMoreLines(){
		return index < content.length;
	}
	
	public String getLine(){
		if (this.hasMoreLines()) {
			System.out.println("Mock: "+(content.length-index));
			return content[index++];
		}
		return null;
	}
}
