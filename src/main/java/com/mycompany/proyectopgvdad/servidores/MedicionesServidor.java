/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectopgvdad.servidores;

/**
 *
 * @author emils
 */
import com.sun.management.OperatingSystemMXBean;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

import java.io.File;
import java.lang.management.ManagementFactory;
import oshi.hardware.GlobalMemory;

public class MedicionesServidor {

    private final SystemInfo systemInfo;
    
    public MedicionesServidor() {
        this.systemInfo = new SystemInfo();
    }

    public double obtenerRamMaximaEnGB() {
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        long totalBytes = hardware.getMemory().getTotal();
        return totalBytes / (1024.0 * 1024.0 * 1024.0); 
    }

    public double obtenerRamUsadaEnGB() {
    HardwareAbstractionLayer hardware = systemInfo.getHardware();
    long totalRAM = hardware.getMemory().getTotal();
    long RAMsinUso = hardware.getMemory().getAvailable();
    double ramUsadaEnBytes = totalRAM - RAMsinUso;
    return ramUsadaEnBytes / (1024.0 * 1024.0 * 1024.0);
}
     public long obtenerEspacioDiscoUsadoEnGB() {
        File cDrive = new File("C:");
        long usedSpace = cDrive.getTotalSpace() - cDrive.getFreeSpace();
        return usedSpace / (1024 * 1024 * 1024); 
    }
    public double obtenerEspacioDiscoTotalEnGB() {
        File cDrive = new File("C:");
        long totalSpace = cDrive.getTotalSpace();
        return totalSpace / (1024.0 * 1024.0 * 1024.0);
    }

    public double obtenerEspacioDiscoDisponibleEnGB() {
        File cDrive = new File("C:");
        long freeSpace = cDrive.getFreeSpace();
        return freeSpace / (1024.0 * 1024.0 * 1024.0);
    }

   public double obtenerCargaPromedioCPU() {
    OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    return operatingSystemMXBean.getSystemCpuLoad() * 100; 
}

    public double obtenerMaxCPU() {
        return 100.0;
    }
}
