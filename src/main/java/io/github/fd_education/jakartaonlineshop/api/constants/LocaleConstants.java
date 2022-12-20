package io.github.fd_education.jakartaonlineshop.api.constants;

import io.github.fd_education.jakartaonlineshop.domain.enums.LocaleEnum;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.Getter;

/**
 * Constants Bean to make enum values for locales available in JSF facelets.
 * Offers lombok-created getters for each constant (DE, EN).
 *
 * @author Fabian Diemand
 */
@Named @ApplicationScoped
@Getter
public class LocaleConstants {
    final LocaleEnum DE = LocaleEnum.DE;
    final LocaleEnum EN = LocaleEnum.EN;
}
