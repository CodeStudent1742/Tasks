package com.crud.tasks.com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class Mail {
    private final String mailTo;
    private final String subject;
    private final String message;
    private final String toCc;

//    private static class MailBuilder {
//        private String mailTo;
//        private String subject;
//        private String message;
//        private String toCc;
//
//        public MailBuilder mailTo(String mailTo) {
//            this.mailTo = mailTo;
//            return this;
//        }
//        public MailBuilder subject(String subject){
//            this.subject = subject;
//            return this;
//        }
//        public MailBuilder message(String message){
//            this.message = message;
//            return this;
//        }
//        public MailBuilder toCC(String toCc){
//            this.toCc = toCc;
//            return this;
//        }
//        public Mail build(){
//            return new Mail (mailTo,m)
//        }
//    }
}