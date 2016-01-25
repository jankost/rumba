package pl.edu.pw.fizyka.sk;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

public class GUI extends JFrame{
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    public GUI(final AppData appdata){
    	
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
    	for(int i=0;i<appdata.ownFiles.size();i++){
    		peers.append(appdata.ownFiles.get(i)+ "\n");
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
    	listFiles.setLayout(null);
    	listFiles.setPreferredSize(new Dimension(420, 450));
    	listFiles.setBackground(Color.GRAY);
    	
    	final JFileChooser fileChooser = new JFileChooser();
    	JButton button = new JButton("Choose a file!");
    	button.addActionListener(new ActionListener() {
    		
    		@Override
    		public void actionPerformed(ActionEvent arg0) {
    			int chosen = fileChooser.showOpenDialog(gui);
    			if (chosen == JFileChooser.APPROVE_OPTION){
    				File file = fileChooser.getSelectedFile();
    				String name = file.getName();
    				String path = file.getAbsolutePath();
    				if(appdata.ownFiles.contains(path)){
    					System.out.println("Plik już jest udostepniany!");
    				}
    				else{
    					appdata.ownFiles.add(path);
    				}
    			}
    			
    		}
    	});
    	gui.add(button);
    	
    	JTextArea ownfiles = new JTextArea();
    	gui.add(ownfiles);
    	
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