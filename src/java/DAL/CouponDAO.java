package DAL;

import model.Coupon;
import java.sql.*;

public class CouponDAO extends DBcontext {
    public Coupon getCouponByCode(String code) {
        String sql = "SELECT * FROM Coupons WHERE code = ? AND expiration_date >= GETDATE() AND usage_limit > 0";
        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, code);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Coupon(
                    rs.getInt("coupon_id"),
                    rs.getString("code"),
                    rs.getInt("discount_percentage"),
                    rs.getInt("max_discount_amount"),
                    rs.getDate("expiration_date"),
                    rs.getInt("usage_limit"),
                    rs.getBoolean("applicable_to_all_categories"),
                    rs.getInt("created_by")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateCouponUsage(Coupon coupon) {
      String sql = "UPDATE Coupons SET usage_limit = ? WHERE coupon_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, coupon.getUsageLimit());
              st.setInt(2, coupon.getCouponId());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Coupon getCouponById(int couponId) {
    String sql = "SELECT * FROM Coupons WHERE coupon_id = ?";
    try (Connection conn = getConnection();
         PreparedStatement st = conn.prepareStatement(sql)) {
        st.setInt(1, couponId);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return new Coupon(
                rs.getInt("coupon_id"),
                rs.getString("code"),
                rs.getInt("discount_percentage"),
                rs.getInt("max_discount_amount"),
                rs.getDate("expiration_date"),
                rs.getInt("usage_limit"),
                rs.getBoolean("applicable_to_all_categories"),
                rs.getInt("created_by")
            );
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
}