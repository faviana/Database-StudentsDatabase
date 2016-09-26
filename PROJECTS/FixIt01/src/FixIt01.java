/**
 * Created by favianalopez on 9/7/16.
 */
public class FixIt01
{

    private static int x = 12;
    private static int y = 28;
    private static double z = 3.3;

    public static void main(String[] args)
    {
        System.out.println("The answer to(x * y) / 3.3 is :");
        calculate();
    }

    private static double calculate()
    {
        double total = (x * y) / z;
        System.out.println(total);
        return total;
    }
}
