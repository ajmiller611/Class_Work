// person class

public class Person {
private String Name;
private char sex;
private String score;
private int[] grades = new int[5];
private String newName;
public Person()   
{
    
}
public void setName(String g)     //receieves name
{Name = g; }
public void setSex(char c)        //receives gender
{sex = c;}
public void setScore(String cat)  //receives scores
{score = cat;}
public void fixName()
{
 /* HERE IS WHERE YOU TAKE YOUR STRING name
  CONVERT IT TO LOWER CASE, BREAK IT INTO 2 STRINGS
  (FIRST AND LAST), CONVERT THE FIRST LETTER OF EACH
  PART TO CAPITAL, AND CONCATENATE IT BACK TOGETHER AND
  STORE IT IN newname.
*/
    String lowcase = Name.toLowerCase();
    int posOfSpace = lowcase.indexOf(" ");
    String first = lowcase.substring(0, posOfSpace);
    String last = lowcase.substring(posOfSpace + 1);
    newName = last.substring(0, 1).toUpperCase() + last.substring(1, last.length()) + ", " + first.substring(0, 1).toUpperCase() + first.substring(1, first.length());  
} 
public void fixScore()
{
  /*HERE YOU TAKE YOUR STRING CALLED scores ABOVE AND USE
   AND STORE IT IN YOUR INTEGER ARRAY CALLED GRADES (ABOVE). 
*/
    int pos = 0;
        for (int a = 0; a < grades.length; a++) {
            int posOfComma = score.indexOf(",", pos);
            if (a != grades.length - 1) {
                grades[a] = Integer.parseInt(score.substring(pos, posOfComma));
                pos = posOfComma + 1;
            }
            else {
                grades[a] = Integer.parseInt(score.substring(pos, score.length()));
            }               
        }
}
public String getName()  //returns fixed name
{return newName;}
public char getSex()     //returns gender
{return sex;}
//---provide a function that will return the 5 scores for that person.
public int[] getgrades()
{return grades;}
}
