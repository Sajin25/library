package lms;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JWindow;

import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.*;
import java.sql.Statement;

public class mainwindow {
	static JFrame frame = new JFrame();
		public static void main(String Args[]) {
			Splash.showSplash();
			
		}
		
		
	
		
		static class window {
			public  static void userinterface() {
				
				JLabel heading = new JLabel();

				heading.setBounds(650, 1, 1000, 20);
				heading.setSize(1000, 50);
				heading.setText("Library Management System");
				heading.setFont(new Font("Courier New", Font.PLAIN, 30));
				Icon icon1 = new ImageIcon("C:\\Users\\holla\\OneDrive\\Desktop\\icons\\add.png");
				JButton addbook = new JButton(icon1);
				addbook.setBounds(600, 200, 500, 500);
				addbook.setSize(200, 200);
				addbook.addActionListener(new ActionListener(){  
				    public void actionPerformed(ActionEvent e){  
				    	 addbooks.addbookinfo();
				    }  
				    });  
				Icon icon2 = new ImageIcon("C:\\Users\\holla\\OneDrive\\Desktop\\icons\\search.png");
				JButton searchbook= new JButton(icon2);
				searchbook.setBounds(1000, 200, 500, 500);
				searchbook.setSize(200, 200);
				searchbook.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						searchbooks.search();
					}
					
				});
				Icon icon3 = new ImageIcon("C:\\Users\\holla\\OneDrive\\Desktop\\icons\\remove.png");
				JButton remove = new JButton(icon3);
				remove.setBounds(1000, 500, 500, 500);
				remove.setSize(200, 200);
				remove.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						removebook.delete();
						
					}
					
				});
				Icon icon4 = new ImageIcon("C:\\Users\\holla\\OneDrive\\Desktop\\icons\\borrow.png");
				JButton borrow = new JButton(icon4);
				borrow.setBounds(600, 500, 200, 200);
				borrow.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						borrowbooks.borrow();
					}
					
				});
				
				frame.add(heading);
				frame.add(addbook);
				frame.add(searchbook);
				frame.add(borrow);
				frame.add(remove);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				frame.setBackground(Color.red);
				frame.setLayout(null);  
		        frame.setVisible(true); 
			}
		
		}
		
		
		
		
		public static class addbooks{
			static JTextField tf1, tf2, tf3, tf4;
			static JButton b1,b2,reset,submit;
			static JLabel l1,l2,l3,l4;
			
			static JLabel connect = new JLabel("connectionsuccessfull");
			static String stralert="";
			static JLabel alert1 = new JLabel("connection to database unsuccessful"); 
			static JLabel alert2 = new JLabel("alert2"); 
			static JLabel insertstatus = new JLabel("inserted");
			static Connection conn;
			static JFrame f2 = new JFrame();
			
				public static void addbookinfo() {
					
					//JLabel connect1= new JLabel("connected");
					l1 = new JLabel();
					l1.setBounds(20, 40, 100, 50);
					l1.setText("Bookname");
					tf1 = new JTextField();
					tf1.setBounds(130, 60, 100, 20);
					
					l2= new JLabel();
					l2.setText("Author Name");
					l2.setBounds(20, 100, 100, 50);
					tf2 = new JTextField();
					tf2.setBounds(130, 120, 100, 20);
					
					l3= new JLabel();
					l3.setText("Publisher Name");
					l3.setBounds(20, 150, 100, 50);
					tf3 = new JTextField();
					tf3.setBounds(130, 170, 100, 20);
					
					l4= new JLabel();
					l4.setText("Number of Pages");
					l4.setBounds(20, 200, 100, 50);
					tf4 = new JTextField();
					tf4.setBounds(130, 220, 100, 20);
					
					reset = new JButton("Reset");
					reset.setBounds(220, 260, 100, 30);
					reset.addActionListener(new ActionListener(){  
					    public void actionPerformed(ActionEvent e){  
					    	tf1.setText(null);
					    	tf2.setText("");
					    	tf3.setText("");
					    	tf4.setText("");
							connect.setText(null);
							insertstatus.setText("");
					    }
					    });
					submit = new JButton("submit");
					submit.setBounds(100, 260, 100, 30);
					
					submit.addActionListener(new ActionListener() {
						
						public void actionPerformed(ActionEvent e) {
							try {
								 getConnection();
								//System.out.println(con);
								
							}
							catch(Exception e1) {

							}
						}
					});
				
					f2.add(l1);
					f2.add(l2);
					f2.add(l3);
					f2.add(l4);
					f2.add(tf1);
					f2.add(tf2);
					f2.add(tf3);
					f2.add(tf4);
					f2.add(submit);
					f2.add(reset); 
					f2.add(insertstatus);
					f2.add(alert1);
					f2.add(alert2);
					f2.add(connect);
				//	f2.setLocation(1120/2, 700/2);
					f2.setSize(500, 500);
					f2.setLayout(null);  
					f2.setVisible(true);
					
					}
		
				public static Connection getConnection() throws Exception{
					conn=null;
					try { 
						
						String url ="jdbc:mysql://localhost:3306/shrinidhi_holla?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
						 conn = DriverManager.getConnection(url,"root","");
						connect.setBounds(20, 300, 400, 50);
						insertdata(conn);
						
						return conn;
						
					}
					catch(Exception e) {
						stralert = e.toString();
						System.out.println(e);
						
						alert1.setBounds(20, 400, 1000, 50);
					} 
					return null;
					
				}
				public static void insertdata(Connection conn) {
					
					String bookname=tf1.getText();
					String authername=tf2.getText();
					String publishername=tf3.getText();
					String numberofpages=tf4.getText();
					String bookstatus="available";
					try {
						//store user entered data values in the database table(lms(addbook))
						
						PreparedStatement posted = conn.prepareStatement("INSERT INTO `lms(addbook)`(`bookname`, `authername`, `publishername`, `numberofpages`,`bookstatus`) VALUES ('"+bookname+"','"+authername+"','"+publishername+"','"+numberofpages+"','"+bookstatus+"')");
						posted.executeLargeUpdate();
						insertstatus.setBounds(20, 350, 100, 50);
						insertstatus.setText("inserted");
					}
					
					catch (Exception e) {
						System.out.println(e);
						alert2.setBounds(20, 300, 100, 50);
					}
					
				}
				}	
public static class searchbooks{
					static Statement stmt=null;
					static JLabel nodata = new JLabel("Searched book is not found");
					static JTextField bn = new  JTextField();
					static JTextArea ta= new JTextArea();
					static JFrame search = new JFrame();
					static JLabel bookname= new JLabel("Bookname");
					static JButton b1= new JButton("search");
					static JLabel instruction = new JLabel();
					
				
					public static void search() {
						ta.setText(null);
						bookname.setBounds(20, 50, 100, 50);
						instruction.setText("press search to view all books");
						instruction.setBounds(20, 20, 500, 50);	
						bn.setBounds(100, 60, 120, 30);
						b1.setBounds(100, 400, 120, 30);
						 
						
						b1.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
						
								ta.setText("Bookname\t");
								ta.append("authorname\t");
								ta.append("\tpublishername\t");
								ta.append("\tnoofpages\t");
								ta.append("\tbookstatus\n\n");
								
								if(bn.getText().equals(""))
								{
								searchall();
								}else
								{
									searchspecific();
								}
							}
							
							
						});
												
						
							search.add(bookname);
							search.add(bn);
							search.add(b1);
							search.add(ta);
							search.add(nodata);
							search.add(instruction);
							search.setSize(1000,600);
							search.setLayout(null);
							search.setVisible(true);
					
					
						
					}
		public static void searchall() {
			String data="";
			ta.setText("Bookname\t");
			ta.append("authorname\t");
			ta.append("\tpublishername\t");
			ta.append("\tnoofpages\t");
			ta.append("\tbookstatus\n\n");
			nodata.setVisible(false);
			try {
				// Get a result set containing all data from test_table
				String url ="jdbc:mysql://localhost:3306/shrinidhi_holla?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
				 Connection con = DriverManager.getConnection(url,"root","");
				 stmt = con.createStatement();
				
				String query = "SELECT * FROM `lms(addbook)`";
			      ResultSet rs = stmt.executeQuery(query);
			      if(!rs.next()) {
					 dataunavailable();
				 }else {      
				do {
			
					    	data=rs.getString(1);
					    	 ta.append(data+"     \t");    
							    
							    
					    	data=rs.getString(2)+"\t";
					    	 ta.append(data+"     \t");    
							    
			
					    	data=rs.getString(3)+"\t";
					    	 ta.append(data+"     \t");    
							    
			
					    	data=rs.getString(4)+"\t";
					    	 ta.append(data+"     \t");    
					    	 
					    	 data=rs.getString(5);
					    	 ta.append(data+"     \t");    
							    
							    
							    ta.append("\n");
					  
					}while (rs.next());
				 }
			    ta.setEditable(false);
				ta.setVisible(true);
				ta.setBounds(20,100,800,300);
				  
		
			}	
			catch (SQLException e) {
			 
			  System.out.println("Could not retrieve data from the database " + e.getMessage());
			    }
		}
		public static void searchspecific() {
					
					String data="";
					 String text= bn.getText();
					 dataunavailable();
					 try {
					 String query="SELECT * FROM `lms(addbook)` WHERE bookname='"+text+"'";
					 ResultSet rs = stmt.executeQuery(query);
					 
						//ta.setVisible(true);
								 while (rs.next()) {				//if query returns data display data	
									 	
								data=rs.getString("bookname");
						    	 ta.append(data+"     \t");    
								    
								    
								    //ta.append("\n");
						    	data=rs.getString("authername")+"\t";
						    	 ta.append(data+"     \t");    
								    
								    
								   /// ta.append("\n");
						    	data=rs.getString("publishername")+"\t";
						    	 ta.append(data+"     \t");    
								    
								    
								    //ta.append("\n");
						    	data=rs.getString("numberofpages");
						    	 ta.append(data+"     \t"); 
						    	 
						    	 data=rs.getString("bookstatus");
						    	 ta.append(data+"     \t"); 
								    
								    
								    ta.append("\n\n");
								    ta.setEditable(false);
								    ta.setVisible(true);
								    ta.setBounds(20,100,800,300);	
								    
								    }
								 //}
							
							
						 
					 	
				}catch(Exception e) {
					System.out.println("Could not retrieve data from the database " + e.getMessage());
				}
				}
				
		public static void dataunavailable() { 									
			// String text= bn.getText();
				nodata.setVisible(true);
			 try {
					String url ="jdbc:mysql://localhost:3306/shrinidhi_holla?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
					 Connection con = DriverManager.getConnection(url,"root","");
					 stmt = con.createStatement();
					 String query="SELECT * FROM `lms(addbook)` WHERE bookname='"+bn.getText()+"'";
				ResultSet rs = stmt.executeQuery(query);
			if(rs.next()==false) {
				ta.setVisible(false);
				ta.setEditable(false);
				nodata.setBounds(20,400,800,300);
				
			}
			 
			 } catch (SQLException e) {
			
				e.printStackTrace();
			}
			
		}
}

public static class removebook{
		static JFrame remove = new JFrame();
		static JLabel lblBookname= new JLabel("Bookname");
		static JLabel lblResult = new JLabel();
		static JTextField tfBookname= new JTextField();
		static JButton btnRemove= new JButton("Remove");
		static JFrame prompt = new JFrame();
		
				public static void delete() {
					lblResult.setVisible(false);
					btnRemove.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								
							String url ="jdbc:mysql://localhost:3306/shrinidhi_holla?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
							 Connection con = DriverManager.getConnection(url,"root","");
							 Statement stmt = con.createStatement();
							 String query1="SELECT * FROM `lms(addbook)` WHERE bookname='"+tfBookname.getText()+"'";
							 ResultSet rs = stmt.executeQuery(query1);
							 if(rs.next()==false) {
								 lblResult.setVisible(true);
								 lblResult.setText(tfBookname.getText()+" not Found");
								 lblResult.setBounds(100, 200, 150, 30);
							 }else {
								 	
								 	   Warning(con);
										    }
							 
							
							 lblResult.setBounds(100, 100, 150, 30);
						}
							
							
							catch(Exception e1) {
							System.out.println(e1);
						}
					
					}
					
					});
					
					lblBookname.setBounds(20, 50, 100, 50);
					tfBookname.setBounds(100, 60, 150, 30);
					btnRemove.setBounds(20, 200, 100, 30);
					
					remove.add(lblBookname);
					remove.add(tfBookname);
					remove.add(lblResult);
					remove.add(btnRemove);
					remove.setSize(500, 300);      	
					remove.setLayout(null); 
					remove.setVisible(true);
				}
		public static void Warning(Connection con) {
					JLabel warning = new JLabel("Delete the book?");
				 	JButton yes = new JButton("yes");
				 	JButton no = new JButton("no");
				 	      
				 	
				 	yes.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							String data="";
							try {
							PreparedStatement query=con.prepareStatement("DELETE FROM `lms(addbook)` WHERE bookname='"+tfBookname.getText()+"'");
							query.executeLargeUpdate();
							PreparedStatement query1=con.prepareStatement("DELETE FROM `borrowdetails` WHERE bookname='"+tfBookname.getText()+"'");
							query1.executeLargeUpdate();
					 		data=tfBookname.getText();
					    	 lblResult.setText(data+ " is removed \t");    
							}catch(Exception e1) {
								
							}
							prompt.setVisible(false);
						
						}
				 		
				 	});
				 	no.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							
							
						}
				 		
				 	});
				 	
				 	yes.setBounds(50, 100, 70, 30);
				 	no.setBounds(140, 100, 70, 30);
				 	warning.setBounds(80, 20, 100, 30);
				 	
				 	prompt.add(yes);
				 	prompt.add(no);
				 	prompt.add(warning);
				 	prompt.setSize(300,200);
				 	prompt.setLayout(null); 
					prompt.setVisible(true);
				}
}
	public static class borrowbooks{
				static JFrame borrowbook = new JFrame();
				static JButton btnborrow = new JButton("Borrow");
				static JButton btnrefresh = new JButton("refresh");
				static  JLabel lblborrowerName = new JLabel("Person Name");
				static JTextField tfborrowerName = new JTextField();
				static  JLabel lblbookName = new JLabel("Book Name");
				static JTextField tfbookName = new JTextField();
				static JTextArea ta = new JTextArea();
				static String bookstatus="";
				static JLabel statusborrow = new JLabel();
				static JLabel statusreturn = new JLabel();
				static JButton btnreturnbook= new JButton("return");
				static String bookstatusfalse="Borrowed";
				static String bookstatustrue="available";
				static String data="";
				
				
		public static void borrow() {
			
			try {
				ta.setVisible(true);
				ta.setText("Bookname\t");
				ta.append("authorname\t");
				ta.append("\tpublishername\t");
				ta.append("\tnoofpages\t");
				ta.append("\tbookstatus\n\n");
				
				String url ="jdbc:mysql://localhost:3306/shrinidhi_holla?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
				Connection con = DriverManager.getConnection(url,"root","");
				PreparedStatement stmt=con.prepareStatement("SELECT * FROM `lms(addbook)` WHERE bookstatus= '"+bookstatustrue+"'");
				ResultSet rs= stmt.executeQuery();
				while(rs.next()) {
					
			    	data=rs.getString(1);
			    	 ta.append(data+"     \t");    
					    
					    
			    	data=rs.getString(2)+"\t";
			    	 ta.append(data+"     \t");    
					    
	
			    	data=rs.getString(3)+"\t";
			    	 ta.append(data+"     \t");    
					    
	
			    	data=rs.getString(4)+"\t";
			    	 ta.append(data+"     \t");    
			    	 
			    	 data=rs.getString(5);
			    	 ta.append(data+"     \t");    
					    
					    
					    ta.append("\n");
			  
			}
			}catch(Exception e1) {
				
				statusborrow.setText(e1.toString());
				System.out.println(e1);
		}
					btnborrow.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
						
							try {
								statusborrow.setVisible(false);
							String url ="jdbc:mysql://localhost:3306/shrinidhi_holla?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
							Connection con = DriverManager.getConnection(url,"root","");
				
									PreparedStatement posted = con.prepareStatement("INSERT INTO `borrowdetails`(`borrowername`, `bookname`) VALUES ('"+tfborrowerName.getText()+"','"+tfbookName.getText()+"')");
									posted.executeLargeUpdate();
									
									PreparedStatement borrow = con.prepareStatement("UPDATE `lms(addbook)`SET bookstatus = '"+bookstatusfalse+"' where bookname = '"+tfbookName.getText()+"'");
									borrow.executeLargeUpdate();
									statusborrow.setBounds(1, 300, 100, 30);
									statusborrow.setText("Borrowed successfully");
									refresh();
									
								}
											catch(Exception e1) {
								
								statusborrow.setText(e1.toString());
								System.out.println(e1);
						}
				}
						
						
					});
					
					
					btnrefresh.addActionListener(new ActionListener() {
							
						@Override
						public void actionPerformed(ActionEvent e) {
							refresh();
							
							
						}
						
					});
					
					btnreturnbook.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							returnbooks();
						}
					});
					
					ta.setBounds(400,50,800,300);
			
					btnborrow.setBounds(100, 400, 100, 30);
					btnrefresh.setBounds(300,400,100,30);
					btnreturnbook.setBounds(500,400,100,30);
					lblborrowerName.setBounds(20, 40, 100, 100);
					tfborrowerName.setBounds(150, 70, 200, 30);
					lblbookName.setBounds(20, 120, 100, 100);
					tfbookName.setBounds(150, 150, 200, 30);
					statusborrow.setBounds(100, 400, 100, 30);
					
					borrowbook.add(ta);
					borrowbook.add(lblborrowerName);
					borrowbook.add(tfborrowerName);
					borrowbook.add(lblbookName);
					borrowbook.add(tfbookName);
					borrowbook.add(btnborrow);
					borrowbook.add(btnrefresh);
					borrowbook.add(btnreturnbook);
					borrowbook.add(statusborrow);
					borrowbook.add(statusreturn);
					borrowbook.setSize(1500, 600);
					borrowbook.setLayout(null);
					borrowbook.setVisible(true);
				}
		public static void refresh() {
			ta.setVisible(true);
			ta.setText("Bookname\t");
			ta.append("authorname\t");
			ta.append("\tpublishername\t");
			ta.append("\tnoofpages\t");
			ta.append("\tbookstatus\n\n");
			//statusreturn.setText("");
			tfbookName.setText("");
			tfborrowerName.setText("");
			
			try {
				
				String url ="jdbc:mysql://localhost:3306/shrinidhi_holla?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
				Connection con = DriverManager.getConnection(url,"root","");
				PreparedStatement stmt=con.prepareStatement("SELECT * FROM `lms(addbook)` WHERE bookstatus= '"+bookstatustrue+"'");
				ResultSet rs= stmt.executeQuery();
				while(rs.next()) {
					
			    	data=rs.getString(1);
			    	 ta.append(data+"     \t");    
					    
					    
			    	data=rs.getString(2)+"\t";
			    	 ta.append(data+"     \t");    
					    
	
			    	data=rs.getString(3)+"\t";
			    	 ta.append(data+"     \t");    
					    
	
			    	data=rs.getString(4)+"\t";
			    	 ta.append(data+"     \t");    
			    	 
			    	 data=rs.getString(5);
			    	 ta.append(data+"     \t");    
					    
					    
					    ta.append("\n");
			  
			}
			}catch(Exception e1) {
				
				statusborrow.setText(e1.toString());
				System.out.println(e1);
		}
		}
		
		
		//add return button and 
		public static void returnbooks() {
			try {
				statusreturn.setVisible(true);
			String url ="jdbc:mysql://localhost:3306/shrinidhi_holla?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection con = DriverManager.getConnection(url,"root","");
			 Statement stmt = con.createStatement();
			 String query1="SELECT * FROM `borrowdetails` WHERE bookname='"+tfbookName.getText()+"'";
			 PreparedStatement query=con.prepareStatement("DELETE FROM `borrowdetails` WHERE bookname='"+tfbookName.getText()+"'");
			
			 ResultSet rs = stmt.executeQuery(query1);
			 if(rs.next()==false) {
				 statusreturn.setText(tfbookName.getText()+" not Found");
				
			 }else {
				 		query.executeLargeUpdate();
				 		data=tfbookName.getText();
				 		 statusreturn.setBounds(1, 300, 100, 30);
				    	 statusreturn.setText(data+ " is returned \t");    
				    	 PreparedStatement returnbook = con.prepareStatement("UPDATE `lms(addbook)`SET bookstatus = '"+bookstatustrue+"' where bookname = '"+tfbookName.getText()+"'");
				    	 returnbook.executeLargeUpdate();
				    	
				    	
				    	 refresh();
						    }
			
			}catch(Exception e1) {
				System.out.println(e1.toString());
			}
		}	
	}
	public static class Splash {
	  
	    public static void showSplash() {
	    	int duration =3000;
	        JWindow splash = new JWindow();
	        JPanel content = (JPanel) splash.getContentPane();

	        // set the window's bounds, centering the window
	        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	        int x = (screen.width - 400) / 2;
	        int y = (screen.height -400) / 2;
	        splash.setBounds(x, y, 400, 310);

	        // build the splash screen
	        JLabel label = new JLabel(new ImageIcon("C:\\Users\\Lucifer\\Desktop\\icons\\Library-Management-Systems.jpg"));
	        JLabel copyrt = new JLabel("", JLabel.CENTER);
	        copyrt.setFont(new Font("Tahoma", Font.BOLD, 10));
	       
	        content.setBackground(Color.LIGHT_GRAY);
	       
	        content.add(label, BorderLayout.CENTER);
	        content.add(copyrt, BorderLayout.SOUTH);
	        content.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

	    
	        splash.setVisible(true);

	    	        try {
	            Thread.sleep(duration);
	            window.userinterface();
	        } 
	    	        catch (Exception e) {
	    	        	
	    	        	System.out.println(e);
	        }

	        splash.setVisible(false);
	    }
	}

}
	



