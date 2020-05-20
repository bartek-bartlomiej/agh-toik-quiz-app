package agh.toik.api;

import agh.toik.model.Difficulty;
import agh.toik.model.Question;
import agh.toik.repo.QuestionRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-19T18:19:31.581Z[GMT]")
@Controller
public class QuizApiController implements QuizApi {

    private static final Logger log = LoggerFactory.getLogger(QuizApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private QuestionRepo questionRepo;

    @org.springframework.beans.factory.annotation.Autowired
    public QuizApiController(ObjectMapper objectMapper, HttpServletRequest request, QuestionRepo questionRepo) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.questionRepo = questionRepo;
    }

    public ResponseEntity<List<Question>> getQuizQuestions(@ApiParam(value = "How much Questions do you want", defaultValue = "5") @Valid @RequestParam(value = "quantity", required = false, defaultValue="5") Integer quantity
, @ApiParam(value = "How difficult Questions should be") @Valid @RequestParam(value = "difficulty", required = false) String difficulty
, @ApiParam(value = "Question category") @Valid @RequestParam(value = "category", required = false) String category
) {
        //TODO: validate parameters

        Query query = new Query();
        query.addCriteria(Criteria.where("difficulty").is(difficulty));
        List<Question> questions = questionRepo.findQuestionsByDifficultyAndCategory(difficulty, category);
        //TODO: shuffle questions
        questions = questions.subList(0, Math.min(questions.size(), quantity));
        return ResponseEntity.ok(questions);
    }

}
