package ra.service;

import ra.model.Product;
import ra.run.BookManagement;
import ra.user.User;

import java.util.List;
import java.util.Scanner;

public class UserService implements  IGenericService<User,Integer>{


    @Override
    public List<User> getAll() {
        return BookManagement.CartList;
    }

    @Override
    public void save(User user) {
        BookManagement.CartList.add(user);
    }

    @Override
    public User findById(Integer id) {
        return BookManagement.CartList.stream().filter(cartItem -> cartItem.getCartItemId() == id).findFirst().orElse(null);
    }

    @Override
    public void delete(Integer id) {

        User cartItem = findById(id);
        if (cartItem != null) {
            BookManagement.CartList.remove(cartItem);
            System.out.println(" sản phẩm  đã được xóa.");
        } else {
            System.out.println("Không tìm thấy sản phẩm với mã này .");
        }
    }
    public void showProduct() {
        BookManagement.productList.forEach(product -> product.displayProduct());
    }
    public void addToCart(Scanner sc) {
        BookManagement.productList.forEach(product -> product.displayProduct());
        System.out.println("nhập id sản phẩm muốn thêm vào giỏ hàng ");
        User cartItem = new User();
        cartItem.inputShoppingCart(sc);
        save(cartItem);
    }
    public void showCart() {
        BookManagement.CartList.forEach(cartItem ->cartItem.displayShoppingCart());
    }
    public void updataQuantity(Scanner sc) {
        BookManagement.CartList.forEach(cartItem ->cartItem.displayShoppingCart());
        System.out.println("nhập id cart muốn sửa số lượng ");
        int id = Integer.parseInt(sc.nextLine());
        User cartItem = findById(id);
        if (cartItem != null) {
            cartItem.getProduct().setStock(cartItem.getProduct().getStock() + cartItem.getQuantity());
            System.out.println("nhập số lại số lượng muốn mua ");
            int quantity = Integer.parseInt(sc.nextLine());

            if (quantity > cartItem.getProduct().getStock()) {
                System.out.println("sản phẩm trong kho không đủ ");
            } else  {
                cartItem.setQuantity(cartItem.getProduct().getStock() - quantity);
            }
        } else  {
            System.out.println("nhập dữ liệu không hợp lệ ");
        }
    }
    public void deleteFromCart(Scanner sc) {
        System.out.println("nhập id cartItem muốn xóa ");
        int id = Integer.parseInt(sc.nextLine());
        User cartItem = findById(id);
        if (cartItem != null) {
            delete(id);
        } else  {
            System.out.println("id không tồn tại ");
        }
    }
    public void deleteAll() {
        BookManagement.CartList.clear();
    }
    public void userServiceController(Scanner sc) {
        boolean isLoop = true;
        while (isLoop) {
            System.out.println("1. Xem danh sách sản phẩm ");
            System.out.println("2. Thêm vào giỏ hàng ");
            System.out.println("3. Xem tất cả sản phẩm giỏ hàng ");
            System.out.println("4. Thay đổi số lượng sản phẩm trong giỏ hàng ");
            System.out.println("5. Xóa 1 sản phẩm trong giỏ hàng ");
            System.out.println("6. Xóa toàn bộ sản phẩm trong giỏ hàng ");
            System.out.println("7. Quay lại ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    showProduct();
                    break;
                    case 2:
                        addToCart(sc);
                        break;
                        case 3:
                            showCart();
                            break;
                            case 4:
                                updataQuantity(sc);
                                break;
                                case 5:
                                    deleteFromCart(sc);
                                    break;
                                    case 6:
                                        deleteAll();
                                        break;
                                        case 7:
                                            isLoop = false;
                default:
                    System.err.println("hãy nhập từ 1 đến 7 ");
            }
        }
    }
}
