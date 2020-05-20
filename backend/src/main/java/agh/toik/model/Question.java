package agh.toik.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * Question
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-19T18:19:31.581Z[GMT]")
@Document
public class Question   {
  @JsonProperty("id")
  @Id
  private Long id = null;

  @JsonProperty("category")
  private String category = null;

  @JsonProperty("difficulty")
  private String difficulty = null;

  @JsonProperty("body")
  private String body = null;

  @JsonProperty("correctAnswer")
  private Integer correctAnswer = null;

  @JsonProperty("answers")
  @Valid
  private List<String> answers = null;

  public Question() {
  }

  public Question id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  
    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Question category(String category) {
    this.category = category;
    return this;
  }

  /**
   * Get category
   * @return category
  **/
  @ApiModelProperty(value = "")
  
    public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Question difficulty(String difficulty) {
    this.difficulty = difficulty;
    return this;
  }

  /**
   * Get difficulty
   * @return difficulty
  **/
  @ApiModelProperty(value = "")
  
    public String getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(String difficulty) {
    this.difficulty = difficulty;
  }

  public Question body(String body) {
    this.body = body;
    return this;
  }

  /**
   * Get body
   * @return body
  **/
  @ApiModelProperty(value = "")
  
    public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Question correctAnswer(Integer correctAnswer) {
    this.correctAnswer = correctAnswer;
    return this;
  }

  /**
   * Get correctAnswer
   * @return correctAnswer
  **/
  @ApiModelProperty(value = "")
  
    public Integer getCorrectAnswer() {
    return correctAnswer;
  }

  public void setCorrectAnswer(Integer correctAnswer) {
    this.correctAnswer = correctAnswer;
  }

  public Question answers(List<String> answers) {
    this.answers = answers;
    return this;
  }

  public Question addAnswersItem(String answersItem) {
    if (this.answers == null) {
      this.answers = new ArrayList<String>();
    }
    this.answers.add(answersItem);
    return this;
  }

  /**
   * Get answers
   * @return answers
  **/
  @ApiModelProperty(value = "")
  
    public List<String> getAnswers() {
    return answers;
  }

  public void setAnswers(List<String> answers) {
    this.answers = answers;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Question question = (Question) o;
    return Objects.equals(this.id, question.id) &&
        Objects.equals(this.category, question.category) &&
        Objects.equals(this.difficulty, question.difficulty) &&
        Objects.equals(this.body, question.body) &&
        Objects.equals(this.correctAnswer, question.correctAnswer) &&
        Objects.equals(this.answers, question.answers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, category, difficulty, body, correctAnswer, answers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Question {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    difficulty: ").append(toIndentedString(difficulty)).append("\n");
    sb.append("    body: ").append(toIndentedString(body)).append("\n");
    sb.append("    correctAnswer: ").append(toIndentedString(correctAnswer)).append("\n");
    sb.append("    answers: ").append(toIndentedString(answers)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
