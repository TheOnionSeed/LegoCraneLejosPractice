import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.WindowEvent;

import lejos.hardware.Sound;
import lejos.hardware.motor.Motor;
import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.remote.ev3.RemoteEV3;
import lejos.utility.Delay;

public class Robot {

	static RemoteEV3 ev3;
	static RMIRegulatedMotor motorup;
	static RMIRegulatedMotor motorright;
	static RMIRegulatedMotor clip;
	
	static JFrame frame = new JFrame("FrameDemo");
	static JPanel panel = new JPanel();
	 
	static JButton right = new JButton();
	static JButton left = new JButton();
	static JButton up = new JButton();
	static JButton down = new JButton();
	static JButton clipopen = new JButton();
	static JButton clipclose = new JButton();
	
	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException, InterruptedException {
		// TODO Auto-generated method stub

		frame.addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		    	System.out.println("Save and Quit");
		    	try {
					Robot.motorup.close();
					Robot.motorright.close();
					Robot.clip.close();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	System.exit(0);
		    }
		});
		
		frame.setSize(500,500);
        right.setSize(100,100);
        right.setText("----->");
        left.setSize(100,100);
        left.setText("<-----");
        up.setSize(100,100);
        up.setText("UP");
        down.setSize(100,100);
        down.setText("DOWN");
        clipopen.setSize(100,100);
        clipopen.setText("CLIP OP");
        clipclose.setSize(100,100);
        clipclose.setText("CLIP CL");
        panel.add(left);
        panel.add(right);
        panel.add(up);
        panel.add(down);
        panel.add(clipopen);
        panel.add(clipclose);
        
        frame.getContentPane().add(panel);

		ev3 = new RemoteEV3("10.0.1.1");
		
		
	    Robot.motorright=ev3.createRegulatedMotor("A",'L');
		
		//Show Frame.
		frame.setVisible(true);
		
		right.addMouseListener(new MouseAdapter() {
				
				
		      @Override
		      public void mousePressed(MouseEvent e) {

		    	  try {
						System.out.println("right down");
						Robot.motorright.setSpeed(50);
						Robot.motorright.forward();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						try {
							Robot.motorright.stop(true);
							Robot.motorright.close();
						} catch (RemoteException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
				
		      }
		      
		      public void mouseReleased(MouseEvent e) {

		    	  try {
						Robot.motorright.stop(true);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    System.out.println("right up");
				
			  }
		    });
		
		left.addMouseListener(new MouseAdapter() {
			
		      @Override
		      public void mousePressed(MouseEvent e) {

		    	  try {
						System.out.println("left down");
						Robot.motorright.setSpeed(50);
						Robot.motorright.backward();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						try {
							Robot.motorright.stop(true);
							Robot.motorright.close();
						} catch (RemoteException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
		      }
		      
		      public void mouseReleased(MouseEvent e) {

		    	  try {
						Robot.motorright.stop(true);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    System.out.println("left up");
			  }
		    });
		

	       Robot.motorup=ev3.createRegulatedMotor("C",'L');
		up.addMouseListener(new MouseAdapter() {
			 
		      @Override
		      public void mousePressed(MouseEvent e) {

		       
				try {
					System.out.println("up down");
					Robot.motorup.setSpeed(50);
					Robot.motorup.forward();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					try {
						Robot.motorup.stop(true);
						Robot.motorup.close();
					} catch (RemoteException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
		      }
		      
		      public void mouseReleased(MouseEvent e) {
		    	try {
					Robot.motorup.stop(true);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    System.out.println("up up");
			
			  }
		    });
		
		down.addMouseListener(new MouseAdapter() {
			 
		      @Override
		      public void mousePressed(MouseEvent e) {

		       
				try {
					System.out.println("down down");
					Robot.motorup.setSpeed(50);
					Robot.motorup.backward();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					try {
						Robot.motorup.stop(true);
						Robot.motorup.close();
					} catch (RemoteException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
		      }
		      
		      public void mouseReleased(MouseEvent e) {
		    	try {
					Robot.motorup.stop(true);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    System.out.println("down up");
			
			  }
		    });
		
		Robot.clip=ev3.createRegulatedMotor("B",'L');
		clipopen.addMouseListener(new MouseAdapter() {
			 
		      @Override
		      public void mousePressed(MouseEvent e) {

		       
				try {
					System.out.println("clip open");
					Robot.clip.setSpeed(70);
					Robot.clip.backward();
					Thread.sleep(1000);
					Robot.clip.stop(true);
				} catch (RemoteException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					try {
						Robot.clip.stop(true);
						Robot.clip.close();
					} catch (RemoteException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
		      }
		    });
		
		clipclose.addMouseListener(new MouseAdapter() {
			 
		      @Override
		      public void mousePressed(MouseEvent e) {

		       
				try {
					System.out.println("clip close");
					Robot.clip.setSpeed(70);
					Robot.clip.forward();
					Thread.sleep(1000);
					Robot.clip.stop(true);
				} catch (RemoteException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					try {
						Robot.clip.stop(true);
						Robot.clip.close();
					} catch (RemoteException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
		      }
		    });
	
	}

}
