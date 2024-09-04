package ra.service;

import ra.business.entity.Catalog;
import ra.run.BookManagement;

import java.util.List;
import java.util.Scanner;

public class Catalogservice implements  IGenericService<Catalog,Integer>{
    @Override
    public List<Catalog> getAll() {
        return BookManagement.catelogList;
    }

    @Override
    public void save(Catalog catalog) {
        BookManagement.catelogList.add(catalog);

    }

    @Override
    public Catalog findById(Integer id) {
        return BookManagement.catelogList.stream().filter(catalog -> catalog.getCatalogid() == id).findFirst().orElse(null);

    }

    @Override
    public void delete(Integer id) {

        Catalog catalog = findById(id);
        if (catalog != null) {
            BookManagement.catelogList.remove(catalog);
            System.out.println("Danh mục đã được xóa.");
        } else {
            System.out.println("Không tìm thấy danh mục với mã này.");
        }

    }

    public void catalogController(Scanner sc) {
        boolean isLoop = true;
        while (isLoop) {
            System.out.println("1. thêm danh mục  ");
            System.out.println("2. hiển thị danh mục ");
            System.out.println("3. sửa tên danh mục theo id ");
            System.out.println("4. xóa danh mục theo mã danh mục ");
            System.out.println("5. quay lại ");

            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    addCatalog(sc);
                    break;
                    case 2:
                        showCatelog();
                        break;
                        case 3:
                            updataCatalog(sc);
                            break;
                            case 4:
                                deleteCatalog(sc);
                                break;
                                case 5:
                                    isLoop = false;
            }

        }
    }
    public void addCatalog(Scanner sc) {
        System.out.println("nhập số lượng câtlog muốn thêm");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            Catalog catalog = new Catalog();
            catalog.inputData(sc);
            save(catalog);
        }
    }
    public void updataCatalog(Scanner sc) {
        System.out.println("nhập vào id muốn sửa ");
        int id = Integer.parseInt(sc.nextLine());
        Catalog catalog = findById(id);
        if (catalog != null) {
            catalog.inputData(sc);
        }else  {
            System.err.println("id không tồn tại ");
        }
    }

    public void deleteCatalog(Scanner sc) {
        System.out.println("nhập id catalog muốn xóa :");
        int id = Integer.parseInt(sc.nextLine());
        Catalog catalog = findById(id);

        boolean isHas = BookManagement.productList.stream().filter(product -> product.getCatalog().getCatalogid() == id).findFirst().isPresent();
        if (!isHas) {
            if (catalog != null) {
                delete(id);
            }
        } else  {
            System.out.println("trong catalog chứ sản phẩm ");
        }

    }
    public void showCatelog() {
        BookManagement.catelogList.forEach(catelog ->catelog.displayCatalog());
    }

}
