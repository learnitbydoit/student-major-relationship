package vn.edu.eiu.testlab.infra;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/** Lớp này dùng để thực hiện các tác vụ sau:
 * 1. khởi tạo kết nối Database bằng cách đọc thông tin trong persistence-unit => load nhiều thông tin cấu hình, xóa cấu trúc bảng, tạo lại cấu bảng,... ==> siêu nặng =>chỉ nên load một lần duy nhất lúc chạm đến lần đầu tiên => dùng kỹ thuật SINGLETON để làm.
 * 2. Tạo ra đối tượng quản lý việc tương tác với database (EntityManager)
 * */
public class JpaUtil {
    //#1.
    private static final EntityManagerFactory emf;

    //Đoạn code static không tên, sẽ được gọi ngay khi lớp JpaUtil
    //được chạm đến
    static {
        try {
            emf = Persistence.createEntityManagerFactory("pu1_student_major_relationship");
        } catch (Exception e) {
            System.out.println("Can't create connection to Database.");
            throw new RuntimeException(e);
        }
    }
    /*Để đoạn code static{} trên chạy được thì cần phải vô hiệu hóa tất cả constructor. Mặc định không khai báo constructor thì class sẽ kế thừa constructor rỗng của class object */
    private JpaUtil() {} //Vô hiệu hóa constructor kế thừa từ obj

    //#2. Tạo ra EntityManager quản lý việc tương tác với DB
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
