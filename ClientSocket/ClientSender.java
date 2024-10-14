package JavaGuiSwing.ClientSocket;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ClientSender {
    final File fileToSend[] = new File[1];
    private JFrame jFrame;

    public ClientSender(){
        initialize();
    }

    protected void initialize(){
        jFrame = new JFrame("Client File sender.");
        jFrame.setSize(600,400);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));

        JLabel jTitle = new JLabel("Choose a file to send.");
        jTitle.setFont(new Font("Arial",Font.BOLD,25));
        jTitle.setBorder(new EmptyBorder(70,0,5,0));
        jTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel jFileName = new JLabel(" ");
        jFileName.setFont(new Font("Arial",Font.BOLD,25));
        jFileName.setBorder(new EmptyBorder(5,0,10,0));
        jFileName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel jButtoPanel = new JPanel();
        jButtoPanel.setFont(new Font("Arial",Font.BOLD,15));
        jButtoPanel.setBorder(new EmptyBorder(50,0,0,0));
        
        JButton jUploadButton = new JButton("Upload");
        jUploadButton.setFocusable(false);
        jUploadButton.setPreferredSize(new Dimension(120,45));

        JButton jChooseButton = new JButton("Choose file.");
        jChooseButton.setFocusable(false);
        jChooseButton.setPreferredSize(new Dimension(120,45));

        jButtoPanel.add(jChooseButton);
        jButtoPanel.add(jUploadButton); 

        jChooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setDialogTitle("Choose a file.");

                if (jFileChooser.showOpenDialog(null)==jFileChooser.APPROVE_OPTION){
                    fileToSend[0] = jFileChooser.getSelectedFile();
                    jFileName.setText("The file you want to send is: "+ fileToSend[0].getName());
                }
            }
        });

        jUploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (fileToSend[0]==null){
                    jFileName.setText("Please Choose a file first.");
                }else{
                    try{
                        FileInputStream fileInputStream = new FileInputStream(fileToSend[0].getAbsolutePath());
                        Socket soc = new Socket("localhost",1234);

                        DataOutputStream dataOutputStream = new DataOutputStream(soc.getOutputStream());

                        String fileName = fileToSend[0].getName();
                        byte fileNameBytes[] = fileName.getBytes();

                        byte fileContentBytes[] = new byte[(int)fileToSend[0].length()];
                        fileInputStream.read(fileContentBytes);

                        dataOutputStream.writeInt(fileNameBytes.length);
                        dataOutputStream.write(fileNameBytes);

                        dataOutputStream.writeInt(fileContentBytes.length);
                        dataOutputStream.write(fileContentBytes);
                    }catch(IOException error){
                        error.printStackTrace();
                    }
                }
            }
        });

        jFrame.add(jTitle);
        jFrame.add(jFileName);
        jFrame.add(jButtoPanel);

        jFrame.setVisible(true);
    }
}
