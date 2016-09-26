import org.h2.tools.Server;

import java.sql.*;

/**
 * Created by favianalopez on 9/26/16.
 */
public class HelloDatabase {

    public static void main(String[] args) throws SQLException {
        Server.createWebServer().start();
        Connection conn = DriverManager.getConnection("jdbc:h2:./main","sa", null);
        Statement stmt = conn.createStatement();

        stmt.execute("CREATE TABLE IF NOT EXISTS players (id IDENTITY, name VARCHAR, is_alive BOOLEAN, score INT, health DOUBLE)");
        stmt.execute("INSERT INTO players VALUES (NULL, 'Alice', true, 0, 100.0)");



        //BAD SQL
        // String name = "', true, 0, 0); DROP TABLE players;--";
       // stmt.execute(String.format("INSERT INTO players VALUES (NULL, '%s', true, 0, 100.0)", name));

        // GOOD
        PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO players VALUES (NULL, ?, ?, ?, ?)");
        stmt2.setString(1, "David");
        stmt2.setBoolean(2, true);
        stmt2.setInt(3, 0);
        stmt2.setDouble(4, 100.0);
        stmt2.execute();

        //READ DATA

        PreparedStatement stmt3 = conn.prepareStatement("SELECT * FROM players");
        ResultSet results = stmt3.executeQuery();
        while (results.next()) {
            String playerName = results.getString("name");
            double health = results.getDouble("health");
            int score = results.getInt("score");
            System.out.printf("%s %s %s\n", playerName, health, score);
        }
    }
  }


