package task2.validator;

import task2.exception.CustomException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidatorTest {
    @Test
    public void testValidate() throws CustomException {
        FlowerXMLValidator validator = new FlowerXMLValidator();
        boolean isRead = validator.validate();
        Assert.assertTrue(isRead);
    }
}