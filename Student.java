import java.util.*;
import java.io.*;

public class Student implements Serializable
{
   private String Student_name;
   private int Student_id;
   ArrayList Student_courses = new ArrayList(); //using reference variable technique
   public Student()
   {
       
   }
   
   public void setStudent_name(String a)
   {
       Student_name = a;
   }
   
   public String getStudent_name()
   {
       return Student_name;
    }
    
   public void setStudent_id(int a)
   {
       Student_id = a;
    }
    
   public int getStudent_id()
   {
       return Student_id;
    }
    
   public void updateArrayList(Course a)
   {
       Student_courses.add(a);
    }
    
   public ArrayList getStudentCourses()
   {
       return Student_courses;
    }
}
