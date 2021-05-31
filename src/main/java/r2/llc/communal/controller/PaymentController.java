package r2.llc.communal.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import r2.llc.communal.model.service.CategoryService;
import r2.llc.communal.model.service.CommunalService;
import r2.llc.communal.model.service.UserService;


@Slf4j
@RestController
@RequestMapping(value = "api/v1/category")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PaymentController {

    private final UserService userService;
    private final CommunalService communalService;
    private final CategoryService categoryService;

}
