import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.imageio.*;
import javax.swing.*;

public class driver
{
	public static JFrame window;
	public static Environment airport;
	public static ArrayList<Airplane> planes = new ArrayList<Airplane>();
	public static int running = 0;
	public static JPanel main;
	public static int initalized = 0;
	public static int up = 0;
	public static int go = 0;
	public static String p1Dir = "E";
	public static String p2Dir = "S";
	public static boolean inAir = true;
	
	public static void setUp()
	{
		
		window = new JFrame("Environment set up");
		window.setSize(500, 500);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		
		JPanel rowOne = new JPanel();
		JTextField backgroundText = new JTextField("Background Color");
		backgroundText.setEditable(false);
		String[] backgroundList = {"Select", "Blue", "Green"};
		JComboBox<String> backgroundOptions = new JComboBox<String>(backgroundList);
		backgroundOptions.setSelectedIndex(1);
		
		rowOne.add(backgroundText);
		rowOne.add(backgroundOptions);
		
		JPanel rowTwo = new JPanel();
		JTextField lineColorText = new JTextField("Line Color");
		lineColorText.setEditable(false);
		String[] lineColorList = {"Select", "Blue", "Green"};
		JComboBox<String> lineOptions = new JComboBox<String>(lineColorList);
		lineOptions.setSelectedIndex(1);
		
		rowTwo.add(lineColorText);
		rowTwo.add(lineOptions);
		
		JPanel rowThree = new JPanel();
		JTextField lineSpaceText = new JTextField("Line Spacing");
		lineSpaceText.setEditable(false);
		ButtonGroup lineSpacingOptions = new ButtonGroup();
		JRadioButton spaceFive = new JRadioButton("5 miles");
		spaceFive.setSelected(true);
		JRadioButton spaceTen = new JRadioButton("10 miles");
		
		rowThree.add(lineSpaceText);
		lineSpacingOptions.add(spaceFive);
		lineSpacingOptions.add(spaceTen);
		rowThree.add(spaceFive);
		rowThree.add(spaceTen);
		//rowThree.add(lineSpacingOptions);
		
		//JPanel rowFour = new JPanel();
		//JTextField airText = new JTextField("Airport Name");
		//airText.setEditable(false);
		//JTextField airportName = new JTextField();
		//airportName.setEditable(true);
		//airportName.setColumns(10);
			
		//rowFour.add(airText);
		//rowFour.add(airportName);
		
		JPanel rowFive = new JPanel();
		JTextField entryPointText = new JTextField("Simulation");
		entryPointText.setEditable(false);
		ButtonGroup entryGroup = new ButtonGroup();
		JRadioButton takeOff = new JRadioButton("Take off");
		JRadioButton land = new JRadioButton("Landing");
		JRadioButton crash = new JRadioButton("Crash planes");
		JRadioButton diversion = new JRadioButton("Divert planes");
		takeOff.setSelected(true);
		
		rowFive.add(entryPointText);
		entryGroup.add(takeOff);
		entryGroup.add(land);
		entryGroup.add(crash);
		entryGroup.add(diversion);
		rowFive.add(takeOff);
		rowFive.add(land);
		rowFive.add(crash);
		rowFive.add(diversion);
		
		JPanel buttons = new JPanel();
		Button submit = new Button("Submit");
		
		airport = new Environment();
		submit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(backgroundOptions.getSelectedIndex() != 0 || lineOptions.getSelectedIndex() != 0)
				{
					airport.setBackgroundColor(backgroundOptions.getSelectedItem().toString().charAt(0));
					airport.setLineColor(lineOptions.getSelectedItem().toString().charAt(0));
					if(spaceFive.isSelected())
						airport.setLineSpacing(5);
					else
						airport.setLineSpacing(10);
					
					if(takeOff.isSelected())
					{
						Airplane p = new Airplane();
						p.setX(825);
						p.setY(440);
						p1Dir = "S";
						p.setName("plane1");
						planes.add(p);
						airport.setSimulation(1);
					}
					if(land.isSelected())
					{
						Airplane p = new Airplane();
						p.setX(50);
						p.setY(460);
						p.setName("plane1");
						planes.add(p);
						airport.setSimulation(2);
					}
					if(crash.isSelected())
					{
						
						Airplane p1 = new Airplane();
						p1.setX(50);
						p1.setY(460);
						p1.setName("plane1");
						planes.add(p1);
						
						Airplane p2 = new Airplane();
						p2.setX(825);
						p2.setY(440);
						p2.setName("plane2");
						planes.add(p2);

						airport.setSimulation(3);
					}
					if(diversion.isSelected())
					{
						
						Airplane p1 = new Airplane();
						p1.setX(50);
						p1.setY(460);
						p1.setName("plane1");
						planes.add(p1);
						
						Airplane p2 = new Airplane();
						p2.setX(825);
						p2.setY(440);
						p2.setName("plane2");
						planes.add(p2);

						airport.setSimulation(4);
					}

					window.dispose();
					go++;
					return;
				}
				else
				{
					JFrame popUp = new JFrame("Invalid Input!");
					popUp.setSize(450, 200);
					popUp.setLocationRelativeTo(null);
					
					JPanel main = new JPanel();
					//main.setBackground(new Color(173,216,230));
					
					JPanel title = new JPanel();
					JTextField mess = new JTextField("Make sure that all fields are filled out!");
					mess.setEditable(false);
					mess.setBorder(null);
					mess.setFont(new Font("SansSerif", Font.BOLD, 15));
					title.add(mess);
					
					JPanel butt = new JPanel();
					JButton close = new JButton("Close");
					close.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent arg0)
								{
									popUp.dispose();
								}				
							});
					butt.add(close);
					
					main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
					main.add(title);
					main.add(butt);
					popUp.add(main);
					popUp.setVisible(true);
				}
			}
	
		});
		Button reset = new Button("Reset");
		reset.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				backgroundOptions.setSelectedIndex(0);
				lineOptions.setSelectedIndex(0);
				spaceFive.setSelected(true);
				//airportName.setText(null);
			}
	
		});
		Button cancel = new Button("Cancel");
		cancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
			}
	
		});
		
		buttons.add(submit);
		buttons.add(reset);
		buttons.add(cancel);
		
		main.add(rowOne);
		main.add(rowTwo);
		main.add(rowThree);
		//main.add(rowFour);
		main.add(rowFive);
		main.add(buttons);
		
		window.add(main);
		
		window.setVisible(true);
		
		return;
	}
	
	public static Icon resizeImage(String color, String dir)
	{
		Icon imageIcon = new ImageIcon(driver.class.getResource("content\\airplane"+ color + dir +".png"), "LOL");
				
		Image image = ((ImageIcon) imageIcon).getImage(); // transform it 
		Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
		
		return imageIcon;
	}
	
	public static void main(String[] args) throws FileNotFoundException, InterruptedException
	{
		//red = resizeImage(red);
		
		setUp();
		
		while(go == 0)
		{
			//LOL
			System.out.println("LOL");
		}
		
		window = new JFrame("afd");
		window.setSize(1600, 1000);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		window.getContentPane().removeAll();
		window.repaint();
		
		
		main = new JPanel()
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paint(Graphics g)
			{
				super.paintComponent(g);
				/*if(initalized != 0)
				{
					try
					{
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}*/
				initalized++;
		        Graphics2D g2 = (Graphics2D) g;
		        g2.setColor(Color.green);
		        g2.drawLine(getWidth()/2, 0, getWidth()/2, getHeight());
		        g2.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);
		        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        g2.setColor(Color.GREEN);
		        g2.setStroke(new BasicStroke(5));
		        //Draw outer line
		        g2.drawArc(335, 2, getHeight() - 50, getHeight()-100, 0, 180);
		        g2.drawArc(335, 98, getHeight()-50, getHeight()-100, -180, 180);
		        //left entry point
		        g2.drawLine(310, (int) getHeight()/2 + 48, 360, (int) getHeight()/2 + 48);
		        g2.drawLine(310, (int) getHeight()/2 -48, 360, (int) getHeight()/2 - 48);
		        //right entry point
		        g2.drawLine(1225, (int) getHeight()/2 + 48, 1275, (int) getHeight()/2 + 48);
		        g2.drawLine(1225, (int) getHeight()/2 - 48, 1275, (int) getHeight()/2 - 48);
		        
		        
		        g2.setStroke(new BasicStroke(3));
		        //left strip
		        g2.drawRect(getWidth()/2 - 80, getHeight()/2 - 50, 60, 110);
		        g2.setStroke(new BasicStroke(4));
		        g2.drawLine(getWidth()/2 - 50, getHeight()/2 - 40, getWidth()/2 - 50, getHeight()/2 - 25);
		        g2.drawLine(getWidth()/2 - 50, getHeight()/2 - 15, getWidth()/2 - 50, getHeight()/2);
		        g2.drawLine(getWidth()/2 - 50, getHeight()/2 + 10, getWidth()/2 - 50, getHeight()/2 + 25);
		        g2.drawLine(getWidth()/2 - 50, getHeight()/2 + 35, getWidth()/2 - 50, getHeight()/2 + 50);
		        //right strip
		        g2.setStroke(new BasicStroke(3));
		        g2.drawRect(getWidth()/2 + 20, getHeight()/2 - 50, 60, 110);
		        g2.setStroke(new BasicStroke(4));
		        g2.drawLine(getWidth()/2 + 50, getHeight()/2 - 40, getWidth()/2 + 50, getHeight()/2 - 25);
		        g2.drawLine(getWidth()/2 + 50, getHeight()/2 - 15, getWidth()/2 + 50, getHeight()/2);
		        g2.drawLine(getWidth()/2 + 50, getHeight()/2 + 10, getWidth()/2 + 50, getHeight()/2 + 25);
		        g2.drawLine(getWidth()/2 + 50, getHeight()/2 + 35, getWidth()/2 + 50, getHeight()/2 + 50);

		        
		        final float dash1[] = {30};
		        g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f));
		        if(airport.getLineSpacing() == 10)
		        {
			        g2.drawArc( 355, 50, getHeight()-100, getHeight()-100, 0, 360);
			        g2.drawArc( 405, 100, getHeight()-200, getHeight()-200, 0, 360);
			        g2.drawArc( 455, 150, getHeight()-300,getHeight()-300, 0, 360);
			        g2.drawArc( 505, 200, getHeight()-400, getHeight()-400, 0, 360);
			        g2.drawArc( 555, 250, getHeight()-500, getHeight()-500, 0, 360);
			        g2.drawArc( 605, 300, getHeight()-600, getHeight()-600, 0, 360);
		        }
		        if(airport.getLineSpacing() == 5)
		        {
			        g2.drawArc( 405, 100, getHeight()-200, getHeight()-200, 0, 360);
			        g2.drawArc( 505, 200, getHeight()-400, getHeight()-400, 0, 360);
			        g2.drawArc( 605, 300, getHeight()-600, getHeight()-600, 0, 360);
		        }
			       		        
		        int plane1X = 0;
		        int plane1Y = 0;
		        int plane2X = 0;
		        int plane2Y = 0;
		        
		        if(!planes.isEmpty())
		        {
			        plane1X = planes.get(0).getX();
			        plane1Y = planes.get(0).getY();
			        if(planes.size() == 2)
			        {
				        plane2X = planes.get(1).getX();
				        plane2Y = planes.get(1).getY();
			        }
		        }
		        
		        int xDis = Math.abs(plane1X - plane2X);
		        int YDis = Math.abs(plane1Y - plane2Y);
		       // System.out.println(xDis);
		        //System.out.println(YDis);
		        Icon image1 = null;
		        Icon image2 = null;
		        
		        if(YDis > 50 || xDis > 50)
		        {
			        image1 = resizeImage("Blue", p1Dir);
			        image2 = resizeImage("Blue", p2Dir);
		        }
		        if(YDis < 150 && xDis < 150)
		        {
		        	image1 = resizeImage("Yellow", "");
			        image2 = resizeImage("Yellow", "");
		        }
		        if(YDis <= 50 && xDis <= 50)
		        {
		        	image1 = resizeImage("Red", "");
			        image2 = resizeImage("Red", "");
		        }
		        if(!planes.isEmpty() && inAir == true)
		        {
				        image1.paintIcon(this, g, planes.get(0).getX(), planes.get(0).getY()); 
				        if(planes.size() == 2)
				        {
				        	image2.paintIcon(this, g, planes.get(1).getX(), planes.get(1).getY());
				        }
		        }
		        if(inAir == false)
		        {
		        	image1 = resizeImage("Crash", "");
		        	image1.paintIcon(this, g, 630, 635);
		        }
			}
		};
		main.setBackground(Color.black);
		window.add(main);
		main.repaint();
		
		run();
	}
	
	public static synchronized void run() throws InterruptedException
	{	
		running++;
		if(planes.size() == 1)
		{
			if(airport.getSimulation() == 1)
			{
				while(running != 0)
				{
					main.repaint();
					//TimeUnit.MILLISECONDS.sleep(200);
					TimeUnit.MILLISECONDS.sleep(1500);

					if(planes.isEmpty())
					{
						System.out.println("YES");
						break;
					}
					if(planes.get(0).getY() <= 730 && planes.get(0).getX() ==  825 && up == 0)
					{
						planes.get(0).setY(planes.get(0).getY() + 50);
					}
					if(planes.get(0).getY() == 740)
					{
						up++;
					}
					if(planes.get(0).getY() !=  510 &&planes.get(0).getX() <= 1600 && up > 0 && up < 9)
					{
						p1Dir = "NE";
						if(up < 6)
						{
							planes.get(0).setX(planes.get(0).getX() + 50);
							planes.get(0).setY(planes.get(0).getY() - 50);
						}
						else
						{
							planes.get(0).setX(planes.get(0).getX() + 10);
							planes.get(0).setY(planes.get(0).getY() - 10);
						}
						up++;
					}
					if(planes.get(0).getY() == 460 && planes.get(0).getX() <=  1600)
					{
						p1Dir = "E";
						planes.get(0).setX(planes.get(0).getX() + 50);
					}
					if(planes.get(0).getX() > 1600)
					{
						planes.clear();
					}
				}
			}
			if(airport.getSimulation() == 2)
			{
				while(running != 0)
				{
					main.repaint();
					//TimeUnit.MILLISECONDS.sleep(200);
					TimeUnit.MILLISECONDS.sleep(1500);
					if(planes.isEmpty())
					{
						break;
					}
					if(planes.get(0).getY() == 460 && planes.get(0).getX() < 400)
					{
						planes.get(0).setX(planes.get(0).getX() + 50);
					}
					if(planes.get(0).getY() < 800 && planes.get(0).getX() >= 400 && up != 8)
					{
						p1Dir = "SE";
						if(up < 6)
						{
							planes.get(0).setX(planes.get(0).getX() + 50);
							planes.get(0).setY(planes.get(0).getY() + 50);
						}
						else
						{
							planes.get(0).setX(planes.get(0).getX() + 10);
							planes.get(0).setY(planes.get(0).getY() + 10);
						}
						up++;
					}
					if(planes.get(0).getY() > 460 && planes.get(0).getX() >= 720)
					{
						p1Dir = "N";
						planes.get(0).setY(planes.get(0).getY() - 50);
					}
					if(planes.get(0).getY() <= 470 && planes.get(0).getX() >= 720)
					{
						main.repaint();
						TimeUnit.SECONDS.sleep(2);
						planes.clear();
					}
				}
			}
		}
		if(airport.getSimulation() == 3)
		{
			//825 440
			//50 460
			while(running != 0)
			{
				main.repaint();
				//TimeUnit.MILLISECONDS.sleep(200);
				TimeUnit.MILLISECONDS.sleep(1500);
				if((planes.get(0).getY() == 460 && planes.get(0).getX() < 410))
				{
					planes.get(0).setX(planes.get(0).getX() + 50);
					planes.get(1).setY(planes.get(1).getY() + 50);
				}
				if((planes.get(0).getY() < 650 && planes.get(0).getX() > 410))
				{
					p1Dir = "SE";
					planes.get(0).setX(planes.get(0).getX() + 50);
					planes.get(0).setY(planes.get(0).getY() + 50);
					
					p2Dir = "NW";
					planes.get(1).setX(planes.get(1).getX() - 50);
					planes.get(1).setY(planes.get(1).getY() - 50);
					go++;
				}
				if(go == 5)
				{
					main.repaint();
					TimeUnit.MILLISECONDS.sleep(1500);
					planes.clear();
					inAir = false;
					main.repaint();
					break;
				}
			}
		}
		if(airport.getSimulation() == 4)
		{
			//825 440
			//50 460
			while(running != 0)
			{
				main.repaint();
				//TimeUnit.MILLISECONDS.sleep(500);
				TimeUnit.MILLISECONDS.sleep(1500);
				if((planes.get(0).getY() == 460 && planes.get(0).getX() < 410) && up < 1)
				{
					planes.get(0).setX(planes.get(0).getX() + 50);
					planes.get(1).setY(planes.get(1).getY() + 50);
				}
				if((planes.get(0).getY() < 600 && planes.get(0).getX() > 410) && up < 3)
				{
					p1Dir = "SE";
					planes.get(0).setX(planes.get(0).getX() + 50);
					planes.get(0).setY(planes.get(0).getY() + 50);
					
					p2Dir = "NW";
					planes.get(1).setX(planes.get(1).getX() - 50);
					planes.get(1).setY(planes.get(1).getY() - 50);
					up++;
					if(up == 3)
					{
						main.repaint();
						TimeUnit.MILLISECONDS.sleep(1500);
					}
				}
				if( up > 2 && up < 5)
				{
					up++;
					p1Dir = "E";
					planes.get(0).setX(planes.get(0).getX() + 50);
					
					p2Dir = "W";
					planes.get(1).setX(planes.get(1).getX() - 50);
				}
				if(up == 5)
				{
					main.repaint();
					TimeUnit.MILLISECONDS.sleep(1500);
					
					up++;
					p1Dir = "E";
					planes.get(0).setX(planes.get(0).getX() + 20);
					
					p2Dir = "W";
					planes.get(1).setX(planes.get(1).getX() - 20);
				}
				if(up > 5 && up < 9)
				{
					up++;
					p1Dir = "N";
					planes.get(0).setY(planes.get(0).getY() - 50);
					
					p2Dir = "NW";
					planes.get(1).setX(planes.get(1).getX() - 50);
					planes.get(1).setY(planes.get(1).getY() - 50);
					if(up == 6)
					{
						main.repaint();
						TimeUnit.MILLISECONDS.sleep(1500);
					}
				}
				if(up == 9)
				{
					up++;
					p1Dir = "N";
					planes.get(0).setY(planes.get(0).getY() - 20);
					
					p2Dir = "NW";
					planes.get(1).setX(planes.get(1).getX() - 50);
					planes.get(1).setY(planes.get(1).getY() - 50);
					
					main.repaint();
					TimeUnit.MILLISECONDS.sleep(1500);
				}
				if(up > 9)
				{
					up++;
					if(up == 11)
					{
						planes.remove(0);
						p1Dir = "NW";
						planes.get(0).setX(planes.get(0).getX() - 10);
						planes.get(0).setY(planes.get(0).getY() - 20);
						main.repaint();
						TimeUnit.MILLISECONDS.sleep(1500);
					}
					
					System.out.println(planes.size());
					p1Dir = "W";
					planes.get(0).setX(planes.get(0).getX() - 50);
				}
			}
		}
	}
}
