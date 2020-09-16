
package com.furnisoft.dao;

import com.furnisoft.pojos.Admin;
import com.furnisoft.pojos.Message;

public interface AdminDAO {
    public Message loginAdmin();
    public Message changeCredentials(Admin oldAdmin,Admin newAdmin);
}
