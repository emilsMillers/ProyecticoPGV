/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectopgvdad.servidores;

/**
 *
 * @author emils
 */
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServidorTCP {

    private static final int PUERTO_INICIAL = 8080;
    private static int contadorServidores = 0;
    private static final MedicionesServidor medicionesServidor = new MedicionesServidor();
    private static final Map<String, ServerSocket> mapaServidoresSockets = new HashMap<>();
    private static final Map<String, Socket> mapaClientesSockets = new HashMap<>();
    private final Thread serverThread;

    public ServidorTCP() {
        this.serverThread = new Thread(() -> {
            contadorServidores++;
            int puerto = PUERTO_INICIAL;
            try {
                ServerSocket serverSocket = new ServerSocket(puerto);
                mapaServidoresSockets.put("Servidor " + contadorServidores, serverSocket);
                System.out.println("Servidor TCP iniciado en el puerto " + puerto);

                while (!Thread.interrupted()) {
                    Socket clientSocket = serverSocket.accept();
                    mapaClientesSockets.put("Cliente " + mapaClientesSockets.size(), clientSocket);

                    enviarDatos(clientSocket);

                    clientSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "Servidor-" + contadorServidores);
    }

    public void iniciarServidor(String direccionIP) {
        serverThread.start();
    }

    private void enviarDatos(Socket clientSocket) {
        try {
            double usoCPU = medicionesServidor.obtenerCargaPromedioCPU();
            double porcentajeRAM = medicionesServidor.obtenerRamUsadaEnGB();
            double ramTotalEnMB = medicionesServidor.obtenerRamMaximaEnGB();
            double usoDiscoEnGB = medicionesServidor.obtenerEspacioDiscoUsadoEnGB();
            double discoTotalEnGB = medicionesServidor.obtenerEspacioDiscoDisponibleEnGB();

            Datos datos = new Datos(usoCPU, porcentajeRAM, ramTotalEnMB, usoDiscoEnGB, discoTotalEnGB);
            ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            outputStream.writeObject(datos);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void detenerServidor() {
        serverThread.interrupt();
        try {
            for (Socket clientSocket : mapaClientesSockets.values()) {
                clientSocket.close();
            }
            for (ServerSocket serverSocket : mapaServidoresSockets.values()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
