package r2.llc.communal.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2.llc.communal.model.data.DistrictModel;
import r2.llc.communal.model.data.RegionModel;
import r2.llc.communal.model.service.RegionService;
import r2.llc.communal.util.ResponseUtil;


@Slf4j
@RestController
@RequestMapping(value = "api/v1/region")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RegionController {

    private final RegionService regionService;

    @GetMapping
    @ApiOperation(value = "Get All Region items", produces = "application/json")
    public ResponseEntity<?> getAll() {
        return ResponseUtil.of(regionService::getAll);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Region by id", produces = "application/json")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseUtil.of(() -> regionService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "New Region api", produces = "application/json")
    public ResponseEntity<?> create(@RequestBody RegionModel regionModel) {
        return ResponseUtil.of(() -> regionService.save(regionModel));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Region by id", produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseUtil.of(() -> regionService.deleteById(id));
    }

    @PostMapping("/addDistrict/{id}")
    @ApiOperation(value = "Add DistrictModel api", produces = "application/json")
    public ResponseEntity<?> create(@PathVariable Long id, @RequestBody DistrictModel districtModel) {
        return ResponseUtil.of(() -> regionService.addDistrict(id, districtModel));
    }
}
