package agh.toik.broker;

import agh.toik.model.Category;
import agh.toik.model.Question;
import agh.toik.repo.CategoryRepo;
import agh.toik.repo.QuestionRepo;
import agh.toik.utils.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class TriviaScheduler {

    private static final Logger log = LoggerFactory.getLogger(TriviaScheduler.class);
    private QuestionRepo questionRepo;
    private CategoryRepo categoryRepo;

    @Autowired
    public TriviaScheduler(QuestionRepo questionRepo, CategoryRepo categoryRepo) {
        this.questionRepo = questionRepo;
        this.categoryRepo = categoryRepo;
    }

    @Scheduled(cron = "0 44 */2 * * *")
    public void parseAndSaveQuestions() throws Exception {

        int categoryId = Helper.getRandomNumberInRange(9, 32);
        int quantity = Helper.getRandomNumberInRange(5, 10);
        String level = Helper.getRandomElementOfList(Arrays.asList("easy", "medium", "hard"));

        URL url = new URL("https://opentdb.com/api.php" +
                "?amount=" + quantity +
                "&category=" + categoryId +
                "&difficulty=" + level +
                "&type=multiple"
        );
        System.out.println(url);

        InputStreamReader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8);
        TriviaDTO dto = new Gson().fromJson(reader, TriviaDTO.class);
        if (dto.results == null) return;

        // create category
        Category category = addCategory(dto.results.get(0).category);

        // add questions
        for (TriviaQuestion tq : dto.results) {
            addQuestion(tq, category);
        }
    }

    private static class TriviaDTO {
        Integer response_code;
        List<TriviaQuestion> results;
    }

    private static class TriviaQuestion {
        String category;
        String type;
        String difficulty;
        String question;
        String correct_answer;
        List<String> incorrect_answers;
    }

    private Category addCategory(String name) {
        Category categoryWithName = categoryRepo.findCategoryByName(name);
        if (categoryWithName != null) {
            return categoryWithName;
        }

        Category category = new Category();
        category.setName(name);
        category.setId(Helper.generateID());
        categoryRepo.save(category);
        log.debug("Added category\n" + category);

        return category;
    }

    private void addQuestion(TriviaQuestion entity, Category category) {
        List<String> answers = entity.incorrect_answers;
        answers.add(entity.correct_answer);
        Collections.shuffle(answers);
        Integer correct_index = answers.indexOf(entity.correct_answer);

        Question question = new Question();
        question.setBody(entity.question);
        question.setDifficulty(entity.difficulty);
        question.setId(Helper.generateID());
        question.setCategory(category);
        question.setAnswers(answers);
        question.setCorrectAnswer(correct_index);

        questionRepo.save(question);
        log.debug("Added question\n" + category);
    }

}
