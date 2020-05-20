package agh.toik.api;

import agh.toik.model.Category;
import agh.toik.repo.CategoryRepo;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-19T18:19:31.581Z[GMT]")
@Controller
public class CategoriesApiController implements CategoriesApi {

    private static final Logger log = LoggerFactory.getLogger(CategoriesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private CategoryRepo categoryRepo;

    @org.springframework.beans.factory.annotation.Autowired
    public CategoriesApiController(ObjectMapper objectMapper, HttpServletRequest request, CategoryRepo categoryRepo) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.categoryRepo = categoryRepo;
    }

    public ResponseEntity<String> addCategory(@ApiParam(value = "Category that needs to be added to the db" ,required=true )  @Valid @RequestBody String name
) {
        Category category = new Category();
        category.setId(Helper.generateID());
        category.setName(name);
        categoryRepo.save(category);
        return ResponseEntity.ok(name);
    }

    public ResponseEntity<List<String>> getCategories() {
        List<Category> categories = categoryRepo.findAll();
        List<String> names = new ArrayList<>();

        for (Category c : categories) {
            names.add(c.getName());
        }
        return ResponseEntity.ok(names);
    }

}
