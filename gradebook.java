import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;


public class gradebook extends JFrame implements Serializable
/*add a student
 * add a course to a student record
 * print an itemized student record
 * exit
 */
{
private Button endProgram;
private Button addStudent;
private Button addCourse;
private Button printResults;
private Student[] stu = new Student[10];
private int NumStudents = 0;
Container cat;
private JPanel topPanel;
private JTable table;
private JScrollPane scrollPane;
private int capacity = 0;
private String[][] data;
private String[][] tempData;
private int courseCounter = 0;
private int sizeCounter = 0;
public gradebook()
{
    super("Setting up and using GUI components ");
    
    //Creates 10 student objects and stores them in a student array
    for (int a = 0; a <= 9; a++)
    {
        stu[a] = new Student();
    }
 
    //Check to see if the data file is exists and if true then read the data from the file
    boolean exists = (new File("DATAFILE")).exists(); 
    if (exists) 
    { 
         JOptionPane.showMessageDialog(null,"good"); 
         try 
         { 
             FileInputStream filein = new FileInputStream("DATAFILE"); 
             ObjectInputStream oin = new ObjectInputStream(filein); 
             NumStudents=oin.readInt(); 
             for(int aa=0;aa<NumStudents;aa++)
 
                stu[aa]=(Student)oin.readObject(); 
             oin.close(); 
         } 
         catch(IOException e) {JOptionPane.showMessageDialog(null,e); }
 
         catch(ClassNotFoundException ff) { JOptionPane.showMessageDialog(null,"Sorry");} 
    } 
    else 
    {
        JOptionPane.showMessageDialog(null,"no file"); 
    }
    
    ButtonHandler handler = new ButtonHandler();
    cat = getContentPane();
    cat.setLayout(new FlowLayout());

    addStudent = new Button("Add Student");
    addStudent.addActionListener(handler);
    cat.add(addStudent);

    addCourse = new Button("Add Course To Student");
    addCourse.addActionListener(handler);
    cat.add(addCourse);

    printResults = new Button("Print a Student Record");
    printResults.addActionListener(handler);
    cat.add(printResults);

    endProgram = new Button("Exit");
    endProgram.addActionListener(handler);
    cat.add(endProgram);

    setSize(800,600);
    setVisible(true);
}

public class ButtonHandler implements ActionListener
{
public void actionPerformed(ActionEvent e)
{
    if(e.getSource() == addStudent) //Get information from user and sets a student object to entered information
    {
        String name = JOptionPane.showInputDialog("Enter a Student Name");
        String str_id = JOptionPane.showInputDialog("Enter a Student ID Number");
        int id = Integer.parseInt(str_id);
        stu[NumStudents].setStudent_name(name);
        stu[NumStudents].setStudent_id(id);
        NumStudents++;
        return;
    }
    else if(e.getSource() == addCourse) //Creates a course object and sets the course object to the entered information from the user. Also adds the course object to the student object.
    {
        String temp, letter, tempID;
        char letterGrade;
        int course, studentID;
        int index = 0;
        
        ArrayList tempList;
        temp = JOptionPane.showInputDialog("Enter a Course ID Number");
        course = Integer.parseInt(temp);
        letter = JOptionPane.showInputDialog("Enter the Letter Grade");
        letterGrade = letter.charAt(0);
        Course newCourse = new Course();
        newCourse.setCourse_id(course);
        newCourse.setCourse_letter(letterGrade);
        tempID = JOptionPane.showInputDialog("Enter the Student ID to add course");
        studentID = Integer.parseInt(tempID);
        for (int a = 0; a < NumStudents; a++)
        {
            if (stu[a].getStudent_id() == studentID)
            index = a;
        }
        stu[index].updateArrayList(newCourse);        
    }        
    else if(e.getSource() == printResults)  //prints out each student with their course ids, grade, and gpa
    {
        if (NumStudents == 0)
        {
            return;
        }
        
        ArrayList alist;
        Course cr;
        int courseID = 0;
        char courseGrade = 'z';
        double totalPoints = 0;
        double gpa = 0;        
        
        setTitle("Student Results");
        setSize(500,300);
        setBackground(Color.gray);
        
        topPanel = new JPanel();
        topPanel.setLayout( new BorderLayout());
        getContentPane().add(topPanel);
        
        String[] columnNames = {"Student ID", "Student Name", "Course ID", "Grade", "GPA"};
        data = new String[capacity][5];
        
        //Get the data from each student object and store it into a data array which will be passed to a Jtable to be printed
        for (int b = 0; b < NumStudents; b++)
        {            
            alist = stu[b].getStudentCourses();
            tempData = new String[alist.size()][5];            
            tempData[0][0] = stu[b].getStudent_id()+ "";
            tempData[0][1] = stu[b].getStudent_name() + "";
            
            for (int c = 0; c < alist.size(); c++)
            {
                cr = (Course) alist.get(c);
                tempData[c][2] = cr.getCourse_id() + "";;
                tempData[c][3] = cr.getCourse_letter()+ "";
                
                //add up total points to be able to calculate the gpa
                if (tempData[c][3].equals("A") || tempData[c][3].equals("a"))
                {
                    totalPoints += 4 * 3;
                }
                else if (tempData[c][3].equals("B") || tempData[c][3].equals("b"))
                {
                    totalPoints += 3 * 3;
                }
                else if (tempData[c][3].equals("C") || tempData[c][3].equals("c"))
                {
                    totalPoints += 2 * 3;
                }
                else if (tempData[c][3].equals("D") || tempData[c][3].equals("d"))
                {
                    totalPoints += 1 * 3;
                }
                courseCounter++; //keep track of total courses for current student object
                sizeCounter++;  //keep track of the size needed for the data array
            }
            gpa = totalPoints / (alist.size() * 3);
            tempData[0][4] = gpa + "";
            totalPoints = 0;
            
            if (b != 0)
            {
                if (sizeCounter >= capacity) //call resize method if the size counter is larger than the current data array's capacity
                {
                    resize();
                }
            
                for (int d = 0; d < courseCounter; d++)
                {
                    for (int f = 0; f < 5; f++)
                    {
                        data[d + capacity - courseCounter][f] = tempData[d][f];
                    }
                }
            }
            else
            {
                if (sizeCounter >= capacity) //call resize method if the size counter is larger than the current data array's capacity
                {
                    resize();
                }
            
                for (int d = 0; d < tempData.length; d++)
                {
                    System.arraycopy(tempData[d], 0, data[d], 0, 5);
                }
            }           
            courseCounter = 0;            
        }
        
        table = new JTable( data, columnNames);
        scrollPane = new JScrollPane(table);
        topPanel.add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
        
        return;
    }
    else if(e.getSource()== endProgram) //write the data to a file and exit the program
    {
        try
        {
        FileOutputStream fos = new FileOutputStream("DATAFILE");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
 
        oos.writeInt(NumStudents);
        for (int a = 0; a < NumStudents; a++)
        oos.writeObject(stu[a]);
        
        oos.flush();
        oos.close();
        }
        catch (IOException err)
        {
            
        }        
        System.exit(0);
    }
}

//Create a bigger data array when the current data array reaches its capacity
public void resize()
{
    capacity += courseCounter;
    String[][] a = new String[capacity][5];
    if (data.length == 0)
    data = a;
    for (int b = 0; b < data.length; b++)
    {
        System.arraycopy(data[b], 0, a[b], 0, 5);
    }
    data = a;
}
}

public static void main(String[] args)
{
    gradebook app = new gradebook();
}
}