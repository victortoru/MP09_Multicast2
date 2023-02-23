
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SrvVelocitats {
    /* Servidor Multicast que ens comunica la velocitat que porta d'un objecte */

    MulticastSocket socket;
    InetAddress multicastIP;
    int port;
    boolean continueRunning = true;

    String[] lista;


    public SrvVelocitats(int portValue, String strIp) throws IOException {
        socket = new MulticastSocket(portValue);
        multicastIP = InetAddress.getByName(strIp);
        port = portValue;
        lista = new String[]{"la mama de la mama de la mama de la mama de la mama de la mama",
                             "La clau d’accés de cada usuari no s’ha de donar a conèixer a ningú més",
                             "L’accés a Internet haurà de limitar-se als objectius acadèmics fixats pel departament",
                             " Per tal de garantir el respecte d’aquestes normes, els professors es reserven el dret d’auditar qualsevol treball"
        };
    }

    public void runServer() throws IOException{
        DatagramPacket packet;
        byte [] sendingData;

        while(continueRunning){
            int aleatorio = new Random().nextInt(lista.length);
            String fraseElegida = lista[aleatorio];
            byte [] enviarFrase = fraseElegida.getBytes();
            System.out.println(fraseElegida);
            sendingData = ByteBuffer.allocate(enviarFrase.length).put(enviarFrase).array();
            packet = new DatagramPacket(sendingData, sendingData.length,multicastIP, port);
            socket.send(packet);

            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.getMessage();
            }


        }
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        //Canvieu la X.X per un número per formar un IP.
        //Que no sigui la mateixa que la d'un altre company
        SrvVelocitats srvVel = new SrvVelocitats(5557, "224.0.11.111");
        srvVel.runServer();
        System.out.println("Parat!");

    }

}
