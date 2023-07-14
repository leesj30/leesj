import java.io.*;
import java.util.*;

//직렬화때 에러가 너무 많이 생겨서 직렬화를 위한 클래스
public class GoodsList implements Serializable, Iterable<Goods>   {
    private ArrayList<Goods> goodsList;
    
    public GoodsList() {
        goodsList = new ArrayList<>();
    }

    public ArrayList<Goods> getAllGoods() {
        return goodsList;
    }

    public void addGoods(Goods goods) {
        goodsList.add(goods);
    }
    
    public boolean contains(Goods goods) {
        return goodsList.contains(goods);
    }
    
    public void set(int index, Goods goods) {
    	goodsList.set(index, goods);
    }
    
    public Goods get(int index) {
        return goodsList.get(index);
    }
    
    public int size() {
        return goodsList.size();
    }
    
    @Override
    public Iterator<Goods> iterator() {
        return goodsList.iterator();
    }
    
    public Goods getGoodsByName(String name) {
        for (Goods goods : goodsList) {
            if (goods.getName().equals(name)) {
                return goods;
            }
        }
        return null; // 해당 이름의 상품이 없을 경우 null 반환
    }

    public void removeGoods(Goods goods) {
        goodsList.remove(goods);
    }
    
}