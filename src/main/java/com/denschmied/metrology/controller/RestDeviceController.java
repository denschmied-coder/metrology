
package com.denschmied.metrology.controller;

import com.denschmied.metrology.entity.DeviceList;
import com.denschmied.metrology.service.DeviceListService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping ("/api")
public class RestDeviceController {

    @Autowired
    private DeviceListService deviceService;
    //Получение данных с сортировкой по типам средств измерений
    @GetMapping("/devices/sortType")
    public Page<DeviceList> getAllDevicesType(@PageableDefault(size = 10) Pageable pageable) {
    Pageable sortedPage = PageRequest.of(pageable.getPageNumber(),
        pageable.getPageSize(),Sort.by("typeOfDevice").ascending()
    );
    return deviceService.getAllDevice(sortedPage);
}
    //Получение данных с сортировкой по датам следующей поверки
    @GetMapping ("/devices/sortDate")
    public Page<DeviceList> getAllDevicesDate(@PageableDefault(size = 10) Pageable pageable) {
    Pageable sortedPage = PageRequest.of(pageable.getPageNumber(),
        pageable.getPageSize(),Sort.by("dateOfnextCalibr").ascending()
    );
    return deviceService.getAllDevice(sortedPage);
}
    //Получение данных по идентиф. номеру
    @GetMapping ("/devices/{id}")
    public DeviceList getDeviceById (@PathVariable ("id") Long id){
   DeviceList device=deviceService.getDeviceById(id);
    return device;
    }
    // Внесение нового средства измерений
    @PostMapping ("/devices")
    public DeviceList addNewDeviceList( @RequestBody DeviceList device){
    deviceService.saveDevice(device);
    System.out.println("Saved! ID: " + device.getId());
    return device;
    }
    // Корректировка средства измерений
    @PutMapping ("/devices/{id}")
    public DeviceList upDateDevice ( @RequestBody DeviceList device,@PathVariable ("id") Long id){
    device.getId();
    deviceService.saveDevice(device);
    return device;
    }
    // Удаление по идентиф. номеру
    @DeleteMapping ("/devices/{id}")
    public String deleteBookById (@PathVariable ("id") Long id){
    deviceService.deleteDevice(id);
    return "Device with ID "+id+" was deleted";
    }
    //получение объектов с приближающейся  датой калибровки и отправка в текстовый файл
    @GetMapping("/devices/date")
    public ResponseEntity<Resource> getDateDevices(@RequestParam("definiteDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate definiteDate) {
    
    List<DeviceList> list = deviceService.getDevices();
    
    List<String> data = list.stream()
        .filter(element -> definiteDate.equals(element.getDateOfnextCalibr().minusDays(7)))
        .map(element -> String.format("Название прибора: %s, Серийный номер: %s, Дата следующей поверки: %s, Место установки: %s",
            element.getNameOfDevice(),
            element.getSerialNum(),
            element.getDateOfnextCalibr(),
            element.getPlace()))
        .collect(Collectors.toList());
    //отправка отфильтрованных приборов в текстовый файл
   try {
            Path tempFile = Files.createTempFile("devices_", ".txt");
            Files.write(tempFile, data, StandardCharsets.UTF_8);
            
            Resource resource =  new FileSystemResource(tempFile.toFile());
            
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, 
                    "attachment; filename=\"devices_" + 
                    LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ".txt\"")
                .contentType(MediaType.TEXT_PLAIN)
                .body(resource);
                
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при создании файла", e);
        }
    }
}
    
 
   
    

