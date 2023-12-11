import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

class Food implements Serializable
{
    int itemno;
    int quantity;   
    float price;
    
    Food(int itemno,int quantity)
    {
        this.itemno=itemno;
        this.quantity=quantity;
        switch(itemno)
        {
            case 1:price=quantity*50;
                break;
            case 2:price=quantity*60;
                break;
            case 3:price=quantity*70;
                break;
            case 4:price=quantity*30;
                break;
        }
    }
}
class Singleroom implements Serializable
{
    String name;
    String contact;
    String gender;   
    ArrayList<Food> food =new ArrayList<>();

   
    Singleroom()
    {
        this.name="";
    }
    Singleroom(String name,String contact,String gender)
    {
        this.name=name;
        this.contact=contact;
        this.gender=gender;
    }
}
class Doubleroom extends Singleroom implements Serializable
{ 
    String name2;
    String contact2;
    String gender2;  
    
    Doubleroom()
    {
        this.name="";
        this.name2="";
    }
    Doubleroom(String name,String contact,String gender,String name2,String contact2,String gender2)
    {
        this.name=name;
        this.contact=contact;
        this.gender=gender;
        this.name2=name2;
        this.contact2=contact2;
        this.gender2=gender2;
    }
}


class holder implements Serializable
{
    Doubleroom luxury_doublerrom[]=new Doubleroom[10]; //Luxury
    Doubleroom deluxe_doublerrom[]=new Doubleroom[20]; //Deluxe
    Singleroom luxury_singleerrom[]=new Singleroom[10]; //Luxury
    Singleroom deluxe_singleerrom[]=new Singleroom[20]; //Deluxe
}

class Hotel
{
    static holder hotel_ob=new holder();
    static Scanner sc = new Scanner(System.in);
    static void CustDetails(int i,int rn)
    {
        String name, contact, gender;
        String name2 = null, contact2 = null; 
        String gender2="";
        System.out.print("\nEnter customer name: ");
        name = sc.next();
        System.out.print("Enter contact number: ");
        contact=sc.next();
        System.out.print("Enter gender: ");
        gender = sc.next();
        if(i<3)
        {
        System.out.print("Enter second customer name: ");
        name2 = sc.next();
        System.out.print("Enter contact number: ");
        contact2=sc.next();
        System.out.print("Enter gender: ");
        gender2 = sc.next();
        }      
        
          switch (i) {
            case 1:hotel_ob.luxury_doublerrom[rn]=new Doubleroom(name,contact,gender,name2,contact2,gender2);
                break;
            case 2:hotel_ob.deluxe_doublerrom[rn]=new Doubleroom(name,contact,gender,name2,contact2,gender2);
                break;
            case 3:hotel_ob.luxury_singleerrom[rn]=new Singleroom(name,contact,gender);
                break;
            case 4:hotel_ob.deluxe_singleerrom[rn]=new Singleroom(name,contact,gender);
                break;
            default:System.out.println("Wrong option");
                break;
        }
    }
    static void bookroom(int roomTypeIndex, Object[] roomArray, int baseNumber) {
        int j;
        int rn;
        System.out.println("\nChoose room number from : ");
        for (j = 0; j < roomArray.length; j++) {
            if (roomArray[j] == null) {
                System.out.print((j + baseNumber) + ",");
            }
        }
        System.out.print("\nEnter room number: ");
        try {
            rn = sc.nextInt();
            rn--;
            if (roomArray[rn] != null)
                throw new IllegalStateException("Room not available");
            CustDetails(roomTypeIndex, rn);
        } catch (Exception e) {
            System.out.println("Invalid Option");
            return;
        }
        System.out.println("Room Booked");
    }

    static void bookroom(int i) {
        Object[] roomDetails;
        int baseNumber;
        switch (i) {
            case 1:
                roomDetails = hotel_ob.luxury_doublerrom;
                baseNumber = 1;
                break;
            case 2:
                roomDetails = hotel_ob.deluxe_doublerrom;
                baseNumber = 11;
                break;
            case 3:
                roomDetails = hotel_ob.luxury_singleerrom;
                baseNumber = 31;
                break;
            case 4:
                roomDetails = hotel_ob.deluxe_singleerrom;
                baseNumber = 41;
                break;
            default:
                System.out.println("Enter valid option");
                return;
        }
        bookroom(i, roomDetails, baseNumber);
    }


    
    static void features(int i)
    {
        switch (i) {
            case 1:System.out.println("Number of double beds : 1\nAC : Yes\nFree breakfast : Yes\nCharge per day:4000 ");
                break;
            case 2:System.out.println("Number of double beds : 1\nAC : No\nFree breakfast : Yes\nCharge per day:3000  ");
                break;
            case 3:System.out.println("Number of single beds : 1\nAC : Yes\nFree breakfast : Yes\nCharge per day:2200  ");
                break;
            case 4:System.out.println("Number of single beds : 1\nAC : No\nFree breakfast : Yes\nCharge per day:1200 ");
                break;
            default:
                System.out.println("Enter valid option");
                break;
        }
    }


    static int countAvailableRooms(Object[] rooms) {
        int count = 0;
        for (Object room : rooms) {
            if (room == null) {
                count++;
            }
        }
        return count;
    }
    	static void availability(int i) {
    		    int availableRooms = 0;
    		    switch (i) {
    		        case 1:
    		            availableRooms = countAvailableRooms(hotel_ob.luxury_doublerrom);
    		            break;
    		        case 2:
    		            availableRooms = countAvailableRooms(hotel_ob.deluxe_doublerrom);
    		            break;
    		        case 3:
    		            availableRooms = countAvailableRooms(hotel_ob.luxury_singleerrom);
    		            break;
    		        case 4:
    		            availableRooms = countAvailableRooms(hotel_ob.deluxe_singleerrom);
    		            break;
    		        default:
    		            System.out.println("Enter valid option");
    		            return;
    		    }
    		    System.out.println("Number of rooms available : " + availableRooms);
    		}

    
     static void displayRoomCharge(int roomCharge) {
        System.out.println("\nRoom Charge - " + roomCharge);
        System.out.println("\n===============");
        System.out.println("Food Charges:- ");
        System.out.println("===============");
        System.out.println("Item   Quantity    Price");
        System.out.println("-------------------------");
    }
     static double calculateFoodCharges(ArrayList<Food> foods, String[] foodList) {
        double foodCharge = 0;
        String format = "%-10s%-10s%-10s%n";
        for (Food food : foods) {
            foodCharge += food.price;
            System.out.printf(format, foodList[food.itemno - 1], food.quantity, food.price);
        }
        return foodCharge;
    }
    static void bill(int rn, int rtype) {
        double amount = 0;
        String[] list = {"Sandwich", "Pasta", "Noodles", "Coke"};

        System.out.println("\n*******");
        System.out.println(" Bill:-");
        System.out.println("*******");

        switch (rtype) {
            case 1:
                displayRoomCharge(4000);
                amount += 4000 + calculateFoodCharges(hotel_ob.luxury_doublerrom[rn].food, list);
                break;
            case 2:
                displayRoomCharge(3000);
                amount += 3000 + calculateFoodCharges(hotel_ob.deluxe_doublerrom[rn].food, list);
                break;
            case 3:
                displayRoomCharge(2200);
                amount += 2200 + calculateFoodCharges(hotel_ob.luxury_singleerrom[rn].food, list);
                break;
            case 4:
                displayRoomCharge(1200);
                amount += 1200 + calculateFoodCharges(hotel_ob.deluxe_singleerrom[rn].food, list);
                break;
            default:
                System.out.println("Not valid");
        }
        System.out.println("\nTotal Amount- " + amount);
    }

    
    static void deallocate(int rn, int rtype) {
        switch (rtype) {
            case 1:
            case 2:
            case 3:
            case 4:
                handleRoomDeallocation(rn, rtype);
                break;
            default:
                System.out.println("\nEnter valid option : ");
                break;
        }
    }

    static void handleRoomDeallocation(int rn, int rtype) {
        String[] roomNames = {"luxury_doublerrom", "deluxe_doublerrom", "luxury_singleerrom", "deluxe_singleerrom"};
        String roomName = roomNames[rtype - 1];

        if (isRoomBooked(roomName, rn)) {
            String roomUsedBy = getRoomUsedBy(roomName, rn);
            System.out.println("Room used by " + roomUsedBy);
            System.out.println("Do you want to checkout ? (y/n)");
            char w = sc.next().charAt(0);
            if (w == 'y' || w == 'Y') {
                bill(rn, rtype);
                deallocateRoom(roomName, rn);
                System.out.println("Deallocated successfully");
            }
        } else {
            System.out.println("Empty Already");
        }
    }

    static boolean isRoomBooked(String roomName, int rn) {
        switch (roomName) {
            case "luxury_doublerrom":
                return hotel_ob.luxury_doublerrom[rn] != null;
            case "deluxe_doublerrom":
                return hotel_ob.deluxe_doublerrom[rn] != null;
            case "luxury_singleerrom":
                return hotel_ob.luxury_singleerrom[rn] != null;
            case "deluxe_singleerrom":
                return hotel_ob.deluxe_singleerrom[rn] != null;
            default:
                return false;
        }
    }

    static String getRoomUsedBy(String roomName, int rn) {
        switch (roomName) {
            case "luxury_doublerrom":
                return hotel_ob.luxury_doublerrom[rn].name;
            case "deluxe_doublerrom":
                return hotel_ob.deluxe_doublerrom[rn].name;
            case "luxury_singleerrom":
                return hotel_ob.luxury_singleerrom[rn].name;
            case "deluxe_singleerrom":
                return hotel_ob.deluxe_singleerrom[rn].name;
            default:
                return "";
        }
    }

    static void deallocateRoom(String roomName, int rn) {
        switch (roomName) {
            case "luxury_doublerrom":
                hotel_ob.luxury_doublerrom[rn] = null;
                break;
            case "deluxe_doublerrom":
                hotel_ob.deluxe_doublerrom[rn] = null;
                break;
            case "luxury_singleerrom":
                hotel_ob.luxury_singleerrom[rn] = null;
                break;
            case "deluxe_singleerrom":
                hotel_ob.deluxe_singleerrom[rn] = null;
                break;
        }
    }


    
    static void order(int rn,int rtype)
    {
        int i,q;
        char wish;
         try{
             System.out.println("\n==========\n   Menu:  \n==========\n\n1.Sandwich\tRs.50\n2.Pasta\t\tRs.60\n3.Noodles\tRs.70\n4.Coke\t\tRs.30\n");
        do
        {
            i = sc.nextInt();
            System.out.print("Quantity- ");
            q=sc.nextInt();
           
              switch(rtype){
            case 1: hotel_ob.luxury_doublerrom[rn].food.add(new Food(i,q));
                break;
            case 2: hotel_ob.deluxe_doublerrom[rn].food.add(new Food(i,q));
                break;
            case 3: hotel_ob.luxury_singleerrom[rn].food.add(new Food(i,q));
                break;
            case 4: hotel_ob.deluxe_singleerrom[rn].food.add(new Food(i,q));
                break;                                                 
        }
              System.out.println("Do you want to order anything else ? (y/n)");
              wish=sc.next().charAt(0); 
        }while(wish=='y'||wish=='Y');  
        }
         catch(NullPointerException e)
            {
                System.out.println("\nRoom not booked");
            }
         catch(Exception e)
         {
             System.out.println("Cannot be done");
         }
    }
}


class write implements Runnable
{
    holder hotel_ob;
    write(holder hotel_ob)
    {
        this.hotel_ob=hotel_ob;
    }
    @Override
    public void run() {
          try{
        FileOutputStream fout=new FileOutputStream("backup");
        ObjectOutputStream oos=new ObjectOutputStream(fout);
        oos.writeObject(hotel_ob);
        }
        catch(Exception e)
        {
            System.out.println("Error in writing "+e);
        }         
        
    }
    
}

public class Main_v2 {
    public static void main(String[] args){
        
        try
        {           
        File f = new File("backup");
        if(f.exists())
        {
            FileInputStream fin=new FileInputStream(f);
            ObjectInputStream ois=new ObjectInputStream(fin);
            Hotel.hotel_ob=(holder)ois.readObject();
        }
        Scanner sc = new Scanner(System.in);
        int ch,ch2;
        char wish;
        x:
        do{

        System.out.println("\nEnter your choice :\n1.Display room details\n2.Display room availability \n3.Book\n4.Order food\n5.Checkout\n6.Exit\n");
        ch = sc.nextInt();
        switch(ch){
            case 1: System.out.println("\nChoose room type :\n1.Luxury Double Room \n2.Deluxe Double Room \n3.Luxury Single Room \n4.Deluxe Single Room\n");
                    ch2 = sc.nextInt();
                    Hotel.features(ch2);
                break;
            case 2:System.out.println("\nChoose room type :\n1.Luxury Double Room \n2.Deluxe Double Room \n3.Luxury Single Room\n4.Deluxe Single Room\n");
                     ch2 = sc.nextInt();
                     Hotel.availability(ch2);
                break;
            case 3:System.out.println("\nChoose room type :\n1.Luxury Double Room \n2.Deluxe Double Room \n3.Luxury Single Room\n4.Deluxe Single Room\n");
                     ch2 = sc.nextInt();
                     Hotel.bookroom(ch2);                     
                break;
            case 4:
                 System.out.print("Room Number -");
                     ch2 = sc.nextInt();
                     if(ch2>60)
                         System.out.println("Room doesn't exist");
                     else if(ch2>40)
                         Hotel.order(ch2-41,4);
                     else if(ch2>30)
                         Hotel.order(ch2-31,3);
                     else if(ch2>10)
                         Hotel.order(ch2-11,2);
                     else if(ch2>0)
                         Hotel.order(ch2-1,1);
                     else
                         System.out.println("Room doesn't exist");
                     break;
            case 5:                 
                System.out.print("Room Number -");
                     ch2 = sc.nextInt();
                     if(ch2>60)
                         System.out.println("Room doesn't exist");
                     else if(ch2>40)
                         Hotel.deallocate(ch2-41,4);
                     else if(ch2>30)
                         Hotel.deallocate(ch2-31,3);
                     else if(ch2>10)
                         Hotel.deallocate(ch2-11,2);
                     else if(ch2>0)
                         Hotel.deallocate(ch2-1,1);
                     else
                         System.out.println("Room doesn't exist");
                     break;
            case 6:break x;
                
        }
           
            System.out.println("\nContinue : (y/n)");
            wish=sc.next().charAt(0); 
            if(!(wish=='y'||wish=='Y'||wish=='n'||wish=='N'))
            {
                System.out.println("Invalid Option");
                System.out.println("\nContinue : (y/n)");
                wish=sc.next().charAt(0); 
            }
            
        }while(wish=='y'||wish=='Y');    
        
        Thread t=new Thread(new write(Hotel.hotel_ob));
        t.start();
        }        
            catch(Exception e)
            {
                System.out.println("Not a valid input");
            }
    }
}
