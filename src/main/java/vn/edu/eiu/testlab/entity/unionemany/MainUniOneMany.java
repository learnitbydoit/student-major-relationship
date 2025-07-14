package vn.edu.eiu.testlab.entity.unionemany;

import jakarta.persistence.EntityManager;
import vn.edu.eiu.testlab.entity.Gender;
import vn.edu.eiu.testlab.infra.JpaUtil;

import java.time.LocalDate;

public class MainUniOneMany {
    //Gõ psvm + enter => hàm main
    public static void main(String[] args) {
        //Tạo mới Major
        Major cse = new Major("CSE", "Software Engineering");

        //Tạo mới Student
        Student std1 = new Student("Nhất Nguyễn", Gender.MALE, LocalDate.parse("2001-12-20"),2023);

        //Add sinh viên vào danh sách của major
        cse.addStudent(std1);

        //Ghi xuống database
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(cse);//CascadeType.ALL ==> student sẽ tự động ghi xuống luôn
        em.getTransaction().commit();
        em.close();

    }
}
