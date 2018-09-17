package dataaccess;

/**
 * Created by Seong-EunCho on 2/28/17.
 */

public class DatabaseException extends Exception {

    public DatabaseException(String s){
        super(s);
    }

    public DatabaseException(String s, Throwable throwable){
        super(s, throwable);
    }
}
