import java.util.*;
import java.io.*;

class Testmain{
  public static void main(String[] args){
    File fil1 = new File("3.in");
    Labyrint x = null;
    try{
      x = Labyrint.lesFraFil(fil1);
      System.out.println(x);
    }
    catch(FileNotFoundException e){
      System.out.println("Rare greier");
    }
    x.finnUtveiFra(5, 3);
    
  }
}
