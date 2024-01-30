import java.net.*;
import java.io.*;

public class ClienteUDP {
    public static void main(String[] args) {
        final String servidorIP = "localhost";
        final int puerto = 12345;

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress servidorDireccion = InetAddress.getByName(servidorIP);

            int numero = 5; // Metemos el número solicitado en el ejercicio
            String mensaje = Integer.toString(numero);
            byte[] sendData = mensaje.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, servidorDireccion, puerto);

            socket.send(sendPacket); // Envía el número al servidor

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            socket.receive(receivePacket); // Espera a recibir la respuesta del servidor

            String respuesta = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Respuesta del servidor: " + respuesta);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
