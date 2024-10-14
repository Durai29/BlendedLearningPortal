package JavaGuiSwing.ClientSocket;

import javax.swing.SwingUtilities;

public class Launcher {
    public static void main(String arg[]){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                ClientSender cs = new ClientSender();
            }
        });
    }
}
