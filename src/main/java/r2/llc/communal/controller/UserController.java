package r2.llc.communal.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2.llc.communal.model.data.AuthModel;
import r2.llc.communal.model.data.RegModel;
import r2.llc.communal.model.service.UserService;
import r2.llc.communal.util.ResponseUtil;
import r2.llc.communal.util.ServletUtil;


@Slf4j
@RestController
@ExtensionMethod({ServletUtil.class})
@RequestMapping(value = "api/v1/user")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserController {

    private final UserService userService;

    @GetMapping("/getMe")
    @ApiOperation(value = "Get Me", produces = "application/json")
    public ResponseEntity<?> getMe(@RequestHeader("Authorization") String authorization) {
        return ResponseUtil.of(() -> userService.loadUserModelByToken(authorization.resolveToken()));
    }

    @PostMapping("/auth")
    @ApiOperation(value = "Auth method", produces = "application/json")
    public ResponseEntity<?> auth(@RequestBody AuthModel authModel) {
        return ResponseUtil.of(() -> userService.auth(authModel.getLogin(), authModel.getPassword()));
    }

    @PostMapping("/reg")
    @ApiOperation(value = "Auth method", produces = "application/json")
    public ResponseEntity<?> reg(@RequestBody RegModel regModel) {
        return ResponseUtil.of(() -> userService.save(regModel));
    }
}
