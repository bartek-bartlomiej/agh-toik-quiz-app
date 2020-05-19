package agh.toik.api;

import agh.toik.model.Difficulty;
import agh.toik.model.Question;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @org.springframework.beans.factory.annotation.Autowired
    public QuizApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Question>> getQuizQuestions(@ApiParam(value = "How much Questions do you want", defaultValue = "5") @Valid @RequestParam(value = "quantity", required = false, defaultValue="5") Integer quantity
, @ApiParam(value = "How difficult Questions should be") @Valid @RequestParam(value = "difficulty", required = false) Difficulty difficulty
, @ApiParam(value = "Question category") @Valid @RequestParam(value = "category", required = false) String category
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Question>>(objectMapper.readValue("[ {\n  \"difficulty\" : \"difficulty\",\n  \"answers\" : [ \"answers\", \"answers\" ],\n  \"id\" : 0,\n  \"category\" : \"category\",\n  \"body\" : \"body\",\n  \"correctAnswer\" : 6\n}, {\n  \"difficulty\" : \"difficulty\",\n  \"answers\" : [ \"answers\", \"answers\" ],\n  \"id\" : 0,\n  \"category\" : \"category\",\n  \"body\" : \"body\",\n  \"correctAnswer\" : 6\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Question>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Question>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
