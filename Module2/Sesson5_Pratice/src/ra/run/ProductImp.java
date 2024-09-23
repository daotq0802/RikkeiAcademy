package ra.run;

import ra.CafeShop.Category;
import ra.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductImp {
    private static Product[] products = new Product[0];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int choice = printMenu(sc);
            if (products.length == 0) {
                if (choice <= 0 || choice > 2) {
                    System.err.println("Select menu again!!");
                } else {
                    switch (choice) {
                        case 1:
                            addProduct(sc);
                            break;
                        case 2:
                            System.out.println("See you again!!");
                            System.exit(0);
                            break;
                    }
                }
            } else {
                if (choice <= 0 || choice > 10) {
                    System.err.println("Select menu again!!");
                } else {
                    switch (choice) {
                        case 1:
                            addProduct(sc);
                            break;
                        case 2:
                            showAllProducts(products);
                            break;
                        case 3:
                            profitProduct();
                            break;
                        case 4:
                            sortProductsByProfit();
                            break;
                        case 5:
                            sortProductsFromPriceToPrice(sc);
                            break;
                        case 6:
                            searchProductsByName(sc);
                            break;
                        case 7:
                            importProducts(sc);
                            break;
                        case 8:
                            sellProducts(sc);
                            break;
                        case 9:
                            updateStatus(sc);
                            break;
                        case 10:
                            System.out.println("See you again!!");
                            System.exit(0);
                            break;
                    }
                }
            }
        }
    }

    public static int printMenu(Scanner sc) {
        List<String> listMenu = new ArrayList<>();
        try {
            listMenu.add("Add new product");
            listMenu.add("Show all products");
            listMenu.add("Calculate profit all products");
            listMenu.add("Sort profit from up to down");
            listMenu.add("Sort products from price to price");
            listMenu.add("Search products by name");
            listMenu.add("Import products");
            listMenu.add("Sell products");
            listMenu.add("Update status products");
            listMenu.add("Exit program");
            if (products.length == 0) {
                System.out.println("1. Add new product");
                System.out.println("2. Exit program");
            } else {
                for (int i = 0; i < listMenu.size(); i++) {
                    System.out.println(i + 1 + ". " + listMenu.get(i));
                }
            }
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static void addProduct(Scanner sc) {
        System.out.print("Enter number of products want to add: ");
        while (true) {
            try {
                int num = Integer.parseInt(sc.nextLine());
                if (num <= 0) {
                    System.err.println("Input start from 1");
                } else {
                    Product[] newProducts = new Product[products.length + num];
                    for (int i = 0; i < newProducts.length; i++) {
                        if (i < products.length) {
                            newProducts[i] = products[i];
                        } else {
                            System.out.println("Enter information of product");
                            Product product = new Product();
                            product.inputData(sc, products);
                            newProducts[i] = product;
                            System.out.println("----------------------------------");
                        }
                    }
                    products = newProducts;
                    break;
                }
            } catch (Exception e) {
                System.err.println("Input must be number!");
            }

        }
    }

    public static void showAllProducts(Product[] products) {
        for (Product product : products) {
            System.out.println(product.displayData());
            System.out.println("----------------------------------");
        }
    }

    public static void profitProduct() {
        for (Product product : products) {
            System.out.println("Product ID: " + product.getProductID()
                    + "\t-\tProduct name: " + product.getProductName()
                    + "\nProfit: " + product.getProfit());
            System.out.println("----------------------------------");
        }
    }

    public static void sortProductsByProfit() {
        Product[] sortProducts = products;
        for (int i = 0; i < sortProducts.length; i++) {
            for (int j = i + 1; j < sortProducts.length; j++) {
                if (sortProducts[i].getProfit() < sortProducts[j].getProfit()) {
                    Product temp = sortProducts[i];
                    sortProducts[i] = sortProducts[j];
                    sortProducts[j] = temp;
                }
            }
        }
        showAllProducts(sortProducts);
    }

    public static void sortProductsFromPriceToPrice(Scanner sc) {
        System.out.println("Enter price to price - Input example: xxx.xxx - xxx.xxx ");
        try {
            boolean isRangeGood = false;
            while (!isRangeGood) {
                String price = sc.nextLine();
                double start = Double.parseDouble(price.split(" - ")[0]);
                double end = Double.parseDouble(price.split(" - ")[1]);
                if (start >= end) {
                    System.err.println("Price range not good");
                } else {
                    int count = 0;
                    for (Product product : products) {
                        if (product.getExportPrice() >= start && end >= product.getExportPrice()) {
                            count++;
                            System.out.println(product.displayData());
                            System.out.println("----------------------------------");
                        }
                    }
                    if (count > 0) {
                        System.out.println("Found " + count + " product(s)");
                    } else {
                        System.out.println("No product(s) found");
                    }
                    isRangeGood = true;
                }
            }
        } catch (Exception e) {
            System.err.println("Invalid price range");
            sortProductsFromPriceToPrice(sc);
        }
    }

    public static void searchProductsByName(Scanner sc) {
        System.out.println("Enter product name: ");
        try {
            while (true) {
                String name = sc.nextLine();
                int count = 0;
                for (Product product : products) {
                    if (product.getProductName().toLowerCase().contains(name.toLowerCase())) {
                        System.out.println(product.displayData());
                        System.out.println("----------------------------------");
                        count++;
                    }
                }
                if (count > 0) {
                    System.out.println("Found " + count + " product(s)");
                    break;
                } else {
                    System.err.println("No product(s) found");
                }
            }
        } catch (Exception e) {
            System.err.println("Invalid name");
        }
    }

    public static void importProducts(Scanner sc) {
        System.out.println("Enter product ID: ");
        String addProductID = sc.nextLine();
        int index = -1;
        for (int i = 0; i < products.length; i++) {
            if (products[i].getProductID().equals(addProductID)) {
                index = i;
            }
        }
        if (index == -1) {
            System.err.println("Product ID not found");
        } else {
            System.out.print("Enter product quantity want to add: ");
            while (true) {
                try {
                    int quantity = Integer.parseInt(sc.nextLine());
                    if (quantity > 0) {
                        products[index].setQuantity(products[index].getQuantity() + quantity);
                        System.out.println("Has add " + quantity + " to product " + products[index].getProductID());
                        break;
                    } else {
                        System.err.println("Input quantity invalid");
                    }
                } catch (Exception e) {
                    System.err.println("Quantity must be numbers");
                }
            }
        }
    }

    public static void sellProducts(Scanner sc) {
        System.out.println("Enter product ID: ");
        String sellProductID = sc.nextLine();
        int index = -1;
        for (int i = 0; i < products.length; i++) {
            if (products[i].getProductID().equals(sellProductID)) {
                index = i;
            }
        }
        if (index == -1) {
            System.err.println("Product ID not found");
        } else {
            System.out.println("Product ID: " + products[index].getProductID() + " has " + products[index].getQuantity() + " in stock");
            System.out.print("Enter product quantity want to sell: ");
            while (true) {
                try {
                    int quantity = Integer.parseInt(sc.nextLine());
                    if (quantity > 0) {
                        if (products[index].getQuantity() - quantity >= 0) {
                            products[index].setQuantity(products[index].getQuantity() - quantity);
                            System.out.println("Has sold " + quantity + " to product " + products[index].getProductID());
                            break;
                        } else {
                            System.err.println("Quantity not enough");
                        }
                    } else {
                        System.err.println("Input quantity invalid");
                    }
                } catch (Exception e) {
                    System.err.println("Quantity must be numbers");
                }
            }
        }
    }

    public static void updateStatus(Scanner sc) {
        System.out.println("Enter product ID: ");
        String sellProductID = sc.nextLine();
        int index = -1;
        for (int i = 0; i < products.length; i++) {
            if (products[i].getProductID().equals(sellProductID)) {
                index = i;
            }
        }
        if (index == -1) {
            System.err.println("Product ID not found");
        } else {
            if (products[index].isStatus()) {
                while (true) {
                    System.out.println("This product is already selling, want to stop?");
                    try {
                        System.out.println("1. Yes");
                        System.out.println("2. No");
                        int choice = Integer.parseInt(sc.nextLine());
                        if (choice == 1) {
                            products[index].setStatus(false);
                            System.out.println("Status have been updated");
                            break;
                        } else if (choice == 2) {
                            break;
                        } else {
                            System.err.println("Input wrong, try again!!");
                        }
                    } catch (Exception e) {
                        System.err.println("Invalid choice");
                    }
                }
            } else {
                while (true) {
                    System.out.println("This product is not selling, want to sell?");
                    try {
                        System.out.println("1. Yes");
                        System.out.println("2. No");
                        int choice = Integer.parseInt(sc.nextLine());
                        if (choice == 1) {
                            products[index].setStatus(true);
                            System.out.println("Status have been updated");
                            break;
                        } else if (choice == 2) {
                            break;
                        } else {
                            System.err.println("Input wrong, try again!!");
                        }
                    } catch (Exception e) {
                        System.err.println("Invalid choice");
                    }
                }
            }
        }
    }
}
