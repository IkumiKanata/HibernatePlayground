package com.example.hibernateplayground.dao;

import com.example.hibernateplayground.entity.Course;
import com.example.hibernateplayground.entity.Instructor;
import com.example.hibernateplayground.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {
    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailById(int id);

    Course findCourseById(int id);

    List<Course> findCoursesByInstructorId(int id);

    Instructor findInstructorByIdJoinFetch(int id);

    void update(Instructor instructor);

    void update(Course course);

    void save(Course course);

    Course findCourseAndReviewsByCourseId(int id);

    void deleteCourseById(int i);
}
