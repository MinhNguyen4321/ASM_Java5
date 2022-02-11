package com.eshop.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    @Autowired
    HttpSession session;

    /**
     * Đọc giá trị của attribute trong session
     * 
     * @param name tên attribute
     * @return giá trị đọc được hoặc null nếu không tồn tại
     */
    public Object getAttribute(String name) {
        return session.getAttribute(name);
    }

    /**
     * Thay đổi hoặc tạo mới attribute trong session
     * 
     * @param name  tên attribute
     * @param value giá trị attribute
     */
    public void setAttribute(String name, Object value) {
        session.setAttribute(name, value);
    }

    /**
     * Xóa attribute trong session
     * 
     * @param name tên attribute cần xóa
     */
    public void removeAttribute(String name) {
        session.removeAttribute(name);
    }
}
