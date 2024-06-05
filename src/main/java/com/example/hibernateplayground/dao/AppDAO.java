package com.example.hibernateplayground.dao;

import com.example.hibernateplayground.entity.Instructor;

public interface AppDAO {
    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);
}
