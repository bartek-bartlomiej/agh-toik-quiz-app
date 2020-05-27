package agh.toik.repo;

import agh.toik.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends MongoRepository<Question, Long> {

    @Query("{'difficulty' : ?0 , 'category.id' : ?1}")
    List<Question> findQuestionsByDifficultyAndCategory(String difficulty, Long category);

    @Query("{'category.id' : ?0}")
    List<Question> findQuestionsByCategory(Long category);

}
