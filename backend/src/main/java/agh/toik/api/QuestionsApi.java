/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.20).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package agh.toik.api;

import agh.toik.model.Question;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-19T18:19:31.581Z[GMT]")
@Api(value = "questions", description = "the questions API")
public interface QuestionsApi {

    @ApiOperation(value = "Add a new Question", nickname = "addQuestion", notes = "", response = Question.class, tags={ "questions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "OK", response = Question.class),
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/questions",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Question> addQuestion(@ApiParam(value = "Question object that needs to be added to the db" ,required=true )  @Valid @RequestBody Question body
);


    @ApiOperation(value = "Update an existing Question", nickname = "updateQuestion", notes = "", response = Question.class, tags={ "questions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Question.class),
        @ApiResponse(code = 400, message = "Invalid Question supplied"),
        @ApiResponse(code = 404, message = "Question not found"),
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/questions",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Question> updateQuestion(@ApiParam(value = "Question object that needs to be updated to the db" ,required=true )  @Valid @RequestBody Question body
);

}
