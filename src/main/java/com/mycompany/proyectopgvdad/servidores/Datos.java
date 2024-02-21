/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectopgvdad.servidores;

/**
 *
 * @author emils
 */
import java.io.Serializable;

public class Datos implements Serializable {
    private double usoCPU;
    private double porcentajeRAM;
    private double ramTotalEnMB;
    private double usoDiscoEnGB;
    private double discoTotalEnGB;

    public Datos(double usoCPU, double porcentajeRAM, double ramTotalEnMB, double usoDiscoEnGB, double discoTotalEnGB) {
        this.usoCPU = usoCPU;
        this.porcentajeRAM = porcentajeRAM;
        this.ramTotalEnMB = ramTotalEnMB;
        this.usoDiscoEnGB = usoDiscoEnGB;
        this.discoTotalEnGB = discoTotalEnGB;
    }

    public double getUsoCPU() {
        return usoCPU;
    }

    public void setUsoCPU(double usoCPU) {
        this.usoCPU = usoCPU;
    }

    public double getPorcentajeRAM() {
        return porcentajeRAM;
    }

    public void setPorcentajeRAM(double porcentajeRAM) {
        this.porcentajeRAM = porcentajeRAM;
    }

    public double getRamTotalEnMB() {
        return ramTotalEnMB;
    }

    public void setRamTotalEnMB(double ramTotalEnMB) {
        this.ramTotalEnMB = ramTotalEnMB;
    }

    public double getUsoDiscoEnGB() {
        return usoDiscoEnGB;
    }

    public void setUsoDiscoEnGB(double usoDiscoEnGB) {
        this.usoDiscoEnGB = usoDiscoEnGB;
    }

    public double getDiscoTotalEnGB() {
        return discoTotalEnGB;
    }

    public void setDiscoTotalEnGB(double discoTotalEnGB) {
        this.discoTotalEnGB = discoTotalEnGB;
    }
}