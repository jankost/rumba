package pl.edu.pw.fizyka.sk;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;

public class GUI extends JFrame{
	public GUI() throws HeadlessException
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setSize(850, 647);

		setResizable(false);
	}
	
	public static void main(String[] args)
	{
		GUI mainWindow = new GUI();
		mainWindow.setVisible(true);
	}
	
}
