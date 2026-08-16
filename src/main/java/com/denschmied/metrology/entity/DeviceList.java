
package com.denschmied.metrology.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table (name="device_list")
public class DeviceList {
    
@Id
@Column (name="id") 
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column (name="name_of_device")
@NotEmpty(message = "Название прибора обязательно для заполнения")
private String nameOfDevice;

@Column (name="serial_num") 
private String serialNum;

@Column (name="type_of_device")
@NotEmpty(message = "Тип прибора обязателен для заполнения")
private String typeOfDevice;

@Column (name="registr_num")
private String registrNum;

@Column (name="measur_range")
private String measurRange;

@Column (name="accuracy")
private String accuracy;

@Column (name="calibr_date")

@JsonFormat(pattern = "dd MM yyyy")
@NotNull(message = "Дата поверки обязательна для заполнения")
private LocalDate calibrDate;

@Column (name="calibr_interval")
@NotNull(message = "Интервал поверки не может быть пустым")
@Min(value = 1, message = "Интервал поверки должен быть больше 0")
private Integer calibrInterval;

@Column (name="date_of_next_calibr")

@JsonFormat(pattern = "dd MM yyyy")
private LocalDate dateOfnextCalibr;

@Column (name="place")
@NotEmpty(message = "Место установки обязательно для заполнения,если прибор не установлен, пишется Склад")
private String place;

@Column (name="status")
private String status;

    public DeviceList() {
    }

    public DeviceList(String nameOfDevice, String serialNum, String typeOfDevice, String registrNum, String measurRange, String accuracy, LocalDate calibrDate, Integer calibrInterval, LocalDate dateOfnextCalibr, String place, String status) {
        this.nameOfDevice = nameOfDevice;
        this.serialNum = serialNum;
        this.typeOfDevice = typeOfDevice;
        this.registrNum = registrNum;
        this.measurRange = measurRange;
        this.accuracy = accuracy;
        this.calibrDate = calibrDate;
        this.calibrInterval = calibrInterval;
        this.dateOfnextCalibr = dateOfnextCalibr;
        this.place = place;
        this.status = status;
    }

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfDevice() {
        return nameOfDevice;
    }

    public void setNameOfDevice(String nameOfDevice) {
        this.nameOfDevice = nameOfDevice;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getTypeOfDevice() {
        return typeOfDevice;
    }

    public void setTypeOfDevice(String typeOfDevice) {
        this.typeOfDevice = typeOfDevice;
    }

    public String getRegistrNum() {
        return registrNum;
    }

    public void setRegistrNum(String registrNum) {
        this.registrNum = registrNum;
    }

    public String getMeasurRange() {
        return measurRange;
    }

    public void setMeasurRange(String measurRange) {
        this.measurRange = measurRange;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public LocalDate getCalibrDate() {
        return calibrDate;
    }

    public void setCalibrDate(LocalDate calibrDate) {
        this.calibrDate = calibrDate;
    }

    public Integer getCalibrInterval() {
        return calibrInterval;
    }

    public void setCalibrInterval(Integer calibrInterval) {
        this.calibrInterval = calibrInterval;
    }

    
    public LocalDate getDateOfnextCalibr() {
        return dateOfnextCalibr;
    }

    public void setDateOfnextCalibr(LocalDate dateOfnextCalibr) {
        this.dateOfnextCalibr = dateOfnextCalibr;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DeviceList{" + "id=" + id + ", nameOfDevice=" + nameOfDevice + ", serialNum=" + serialNum + ", typeOfDevice=" + typeOfDevice + ", registrNum=" + registrNum + ", measurRange=" + measurRange + ", accuracy=" + accuracy + ", calibrDate=" + calibrDate + ", calibrInterval=" + calibrInterval + ", dateOfnextCalibr=" + dateOfnextCalibr + ", place=" + place + ", status=" + status + '}';
    }

   

    
    
    
    
    
}
