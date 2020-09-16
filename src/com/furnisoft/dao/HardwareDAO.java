
package com.furnisoft.dao;

import com.furnisoft.pojos.Hardwares;
import com.furnisoft.pojos.Message;
import java.util.List;

public interface HardwareDAO {
    public Message registerHardware(Hardwares hardware);
    public List<Hardwares> getHardwares();
    public Message updateHardware(Hardwares hardware);
    public void updateHardwares(String oldCategory,String newCategory);
    public Message deleteHardware(Hardwares hardware);
}
