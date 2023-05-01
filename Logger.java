import java.sql.Timestamp;

public class Logger {
    public void log(String message){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "]: " + message);
    }
}
