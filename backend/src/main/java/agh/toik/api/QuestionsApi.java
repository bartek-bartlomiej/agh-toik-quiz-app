/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.20).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package agh.toik.api;

import agh.toik.model.Question;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-26T17:58:06.365Z[GMT]")
@Api(value = "questions", description = "the questions API")
public interface QuestionsApi {

    @ApiOperation(value = "Add a new question", nickname = "addQuestion", notes = "", response = Question.class, tags={ "questions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Operation successful - contains question object with generated ID", response = Question.class),
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/questions",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Question> addQuestion(@ApiParam(value = "Question that needs to be added to the database" ,required=true )  @Valid @RequestBody Question body
);


    @ApiOperation(value = "Delete a question", nickname = "deleteQuestion", notes = "", tags={ "questions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Operation successful"),
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Question not found") })
    @RequestMapping(value = "/questions/{questionId}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteQuestion(@ApiParam(value = "ID of question to delete",required=true) @PathVariable("questionId") Long questionId
);


    @ApiOperation(value = "Return questions by category", nickname = "getQuestions", notes = "", response = Question.class, responseContainer = "List", tags={ "questions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Operation successful", response = Question.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid ID supplied") })
    @RequestMapping(value = "/questions",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Question>> getQuestions(@NotNull @ApiParam(value = "Category ID", required = true) @Valid @RequestParam(value = "category", required = true) Long category
);


    @ApiOperation(value = "Update an existing question", nickname = "updateQuestion", notes = "", response = Question.class, tags={ "questions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Operation successful", response = Question.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Question not found"),
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/questions",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Question> updateQuestion(@ApiParam(value = "Question that needs to be updated" ,required=true )  @Valid @RequestBody Question body
);

}
