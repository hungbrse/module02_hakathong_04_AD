package ra.user;

import ra.business.entity.Catalog;
import ra.model.Product;
import ra.run.BookManagement;

import java.util.Optional;
import java.util.Scanner;

public class User {
    private int cartItemId;
    private Product product;
    private int quantity;

    public User() {
    }

    public User(int quantity, Product product, int cartItemId) {
        this.quantity = quantity;
        this.product = product;
        this.cartItemId = cartItemId;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItem) {
        this.cartItemId = cartItemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void inputShoppingCart(Scanner sc) {
        this.cartItemId = inputCartId();
        this.product = inputProduct(sc);
        this.quantity = inputQuantity(sc);
    }

    private Product inputProduct(Scanner sc) {
        System.out.println("id cartItem hiện tại " + this.cartItemId);
        BookManagement.productList.forEach(product -> product.displayProduct());
        System.out.println("hãy chọn id sản phẩm ");
        do {
            String choiceId = sc.nextLine();
            Optional<Product> productOptional = BookManagement.productList.stream().filter(product -> product.getProdictId().equals(choiceId)).findFirst();

            if (productOptional.isPresent()) {
                return productOptional.get();
            } else {
                System.err.println("id sản phẩm không tồn tại ");
            }

        } while (true);
    }

    private int inputCartId() {
        int maxId = BookManagement.CartList.stream()
                .mapToInt(User::getCartItemId)
                .max()
                .orElse(0);

        if (maxId == 0) {
            maxId = 1;
            return maxId;
        } else {
            return maxId + 1;
        }
    }

    private int inputQuantity(Scanner sc) {
        System.out.println("Nhập số lượng sản phẩm muốn mua: ");
        do {
            try {
                int quantity = Integer.parseInt(sc.nextLine());
                if (quantity > 0) {
                    if (product.getStock() >= quantity) {
                        product.setStock(product.getStock() - quantity);
                        System.out.println("Sản phẩm đã được thêm vào giỏ hàng thành công. Số lượng còn lại trong kho: " + product.getStock());
                        return quantity;
                    } else {
                        System.err.println("Số lượng sản phẩm không đủ trong kho. Số lượng còn lại: " + product.getStock());
                    }
                } else {
                    System.err.println("Số lượng phải lớn hơn 0.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập số hợp lệ.");
            }
        } while (true);
    }
    public void displayShoppingCart() {
        System.out.printf("[CartItemId : %d | Product : %s | quantity :%d ]\n",
                cartItemId,
                product.getProdictName(),
                quantity
        );
    }

}
