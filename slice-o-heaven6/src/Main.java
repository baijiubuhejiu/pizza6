import java.util.Scanner;


enum PizzaSelection {
    MARGHERITA("Margherita", "Tomato Sauce, Mozzarella Cheese, Basil", 12.0),
    PEPPERONI("Pepperoni", "Tomato Sauce, Mozzarella Cheese, Pepperoni", 14.0),
    HAWAIIAN("Hawaiian", "Tomato Sauce, Mozzarella Cheese, Ham, Pineapple", 15.0),
    VEGGIE("Veggie", "Tomato Sauce, Mozzarella Cheese, Bell Peppers, Onions, Mushrooms", 13.0),
    MEAT_LOVERS("Meat Lovers", "Tomato Sauce, Mozzarella Cheese, Sausage, Bacon, Pepperoni", 16.0);

    private final String name;
    private final String ingredients;
    private final double price;

    PizzaSelection(String name, String ingredients, double price) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - Ingredients: " + ingredients + ", Price: $" + price;
    }
}


enum PizzaToppings {
    TOMATO_SAUCE("Tomato Sauce", 1.0),
    MOZZARELLA_CHEESE("Mozzarella Cheese", 1.5),
    PEPPERONI("Pepperoni", 2.0),
    HAM("Ham", 2.0),
    PINEAPPLE("Pineapple", 1.5),
    BELL_PEPPERS("Bell Peppers", 1.0),
    ONIONS("Onions", 1.0),
    MUSHROOMS("Mushrooms", 1.0),
    SAUSAGE("Sausage", 2.0),
    BACON("Bacon", 2.0);

    private final String name;
    private final double price;

    PizzaToppings(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - Price: $" + price;
    }
}


enum PizzaSize {
    SMALL("Small", 0.0),
    MEDIUM("Medium", 2.0),
    LARGE("Large", 4.0);

    private final String sizeName;
    private final double priceAdjustment;

    PizzaSize(String sizeName, double priceAdjustment) {
        this.sizeName = sizeName;
        this.priceAdjustment = priceAdjustment;
    }

    public String getSizeName() {
        return sizeName;
    }

    public double getPriceAdjustment() {
        return priceAdjustment;
    }

    @Override
    public String toString() {
        return sizeName + " - Price Adjustment: $" + priceAdjustment;
    }
}


enum SideDish {
    GARLIC_BREAD("Garlic Bread", 3.0),
    ONION_RINGS("Onion Rings", 3.5),
    FRENCH_FRIES("French Fries", 3.0);

    private final String name;
    private final double priceAdjustment;

    SideDish(String name, double priceAdjustment) {
        this.name = name;
        this.priceAdjustment = priceAdjustment;
    }

    public String getName() {
        return name;
    }

    public double getPriceAdjustment() {
        return priceAdjustment;
    }

    @Override
    public String toString() {
        return name + " - Price Adjustment: $" + priceAdjustment;
    }
}


enum Drinks {
    COKE("Coke", 2.0),
    PEPSI("Pepsi", 2.0),
    SPRITE("Sprite", 2.0);

    private final String name;
    private final double priceAdjustment;

    Drinks(String name, double priceAdjustment) {
        this.name = name;
        this.priceAdjustment = priceAdjustment;
    }

    public String getName() {
        return name;
    }

    public double getPriceAdjustment() {
        return priceAdjustment;
    }

    @Override
    public String toString() {
        return name + " - Price Adjustment: $" + priceAdjustment;
    }
}

class PizzaOrderSystem {
    private static final double PIZZA_BASE_PRICE = 10.0;
    private String[] pizzasOrdered;
    private String[] pizzaSizesOrdered;
    private String[] sideDishesOrdered;
    private String[] drinksOrdered;
    private double totalOrderPrice;
    private int orderCount;

    public PizzaOrderSystem() {
        pizzasOrdered = new String[100]; 
        pizzaSizesOrdered = new String[100];
        sideDishesOrdered = new String[100];
        drinksOrdered = new String[100];
        totalOrderPrice = 0.0;
        orderCount = 0;
    }

    public void takeOrder() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Pizza Menu:");
            int index = 1;
            for (PizzaSelection pizza : PizzaSelection.values()) {
                System.out.println(index + ". " + pizza);
                index++;
            }
            System.out.println(index + ". Custom Pizza");
            System.out.println("Enter your pizza choice (1 - " + index + "), or 0 to finish ordering: ");
            int pizzaChoice = scanner.nextInt();
            scanner.nextLine(); 

            if (pizzaChoice == 0) {
                break;
            }

            if (pizzaChoice >= 1 && pizzaChoice <= PizzaSelection.values().length) {
                PizzaSelection selectedPizza = PizzaSelection.values()[pizzaChoice - 1];
                pizzasOrdered[orderCount] = selectedPizza.toString();
                totalOrderPrice += selectedPizza.getPrice();
            } else if (pizzaChoice == PizzaSelection.values().length + 1) {
                System.out.println("Available Pizza Toppings:");
                PizzaToppings[] toppings = PizzaToppings.values();
                for (int i = 0; i < toppings.length; i++) {
                    System.out.println((i + 1) + ". " + toppings[i]);
                }
                double customPizzaPrice = PIZZA_BASE_PRICE;
                StringBuilder customPizzaIngredients = new StringBuilder("Custom Pizza - Ingredients: ");
                int toppingCount = 0;
                while (true) {
                    System.out.println("Select a topping (1 - " + toppings.length + "), or 0 to finish choosing toppings: ");
                    int toppingChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (toppingChoice == 0) {
                        break;
                    }
                    if (toppingChoice >= 1 && toppingChoice <= toppings.length && toppingCount < 10) {
                        PizzaToppings selectedTopping = toppings[toppingChoice - 1];
                        customPizzaIngredients.append(selectedTopping.getName()).append(", ");
                        customPizzaPrice += selectedTopping.getPrice();
                        toppingCount++;
                    } else {
                        System.out.println("Invalid choice or maximum toppings reached.");
                    }
                }
                if (customPizzaIngredients.length() > 0) {
                    customPizzaIngredients.delete(customPizzaIngredients.length() - 2, customPizzaIngredients.length());
                }
                customPizzaIngredients.append(", Price: $").append(customPizzaPrice);
                pizzasOrdered[orderCount] = customPizzaIngredients.toString();
                totalOrderPrice += customPizzaPrice;
            } else {
                System.out.println("Invalid pizza choice.");
                continue;
            }


            System.out.println("Available Pizza Sizes:");
            PizzaSize[] sizes = PizzaSize.values();
            for (int i = 0; i < sizes.length; i++) {
                System.out.println((i + 1) + ". " + sizes[i]);
            }
            System.out.println("Select a pizza size (1 - " + sizes.length + "): ");
            int sizeChoice = scanner.nextInt();
            scanner.nextLine();
            if (sizeChoice >= 1 && sizeChoice <= sizes.length) {
                PizzaSize selectedSize = sizes[sizeChoice - 1];
                pizzaSizesOrdered[orderCount] = selectedSize.toString();
                totalOrderPrice += selectedSize.getPriceAdjustment();
            }


            System.out.println("Available Side Dishes:");
            SideDish[] sideDishes = SideDish.values();
            for (int i = 0; i < sideDishes.length; i++) {
                System.out.println((i + 1) + ". " + sideDishes[i]);
            }
            System.out.println("Select a side dish (1 - " + sideDishes.length + "): ");
            int sideDishChoice = scanner.nextInt();
            scanner.nextLine(); 
            if (sideDishChoice >= 1 && sideDishChoice <= sideDishes.length) {
                SideDish selectedSideDish = sideDishes[sideDishChoice - 1];
                sideDishesOrdered[orderCount] = selectedSideDish.toString();
                totalOrderPrice += selectedSideDish.getPriceAdjustment();
            }


            System.out.println("Available Drinks:");
            Drinks[] drinks = Drinks.values();
            for (int i = 0; i < drinks.length; i++) {
                System.out.println((i + 1) + ". " + drinks[i]);
            }
            System.out.println("Select a drink (1 - " + drinks.length + "): ");
            int drinkChoice = scanner.nextInt();
            scanner.nextLine();
            if (drinkChoice >= 1 && drinkChoice <= drinks.length) {
                Drinks selectedDrink = drinks[drinkChoice - 1];
                drinksOrdered[orderCount] = selectedDrink.toString();
                totalOrderPrice += selectedDrink.getPriceAdjustment();
            }

            orderCount++;
        }
    }

    @Override
    public String toString() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("Order Receipt:\n");
        for (int i = 0; i < orderCount; i++) {
            receipt.append("Order ").append(i + 1).append(":\n");
            receipt.append("Pizza: ").append(pizzasOrdered[i]).append("\n");
            receipt.append("Size: ").append(pizzaSizesOrdered[i]).append("\n");
            receipt.append("Side Dish: ").append(sideDishesOrdered[i]).append("\n");
            receipt.append("Drink: ").append(drinksOrdered[i]).append("\n");
        }
        receipt.append("Total Order Price: $").append(totalOrderPrice);
        return receipt.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        PizzaOrderSystem system = new PizzaOrderSystem();
        system.takeOrder();
        System.out.println(system.toString());
    }
}