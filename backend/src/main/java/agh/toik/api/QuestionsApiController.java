package agh.toik.api;

import agh.toik.model.Question;
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

    @org.springframework.beans.factory.annotation.Autowired
    public QuestionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Question> addQuestion(@ApiParam(value = "Question object that needs to be added to the db" ,required=true )  @Valid @RequestBody Question body
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Question>(objectMapper.readValue("{\n  \"difficulty\" : \"difficulty\",\n  \"answers\" : [ \"answers\", \"answers\" ],\n  \"id\" : 0,\n  \"category\" : \"category\",\n  \"body\" : \"body\",\n  \"correctAnswer\" : 6\n}", Question.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Question>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Question>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Question> updateQuestion(@ApiParam(value = "Question object that needs to be updated to the db" ,required=true )  @Valid @RequestBody Question body
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Question>(objectMapper.readValue("{\n  \"difficulty\" : \"difficulty\",\n  \"answers\" : [ \"answers\", \"answers\" ],\n  \"id\" : 0,\n  \"category\" : \"category\",\n  \"body\" : \"body\",\n  \"correctAnswer\" : 6\n}", Question.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Question>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Question>(HttpStatus.NOT_IMPLEMENTED);
    }

}
