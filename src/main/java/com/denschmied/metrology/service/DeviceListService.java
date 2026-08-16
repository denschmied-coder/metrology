
package com.denschmied.metrology.service;

import com.denschmied.metrology.entity.DeviceList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeviceListService {
public Page <DeviceList> getAllDevice (Pageable pageable); 
public DeviceList getDeviceById (Long id);
public void saveDevice (DeviceList device);
public void deleteDevice (Long id);

public Page <DeviceList> findByNameOfDevice (String substring, Pageable pageable);
public Page <DeviceList> findByTypeOfDevice (String substring, Pageable pageable);
public Page <DeviceList> findByPlace (String substring, Pageable pageable);    
public Page <DeviceList> findByStatus (String substring, Pageable pageable); 
public List <DeviceList> getDevices();
}
