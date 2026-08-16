
package com.denschmied.metrology.dao;

import com.denschmied.metrology.entity.DeviceList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DeviceListRepository extends JpaRepository <DeviceList, Long>{
Page<DeviceList> findByNameOfDeviceContainingIgnoreCase(String substring, Pageable pagable); 
Page<DeviceList> findByTypeOfDeviceContainingIgnoreCase(String substring, Pageable pagable);
Page<DeviceList> findByPlaceContainingIgnoreCase(String substring, Pageable pagable);
Page<DeviceList> findByStatusContainingIgnoreCase(String substring, Pageable pagable);
}
