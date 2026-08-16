
package com.denschmied.metrology.controller;

import com.denschmied.metrology.entity.DeviceList;
import com.denschmied.metrology.service.DeviceListService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping ("/user")
public class DeviceController {
    @Autowired
    private DeviceListService deviceService;
    //запуск стартовой страницы
    @GetMapping ("/start")
    public String showStartView (){
    return "start-view";
    }
   // Форма для добавления СИ
  @GetMapping ("/add") 
  public String showAddView (Model model){
    model.addAttribute("device", new DeviceList ());
    return "device-add-view";
  }
  // Отправка данных для добавления СИ
  @PostMapping ("/add")
  public String addDevice ( @Valid @ModelAttribute ("device")DeviceList device, BindingResult bindingResult,Model model){
      if (bindingResult.hasErrors()) {
            return "device-add-view"; 
        }
      if (device.getCalibrDate() != null && device.getCalibrInterval() != null) {
        LocalDate nextCalibrDate = device.getCalibrDate().plusMonths(device.getCalibrInterval());
        device.setDateOfnextCalibr(nextCalibrDate);
    }
      deviceService.saveDevice(device);
      return "device-add-view";
  }
 // Получение всех СИ по типу
  @GetMapping ("/findAll/Type")
  public String showAllDeviceType (@PageableDefault(size=5) Pageable pageable, Model model){
  Pageable sortedPage = PageRequest.of(pageable.getPageNumber(),
       pageable.getPageSize(),Sort.by("typeOfDevice").ascending());    
  Page <DeviceList> devices=deviceService.getAllDevice(sortedPage);
    model.addAttribute("devices", devices);
    return "all-device-view";
   }
  
  // Получение всех СИ по дате
  @GetMapping ("/findAll/Date")
  public String showAllDeviceDate (@PageableDefault(size=5) Pageable pageable, Model model){
  Pageable sortedPage = PageRequest.of(pageable.getPageNumber(),
       pageable.getPageSize(),Sort.by("dateOfnextCalibr").ascending()); 
  Page <DeviceList> devices=deviceService.getAllDevice(sortedPage);
    model.addAttribute("devices", devices);
    return "all-deviceDate-view";
  }
 // Удаление СИ
 @PostMapping ("/delete/{id}") 
 public String deleteDeviceById (@PathVariable  Long id){
  deviceService.deleteDevice(id);
  return "redirect:/user/findAll/Type";
 }
 //Редактирование СИ
 @GetMapping("/edit/{id}")
 public String showUpdateDevice(@PathVariable Long id, Model model) {
        DeviceList device = deviceService.getDeviceById(id);
        model.addAttribute("device", device);
        return "device-update-view"; 
    }
  @PostMapping("/update/{id}") 
  public String updateDevice (@Valid @PathVariable Long id, @ModelAttribute ("device") DeviceList device,BindingResult bindingResult){
      device.setId(id);
      if (bindingResult.hasErrors()) {
            return "device-update-view"; 
        }
      if (device.getCalibrDate()!=null&&device.getCalibrInterval()!=null){
      LocalDate nextDate = device.getCalibrDate().plusMonths(device.getCalibrInterval());
      device.setDateOfnextCalibr(nextDate);
      }
      deviceService.saveDevice(device);
      return "redirect:/user/findAll/Type";
  }
      
 //  Форма для поиска прибора
        @GetMapping("/find")
    public String showFindView() {
        return "view-finddevice-by";  
} 
    // Получение искомого прибора по названию
       @GetMapping("/findedN")
    public String showFindedDeviceN(@RequestParam(required = false) String nameOfDevice,@PageableDefault(size=5) Pageable pageable, Model model) {
     if (nameOfDevice != null && !nameOfDevice.trim().isEmpty()) {
            Page<DeviceList> devices = deviceService.findByNameOfDevice(
                nameOfDevice.trim(), pageable);
            model.addAttribute("devices", devices);
            model.addAttribute("searchName", nameOfDevice.trim());
        }
        
        return "view-findeddevice";
    }
    // Получение искомого прибора по типу
       @GetMapping("/findedT")
    public String showFindedDeviceT(@RequestParam(required = false) String typeOfDevice,@PageableDefault(size=5) Pageable pageable, Model model) {
     if (typeOfDevice != null && !typeOfDevice.trim().isEmpty()) {
            Page<DeviceList> devices = deviceService.findByTypeOfDevice(
                typeOfDevice.trim(), pageable);
            model.addAttribute("devices", devices);
            model.addAttribute("searchType", typeOfDevice.trim());
}
     return "view-findeddeviceT";
    }
    // Получение искомого прибора по месту
       @GetMapping("/findedP")
    public String showFindedDeviceP(@RequestParam(required = false) String place,@PageableDefault(size=5) Pageable pageable, Model model) {
            if (place != null && !place.trim().isEmpty()) {
            Page<DeviceList> devices = deviceService.findByPlace(
                place.trim(), pageable);
            model.addAttribute("devices", devices);
            model.addAttribute("searchPlace", place.trim());
}
        return "view-findeddeviceP";
}
    // Получение искомого прибора по статусу
       @GetMapping("/findedS")
    public String showFindedDeviceS(@RequestParam(required = false) String status,@PageableDefault(size=5) Pageable pageable, Model model) {
            if (status != null && !status.trim().isEmpty()) {
            Page<DeviceList> devices = deviceService.findByStatus(
                status.trim(), pageable);
            model.addAttribute("devices", devices);
            model.addAttribute("searchStatus", status.trim());
}
        return "view-findeddeviceS";
}
    // получение формы для ввода даты задания
    @GetMapping("/devices/dateEnter")
    public String showDateForm(Model model) {
        model.addAttribute("definiteDate", LocalDate.now());
        return "enter-date-view"; 
    }
    }