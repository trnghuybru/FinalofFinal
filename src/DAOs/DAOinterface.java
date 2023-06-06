package DAOs;

//public class DAOinterface {

import java.util.ArrayList;

/**
 *
 * @authordung 
 */
public interface DAOinterface<T> {
	public int insert(T t);

	public int update(T t);

	public int delete(T t);

	public ArrayList<T> selectAll();

	public T selectById(String st);

	public ArrayList<T> selectByCondition(String condition);
}
