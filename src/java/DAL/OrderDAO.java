package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Account;
import model.Cart;
import java.sql.ResultSet;
import model.Item;
import model.Product;

/**
 *
 * @author ngoch
 */
public class OrderDAO extends DBcontext {

    public int addOrder(Account a, Cart cart, String note) {
    int orderId = -1; 
    try {
        Connection conn = DBcontext.getConnection();
        String sql = "INSERT INTO Orders (account_id, coupon_id, fullname, email, phone_number, address, order_date, status, total_money, note) "
                   + "VALUES (?, ?, ?, ?, ?, ?, GETDATE(), ?, ?, ?)";
        PreparedStatement st = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        st.setInt(1, a.getAccount_id());
        st.setObject(2, cart.getAppliedCoupon() != null ? cart.getAppliedCoupon().getCouponId() : null);
        st.setString(3, a.getFullname());
        st.setString(4, a.getEmail());
        st.setString(5, a.getPhone_number());
        st.setString(6, a.getAddress());
        st.setInt(7, 0); 
        st.setDouble(8, cart.getTotalMoney());
        st.setString(9, note); 
        st.executeUpdate();

        
        try (ResultSet rs = st.getGeneratedKeys()) {
            if (rs.next()) {
                orderId = rs.getInt(1); 
            }
        }

       
        if (orderId != -1) {
            for (Item i : cart.getItems()) {
                String sql2 = "INSERT INTO Orders_Details (order_id, product_id, price, amount) VALUES (?, ?, ?, ?)";
                PreparedStatement st2 = conn.prepareStatement(sql2);
                st2.setInt(1, orderId);
                st2.setInt(2, i.getProduct().getProductId());
                st2.setInt(3, i.getDiscountPrice());
                st2.setInt(4, i.getQuantity());
                st2.executeUpdate();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return orderId; 
}



     
}



