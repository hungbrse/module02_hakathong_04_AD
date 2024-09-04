package ra.business.entity;

import ra.run.BookManagement;

import java.util.Scanner;

public class Catalog {
    private int Catalogid;
    private String Catalogname;
    private String descriptions;

    public int getCatalogid() {
        return Catalogid;
    }

    public void setCatalogid(int catalogid) {
        Catalogid = catalogid;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getCatalogname() {
        return Catalogname;
    }

    public void setCatalogname(String catalogname) {
        Catalogname = catalogname;
    }

    public Catalog(int catalogid, String descriptions, String catalogname) {
        Catalogid = catalogid;
        this.descriptions = descriptions;
        Catalogname = catalogname;
    }

    public Catalog() {
    }

    public void inputData(Scanner sc) {
        this.Catalogid = inputCatelogId();
        this.Catalogname = inputCatelogName(sc);
        this.descriptions = inputCatelogDescription(sc);
    }
    private int inputCatelogId() {

        int maxId = BookManagement.catelogList.stream()
                .mapToInt(Catalog::getCatalogid)
                .max()
                .orElse(0);

        if (maxId == 0) {
            maxId = 1;
            return  maxId;
        } else  {
            return  maxId + 1;
        }


    }
    private String inputCatelogName(Scanner sc) {
        System.out.println("hãy nhập catelogName :");
        do {
            String catelogName = sc.nextLine();
            if (catelogName.isBlank()) {
                System.err.println("đừng để tên trông ");
            }else  {
                return  catelogName;
            }

        }while (true);

    }
    private String inputCatelogDescription(Scanner sc) {
        System.out.println("hãy nhập miêu tả :");
        do {
            String descriptions  = sc.nextLine();
            if (descriptions .isBlank()) {
                System.err.println("đừng để miêu tả trống ");
            }else  {
                return  descriptions ;
            }

        }while (true);
    }
    public void displayCatalog() {
        System.out.printf("[Catalogid: %d | CatalogName : %s | CatalogDescription : %s ]\n", Catalogid, Catalogname, descriptions);
    }
}
