package ua.com.sliusar.validators.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.com.sliusar.exceptions.BusinessException;
import ua.com.sliusar.validators.ValidationService;

import java.math.BigDecimal;

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
    public void setUp() {
        validationService = new ValidationServiceImp();
    }

    @Test()
    public void testValidateCorrectAge() {
        try {
            validationService.validateAge(32);
        } catch (Exception ex) {
            Assert.fail("Unexpected exception");
        }
    }

    @Test(expected = BusinessException.class)
    public void testValidateWrongAge() throws BusinessException {
        validationService.validateAge(500);
    }

    @Test(expected = BusinessException.class)
    public void testValidateWrongMail() throws BusinessException {
        validationService.validateEmail("wrongMail");
    }

    @Test()
    public void testValidateCorrectMail() {
        try {
            validationService.validateEmail("ivan.sliusar.ua@gmail.com");
        } catch (Exception ex) {
            Assert.fail("Unexpected exception");
        }
    }

    @Test()
    public void testValidateCorrectPhoneNumber() {
        try {
            validationService.validatePhone("+380974445185");
        } catch (Exception ex) {
            Assert.fail("Unexpected exception");
        }
    }

    @Test(expected = BusinessException.class)
    public void testValidateWrongPhoneNumber() throws BusinessException {
        validationService.validatePhone("00674456245");
    }

    @Test(expected = BusinessException.class)
    public void testValidateWrongBigDecimal() throws BusinessException {
        validationService.validateBigDecimal("-545464f");
    }

    @Test()
    public void testValidateCorrectBigDecimal() {
        try {
            validationService.validateBigDecimal(BigDecimal.ONE.toString());
        } catch (Exception e) {
            Assert.fail("Unexpected exception");
        }
    }
}