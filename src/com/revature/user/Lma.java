package com.revature.user;
import java.sql.Connection;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Lma 
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		UserManagement userManagement = new UserManagement();
		Librarian Lb=new Librarian();
		Member mb=new Member();
		
		try
		{
			System.out.println("Welcome To LIBRARY MANAGEMENT SYSTEM ");
			System.out.println("---------------------------");
			System.out.println("1.Admin");
			System.out.println("2.Librarian");
			System.out.println("3.Member");
			System.out.print("Enter your role :");
			int choice = sc.nextInt();
				switch (choice)
				{
				case 1:
					System.out.println("Please Login:");
					System.out.println();
					System.out.println("Enter Userid:");
					int user=sc.nextInt();
					System.out.println("Enter password:");
					String pass=sc.next();
					String ad=UserManagement.validateUser(user, pass);
					if(ad!=null)
					{
						String rep1 = "y";
						while(rep1.equalsIgnoreCase("y") ) 
						{
							System.out.println("Welcome To Administrator");
							System.out.println("------------------------");
							System.out.println();
							System.out.println("1. Create User");
							System.out.println("2. Delete User");
							System.out.println("3. Exit");
							System.out.println();
							System.out.print("Enter your option : ");
							int ch = sc.nextInt();
							if(ch == 1) 
							{
								System.out.print("Userid : ");
								int uid1 = sc.nextInt();
								System.out.println("enter username :");
								String un1=sc.next();
								System.out.print("Password : ");
								String pw = sc.next();
								int opt = 0;
								String r = "";
								do {
									System.out.println("1.Librarian 2.Member");
									System.out.print("Enter your option for the role : ");
									opt = sc.nextInt();
									if(opt == 1) 
									{
											r = "librarian";
									}
									if(opt == 2) 
									{
											r = "Member";
									}
									if(opt >= 5 || opt < 1) 
									{
											System.out.println("Invalid Role");
											System.out.println("Select Again");
									}
								}
								while(opt >= 5 || opt < 1);
								if(userManagement.createUser(uid1,un1, pw, r)) 
								{
									System.out.println("User Created Successfully");
								}
								else 
								{
									System.out.println("User Creation Failed");
								}
							  }
							  if(ch == 2)
							  {
									System.out.print("Enter the userid to delete : ");
									int u = sc.nextInt();
									if(userManagement.deleteUser(u)) 
									{
										System.out.println("Deleted User");
									}
									else
									{
										System.out.println("Cannot find user");
									}
							  }
							  System.out.println();
							  System.out.print("Do you want to continue (Y | N) ? ");
						      rep1 = sc.next();
							}
						}	
					System.out.println("Exiting the admin");
					break;
			  case 2:
					String rep2 = "y";
					while(rep2.equalsIgnoreCase("y") )
					{
						System.out.println("Welcome to Librarian");
						System.out.println("------------------------");
						System.out.println();
						System.out.println("1.Login");
						System.out.println();
						System.out.print("Enter Your Option : ");
						int ch = sc.nextInt();
						if(ch == 1) 
						{
							System.out.println("Enter Userid :");
							int id=sc.nextInt();
							System.out.print("Enter The Password :");
							String pw= sc.next();
							if(Lb.login(id, pw))
							{
								System.out.println("Login Success");
								System.out.println();
								String ep="y";
								while(ep.equalsIgnoreCase("y"))
								{
									System.out.println("1.Addbooks");
									System.out.println("2.Issuebook");
									System.out.println("3.Returnbook");
									System.out.println();
									System.out.print("Enter your option : ");
									int c = sc.nextInt();
									if(c == 1) 
									{
										if(Lb.add_book())
										{
											System.out.println("Books Added Successfully");
										}
										else {
											System.out.println("Books Adding Failed");
										}
										
									}
									if(c == 2)
									{
										    Connection conn = ConnectionFactory.getConnection();
											System.out.println("Enter Userid :");
											int id1=sc.nextInt();
											System.out.println("BOOKS AVAILABLE");
											String s="Select * from book";
											Statement st=conn.createStatement();
											ResultSet s1=st.executeQuery(s);
											while(s1.next()) 
											{
												System.out.println(s1.getInt(1)+" "+s1.getString(2)+" "+s1.getInt(3));
											}
											System.out.print("Enter The Book Id:");
											int book_id = sc.nextInt();
										if(Lb.issue_Book(id1,book_id)) 
										{
											System.out.println("Book Issued Successfully");
										}
										else 
										{
											System.out.println("Book Issue Failed");
										}
									}
									if(c == 3)
									{
										System.out.print("Enter The Book Id:");
										int bid = sc.nextInt();
										if(Lb.return_Book(bid)) 
										{
											System.out.println("Book Returned Successfully");
										}
										else
										{
											System.out.println();
											System.out.println("Book Return Failed");
										}
									}
									System.out.println();
									System.out.print("Do you want to continue (Y | N) ? ");
									ep= sc.next();
								}
							}
							else 
							{
								System.out.println();
								System.out.println("Login Failed");
								System.out.println();
								System.out.println("Ask Admin To Add You ! y|n");
								String sw=sc.next();
								if(sw.equalsIgnoreCase("y"))
								{
									System.out.println("Enter Userid :");
									int uid=sc.nextInt();
									System.out.println("Enter Username :");
									String un=sc.next();
									System.out.println("Enter Password :");
									String pd= sc.next();
									System.out.println("Enter Role :");
									String rol=sc.next();
									if(mb.register(uid,un,pd, rol))
									{
										System.out.println("Registered");
										System.out.println("Please Login If You Want To Continue !");
									}
									else 
									{
										System.out.println("Register Failed");
									}
								}
							}
						}
						System.out.println();
						System.out.print("Do You Want To Login Again (Y | N) ? ");
						rep2 = sc.next();
					}
					System.out.println("Exiting the Librarian");
					break;
				case 3:
					String rep3 = "y";
					while(rep3.equalsIgnoreCase("y") ) {
						System.out.println("Welcome to Member");
						System.out.println("------------------------");
						System.out.println();
						System.out.println("1.Register");
						System.out.println("2.login");
						System.out.println();
						System.out.print("Enter your option : ");
						int ch = sc.nextInt();
						if(ch == 1) {
							System.out.println("enter userid :");
							int uid=sc.nextInt();
							System.out.println("enter username :");
							String un=sc.next();
							System.out.println("Enter password :");
							String pd= sc.next();
							System.out.println("enter role :");
							String rol=sc.next();
							if(mb.register(uid,un,pd, rol))
							{
								System.out.println("registered");
								System.out.println("please login if you want to continue !");
							}
							else
							{
								System.out.println("register failed");
							}
						}
						if(ch == 2) 
						{
							System.out.println("enter userid :");
							int id=sc.nextInt();
							System.out.print("Enter the password : ");
							String pw= sc.next();
							if(mb.login(id, pw))
							{
								System.out.println("Login Success");
								System.out.println();
								String re="y";
								while(re.equalsIgnoreCase("y"))
								{
									System.out.println("1.Issuebook");
									System.out.println("2.Returnbook");
									int h=sc.nextInt();
									if(h == 1)
									{
										Connection con = ConnectionFactory.getConnection();
										String s="Select * from book";
										Statement st=con.createStatement();
										ResultSet s1=st.executeQuery(s);
										while(s1.next()) 
										{
											System.out.println(s1.getInt(1)+" "+s1.getString(2)+" "+s1.getInt(3));
										}
										System.out.print("Enter The Book Id:");
										int book_id = sc.nextInt();
										if(mb.issuebook(id,book_id))
										{
											System.out.println("Book Issued Successfully");
										}
										else
										{
											System.out.println("Book Issue Failed");
										}
									}
									if(h == 2) {
										System.out.print("Enter The Book Id:");
										int bid = sc.nextInt();
										if(mb.returnbook(bid)) 
										{
											System.out.println("Book Returned Successfully");
										}
										else
										{
											System.out.println("Book Return Failed");
										}
									}
									System.out.println();
									System.out.print("Do you want to continue to issue or return books(Y | N) ? ");
									re= sc.next();
								}
							}
							else
							{
								System.out.println("Login Failed");
						    }
					    }
						System.out.println();
						System.out.print("Do you want to continue (Y | N) ? ");
						rep3 = sc.next();
					}
					System.out.println("Exiting The Member");
					break;
				default:
					break;
				}
				
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}

