package com.crud.tasks.com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CompanyConfig {
    @Value("${info.company.email}")
    private String companyMail;


    @Value("${info.company.name}")
    private String companyName;

}
