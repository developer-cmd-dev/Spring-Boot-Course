package com.SpringBootCourse.springbootcourse.repository;

import com.SpringBootCourse.springbootcourse.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.function.Function;

public interface UserRepo extends MongoRepository<User, ObjectId> {
    User findByUsername(String userName);
}
