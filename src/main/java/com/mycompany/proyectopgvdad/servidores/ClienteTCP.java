/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectopgvdad.servidores;

import com.mycompany.proyectopgvdad.pantallas.Principal;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.SwingUtilities;

/**
 *
 * @author emils
 */
public class ClienteTCP {
    private Socket socket;
    private ObjectInputStream inputStream;
    private Principal principal;

    public ClienteTCP(Principal principal) {
        this.principal = principal;
    }

    public void conectarAServidor(String ipServidor) {
        try {
            socket = new Socket(ipServidor, 8080);
            inputStream = new ObjectInputStream(socket.getInputStream());

            while (true) {
                Datos datos = (Datos) inputStream.readObject();
                enviarDatosAPrincipal(datos);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void enviarDatosAPrincipal(Datos datos) {
        SwingUtilities.invokeLater(() -> {
            principal.actualizarChartCPU(datos.getUsoCPU());
            principal.actualizarChartRAM(datos.getPorcentajeRAM(), datos.getRamTotalEnMB());
            principal.actualizarChartDisco(datos.getUsoDiscoEnGB(), datos.getDiscoTotalEnGB());
        });
    }

    public void desconectar() {
        try {
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
