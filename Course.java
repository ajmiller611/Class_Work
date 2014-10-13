import java.io.*;

public class Course implements Serializable
{
    private int Course_id;
    private char Course_letter;
    public Course()
    {
       
    }
    
    public void setCourse_id(int a)
    {
        Course_id = a;
    }
    
    public int getCourse_id()
    {
        return Course_id;
    }
    
    public void setCourse_letter(char a)
    {
        Course_letter = a;
    }
    
    public char getCourse_letter()
    {
        return Course_letter;
    }
}