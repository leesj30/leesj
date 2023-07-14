import java.io.Serializable;
import java.util.ArrayList;

public class Buyer extends User implements Serializable {
    
    private int point;
    private ArrayList<Goods> subgoods;
    private ArrayList<Goods> purgoods;

    public Buyer(String id, char[] pwd) {
    	super(id, pwd, 1);
        this.point = 0;
        subgoods = new ArrayList<>();
        purgoods = new ArrayList<>();
    }
    
    
    public int getPoint(){
		return this.point;
	}
    
    public void setPoint(int point){
		this.point = point;
	}
	
	//충전 메소드 
	// 포인트는 Charge로 값을 설정할 수 있으므로 set 메소드 구현 안함 
	public void Charge(int money) {
		if(money<10000) {
			System.out.println("충전 포인트 수가 부족합니다. 10000원 이상부터 충전가능합니다.");
		}
		else{
			this.point += money;
		}	
	}
	//Goods 추가
	public void GoodsAdd(Goods g) {
		subgoods.add(g);
	}
	
	//Goods 제거
	public void GoodsRemove(Goods g) {
        if (subgoods.contains(g)) {
            subgoods.remove(g);
            System.out.println(g.getName() + " 상품을 구독 취소하였습니다.");
        } else {
            System.out.println(g.getName() + " 상품을 구독하고 있지 않습니다.");
        }
    }
	
	//buyer의 point가 충분한지 아닌지를 체크 
	public boolean PointCheck(int price) {
		if(this.point > price) {
			return true;
		}
		else{
			System.out.println("포인트가 부족합니다. 충전해주세요");
			return false;
			//후에 포인트를 충전할지 정하기
		}
	}
	//결재가 확정되면 buyer의 포인트가 차감된다.
	public void Purchase(int price) {
		this.point -= price;
	}
	
	
}
