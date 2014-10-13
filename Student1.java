import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class Student1
{
    private String name;
    private int age;
    public Student1(String nn, int aa)
    {
        name=nn;
        age=aa;
    }
    public int getAge()
    {
        return age;
    }
    public String getName()
    {
        return name;
    }
}