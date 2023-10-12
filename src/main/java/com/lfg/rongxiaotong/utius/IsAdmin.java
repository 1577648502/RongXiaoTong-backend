package com.lfg.rongxiaotong.utius;


import com.lfg.rongxiaotong.domain.User;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;

@Data
public class IsAdmin {
public static String isAdmin(HttpServletRequest request){
    User session = (User) request.getSession().getAttribute("data");
    if (session != null) {
        if (session.getRole().equals("admin")) {
                return "admin";
        } else {
            return "user";
        }
    }
    return "未登录";
}

}
