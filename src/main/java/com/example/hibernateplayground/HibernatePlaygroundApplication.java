package com.example.hibernateplayground;

import com.example.hibernateplayground.dao.AppDAO;
import com.example.hibernateplayground.entity.Instructor;
import com.example.hibernateplayground.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HibernatePlaygroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernatePlaygroundApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
//            createInstructor(appDAO);

//            findInstructor(appDAO);

//            deleteInstructor(appDAO);

            findInstructorDetail(appDAO);

        };
    }

    private void createInstructor(AppDAO appDAO) {
        Instructor instructor = new Instructor("Chad", "Darby", "test.com");

        InstructorDetail instructorDetail = new InstructorDetail("https://sample.com", "Luv 2 code!!!");
        instructor.setInstructorDetail(instructorDetail);
        appDAO.save(instructor);
    }

    private void findInstructor(AppDAO appDAO) {
        Instructor instructor = appDAO.findInstructorById(5);
        System.out.println("instructor: " + instructor);
        System.out.println("instructor detail: " + instructor.getInstructorDetail());
    }

    private void deleteInstructor(AppDAO appDAO) {
        appDAO.deleteInstructorById(1);
    }

    private void findInstructorDetail(AppDAO appDAO) {
        InstructorDetail instructorDetail = appDAO.findInstructorDetailById(1);
        System.out.println("instructor detail: " + instructorDetail);
        System.out.println("instructor: " + instructorDetail.getInstructor());
    }
}
