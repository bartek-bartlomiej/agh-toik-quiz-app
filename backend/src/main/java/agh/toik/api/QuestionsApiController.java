package agh.toik.api;

import agh.toik.model.Question;
import agh.toik.repo.QuestionRepo;
import agh.toik.utils.Helper;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-19T18:19:31.581Z[GMT]")
@Controller
public class QuestionsApiController implements QuestionsApi {

    private static final Logger log = LoggerFactory.getLogger(QuestionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private QuestionRepo questionRepo;

    @org.springframework.beans.factory.annotation.Autowired
    public QuestionsApiController(ObjectMapper objectMapper, HttpServletRequest request, QuestionRepo questionRepo) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.questionRepo = questionRepo;
    }

    public ResponseEntity<Question> addQuestion(@ApiParam(value = "Question object that needs to be added to the db" ,required=true )  @Valid @RequestBody Question entity
) {
        entity.setId(Helper.generateID());
        questionRepo.save(entity);
        return ResponseEntity.ok(entity);
    }

    public ResponseEntity<Question> updateQuestion(@ApiParam(value = "Question object that needs to be updated to the db" ,required=true )  @Valid @RequestBody Question entity
) {
        Question questionToUpdate = questionRepo.findOne(entity.getId());
        if (questionToUpdate == null) {
            return new ResponseEntity<Question>(HttpStatus.NOT_FOUND);
        }

        questionRepo.save(entity); //save updates object that has _id value
        return ResponseEntity.ok(entity);
    }

}
