package agh.toik.controller;

import agh.toik.api.CategoriesApi;
import agh.toik.model.Category;
import agh.toik.repo.CategoryRepo;
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

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-26T17:58:06.365Z[GMT]")
@Controller
public class CategoriesApiController implements CategoriesApi {

    private static final Logger log = LoggerFactory.getLogger(CategoriesApiController.class);
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    private CategoryRepo categoryRepo;

    @Autowired
    public CategoriesApiController(ObjectMapper objectMapper, HttpServletRequest request, CategoryRepo categoryRepo) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.categoryRepo = categoryRepo;
    }

    public ResponseEntity<Category> addCategory(@Valid @RequestBody Category entity) {
        entity.setId(Helper.generateID());
        categoryRepo.save(entity);
        return ResponseEntity.ok(entity);
    }

    public ResponseEntity<Void> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        Category categoryToDelete = categoryRepo.findOne(categoryId);
        if (categoryToDelete == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        categoryRepo.delete(categoryToDelete);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = categoryRepo.findAll();
        return ResponseEntity.ok(categories);
    }

    public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category entity) {
        Category categoryToUpdate = categoryRepo.findOne(entity.getId());
        if (categoryToUpdate == null) {
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }

        categoryRepo.save(entity); // updates object that has _id value
        return ResponseEntity.ok(entity);
    }

}
