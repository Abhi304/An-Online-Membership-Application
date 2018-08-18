package database;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedDataObjectServer
{  public static void main(String[] args ) 
   {  
	
      try 
      {  ServerSocket s = new ServerSocket(2429);
         
         for (;;)
         {  Socket incoming = s.accept( );
            new ThreadedDataObjectHandler(incoming).start();
             
	   	 }   
      }
      catch (Exception e) 
      {  System.out.println(e);
      } 
   } 
}

class ThreadedDataObjectHandler extends Thread
{  public ThreadedDataObjectHandler(Socket i) 
   { 
   		incoming = i;
   }
   
   public void run()
   {  try 
      { 	
			
			ObjectInputStream in =
				new ObjectInputStream(incoming.getInputStream());
      	
      		ObjectOutputStream out =
				new ObjectOutputStream(incoming.getOutputStream());
      		
      		 
      		myObjectChild = (DataObjectChild)in.readObject();
            
			  temp = myObjectChild.getArray();
			  int len = temp.length;
			
			if(temp[0].equalsIgnoreCase("admin") || temp[0].equalsIgnoreCase("user")){
			  String role = temp[0];
			  String username = temp[1];
			  String password = temp[2];
			  System.out.println("Message read: " + role);									
			  if(role.equalsIgnoreCase("Admin")){
				  
				  MakeDB md = new MakeDB();
				  output = md.MakeConnection("Select * from ADMIN");
				//System.out.println("Message read: " + output[0]);
			  if(username.equalsIgnoreCase(output[0]) && password.equalsIgnoreCase(output[1])){
				  
				 x = "Got it";
				
				MakeDB md1 = new MakeDB();
				output = md.MakeConnection("Select * from USERS");
				System.out.println(output.length);
				for(int i = 0; i < output.length; i++){
					System.out.println(output[i]);
				}
				myObjectChild.setArray(output);	
			    out.writeObject(myObjectChild);	
			  }
		 } 
		 if(role.equalsIgnoreCase("user")){
				MakeDB md = new MakeDB();
				String abc = "select * from users where username = '"+username+"'";
				output = md.MakeConnection(abc);
				//System.out.println("Message read: " + output[0]);
			  if(username.equalsIgnoreCase(output[0]) && password.equalsIgnoreCase(output[1])){
				  
				 x = "Got it";
				
				MakeDB md1 = new MakeDB();
				output = md.MakeConnection("Select * from USERS");
				System.out.println(output.length);
				for(int i = 0; i < output.length; i++){
					System.out.println(output[i]);
				}
				myObjectChild.setArray(output);	
			    out.writeObject(myObjectChild);	
			  }
		 }		
		 } else if(temp[0].equalsIgnoreCase("update")){
			 
			  MakeDB md2 = new MakeDB();
			  String f = "Update users set password = '"+temp[2]+"' where username = '"+temp[1]+"'";
			  String[] UpdateOutput = md2.MakeUpdate(f);
			  myObjectChild.setArray(UpdateOutput);	
			  out.writeObject(myObjectChild);	
		 } 
		 else if(temp[0].equalsIgnoreCase("insert")){
			 
			  MakeDB md3 = new MakeDB();
			  System.out.println("Hello1");
			  String xyz = "insert into users values('"+temp[1]+"','"+temp[2]+"')";
			  System.out.println("Hello2");
			  String[] InsertOutput = md3.MakeUpdate(xyz);
			  System.out.println("Inserted");
			  myObjectChild.setArray(InsertOutput);	
			  System.out.println("Array Set");
			  out.writeObject(myObjectChild);
			  System.out.println("Array Inserted");
		 }
		 else{
			 System.out.println("Hello11111");
			 MakeDB md = new MakeDB();
			 int c=0;
			 
				System.out.println("//////////////////////"+temp.length);
					for(int i = 0 ; i < temp.length; i++){
					System.out.println(temp[i]);
					}
					System.out.println("/////////");
			 for(int i = 0 ; i < temp.length; i++){
				 c++;
				 System.out.println("Temp count"+c);
				 if(temp[i]!=null)
				 {
					
				 System.out.println(temp[i]);
				 String quotes = "'";
				 String sql = "delete from users where password ='"+temp[i]+"'";
					//System.out.println("C"+c);
				
				UpdateOut = md.MakeUpdate(sql);
				System.out.println("After Update");
				 }
			 }
			 System.out.println("C="+c);
				myObjectChild.setArray(UpdateOut);	
			    out.writeObject(myObjectChild);	
		 }
			
	  		in.close();
	  		
	  		out.close();
	
	   		incoming.close(); 
 				    


      }
      catch (Exception e) 
      {  System.out.println(e);
	  e.printStackTrace();
      } 
   }
   
   DataObject myObject = null;
   DataObjectChild myObjectChild = null;
   private Socket incoming;
   String[] output = new String[20];
    public static String x = new String();
	private String[] temp;
	private String[] UpdateOut;
	//String[] out = new String[20];
}
