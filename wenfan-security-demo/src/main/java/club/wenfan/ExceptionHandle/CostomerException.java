package club.wenfan.ExceptionHandle;

/**
 * @author:wenfan
 * @description:
 * @data: 2018/12/12 18:04
 */
public class CostomerException extends RuntimeException{

	private int id;
	
	public CostomerException(int id){
       super("my costomer Exception ，id is:"+id);
       this.id=id;
    }
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
}
