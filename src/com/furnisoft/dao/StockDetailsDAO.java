
package com.furnisoft.dao;

import com.furnisoft.pojos.Hardwares;
import com.furnisoft.pojos.Message;
import com.furnisoft.pojos.StockDetails;

public interface StockDetailsDAO {
    public Message updateStock(Hardwares hardware,StockDetails stockDetails,String stockAction);
}
