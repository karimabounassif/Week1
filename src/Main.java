import java.io.File;
import java.io.IOException;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {

        dbManager.insertDB(Download.download(new File("week1-stocks.json")));
        quoteAgg agg = dbManager.getAgg("2018-06-22", "MSFT");
        System.out.println("\nDone");

    }
}
