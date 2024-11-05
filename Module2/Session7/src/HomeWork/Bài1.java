package HomeWork;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bài1 {
   public static void main(String[] args) {
      List<Integer> list = new ArrayList<Integer>();
      for(int i=0;i<10;i++){
         list.add((int) (Math.random()*10));
      }
      System.out.println(Collections.max(list));
   }
}
