package dao;

import bean.Users;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao extends BaseDao {
    public int Validate(String account, String password, HttpServletRequest request,String type) {
        String sql = "select * from users where Account = '" + account + "' and Password = '" + password + "' and Type = '" + type + "'";
        System.out.println(sql);
        ResultSet resultSet = query(sql);
        try {
            if(resultSet.next()){
                request.getSession().setAttribute("KeyNO",resultSet.getInt("UserNO"));
                return 1;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }
}
