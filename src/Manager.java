import java.io.*;
import java.util.*;


public class Manager implements filerw{
	
	private static Manager instance = new Manager();
	private UserList allUsers;
	private GoodsList allGoods;
	private GoodsList forSaleGoods;
	private GoodsList SoldOutGoods;
	
	private Manager() {
		allUsers = new UserList();
		forSaleGoods = new GoodsList();
        SoldOutGoods = new GoodsList();
        allGoods = new GoodsList();
	}
	
	public static Manager getInstance() {
		return instance;
	}
	
	public void join(Object o) {
		fileWrite(o);
		
	}
	
	public User login(String id, char[] pwd) {
	    fileRead();
	    for (User u : allUsers.getAllUsers()) {
	        if (u.getID().equals(id) && Arrays.equals(u.getPWD(), pwd)) {
	            return u; // 로그인 성공
	        }
	    }
	    return null;
	    
	}

	// 계정이 존재하는지 확인하는 메소드
	public boolean userExist(String id) {
	    for (User u : allUsers.getAllUsers()) {
	        if (u.getID().equals(id)) {
	            return true;
	        }
	    }
	    return false;
	}

	
	public boolean idCheck(String id) {
		fileRead();
		for(User u : allUsers.getAllUsers()) {
			if(u.getID().equals(id))
				return false;
		}
		return true;
	
	}
	
	public GoodsList getForSaleGoods() {
	    return forSaleGoods;
	}

	public GoodsList getSoldOutGoods() {
	    return SoldOutGoods;
	}
	
	@Override
	public void fileRead() {
	    try (FileInputStream fis = new FileInputStream("User.ser");
	         ObjectInputStream ois = new ObjectInputStream(fis)) {

	        UserList userList = (UserList) ois.readObject();
	        ArrayList<User> users = userList.getAllUsers();

	        for (User u : users) {
	            if (!allUsers.contains(u)) {
	                allUsers.addUser(u);
	            }
	        }

	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}

	
	
	@Override
	public void fileWrite(Object o) { // 회원가입 후 파일 쓰기
	    fileRead();
	    User newUser = (User) o;
	    boolean userExists = false;

	    for (int i = 0; i < allUsers.getAllUsers().size(); i++) {
	        User existingUser = allUsers.getAllUsers().get(i);
	        if (existingUser.getID().equals(newUser.getID())) {
	            // 동일한 ID를 가진 사용자를 찾았을 때 수정
	            allUsers.getAllUsers().set(i, newUser);
	            userExists = true;
	            break;
	        }
	    }

	    if (!userExists) {
	        // 존재하지 않는 사용자일 경우 추가
	        allUsers.addUser(newUser);
	    }

	    try (FileOutputStream fos = new FileOutputStream("User.ser");
	         ObjectOutputStream oos = new ObjectOutputStream(fos)) {
	        oos.writeObject(allUsers); // 수정된 정보를 파일에 저장
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}



	
	//seller와 goods의 상호 작용
	//seller의 상품 등록
	public Goods Registration(Seller s) {
		
		Goods g = s.GoodsRegister();
	    
	    return g;
	}
	
	 //seller의 등록 취소
	   public void Cancel(Seller s, String name) {
		   GoodsList goodsList = getForSaleGoods(); // forSaleGoods를 가져오는 메소드 호출
		    Iterator<Goods> iterator = goodsList.iterator();
		    while (iterator.hasNext()) {
		        Goods goods = iterator.next();
		        if (goods.getName().equals(name)) {
		            iterator.remove(); // 객체 제거
		        }
		    }
		    gfileDelete(name);
		    s.GoodsCancel(name);
	   }
	   
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	   public void Subscribe(Buyer b, String name) {
		    GoodsList goodsList = getForSaleGoods();
		    ArrayList<Goods> copy = new ArrayList<>(goodsList.getAllGoods()); // 컬렉션 복사

		    for (Goods goods : copy) {
		        if (goods.getName().equals(name)) {
		            if (!(goods.getBuyers().contains(b))) { // 중복 구독 확인
		                goods.Subscribe(b);
		                gfileWrite(goods);
		            } 
		        }
		    }
		}

	//구독 취소 buyer가 구독을 취소하면 goods의 buyer 리스트에서 buyer 삭제, buyer의 goods 리스트에서 goods 삭제
	public void Unsubscribe(Buyer b, String name) {
		GoodsList goodsList = getForSaleGoods();
	    ArrayList<Goods> copy = new ArrayList<>(goodsList.getAllGoods()); // 컬렉션 복사

	    for (Goods goods : copy) {
	        if (goods.getName().equals(name)) {
	            if (goods.getBuyers().contains(b)) { // 중복 구독 확인
	                goods.Unsubscribe(b);
	                gfileWrite(goods);
	            } 
	        }
	    }
	}
	

	public void gfileRead() {
	    try (FileInputStream fis = new FileInputStream("Goods.ser");
	         ObjectInputStream ois = new ObjectInputStream(fis)) {

	        GoodsList goodsList = (GoodsList) ois.readObject();

	        for (Goods g : goodsList.getAllGoods()) {
	            if (g.isSoldout()) {
	                if (SoldOutGoods == null) {
	                    SoldOutGoods = new GoodsList();
	                }
	                if (!SoldOutGoods.contains(g)) { // 중복 체크
	                    SoldOutGoods.addGoods(g);
	                }
	            } else {
	                if (forSaleGoods == null) {
	                    forSaleGoods = new GoodsList();
	                }
	                if (!forSaleGoods.contains(g)) { // 중복 체크
	                    forSaleGoods.addGoods(g);
	                }
	            }
	        }
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}

	    public void gfileWrite(Object o) {
	        Goods newGoods = (Goods) o;

	        if (allGoods == null) {
	            gfileRead(); // allGoods 초기화
	        }

	        for (int i = 0; i < allGoods.size(); i++) {
	            Goods existingGoods = allGoods.get(i);
	            if (existingGoods.getName().equals(newGoods.getName())) {
	                // 동일한 이름을 가진 상품을 찾았을 때 수정
	                allGoods.set(i, newGoods);

	                if (newGoods.isSoldout()) {
	                    SoldOutGoods.set(i, newGoods);
	                } else {
	                    forSaleGoods.set(i, newGoods);
	                }
	                return;
	            }
	        }

	        // 존재하지 않는 상품일 경우 추가
	        allGoods.addGoods(newGoods);

	        if (newGoods.isSoldout()) {
	            SoldOutGoods.addGoods(newGoods);
	        } else {
	            forSaleGoods.addGoods(newGoods);
	        }

	        try (FileOutputStream fos = new FileOutputStream("Goods.ser");
	             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
	            oos.writeObject(allGoods);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

		@Override
		public void gfileDelete(String name) {
			if (forSaleGoods == null) {
		        gfileRead(); // allGoods 초기화
		    }

		    Iterator<Goods> iterator = forSaleGoods.iterator();
		    while (iterator.hasNext()) {
		        Goods existingGoods = iterator.next();
		        if (existingGoods.getName().equals(name)) {
		            iterator.remove(); // 해당 객체 삭제
		            break;
		        }
		    }
		    // 파일에 변경된 리스트 저장
		    try (FileOutputStream fos = new FileOutputStream("Goods.ser");
		         ObjectOutputStream oos = new ObjectOutputStream(fos)) {
		        oos.writeObject(forSaleGoods);
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
			
		}




}