package r2.llc.communal.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2.llc.communal.model.data.Communal;
import r2.llc.communal.model.service.CommunalService;
import r2.llc.communal.util.ResponseUtil;


@Slf4j
@RestController
@RequestMapping(value = "api/v1/communal")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CommunalController {

    private final CommunalService communalService;

    @GetMapping
    @ApiOperation(value = "Get All Communal items", produces = "application/json")
    public ResponseEntity<?> getAll() {
        return ResponseUtil.of(communalService::getAll);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Communal by id", produces = "application/json")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseUtil.of(() -> communalService.getById(id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete communal by id", produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseUtil.of(() -> communalService.deleteById(id));
    }
}
