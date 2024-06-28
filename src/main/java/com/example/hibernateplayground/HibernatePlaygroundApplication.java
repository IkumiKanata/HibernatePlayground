package com.example.hibernateplayground;

import com.example.hibernateplayground.dao.AppDAO;
import com.example.hibernateplayground.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

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

//            findInstructorDetail(appDAO);

//            deleteInstructorDetail(appDAO, 3);

//            findInstructorWithCourses(appDAO);

//            findInstructorWithCoursesJoinFetch(appDAO);

//            updateInstructor(appDAO);

//            createCourseAndReviews(appDAO);

//            retrieveCourseAndReviewsByCourseId(appDAO);

//            deleteCourseAndReviews(appDAO);

            createCourseAndStudents(appDAO);
        };

    }

    private void createCourseAndStudents(AppDAO appDAO) {
        Course course = new Course("Test Course");
        var stundent = new Student("John", "Doe", "email");
        var stundent2 = new Student("John2", "Doe2", "email2");

        course.addStudent(stundent);
        course.addStudent(stundent2);

        appDAO.save(course);


    }

    private void deleteCourseAndReviews(AppDAO appDAO) {
        appDAO.deleteCourseById(10);

    }

    private void retrieveCourseAndReviewsByCourseId(AppDAO appDAO) {
        Course course = appDAO.findCourseAndReviewsByCourseId(10);
        System.out.println("course: " + course);
        System.out.println("reviews: " + course.getReviews());
    }

    private void createCourseAndReviews(AppDAO appDAO) {
        Course course = new Course("Test Course");
        course.addReview(new Review("Great course!"));
        course.addReview(new Review("Great course!!"));
        course.addReview(new Review("Great course!!!"));
        appDAO.save(course);
    }

    private void updateInstructor(AppDAO appDAO) {
        Instructor instructor = appDAO.findInstructorById(1);
        instructor.setFirstName("Ikumi");
        appDAO.update(instructor);
    }

    private void updateCourse(AppDAO appDAO) {
        Course course = appDAO.findCourseById(1);
        course.setTitle("Test Course");
        appDAO.update(course);
    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
        Instructor instructor = appDAO.findInstructorByIdJoinFetch(1);
        System.out.println("instructor: " + instructor);
        System.out.println("courses: " + instructor.getCourses());
        System.out.println("Detail: " + instructor.getInstructorDetail());
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

    private void deleteInstructorDetail(AppDAO appDAO, int id) {
        appDAO.deleteInstructorDetailById(id);

    }

    private void createInstructorWithCourses(AppDAO appDAO) {
        Instructor instructor = new Instructor("Susan", "Darby", "test.com");

        InstructorDetail instructorDetail = new InstructorDetail("https://sample.com", "Love to code!!!");
        instructor.setInstructorDetail(instructorDetail);

        var course = new Course("Air Guitar");
        var course2 = new Course("The Pinball Masterclass");
        instructor.addCourse(course);
        instructor.addCourse(course2);
        appDAO.save(instructor);
    }

    private void findInstructorWithCourses(AppDAO appDAO) {
        Instructor instructor = appDAO.findInstructorById(1);
        List<Course> courses = appDAO.findCoursesByInstructorId(1);
        System.out.println("instructor: " + instructor);
        System.out.println("courses: " + courses);
    }
}
