package com.example.hibernateplayground.dao;

import com.example.hibernateplayground.entity.Course;
import com.example.hibernateplayground.entity.Instructor;
import com.example.hibernateplayground.entity.InstructorDetail;
import com.example.hibernateplayground.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {

    private final EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        var instructor = entityManager.find(Instructor.class, id);
        instructor.getCourses()
                .forEach(course -> course.setInstructor(null));
        entityManager.remove(instructor);


    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }


    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        var detail = findInstructorDetailById(3);
        detail.getInstructor().setInstructorDetail(null);
        entityManager.remove(entityManager.find(InstructorDetail.class, id));
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {

        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
        query.setParameter("data", id);
        return query.getResultList();
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        return entityManager.createQuery("select i from Instructor i join fetch i.courses join fetch i.instructorDetail where i.id = :data", Instructor.class)
                .setParameter("data", id)
                .getSingleResult();
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {
        return entityManager.createQuery("select c from Course c join fetch c.reviews where c.id = :data", Course.class)
                .setParameter("data", id)
                .getSingleResult();
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        entityManager.remove(entityManager.find(Course.class, id));
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int id) {
        return entityManager.createQuery("select c from Course c join fetch c.students where c.id = :data", Course.class)
                .setParameter("data", id)
                .getSingleResult();
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int id) {
        return entityManager.createQuery("select s from Student s join fetch s.courses where s.id = :data", Student.class)
                .setParameter("data", id)
                .getSingleResult();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }



}
