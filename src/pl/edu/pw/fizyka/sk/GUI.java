package pl.edu.pw.fizyka.sk;

import java.awt.*;
import java.io.File;

import javax.swing.*;

class GUI extends JFrame{
	private AppData appData;
	private Config config;

    public GUI(){

    	appData = AppData.getInstance();
    	config = Config.getInstance();
    	JFrame gui = this; 
    	gui.setLayout(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
    	c.fill = GridBagConstraints.HORIZONTAL;
    	
    	c.weightx = 0.5;
    	c.gridx = 0;
    	c.gridy = 0;
    	
    	//List of clients, their files 
    	JPanel listClientsPanel = new JPanel();
    	listClientsPanel.setPreferredSize(new Dimension(420, 450));
    	listClientsPanel.setBackground(Color.BLUE);
    	listClientsPanel.setLayout(new BorderLayout());

    	JTextArea peers = new JTextArea();
    	for(int i=0;i<appData.ownFiles.size();i++){
    		peers.append(appData.ownFiles.get(i)+ "\n");
    	}
		peers.setLineWrap(false);
		peers.setEditable(false);
		peers.setVisible(true);
    	JScrollPane pane = new JScrollPane(peers);
    	pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    	pane.setVisible(true);

		listClientsPanel.add(pane, BorderLayout.CENTER);
    	gui.add(listClientsPanel, c);
    	
    	c.gridx = 1;
    	/* List of own files */
    	JPanel listFiles = new JPanel();
    	listFiles.setLayout(new BorderLayout());
    	listFiles.setPreferredSize(new Dimension(420, 450));
    	listFiles.setBackground(Color.GRAY);
    	
    	final JFileChooser fileChooser = new JFileChooser();
    	JButton button = new JButton("Choose a file!");
    	button.addActionListener(arg0 -> {
            int chosen = fileChooser.showOpenDialog(gui);
            if (chosen == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                String name = file.getName();
                String path = file.getAbsolutePath();
                if(appData.ownFiles.contains(path)){
                    System.out.println("The chosen file is already being shared!");
                }
                else{
                    appData.ownFiles.add(path);
                }
            }

        });
    	
    	final JFileChooser jfc = new JFileChooser();
    	JButton sendFile = new JButton("Send a example file");
    	sendFile.addActionListener(arg0 -> {

            int result = jfc.showSaveDialog(null);
            if (result == JFileChooser.CANCEL_OPTION)
                return;
            File file = jfc.getSelectedFile();
            TCPReceiver receiver = new TCPReceiver(file);
            Thread recthr = new Thread(receiver);
            recthr.start();
            UDPQuery udp = new UDPQuery(0, config.IP);
            Thread rsf = new Thread(udp);
            rsf.start();
            System.out.println("File path: " + file.getAbsolutePath());
        });
    	listFiles.add(sendFile, BorderLayout.WEST);
    	listFiles.add(button, BorderLayout.EAST);
    	
//    	JTextArea ownfiles = new JTextArea();
//    	gui.add(ownfiles);
    	
    	gui.add(listFiles, c);

    	c.gridx = 0;
    	c.gridy = 1;
    	c.gridwidth = 2;
    	JPanel listConnections = new JPanel();
    	listConnections.setLayout(null);
    	listConnections.setPreferredSize(new Dimension(840, 150));
    	listConnections.setBackground(Color.DARK_GRAY);
    	gui.add(listConnections, c);

    }
}