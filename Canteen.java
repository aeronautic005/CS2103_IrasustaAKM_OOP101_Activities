import java.util.Scanner;

public class Canteen {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String[] items = {
            "Chicken Rice",
            "Beef Burger",
            "Carbonara",
            "French Fries",
            "Iced Coffee"
        };

        double[] prices = {
            110.00,
            95.00,
            130.00,
            60.00,
            75.00
        };

        int totalItems = 0;
        double totalBeforeDiscount = 0.00;
        double totalDiscount = 0.00;

        String orderAgain = "Y";

        System.out.println("===== M E N U =====");

        for (int i = 0; i < items.length; i++) {
            System.out.printf("%d. %-12s - $%.2f%n",
                    i + 1, items[i], prices[i]);
        }

        while (orderAgain.equalsIgnoreCase("Y")) {

            System.out.print("\nEnter item number: ");
            int itemNumber = input.nextInt();

            System.out.print("Enter quantity: ");
            int quantity = input.nextInt();

            if (itemNumber < 1 || itemNumber > items.length
                    || quantity < 1 || quantity > 10) {

                System.out.println("\nInvalid order! Please enter a valid item and quantity.");

                System.out.print("\nDo you want to order again? (Y/N): ");
                orderAgain = input.next();

                continue;
            }

            System.out.print("Are you a student? (Y/N): ");
            String studentAnswer = input.next();

            while (!studentAnswer.equalsIgnoreCase("Y")
                    && !studentAnswer.equalsIgnoreCase("N")) {

                System.out.print("Please enter Y or N: ");
                studentAnswer = input.next();
            }

            boolean isStudent = studentAnswer.equalsIgnoreCase("Y");

            double subtotal = prices[itemNumber - 1] * quantity;

            double discountRate;

            if (isStudent && subtotal >= 500) {
                discountRate = 0.15;
            } else if (isStudent) {
                discountRate = 0.10;
            } else if (subtotal >= 500) {
                discountRate = 0.05;
            } else {
                discountRate = 0.00;
            }

            double discount = subtotal * discountRate;
            double orderTotal = subtotal - discount;

            System.out.printf("%nSubtotal: $%.2f%n", subtotal);
            System.out.printf("Discount: $%.2f%n", discount);
            System.out.printf("Order total: $%.2f%n", orderTotal);

            totalItems += quantity;
            totalBeforeDiscount += subtotal;
            totalDiscount += discount;

            System.out.print("\nDo you want to order again? (Y/N): ");
            orderAgain = input.next();
        }

        double finalAmount = totalBeforeDiscount - totalDiscount;

        System.out.println("\n===== ORDER SUMMARY =====");
        System.out.println("Total items: " + totalItems);
        System.out.printf("Total before discount: $%.2f%n", totalBeforeDiscount);
        System.out.printf("Total discount: $%.2f%n", totalDiscount);
        System.out.printf("Final amount: $%.2f%n", finalAmount);
        System.out.println("Thank you for ordering!");

        input.close();
    }
}