package br.com.jeb;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExampleClass {
    private static final Logger logger = LogManager.getLogger(ExampleClass.class);

    public String processText(String input) {
        logger.info("Processing text: {}", input);

        if (StringUtils.isBlank(input)) {
            logger.warn("Input text is blank");
            return "";
        }

        String result = StringUtils.capitalize(input);
        logger.info("Text processed successfully. Result: {}", result);

        return result;
    }
}
