import java.io.*;
import java.util.*;

public class Goods implements Serializable {
	
    private String name;
    private int price;
    private int sellcount;
    private int buycount;
    private boolean isSoldout;
    private ArrayList<Buyer> buyers;
    
    
    //goods 생성자
    public Goods() {
        this.buycount = 0;
        this.buyers = new ArrayList<>();
        this.isSoldout = false;
    }
    
	//getter 메소드
	public String getName(){
		return this.name;
	}
	public int getBuycount(){
		return this.buycount;
	}
	public int getSellcount(){
		return this.sellcount;
	}
	public int getPrice(){
		return this.price;
	}
	
	public boolean isSoldout(){
		return this.isSoldout;
	}
	public ArrayList<Buyer> getBuyers() {
		return this.buyers;
	}
	
	//setter 메소드
	public void setName(String name){
		this.name = name;
	}
	public void setisSoldout(boolean b){
		this.isSoldout = b;
	}
	
	public void setPrice(int price){
		this.price = price;
	}
	public void setSellCount(int Sellcount){
			this.sellcount = Sellcount;
	}
	public void setBuyCount(int buycount){
		this.buycount = buycount;
}

	//구독 메소드
	//buyer가 구독을 하면 buycount가 +1이된다.
	//구독이 이뤄지면 goods의 buyer 목록에 buyer가 추가
	public void Subscribe(Buyer b) {
	    if (!(buyers.contains(b))) { // 중복 구독 확인
	    	if(buycount < sellcount) {
	        buyers.add(b);
	        this.buycount++;                	        	
	        }
	    } 
	    
	}

	
	//buyer가 구독을 취소하면 buycount가 +1이된다.
	//구독이 취소되면 goods의 buyer 목록에 buyer가 제거
	public void Unsubscribe(Buyer b) {
        if (buyers.contains(b)) {
            buyers.remove(b);
            buycount--;
           
        } 
    }
	

}    

		
	
