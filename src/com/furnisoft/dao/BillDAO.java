
package com.furnisoft.dao;

import com.furnisoft.pojos.Bill;
import com.furnisoft.pojos.Message;
import java.util.List;

public interface BillDAO {
    public Message saveBill(Bill bill);
    public List<Bill> getAllBills();
    public Message updateBill(Bill bill);
    public Message deleteBill(Bill bill);
}
