import java.io.RandomAccessFile;
import java.io.EOFException;
import java.io.IOException;
import java.util.Vector;

public class First_314_Project
{
    private RandomAccessFile raf;
    //The entire path to the location of the file
    private final String fileToRead = "DATA314";
    private Vector vector = new Vector();
    private String temp;
    private int c=0;
    private Person  pp;
    private String[] Names = new String[10];
    private char[] Sex = new char[10];
    private int[][] Score = new int[10][5];
    private float[] averages = new float[10];
    private char[] letter = new char[10];
    public First_314_Project()
    {      
         int counter=0;
         try{
            raf = new RandomAccessFile(fileToRead,"r");
            do{
                temp=raf.readUTF();
                System.out.println(temp);
                Person p = new Person();
                int c = temp.indexOf(':');
                String g = temp.substring(0,c);
                char b = temp.charAt(c+1);
                String cat = temp.substring(c+3,temp.length());
                p.setName(g);
                p.setSex(b);
                p.setScore(cat);
                p.fixName();
                p.fixScore();
                counter++;
                vector.add(p);  // add person object to vector
                
              }
             while(counter<10);
            counter=0;
           
           function1();      
           function2();      
       }
        catch(IOException e)
        {System.err.println("File Error:" + e);}
    }
    public void function1()
     {
        // this function will receive all arrays and compute average, letter grade
        // and sort the arrays based upon average (high to low)
        int a, b, c, d;
        System.out.println();
        for (a = 0; a < 10; a++)
        {
            pp = (Person) vector.get(a);
            Names[a] = pp.getName();
            Sex[a] = pp.getSex();
            int[] tempArray = pp.getgrades();
            int totalScore = 0;
            for (b = 0; b < 5; b++)
            {
                Score[a][b] = tempArray[b];
                totalScore += Score[a][b];
            }
            averages[a] = (float) totalScore / 5;
            //find letter grade after taking rounding into consideration
            if (averages[a] >= 89.5) 
            { letter[a] = 'A'; }
            else if (averages[a] < 89.5 && averages[a] >= 79.5)
            { letter[a] = 'B'; }
            else if (averages[a] < 79.5 && averages[a] >= 69.5)
            { letter[a] = 'C'; }
            else if (averages[a] < 69.5 && averages[a] >= 59.5)
            { letter[a] = 'D'; }
            else if (averages[a] < 59.5)
            { letter[a] = 'F'; }
        }
        float[] tempAvg = new float[10];
        float temp1 = 0;
        System.arraycopy(averages, 0, tempAvg, 0, 10);
        //Selection Sort high to low
        for (c = 0; c < 9; c++)
        {
            for (d = c + 1; d < 10; d++)
            {
                if (averages[c] < averages[d])
                {
                    temp1 = averages[c];
                    averages[c] = averages[d];
                    averages[d] = temp1;                   
                }
            }            
        }
                
        int[] position = new int[10];
        String[] tempNames = new String[10];
        char[] tempSex = new char[10];
        char[] tempLetter = new char[10];
        int counter = 0;
        int duplicateCounter = 0;
        float current;
        //Sort other arrays based on averages array
        for (a = 0; a < 10; a++)
        {   
            current = averages[a];
            for (b = 0; b < 10; b++)
            {
                if (averages[a] == tempAvg[b])
                {
                    if (current == averages[b] && b > counter) //finds duplicates
                    {
                        duplicateCounter++;
                        position[a] = b;
                        a += duplicateCounter;
                        
                    }
                    else
                    {
                        position[a] = b;
                    }
                }               
            }
            counter++;
        }
        
        for (a = 0; a < 10; a++)
        {
            tempNames[a] = Names[position[a]];
            tempSex[a] = Sex[position[a]];
            tempLetter[a] = letter[position[a]];
        }
        Names = tempNames;
        Sex = tempSex;
        letter = tempLetter;
    }
    public void function2( )
      {
        // this function prints out the sorted data
        System.out.printf("%-18s %5s %10s %7s %n", "Name", "Sex", "Average", "Grade");
        for (int a = 0; a < 10; a++)
            System.out.printf("%-18s %5s %10.1f %7s %n", Names[a], Sex[a], averages[a], letter[a]);
      }

    public static void main()
    {
        new First_314_Project();
    }
}

