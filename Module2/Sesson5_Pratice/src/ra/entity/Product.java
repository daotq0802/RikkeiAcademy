package ra.entity;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Product {
    private String productID;
    private String productName;
    private float importPrice;
    private float exportPrice;
    private float profit;
    private int quantity;
    private String description;
    private boolean status;

    public Product() {
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public float getProfit() {
        return profit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String inputProductID(Scanner sc, Product[] products) {
        System.out.print("Enter product ID: ");
        while (true) {
            productID = sc.nextLine();
            if (productID.isEmpty() || productID.trim().isEmpty()) {
                System.err.println("Invalid product ID");
            } else {
                boolean found = false;
                if (Pattern.matches("^P[0-9]{3}", productID)) {
                    for (Product product : products) {
                        if (product.getProductID().equals(productID)) {
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        System.err.println("Invalid product ID");
                    } else {
                        return productID;
                    }
                } else {
                    System.err.println("Product ID not match");
                }
            }
        }
    }

    public String inputProductName(Scanner sc, Product[] products) {
        System.out.print("Enter product name: ");
        while (true) {
            productName = sc.nextLine();
            if (productName.isEmpty() || productName.trim().isEmpty()) {
                System.err.println("Invalid product name");
            } else {
                boolean found = false;
                if (productName.length() >= 6 && productName.length() <= 50) {
                    for (Product product : products) {
                        if (product.getProductName().equals(productName)) {
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        System.err.println("Invalid product name");
                    } else {
                        return productName;
                    }
                } else {
                    System.err.println("Product name must be between 6 and 50 characters");
                }
            }
        }
    }

    public float inputImportPrice(Scanner sc) {
        try {
            while (true) {
                importPrice = Float.parseFloat(sc.nextLine());
                if (importPrice <= 0) {
                    System.err.println("Invalid product import price");
                } else {
                    return importPrice;
                }
            }
        } catch (Exception e) {
            System.err.println("Enter a valid product import price");
            return inputImportPrice(sc);
        }
    }

    public float inputExportPrice(Scanner sc) {
        while (true) {
            try {
                exportPrice = Float.parseFloat(sc.nextLine());
                if (exportPrice <= 0) {
                    System.err.println("Invalid product export price");
                } else {
                    if (exportPrice >= importPrice * 1.2) {
                        return exportPrice;
                    } else {
                        System.err.println("Export price must be bigger than 20% of import price");
                    }
                }
            } catch (Exception e) {
                System.err.println("Enter a valid product export price");
                return inputExportPrice(sc);
            }
        }
    }

    public int inputQuantity(Scanner sc) {
        try {
            while (true) {
                quantity = Integer.parseInt(sc.nextLine());
                if (quantity <= 0) {
                    System.err.println("Invalid product quantity");
                } else {
                    return quantity;
                }
            }
        } catch (Exception e) {
            System.err.println("Enter a valid product quantity");
            return inputQuantity(sc);
        }
    }

    public String inputDescription(Scanner sc) {
        System.out.print("Enter product description: ");
        String description = "";
        try {
            while (true) {
                description = sc.nextLine();
                if (description.isEmpty() || description.trim().isEmpty()) {
                    System.err.println("Invalid product description");
                } else {
                    return description;
                }
            }
        } catch (Exception e) {
            System.err.println("Enter a valid product description");
        }
        return description;
    }

    public boolean inputStatus(Scanner sc) {
        System.out.print("Enter product status: ");
        String status = "";
        try {
            while (true) {
                status = sc.nextLine();
                if (status.equals("true") || status.equals("false")) {
                    return Boolean.parseBoolean(status);
                } else {
                    System.err.println("Invalid product status");
                }
            }
        } catch (Exception e) {
            System.err.println("Enter a valid product status");
        }
        return Boolean.parseBoolean(status);
    }

    public float calProfit() {
        this.profit = this.exportPrice - this.importPrice;
        return this.profit;
    }

    public void inputData(Scanner sc, Product[] products) {
        this.productID = inputProductID(sc, products);
        this.productName = inputProductName(sc, products);
        System.out.print("Enter product import price: ");
        this.importPrice = inputImportPrice(sc);
        System.out.print("Enter product export price: ");
        this.exportPrice = inputExportPrice(sc);
        System.out.print("Enter product quantity: ");
        this.quantity = inputQuantity(sc);
        this.description = inputDescription(sc);
        this.status = inputStatus(sc);
        this.profit = calProfit();
    }

    public String displayData() {
        return "Product ID: " + productID + '\t' +
                "Product Name: '" + productName + '\n' +
                "Import Price: " + importPrice + '\t' +
                "Export Price: " + exportPrice + '\n' +
                "Profit: " + profit + '\t' +
                "Quantity: " + quantity + '\n' +
                "Description: " + description + '\n' +
                "Status: " + (status ? "For Sale" : "Not For Sale");
    }
}
