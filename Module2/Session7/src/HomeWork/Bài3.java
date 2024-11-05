package HomeWork;

import java.util.ArrayList;
import java.util.List;

public class Bài3 {
   public static void main(String[] args) {
      List<Integer> list = new ArrayList<>();
      for(int i=0;i<10;i++){
         list.add((int) (Math.random()*10));
      }
      System.out.println(list);
      list.stream().reduce(Integer::sum).ifPresent(System.out::println);
   }
}
