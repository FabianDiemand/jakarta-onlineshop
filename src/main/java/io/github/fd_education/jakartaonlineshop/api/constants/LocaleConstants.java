package io.github.fd_education.jakartaonlineshop.api.constants;

import io.github.fd_education.jakartaonlineshop.domain.enums.LocaleEnum;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.Getter;

@Named @ApplicationScoped
@Getter
public class LocaleConstants {
    final LocaleEnum DE = LocaleEnum.DE;
    final LocaleEnum EN = LocaleEnum.EN;
}
