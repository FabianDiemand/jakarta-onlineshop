package io.github.fd_education.jakartaonlineshop.domain.enums;

import lombok.Getter;

// According to ISO-639-1
public enum LocaleEnum {
    DE("de"){
        @Override
        public String getDatePattern(){
            return "dd.MM.uuuu";
        }
    },
    EN("en"){
        @Override
        public String getDatePattern(){
            return "MM/dd/uuuu";
        }
    };

    @Getter
    private final String code;

    LocaleEnum(String code){
        this.code = code;
    }

    public String getDatePattern(){
        return null;
    }
}
