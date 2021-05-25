package r2.llc.communal.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2.llc.communal.model.data.Category;
import r2.llc.communal.model.data.Communal;
import r2.llc.communal.model.service.CategoryService;
import r2.llc.communal.util.ResponseUtil;


@Slf4j
@RestController
@RequestMapping(value = "api/v1/category")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @ApiOperation(value = "Get All Category items", authorizations = {@Authorization("Authorization")}, produces = "application/json")
    public ResponseEntity<?> getAll() {
        return ResponseUtil.of(categoryService::getAll);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Category by id", produces = "application/json")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseUtil.of(() -> categoryService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "New Category api", produces = "application/json")
    public ResponseEntity<?> create(@RequestBody Category category) {
        return ResponseUtil.of(() -> categoryService.save(category));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Category by id", produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseUtil.of(() -> categoryService.deleteById(id));
    }

    @PostMapping("/addCommunal/{id}")
    @ApiOperation(value = "Add Communal api", produces = "application/json")
    public ResponseEntity<?> create(@PathVariable Long id, @RequestBody Communal communal) {
        return ResponseUtil.of(() -> categoryService.addCommunal(id, communal));
    }
}
