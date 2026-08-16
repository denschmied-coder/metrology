
package com.denschmied.metrology.service;

import com.denschmied.metrology.dao.DeviceListRepository;
import com.denschmied.metrology.entity.DeviceList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl implements DeviceListService {
    @Autowired
    private DeviceListRepository deviceListRepository;
    @Override
    public Page<DeviceList> getAllDevice(Pageable pageable) {
    return deviceListRepository.findAll(pageable);
    }

    @Override
    public DeviceList getDeviceById(Long id) {
       DeviceList device = null; 
     Optional <DeviceList> optional=deviceListRepository.findById(id);
     device=optional.get();
     return device;
    }

    @Override
    public void saveDevice(DeviceList device) {
    deviceListRepository.save(device);
    }

    @Override
    public void deleteDevice(Long id) {
    deviceListRepository.deleteById(id);
    }

    @Override
public Page<DeviceList> findByNameOfDevice(String substring,Pageable pageable) {
    //System.out.println("=== service ===");
    //System.out.println("get prefix: '" + prefix + "'");
    //System.out.println("length string: " + (prefix != null ? prefix.length() : "null"));
    
    Page <DeviceList> result = deviceListRepository.findByNameOfDeviceContainingIgnoreCase(substring,pageable);
    //System.out.println("result from bd: " + (result != null ? result.size() : "null"));
    return result;
}

   @Override
    public Page <DeviceList> findByTypeOfDevice(String substring,Pageable pageable) {
    Page <DeviceList> result =deviceListRepository.findByTypeOfDeviceContainingIgnoreCase(substring,pageable);
    return result;
    } 

    @Override
    public Page<DeviceList> findByPlace(String substring, Pageable pageable) {
    Page <DeviceList> result =deviceListRepository.findByPlaceContainingIgnoreCase(substring,pageable);
    return result;    
    }

    @Override
    public List<DeviceList> getDevices() {
    return deviceListRepository.findAll();
    }

    @Override
    public Page<DeviceList> findByStatus(String substring, Pageable pageable) {
    Page <DeviceList> result =deviceListRepository.findByStatusContainingIgnoreCase(substring, pageable);
    return result;
    }
    
}
