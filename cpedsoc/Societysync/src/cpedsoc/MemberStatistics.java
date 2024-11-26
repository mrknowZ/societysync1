package cpedsoc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberStatistics {

    public static int getTotalMembers() {
        return getCount("SELECT COUNT(*) FROM members");
    }

    public static int getCountByPosition(String position) {
        return getCount("SELECT COUNT(*) FROM members WHERE position = ?", position);
    }

    private static int getCount(String query, String... params) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            if (params.length > 0) {
                stmt.setString(1, params[0]);
            }
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
