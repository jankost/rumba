package pl.edu.pw.fizyka.sk;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.management.Query;
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
    	JPanel listClients = new JPanel();
    	listClients.setLayout(null);
    	listClients.setPreferredSize(new Dimension(420, 450));
    	listClients.setBackground(Color.BLUE);
    		JTextArea instance = new JTextArea();
    	gui.add(listClients, c);

    	c.gridx = 1;
    	/* List of own files */
    	JPanel listFiles = new JPanel();
    	listFiles.setLayout(null);
    	listFiles.setPreferredSize(new Dimension(420, 450));
    	//controls.setSize(new Dimension(387, 273));
    	listFiles.setBackground(Color.GRAY);
    	gui.add(listFiles, c);
    	
    	JButton button = new JButton("Foo");
    	button.addActionListener(new ActionListener() {
    		
    		@Override
    		public void actionPerformed(ActionEvent arg0) {
    			UDPQuery query = new UDPQuery(appdata);
    			
    		}
    	});

    	c.gridx = 0;
    	c.gridy = 1;
    	c.gridwidth = 2;
    	/* List of own files */
    	JPanel listConnections = new JPanel();
    	listConnections.setLayout(null);
    	listConnections.setPreferredSize(new Dimension(840, 150));
    	//controls.setSize(new Dimension(387, 273));
    	listConnections.setBackground(Color.DARK_GRAY);
    	gui.add(listConnections, c);

    }
}