package r2.llc.communal.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import r2.llc.communal.model.data.EntityMapper;
import r2.llc.communal.model.data.PaymentModel;
import r2.llc.communal.model.data.ReceiptModel;
import r2.llc.communal.model.entity.ReceiptEntity;
import r2.llc.communal.model.exception.ValidateException;
import r2.llc.communal.model.repository.CommunalRepository;
import r2.llc.communal.model.repository.DistrictRepository;
import r2.llc.communal.model.repository.ReceiptRepository;
import r2.llc.communal.util.Validator;


@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PaymentService {

    private final DistrictRepository districtRepository;
    private final CommunalRepository communalRepository;

    private final ReceiptRepository repository;
    private final EntityMapper<ReceiptModel, ReceiptEntity> receiptMapper;

    public ReceiptModel publicPay(PaymentModel request) throws ValidateException {
        if (!Validator.validCardNumber(request.getCardNumber()))
            throw new ValidateException("Not Valid card number", HttpStatus.NOT_ACCEPTABLE);

        if (!Validator.validCardExpire(request.getCardExpire()))
            throw new ValidateException("Not Valid card expire", HttpStatus.NOT_ACCEPTABLE);

        if (!Validator.validAmount(request.getSumma()))
            throw new ValidateException("Not Valid card amount", HttpStatus.NOT_ACCEPTABLE);

        if (!Validator.validPhoneNumber(request.getPhoneNumber()))
            throw new ValidateException("Not Valid Phone Number", HttpStatus.NOT_ACCEPTABLE);

        communalRepository
                .findById(request.getCommunalId())
                .orElseThrow(() -> new ValidateException("Not Valid Communal", HttpStatus.NOT_ACCEPTABLE));

        districtRepository
                .findById(request.getDistrictId())
                .orElseThrow(() -> new ValidateException("Not Valid District", HttpStatus.NOT_ACCEPTABLE));

        final var receipt = ReceiptModel
                .builder()
                .districtId(request.getDistrictId())
                .cardExpire(request.getCardExpire())
                .cardNumber(request.getCardNumber())
                .communalId(request.getCommunalId())
                .phoneNumber(request.getPhoneNumber())
                .summa(request.getSumma())
                .build();

        return receiptMapper.mapRT(repository.save(receiptMapper.mapTR(receipt)));
    }


    public ReceiptModel pay(PaymentModel request) {
        throw new RuntimeException();
    }
}
