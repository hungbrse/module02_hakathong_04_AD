package ra.service;

import ra.model.Product;
import ra.run.BookManagement;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ProductService implements  IGenericService<Product,String>{
    @Override
    public List getAll() {
        return BookManagement.productList;
    }

    @Override
    public void save(Product product) {
        BookManagement.productList.add(product);
    }

    @Override
    public Product findById(String id) {
        return BookManagement.productList.stream().filter(product -> product.getProdictId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void delete(String id) {

        Product product = findById(id);
        if (product != null) {
            BookManagement.productList.remove(product);
            System.out.println(" sản phẩm  đã được xóa.");
        } else {
            System.out.println("Không tìm thấy sản phẩm với mã này .");
        }
    }

   public void ProductController(Scanner sc) {
        boolean isLoop = true;
        do {
            System.out.println("1. nhập thông tin sách ");
            System.out.println("2. hiển thị sách ");
            System.out.println("3. sắp xếp sách theo giá giảm dần ");
            System.out.println("4. xóa sách theo id ");
            System.out.println("5. tìm kiếm sách ");
            System.out.println("6. thay đổi thông tin sách ");
            System.out.println("7. thoát ");

            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    addProduct(sc);
                    break;
                    case 2:
                        showProduct();
                        break;
                        case 3:
                            showProductByPrice();
                            break;
                            case 4:
                                deleteProduct(sc);
                                break;
                                case 5:
                                    findProductByName(sc);
                                    case 6:
                                        updataProduct(sc);
                                        break;
                                        case 7:
                                            isLoop = false;
            }

        }while (isLoop);
   }
    public void updataProduct(Scanner sc) {
        System.out.println("nhập id muốn sửa ");
        String id = sc.nextLine();
        Product product = findById(id);
        if (product != null) {
          product.inputData(sc);
        } else  {
            System.err.println("id không tồn tại ");
        }
    }
    public void findProductByName(Scanner sc) {
        System.out.println("hãy nhập tên sách ");
        String name = sc.nextLine();
        BookManagement.productList.stream().filter(product -> product.getProdictName().contains(name)).toList().forEach(product -> product.displayProduct());

    }

    public void deleteProduct(Scanner sc) {
        System.out.println("nhập id catalog muốn xóa :");
        String id = sc.nextLine();
        Product product = findById(id);
        if (product != null) {
            delete(id);
        } else  {
            System.err.println(id + "không tồn tại");
        }

    }

    public void showProductByPrice() {
        Collections.sort(BookManagement.productList);
        BookManagement.productList.forEach(product -> product.displayProduct());
    }
public void showProduct() {

        BookManagement.productList.forEach(product -> product.displayProduct());
}

 public void addProduct(Scanner sc) {
     System.out.println( "nhập số sản phẩm cần thêm  ");
     int n = Integer.parseInt(sc.nextLine());
     for (int i = 0; i < n; i++) {
         Product product = new Product();
         product.inputData(sc);
         save(product);
     }
 }
}
