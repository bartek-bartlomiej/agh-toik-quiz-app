package agh.toik.controller;

import agh.toik.api.QuizApi;
import agh.toik.model.Category;
import agh.toik.model.Difficulty;
import agh.toik.model.Question;
import agh.toik.repo.CategoryRepo;
import agh.toik.repo.QuestionRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Generated;
import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-26T17:58:06.365Z[GMT]")
@Controller
public class QuizApiController implements QuizApi {

    private static final Logger log = LoggerFactory.getLogger(QuizApiController.class);
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    private QuestionRepo questionRepo;
    private CategoryRepo categoryRepo;

    @Autowired
    public QuizApiController(ObjectMapper objectMapper, HttpServletRequest request, QuestionRepo questionRepo, CategoryRepo categoryRepo) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.questionRepo = questionRepo;
        this.categoryRepo = categoryRepo;
    }

    public ResponseEntity<List<Question>> getQuizQuestions(@NotNull @Valid @RequestParam(value = "quantity", required = true, defaultValue = "5") Integer quantity,
                                                           @NotNull @Valid @RequestParam(value = "difficulty", required = true) String difficulty,
                                                           @NotNull @Valid @RequestParam(value = "category", required = true) Long category) {
        Category qCategory = categoryRepo.findOne(category);
        if (qCategory == null || Difficulty.fromValue(difficulty) == null) {
            return new ResponseEntity<List<Question>>(HttpStatus.BAD_REQUEST);
        }

        List<Question> questions = questionRepo.findQuestionsByDifficultyAndCategory(difficulty, category);
        Collections.shuffle(questions);
        questions = questions.subList(0, Math.min(questions.size(), quantity));
        return ResponseEntity.ok(questions);
    }

}
