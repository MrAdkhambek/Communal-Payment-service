package r2.llc.communal.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import r2.llc.communal.model.data.PaymentModel;
import r2.llc.communal.model.service.PaymentService;
import r2.llc.communal.util.ResponseUtil;


@Slf4j
@RestController
@RequestMapping(value = "api/v1/payment")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/pay")
    @ApiOperation(value = "Public payment api", produces = "application/json")
    public ResponseEntity<?> getById(@RequestBody PaymentModel request) {
        return ResponseUtil.of(() -> paymentService.publicPay(request));
    }
}
