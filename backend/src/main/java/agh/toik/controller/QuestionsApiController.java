package agh.toik.controller;

import agh.toik.api.QuestionsApi;
import agh.toik.model.Category;
import agh.toik.model.Question;
import agh.toik.repo.CategoryRepo;
import agh.toik.repo.QuestionRepo;
import agh.toik.utils.Helper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Generated;
import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-26T17:58:06.365Z[GMT]")
@Controller
public class QuestionsApiController implements QuestionsApi {

    private static final Logger log = LoggerFactory.getLogger(QuestionsApiController.class);
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    private QuestionRepo questionRepo;
    private CategoryRepo categoryRepo;

    @Autowired
    public QuestionsApiController(ObjectMapper objectMapper, HttpServletRequest request,
                                  QuestionRepo questionRepo, CategoryRepo categoryRepo) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.questionRepo = questionRepo;
        this.categoryRepo = categoryRepo;
    }

    public ResponseEntity<Question> addQuestion(@Valid @RequestBody Question entity) {
        Category category = categoryRepo.findOne(entity.getCategory().getId());
        if (category == null) {
            return new ResponseEntity<Question>(HttpStatus.METHOD_NOT_ALLOWED);
        }

        entity.setId(Helper.generateID());
        entity.setCategory(category);
        questionRepo.save(entity);
        return ResponseEntity.ok(entity);
    }

    public ResponseEntity<Void> deleteQuestion(@PathVariable("questionId") Long questionId) {
        Question questionToDelete = questionRepo.findOne(questionId);
        if (questionToDelete == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        questionRepo.delete(questionToDelete);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<List<Question>> getQuestions(@NotNull @Valid @RequestParam(value = "category", required = true) Long category) {
        List<Question> questions = questionRepo.findQuestionsByCategory(category);
        return ResponseEntity.ok(questions);
    }

    public ResponseEntity<Question> updateQuestion(@Valid @RequestBody Question entity) {
        Question questionToUpdate = questionRepo.findOne(entity.getId());
        if (questionToUpdate == null) {
            return new ResponseEntity<Question>(HttpStatus.NOT_FOUND);
        }

        Category category = categoryRepo.findOne(entity.getCategory().getId());
        if (category == null) {
            return new ResponseEntity<Question>(HttpStatus.METHOD_NOT_ALLOWED);
        }

        entity.setCategory(category);
        questionRepo.save(entity); // updates object that has _id value
        return ResponseEntity.ok(entity);
    }

}
