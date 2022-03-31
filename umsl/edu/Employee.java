package umsl.edu;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author mamoh, jtdob
 */
public class Employee implements Serializable
{
    private String Fname; //fields or attributes
    private String Lname;

    float rate=0.0f;
    float taxrate=0.2f;
    int hours=0;
    float gross=0.0f;
    float tax=0.0f;
    float net=0.0f;
    float net_percent=0.0f;

    public Employee() //default constructor
    { 
        Fname = "Jane";
        Lname = "Doe"; 
     
    }
     public Employee(String Fname, String Lname, int hours, float rate) //parameter constructor
    {
        this.Fname = Fname;
        this.Lname = Lname;
        this.hours = hours;
        this.rate = rate;
    }
    public void menu()
    {
        Scanner sc = new Scanner(System.in);
        int input;
        do
        {
            System.out.println("1) Calculate Gross Pay");
            System.out.println("2) Calculate Tax");
            System.out.println("3) Calculate Net Pay");
            System.out.println("4) Calculate Net Percent");
            System.out.println("5) Display employee");
            System.out.println("6) Exit");
            System.out.println("Type in which number you want to select");
            input = Integer.parseInt(sc.next());
            
            if(input == 1)
            {
                computeGross();
            }
            else if(input == 2)
            {
                computeTax();
            }
            else if(input == 3)
            {
                computeNet();
            }
            else if (input == 4)
            {
                computeNetperc();
            }
            else if (input == 5)
            {
                displayEmployee();
            }
        }
        while(input != 6);
    }

      public void computeGross()
   {    
       DecimalFormat df = new DecimalFormat("#.00");       
       if (hours <= 40)
       {
           gross = hours * rate;
       }
       else if (hours > 40)
       {
           float reggross = 40 * rate;
           float overhours = hours - 40;
           float overgross = (float) (overhours * rate * 1.5);
           gross = reggross + overgross;
       }
       System.out.println("Your gross pay = " + df.format(gross));
   }
    protected void computeTax()
    {
        DecimalFormat df = new DecimalFormat("#.00");
        if (hours <= 40)
       {
           gross = hours * rate;
       }
       else if (hours > 40)
       {
           float reggross = 40 * rate;
           float overhours = hours - 40;
           float overgross = (float) (overhours * rate * 1.5);
           gross = reggross + overgross;
       }
     tax = taxrate * gross; 
     System.out.println("Your taxes = " + df.format(tax));
    }
    protected void computeNet()
    {
        DecimalFormat df = new DecimalFormat("#.00");
        if (hours <= 40)
       {
           gross = hours * rate;
       }
       else if (hours > 40)
       {
           float reggross = 40 * rate;
           float overhours = hours - 40;
           float overgross = (float) (overhours * rate * 1.5);
           gross = reggross + overgross;
       }
        tax = taxrate * gross; 
        net = gross - tax;
        System.out.println("Your net pay = " + df.format(net));
    }
    protected void computeNetperc()    
    {
        DecimalFormat df = new DecimalFormat("#.00");
        if (hours <= 40)
       {
           gross = hours * rate;
       }
       else if (hours > 40)
       {
           float reggross = 40 * rate;
           float overhours = hours - 40;
           float overgross = (float) (overhours * rate * 1.5);
           gross = reggross + overgross;
       }
        tax = taxrate * gross; 
        net = gross - tax;
        net_percent = net / gross;
        System.out.println("Your net percentage = " + df.format(net_percent));
    }
    protected void displayEmployee()
    {
        computeGross();
        computeTax();
        computeNet();
        computeNetperc();
        
    }

    /**
     * @return the Fname
     */
    public String getFname() 
    {
        return Fname;
    }
    public void setFname(String Fname) 

    /**
     * @param Fname the Fname to set
     */
    {
        this.Fname = Fname;
    }

    /**
     * @return the Lname
     */
    public String getLname() 
    {
        return Lname;
    }

    /**
     * @param Lname the Lname to set
     */
    public void setLname(String Lname) 
    {
        this.Lname = Lname;
    }
}