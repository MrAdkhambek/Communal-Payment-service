package r2.llc.communal.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2.llc.communal.model.data.DistrictModel;
import r2.llc.communal.model.service.DistrictService;
import r2.llc.communal.util.ResponseUtil;


@Slf4j
@RestController
@RequestMapping(value = "api/v1/district")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DistrictController {

    private final DistrictService districtService;

    @GetMapping
    @ApiOperation(value = "Get All District items", produces = "application/json")
    public ResponseEntity<?> getAll() {
        return ResponseUtil.of(districtService::getAll);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get District by id", produces = "application/json")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseUtil.of(() -> districtService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "New District api", produces = "application/json")
    public ResponseEntity<?> create(@RequestBody DistrictModel districtModel) {
        return ResponseUtil.of(() -> districtService.save(districtModel));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete District by id", produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseUtil.of(() -> districtService.deleteById(id));
    }
}
