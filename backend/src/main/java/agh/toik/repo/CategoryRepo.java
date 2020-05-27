package agh.toik.repo;

import agh.toik.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends MongoRepository<Category, Long> {

    @Query("{'name' : ?0}")
    Category findCategoryByName(String name);

}

