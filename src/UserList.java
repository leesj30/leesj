import java.io.Serializable;
import java.util.ArrayList;

//직렬화때 에러가 너무 많이 생겨서 직렬화를 위한 클래스
public class UserList implements Serializable {
    private ArrayList<User> userList;
    

    public UserList() {
        userList = new ArrayList<>();
    }

    public ArrayList<User> getAllUsers() {
        return userList;
    }

    public void addUser(User user) {
        userList.add(user);
    }
    
    public boolean contains(User user) {
        return userList.contains(user);
    }
    
    public void set(int index, User user) {
        userList.set(index, user);
    }
    
    public User get(int index) {
        return userList.get(index);
    }
    
    public int size() {
        return userList.size();
    }
}
