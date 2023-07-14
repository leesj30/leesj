import java.io.Serializable;

public interface filerw extends Serializable{
	void fileRead();
	void fileWrite(Object o);
	void gfileRead();
	void gfileWrite(Object o);
	void gfileDelete(String name);
}