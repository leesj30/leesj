import java.io.*;

public class User implements Serializable{
	private String id;
    private char[] pwd;
    private int key;
    
    public User(String id, char[] pwd, int key){
    	this.id = id;
        this.pwd = pwd;
        this.key = key;
    }
    
    public String getID(){
		return this.id;
	}
    
	public char[] getPWD(){
		return this.pwd;
	}
	
	public int getKEY(){
		return this.key;
	}
	
	public void setID(String id){
		this.id = id;
	}
	public void setPWD(char[] pwd){
		this.pwd = pwd;
	}
	
}
