import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
 
public class LinkedListApp
{
    public LinkedListApp() //build a linked list and sort the list based on user's preference
    {
        String sort;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter high or low for desired sort method: ");
        sort = in.next();
        String[] name = {"DREW BARRYMORE", "SCARLETT JOHANSSON", "JENNIFER ANISTON", "CAMERON DIAZ", "JULIA ROBERTS", "NATALIE PORTMAN", "JESSICA ALBA", "JENNIFER LOPEZ", "ANGELINA JOLIE", "NICOLE KIDMAN", "BLAKE LIVELY", "ANNE HATHAWAY", "MILA KUNIS", "JENNIFER LAWRENCE", "EMMA WATSON", "ALYSSA MILANO", "JENNIFER LOVE HEWITT", "PAMELA ANDERSON", "BROOKE BURKE", "DENISE RICHARDS", "KEIRA KNIGHTLY", "REESE WITHERSPOON", "KRISTEN STEWART", "KATHERINE KEIGL", "SANDRA BULLOCK"};
        int [] age = {39, 30, 45, 42, 47, 33, 33, 45, 39, 47, 27, 32, 33, 24, 24, 42, 35, 47, 43, 43, 29, 38, 24, 36, 50};
 
        Node head=null;
        Node iterNode, trailingNode;

        for(int a=0;a<25;a++)
        {
            name[a] = fixName(name[a]);
            Student1 test = new Student1(name[a],age[a]);   //  build student object
            Node newNode = new Node(test);         //  build Node object
            if(head==null)
            {
                head = newNode;
                continue;
            }
 
            iterNode = head.next;
            trailingNode=head;
            if (sort.equals("low") || sort.equals("Low")) //sorts as the linked list is being built
            {
                if (name[a].compareTo(head.data.getName()) < 0)
                {
                    newNode.next = head;
                    head = newNode;
                }
                else
                {
                    while(iterNode != null && name[a].compareTo(iterNode.data.getName()) > 0 )       
                    {
                        trailingNode = iterNode;
                        iterNode = iterNode.next;
                    }
                    trailingNode.next = newNode;
                    newNode.next = iterNode;
                }
            }
            else if (sort.equals("high") || sort.equals("High")) //sorts as the linked list is being built
            {
                if (name[a].compareTo(head.data.getName()) > 0)
                {
                    newNode.next = head;
                    head = newNode;
                }
                else
                {
                    while(iterNode != null && name[a].compareTo(iterNode.data.getName()) < 0 )       
                    {
                        trailingNode = iterNode;
                        iterNode = iterNode.next;
                    }
                    trailingNode.next = newNode;
                    newNode.next = iterNode;
                }
            }
        }
 
        // loop thru linked list to print the values of Student objects
        String out = "";
        iterNode=head;
        while(iterNode !=null)
        {
            out += iterNode.data.getName() +" "+iterNode.data.getAge()+"\n";
            iterNode=iterNode.next;
        }
        System.out.println();
        System.out.println(out); 
    }

    public String fixName(String str) //fixes the name to a traditional "last name, first name middle name" format
    {
        String str1, first = "", middle = "", last = "", newName;
        int pos = 0, posOfSpace, totalSpace = 0;
        str1 = str.toLowerCase();
        for (int a = 0; a < str1.length(); a++)
        {
            if (Character.toString(str1.charAt(a)).equals(" "))
                totalSpace++;
        }
        
        for (int b = 0; b < totalSpace; b++)
        {
            posOfSpace = str1.indexOf(" ", pos);
            if (totalSpace == 1)
            {
                first = str1.substring(0, 1).toUpperCase() + str1.substring(1, posOfSpace);
                last = str1.substring(posOfSpace + 1, posOfSpace + 2).toUpperCase() + str1.substring(posOfSpace + 2);
                continue;
            }
            if (b == 0)
            {
                first = str1.substring(0, 1).toUpperCase() + str1.substring(1, posOfSpace);
            }
            else
            {
                middle = str1.substring(pos, pos + 1).toUpperCase() + str1.substring(pos + 1, posOfSpace);
                last = str1.substring(posOfSpace + 1, posOfSpace + 2).toUpperCase() + str1.substring(posOfSpace + 2);
            }
            pos = posOfSpace + 1;
        }
        newName = last + ", " + first + " " + middle;
        return newName;
    }
    public static void main(String[] args)
    {
        LinkedListApp app = new LinkedListApp();
        System.exit(0);
    }
}