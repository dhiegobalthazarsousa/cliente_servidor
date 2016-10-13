package servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import view.cliente.FrameCliente;

public class Socket_Servidor implements Runnable {
    
    private int porta;
    private String ip;
    
    public Socket_Servidor(int porta, String ip) {
        this.porta = porta;
        this.ip = ip;
    }
    
    @Override
    public void run() {
        try {
            ServerSocket servidor = new ServerSocket(porta);
            
            FrameCliente.labelRespostaServidor.setText("Porta" + " " + porta + " " + "aberta");
            
            System.out.println("Porta " + porta + " aberta!");
            
            Socket cliente = servidor.accept();
            
            System.out.println("Nova conex�o com o cliente " + cliente.getInetAddress().getHostAddress());
            
            Scanner entrada = new Scanner(cliente.getInputStream());
            
            while (entrada.hasNextLine()) {
                String mensagem = entrada.nextLine();
                System.out.println("O cliente digitou: " + mensagem);
                FrameCliente.textoRecebimento.append(ip + ": " + mensagem + "\n");
            }
            
            entrada.close();
            servidor.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            
            FrameCliente.labelRespostaServidor.setText("Deu ruim, burro!");
            System.out.println("Impossivel ligar o servidor! Burro!");
        }
        
    }
}
