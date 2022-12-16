package io.github.fd_education.jakartaonlineshop.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

// According to ISO-639-1
@AllArgsConstructor
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

    public String getDatePattern(){
        return null;
    }
}
