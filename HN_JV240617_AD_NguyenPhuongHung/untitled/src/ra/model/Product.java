package ra.model;

import ra.business.entity.Catalog;
import ra.run.BookManagement;

import java.util.Optional;
import java.util.Scanner;

public class Product implements Comparable<Product> {
    private String prodictId;
    private String prodictName;
    private double productPrice;
    private String prodictDescription;
    public   int stock;
    private Catalog catalog;
    private boolean status;

    public String getProdictId() {
        return prodictId;
    }

    public void setProdictId(String prodictId) {
        this.prodictId = prodictId;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getProdictDescription() {
        return prodictDescription;
    }

    public void setProdictDescription(String prodictDescription) {
        this.prodictDescription = prodictDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProdictName() {
        return prodictName;
    }

    public void setProdictName(String prodictName) {
        this.prodictName = prodictName;
    }

    public Product(String prodictId, Catalog catalog, boolean status, int stock, String prodictDescription, String prodictName, double productPrice) {
        this.prodictId = prodictId;
        this.catalog = catalog;
        this.status = status;
        this.stock = stock;
        this.prodictDescription = prodictDescription;
        this.prodictName = prodictName;
        this.productPrice = productPrice;
    }

    public Product() {
    }

    public void inputData(Scanner sc) {
        this.prodictId = inputpProdictId(sc);
        this.prodictName = inputProdictName(sc);
        this.productPrice = inputProductPrice(sc);
        this.prodictDescription = inputProdictDescription(sc);
        this.stock = inputStock(sc);
        this.catalog = inputCatalog(sc);
        this.status = inputStatus(sc);
    }

    private String inputpProdictId(Scanner sc) {
        System.out.println("hãy nhập id sản phẩm ");
        do {
            String id = sc.nextLine();
            if (id.matches("^P\\d{4}$")) {
                Optional<Product> isExist = BookManagement.productList.stream().filter(product -> product.prodictId.equals(id)).findFirst();
                if (isExist.isPresent()) {
                    System.out.println(id +" đã tồn tại");
                }else  {
                    return id;
                }
            }else  {
                System.err.println("hãy nhập id đúng định dạng");
            }

        }while (true);
    }

    private Catalog inputCatalog(Scanner sc) {
        BookManagement.catelogList.forEach(catalog -> catalog.displayCatalog() );
        System.out.println("hãy nhập id catalog");
        do {
            int id = Integer.parseInt(sc.nextLine());

            Optional<Catalog> catalogOptional = BookManagement.catelogList.stream().filter(catalog ->  catalog.getCatalogid() == id).findFirst();
            if (catalogOptional.isPresent()) {
                return catalogOptional.get();
            }else  {
                System.err.println(id +"không tồn tại");
            }

        }while (true);

    }

    private boolean inputStatus(Scanner sc) {
        System.out.println("hãy nhập trạng thát ");
        do {
            String status = sc.nextLine();
            if (status.equals("true") || status.equals("false")) {
                return Boolean.parseBoolean(status);
            }else  {
                System.err.println("hãy nhập trạng thái true or false ");
            }
        }while (true);
    }

    private int inputStock(Scanner sc) {
        System.out.println("nhập số lượng >= 10");
        do {
            try {
                int stock = Integer.parseInt(sc.nextLine());
                if (stock< 10 ) {
                    System.err.println("nhập số lượng phải lớn hơn 10 ");
                } else  {
                    return stock ;
                }
            }catch (NumberFormatException e) {
                System.err.println("nhập số lượng không đúng đinh dạng ");
            }

        }while (true);
    }

    private String inputProdictDescription(Scanner sc) {

        System.out.println("hãy nhập productDescription :");
        do {
            String productDescription = sc.nextLine();
            if (productDescription.isBlank()) {
                System.err.println("đừng để tên trông ");
            }else  {
                return  productDescription;
            }

        }while (true);
    }

    private String inputProdictName(Scanner sc) {

        System.out.println("hãy nhập productName :");
        do {
            String productName = sc.nextLine();
            if (productName.isBlank()) {
                System.err.println("đừng để tên trông ");
            }else  {
                return  productName;
            }

        }while (true);
    }

    private double inputProductPrice(Scanner sc) {
        System.out.println("hãy nhập giá ");
        do {

         try {
             double productPrice = Double.parseDouble(sc.nextLine());
             if (productPrice < 0) {
                 System.err.println("giá phải lớn hơn không ");
             } else  {
                 return productPrice;
             }
         }catch (NumberFormatException e) {
             System.err.println("nhập giá không đúng đinh dạng ");
         }

        }while (true);
    }

    @Override
    public String toString() {
        return "Product{" +
                "prodictId=" + prodictId +
                ", prodictName='" + prodictName + '\'' +
                ", productPrice=" + productPrice +
                ", prodictDescription='" + prodictDescription + '\'' +
                ", stock=" + stock +
                ", catalog=" + catalog +
                ", status=" + (status  ? "bán " : "không bán ")+
                '}';
    }
    public void displayProduct() {
        System.out.printf("[productId : %s | productName : %s | productPrice : %.2f | productDescription : %s | stock : %d | Catalog : %s | status : %s]\n",
                prodictId,
                prodictName,
                productPrice,
                prodictDescription,
                stock,
                catalog.getCatalogname(),
                status ? "đang bán" : "không bán"
        );
    }

    @Override
    public int compareTo(Product o) {
        return Double.compare(o.getProductPrice() , this.productPrice);
    }
}
