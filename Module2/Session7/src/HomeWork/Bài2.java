package HomeWork;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

public class Bài2 {


   public static void main(String[] args) {
      String string = "Nguyễn Thị Ngọc Xuân Óc Con Chó";
      String[] arrStr = string.split(" ");
      List<String> list = new ArrayList<String>();
      for(int i = 0; i < arrStr.length; i++){
         list.add(arrStr[i]);
      }
      list.stream().filter(s -> s.length()>3).forEach(System.out::println);
   }
}
