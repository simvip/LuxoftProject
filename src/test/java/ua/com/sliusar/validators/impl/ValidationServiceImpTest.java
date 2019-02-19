package ua.com.sliusar.validators.impl;

import org.junit.Before;
import org.junit.Test;
import ua.com.sliusar.exceptions.BusinessException;
import ua.com.sliusar.validators.ValidationService;

/**
 * Class ValidationServiceImpTest
 *
 * @author create by ivanslusar
 * 2/19/19
 * @project MyLuxoftProject
 */
public class ValidationServiceImpTest {
    ValidationService validationService;

    @Before
    public void setUp(){
        validationService = new ValidationServiceImp();
    }


    @Test(expected = BusinessException.class)
    public void whenWeInputAgeOutOfRange_ThenExpectationSatisfied() throws BusinessException {
        validationService.validateAge(500);
    }

    @Test(expected = BusinessException.class)
    public void whenWeInputWrongMail_ThenExpectationSatisfied() throws BusinessException {
        validationService.validateEmail("wrongMail");
    }

    @Test(expected = BusinessException.class)
    public void whenWeInputWrongPhoneNumber_ThenExpectationSatisfied() throws BusinessException {
        validationService.validatePhone("00674456245");
    }

    @Test(expected = BusinessException.class)
    public void whenWeInputWrongBigDecimal_ThenExpectationSatisfied() throws BusinessException {
        validationService.validateBigВecimal("-545464f");
    }
}