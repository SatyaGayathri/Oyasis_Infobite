//Online train reservation System

import java.util.Random;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Task1 {
     private static final int min=1000;
     private static final int max=9999;

     public static class user{
      private String username;
      public String password;

      Scanner sc=new Scanner(System.in);
      public user(){

      }

      public String getUserName(){
         System.out.println("enter username:");
         username=sc.nextLine();
         return username;
      }
      public String getPassword(){
         System.out.println("Enter password:");
         password=sc.nextLine();
         return password;
      }

     }

     public static class PnrRec{
         private int pnrNum;
         private String passName;
         private String trainNum;
         private String classType;
         private String journeyDate;
         private String from;
         private String to;

         Scanner sc=new Scanner(System.in);
         public int getPnrNum(){
            Random rdm=new Random();
            pnrNum=rdm.nextInt(max)+min;
            return pnrNum;
         }
         public String getPassName(){
            System.out.println("Enter PassengerName:");
            passName=sc.nextLine();
            return passName;
         }
         public String getTrainNum(){
            System.out.println("Enter trainNum:");
            trainNum=sc.nextLine();
            return trainNum;
         }
         public String getClassType(){
            System.out.println("Enter ClassType:");
            classType=sc.nextLine();
            return classType;
         }
         public String getJourneyDate(){
            System.out.println("Enter Journey Date");
            journeyDate=sc.nextLine();
            return journeyDate;
         }
         public String getFrom(){
            System.out.println("Enter Staing Place:");
            from=sc.nextLine();
            return from;
         }
         public String getTo(){
            System.out.println("Enter Destination place:");
            to=sc.nextLine();
            return to;
         }

     }
   
   
     public static void main(String[] args) throws Exception {
        
        Scanner sc=new Scanner(System.in);
        user obj=new user();
       
        String url="jdbc:mysql://localhost:3306/train";
        String userName=obj.getUserName();
        String passWord=obj.getPassword();
        try(Connection con=DriverManager.getConnection(url, userName, passWord)){
           System.out.println("User Connection Granted");
           while(true){
            String insertionQuery="insert into reservation values(?,?,?,?,?,?,?)";
            String deletionQuery="delete from reservation where pnr_number=?";
            String selectionQuery="select * from reservation";
            System.out.println("1.Insertion"+'\t'+"2.Deletion"+'\t'+"3.Printing Record"+'\t'+"4.Exit");
            System.out.print("\nEnter choice:");
            int choice=sc.nextInt();
            System.out.println();
            if(choice==1){
               PnrRec obj2=new PnrRec();
               int pnrNum=obj2.getPnrNum();
               String name=obj2.getPassName();
               String trainName=obj2.getTrainNum();
               String classType=obj2.getClassType();
               String journeyDate=obj2.getJourneyDate();
               String From=obj2.getFrom();
               String To=obj2.getTo();
               try(PreparedStatement preStm=con.prepareStatement(insertionQuery)){
                    preStm.setInt(1, pnrNum);
                    preStm.setString(2, name);
                    preStm.setString(3, trainName);
                    preStm.setString(4, classType);
                    preStm.setString(5, journeyDate);
                    preStm.setString(6, From);
                    preStm.setString(7, To);
               
                    int rowsAffected=preStm.executeUpdate();
                    if(rowsAffected>0){
                     System.out.println("Data inserted succesfully");
                    }
                    else{
                     System.out.println("Data is not inserted");
                    }
               }catch(Exception e){
                  System.out.println("SQL exception:"+e.getMessage());
               }
            
           }
           else if(choice==2){
            try(PreparedStatement preStm=con.prepareStatement(deletionQuery)){
                  System.out.println("Enter Pnr Number to delete entity:");
                  int DupPnr=sc.nextInt();
                  preStm.setInt(1, DupPnr);
                  int rowsAffected=preStm.executeUpdate();
                  if(rowsAffected==1){
                     System.out.println("succesfully data is deleted");
                  }else{
                     System.out.println("Entity is not deleted");
                  }
            }catch(Exception e){
               System.out.println("SQL Exception:"+e.getMessage());
            }
           }

           else if(choice==3){
            try(PreparedStatement preStm=con.prepareStatement(selectionQuery)){
               ResultSet rs=preStm.executeQuery();
               System.out.println("All Records:");
               while(rs.next()){
                  System.out.println("PNR Number: "+rs.getString("pnr_number"));
                  System.out.println("Passenger Name: "+rs.getString("pass_name"));
                  System.out.println("Train Number: "+rs.getString("train_num"));
                  System.out.println("Class Type: "+rs.getString("class_type"));
                  System.out.println("Journey Date: "+rs.getString("journey_date"));
                  System.out.println("From: "+rs.getString("from"));
                  System.out.println("To: "+rs.getString("to"));
                  System.out.println("------------------------------------");
               }
           }catch(Exception e){
            System.err.println("SQLException: " + e.getMessage());
           }
         }

         else if(choice==4){
            System.out.println("Exiting the program.\n");
            break;
         }

         else{
            System.out.println("Invalid Input\n");
         }

      }

      }catch (SQLException e) {
         System.out.println("Error while establishing connetion "+e.getMessage());
      
      }
      sc.close();
       
    } 
    
}
