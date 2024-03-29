package com.crud.tasks.com.crud.tasks.service;

import com.crud.tasks.com.crud.tasks.config.AdminConfig;
import com.crud.tasks.com.crud.tasks.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;


@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");


        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("welcome", "Nice to see you!");
        context.setVariable("tasks_url", "https://codestudent1742.github.io");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig);
        context.setVariable("goodbye", "Have a nice day!");
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_email", companyConfig.getCompanyMail());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String countTasksEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("welcome", "Nice to see you!");
        context.setVariable("tasks_url", "https://codestudent1742.github.io");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig);
        context.setVariable("goodbye", "Have a nice day!");
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_email", companyConfig.getCompanyMail());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
