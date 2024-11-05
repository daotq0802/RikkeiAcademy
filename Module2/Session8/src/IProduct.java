import java.util.Scanner;

public interface IProduct {
   float MIN_INTEREST_RATE= 0.2F;
   void inputData(Scanner scanner);
   String outputData();
   double calProfit();
}
