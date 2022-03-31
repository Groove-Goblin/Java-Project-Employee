package umsl.edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;

/**
 *
 * @author mamoh, jtdob
 */
public class RunEmployee 
{
    Employee[] myEmployees = new Employee[3]; // array 

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        RunEmployee re = new RunEmployee(); //create an instance 
        re.menu(); //can call the menu
    }

    public void menu() throws IOException, ClassNotFoundException
    {
        Scanner sc = new Scanner(System.in);
        int input;
        do
        {
            System.out.println("1) Populate Employees");
            System.out.println("2) Select Employee");
            System.out.println("3) Save Employee");
            System.out.println("4) Load Employee");
            System.out.println("5) Exit");
            System.out.println("Type in which number you want to select");
            input = Integer.parseInt(sc.next());
            
            if(input == 1)
            {
                populateEmployees();
            }
            else if(input == 2)
            {
                selectEmployee();
            }
            else if(input == 3)
            {
                saveEmployee();
            }
            else if (input == 4)
            {
                loadEmployee();
            }
        }
        while(input != 5);

    }
    public void populateEmployees()
    {
        String tempFname;
        String tempLname;
        int temphours;
        float temprate;

        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < myEmployees.length; i++)
        {
            if(myEmployees[i] == null)
            {
                System.out.println("Please enter your first name: ");
                tempFname = sc.next();
                System.out.println("Please enter your last name: ");
                tempLname = sc.next();
                System.out.println("Please enter the number of hours worked: ");
                temphours = Integer.parseInt(sc.next());
                System.out.println("Please enter your pay rate: ");
                temprate = Float.parseFloat(sc.next());

                myEmployees[i] = new Employee(tempFname, tempLname, temphours, temprate);
                break;
            }       
        }
    }
    public void selectEmployee() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int index = -99;
        
        for(int i = 0; i < myEmployees.length; i++)
        {
            System.out.println((i) + ") " + myEmployees[i].getFname() + " " + myEmployees[i].getLname() );
        }
        
        System.out.println("Enter the number of the name you want to select: ");
        index = Integer.parseInt(br.readLine());     
  
        if(index != -99)
        {
            myEmployees[index].menu();
        }
    }

    public void saveEmployee()
    {
        try
        {
            
            FileOutputStream fos = new FileOutputStream("SerializedEmpInfo.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            oos.writeObject(myEmployees);
            oos.flush();
            fos.close();
            
            
            
            
            File myfile = new File("employeeINFO.txt");
            // if file doesnt exists, then create it
            if (!myfile.exists()) //if this file doesn't exist then 
            {
                myfile.createNewFile();  //create a new file
            }
            FileWriter fw = new FileWriter(myfile, false); //change to true to append to the file
            BufferedWriter bw = new BufferedWriter(fw);
            for(int i = 0; i < myEmployees.length; i++)
            {
                String tempFname = myEmployees[i].getFname(); 
                String tempLname = myEmployees[i].getLname();
                float tempgross = myEmployees[i].gross; 
                String tempStringgross = Double.toString(tempgross); 
                float temptax = myEmployees[i].tax;
                String tempStringtax = Double.toString(temptax);
                float tempnet_percent = myEmployees[i].net_percent;
                 String tempStringnet_percent = Double.toString(tempnet_percent);
                bw.write(tempFname);
                bw.write(tempLname);
                bw.write("|"); //seperator
                bw.write(tempStringgross);
                bw.write("|");
                bw.write(tempStringtax);
                bw.write("|");
                bw.write(tempStringnet_percent);
                bw.newLine(); //create a new line and go through the loop to the next array
            } //creating lines of account information 
            //System.out.println("Done");
            bw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void loadEmployee() throws ClassNotFoundException
    {
        boolean goodLoad = false;
        try
        {
        
             //System.out.println("You are in load employee");
            FileInputStream fis = new FileInputStream("SerializedEmpInfo.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            myEmployees = (Employee[])ois.readObject();
            fis.close();
            goodLoad = true;
         }
        catch(IOException e)
        {
            e.printStackTrace();            
        }
        
        if(goodLoad == false){
            System.out.println("UNSUCCESSFUL EMPLOYEE LOAD");           
        }else{
            System.out.println("LOAD SUCCESSFUL, EMPLOYEE INFO HAS BEEN PULLED FROM FILE");
        }
                    
    }
}