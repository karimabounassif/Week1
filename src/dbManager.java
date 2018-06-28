import java.sql.*;
import java.util.Iterator;
import java.util.List;

public class dbManager {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "e3a545jf";
    private static final String CONN_STRING = "jdbc:mysql://localhost/week1?useSSL=false&useUnicode=true" +
                                              "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public static void insertDB(List<Quote> quotes) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt;
        Iterator<Quote> iterator = quotes.iterator();

        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            stmt = conn.prepareStatement("insert into stocks (symbol, price, volume, stockDate) values (?,?,?,?)");
            while(iterator.hasNext()){
                Quote q = iterator.next();
                stmt.setString(1, q.getSymbol());
                stmt.setDouble(2, q.getPrice());
                stmt.setInt(3, q.getVolume());
                stmt.setTimestamp(4,(new java.sql.Timestamp(q.getDate().getTime())));
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static quoteAgg getAgg(String date, String symbol) throws SQLException {
        Connection conn = null;
        Statement stmt;
        quoteAgg agg = new quoteAgg();
        String year = date.split("-")[0];
        String month = date.split("-")[1];

        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);

            //Retrieves High, Low, and Total Volume on specified date.
            String sql = "select MAX(price), MIN(price), SUM(volume) " +
                         "from stocks where symbol = '" + symbol + "' and " +
                         "datediff (stockDate, '" + date + "') = 0";
            stmt = conn.createStatement();
            ResultSet retrieve = stmt.executeQuery(sql);
            retrieve.next();
            agg.setDate(date);
            agg.setSymbol(symbol);
            agg.setHigh(retrieve.getDouble(1));
            agg.setLow(retrieve.getDouble(2));
            agg.setVolume(retrieve.getInt(3));

            //Retrieves closing price on given date.
            sql = "select price from stocks where symbol = '" + symbol + "' and stockDate = \n" +
                    "(select max(stockDate) from stocks where datediff (stockDate, '" + date + "') = 0 and symbol = '" + symbol + "')";
            retrieve = stmt.executeQuery(sql);
            retrieve.next();
            agg.setClosing(retrieve.getDouble(1));
            agg.displayAgg();

            //Retrieves High, Low, and Total Volume for given month.
            sql = "select MAX(price), MIN(price), SUM(volume) from stocks where stockDate between '"
                    + year + "-" + month + "-01' and " +
                    "'" + year + "-" + String.valueOf(Integer.parseInt(month)+1) + "-01' and symbol = '" + symbol + "'";
            retrieve = stmt.executeQuery(sql);
            retrieve.next();
            agg.setMonthlyHigh(retrieve.getDouble(1));
            agg.setMonthlyLow(retrieve.getDouble(2));
            agg.setMonthlyVolume(retrieve.getInt(3));

            //Retrieves closing price for given month.
            sql = "select price from stocks where symbol = '" + symbol + "' and stockDate = \n" +
                    "(select max(stockDate) from stocks where stockDate between '"
                    + year + "-" + month + "-01' and " + "'" + year + "-" + String.valueOf(Integer.parseInt(month)+1) + "-01'"
                    + " and symbol = '" + symbol + "')";
            retrieve = stmt.executeQuery(sql);
            retrieve.next();
            agg.setMonthlyClosing(retrieve.getDouble(1));
            agg.displayMonthlyAgg();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.close();
        }
        return agg;
    }
}
