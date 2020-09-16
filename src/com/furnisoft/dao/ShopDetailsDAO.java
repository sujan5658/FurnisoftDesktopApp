
package com.furnisoft.dao;

import com.furnisoft.pojos.Message;
import com.furnisoft.pojos.ShopDetails;

public interface ShopDetailsDAO {
    public ShopDetails getShopDetails();
    public Message updateShop(ShopDetails shopDetails);
}
