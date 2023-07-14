
import java.io.*;
import java.util.*;

public class Seller extends User implements Serializable{

	private int money; //Seller도 Point가 필요할 것같진 않아서 money로 함
	ArrayList<Goods> goods; // Seller가 등록한 goods
	
	public Seller(String id, char[] pwd) {
		super(id, pwd, 2);
		this.money = 0;
		goods = new ArrayList<Goods>();
	}
	
	//get, set 메소드
	
	public int getMoney(){
		return this.money;
	}
	
	public void setMoney(int money){
		this.money = money;
	}
	
	//상품 생성 메소드
	public Goods GoodsRegister() {
		Goods g = new Goods();
		this.goods.add(g);
		return g;
	}
	
	public ArrayList<Goods> getGoods() {
	    return goods;
	}
	
	//상품 삭제 메소드
	public void GoodsCancel(String name) {
		ArrayList<Goods> goodsList = getGoods(); // Goods 객체를 담고 있는 ArrayList
		goodsList.removeIf(goods -> goods.getName().equals(name)); // 이름을 기준으로 객체 삭제
	
	}
	
	public void Purchase(String goodsName) {
	    Manager manager = Manager.getInstance();
	    GoodsList goodsList = manager.getForSaleGoods();
	    Goods goods = goodsList.getGoodsByName(goodsName);

	    if (goods == null) {
	        System.out.println("Goods not found");
	        return;
	    }

	    if (goods.getBuyers().size() > 0) {
	        Iterator<Buyer> iterator = goods.getBuyers().iterator();
	        while (iterator.hasNext()) {
	            Buyer buyer = iterator.next();
	            if (buyer.getPoint() >= goods.getPrice()) {
	                this.setMoney(this.getMoney() + (goods.getPrice() * goods.getSellcount()));
	                buyer.setPoint(buyer.getPoint() - goods.getPrice());
	                manager.getForSaleGoods().removeGoods(goods);
	                manager.getSoldOutGoods().addGoods(goods);
	                goods.setisSoldout(true);
	                manager.gfileWrite(goods);
	                manager.fileWrite(buyer);
	            } else {
	                iterator.remove();
	                buyer.GoodsRemove(goods);
	                goods.setBuyCount(goods.getBuycount() - 1);
	                manager.gfileWrite(goods);
	                manager.fileWrite(buyer);
	            }
	        }
	    }
	}

	
}
