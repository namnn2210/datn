package ginp14.ngongocnam.datn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class SomeClass
{
    @Autowired
    private TemplateEngine templateEngine;

    public String generateMailHtml(String text, String templateFileName, Context context)
    {
        //Name of the template file without extension
        String output = this.templateEngine.process(templateFileName, context);
        return output;
    }
}
