package com.test.technique.numerotation.service;

import com.test.technique.model.*;
import com.test.technique.numerotation.model.UserInfo;
import com.test.technique.utils.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class NumerotationService {
    public String generate(List<Config> configs, int counter, UserInfo userInfo) {
        validateUserInfo(userInfo);
        StringBuilder generatedNumber = new StringBuilder();
        configs.sort(Comparator.comparingInt(Config::getOrderIndex));

        ValueWrapper valueWrapper = new ValueWrapper();

        for (Config config : configs) {
            CriterionType criterionType = CriterionType.valueOf(config.getCriterionType());

            switch (criterionType) {
                case FIRSTNAME:
                    FirstNameConfig firstNameConfig = (FirstNameConfig) config;
                    ValueFormatter<String> firsNameFormatter = new NameFormatter(firstNameConfig.getLength());
                    generatedNumber.append(valueWrapper.wrap(firsNameFormatter.format(userInfo.getFirstname()), firstNameConfig.getPrefix(), firstNameConfig.getSuffix()));
                    break;
                case NAME:
                    NameConfig nameConfig = (NameConfig) config;
                    ValueFormatter<String> nameFormatter = new NameFormatter(nameConfig.getLength());
                    generatedNumber.append(valueWrapper.wrap(nameFormatter.format(userInfo.getLastname()), nameConfig.getPrefix(), nameConfig.getSuffix()));
                    break;
                case BIRTHDATE:
                    BirthdateConfig birthdateConfig = (BirthdateConfig) config;
                    ValueFormatter<LocalDate> dateFormatter = new DateFormatter(birthdateConfig.getDateFormat());
                    generatedNumber.append(valueWrapper.wrap(dateFormatter.format(userInfo.getBirthdate()), birthdateConfig.getPrefix(), birthdateConfig.getSuffix()));
                    break;
                case COUNTER:
                    CounterConfig counterConfig = (CounterConfig) config;
                    ValueFormatter<Integer> counterFormatter = new CounterFormatter(counterConfig.getLength());
                    generatedNumber.append(valueWrapper.wrap(counterFormatter.format(counter), counterConfig.getPrefix(), counterConfig.getSuffix()));
                    break;
            }

        }
        return generatedNumber.toString();
    }

    private void validateUserInfo(UserInfo userInfo) {
        if (userInfo == null) {
            throw new IllegalArgumentException("UserInfo cannot be null");
        }
        if (userInfo.getFirstname() == null || userInfo.getFirstname().trim().isEmpty()) {
            throw new IllegalArgumentException("Firstname cannot be null or empty");
        }
        if (userInfo.getLastname() == null || userInfo.getLastname().trim().isEmpty()) {
            throw new IllegalArgumentException("Lastname cannot be null or empty");
        }
        if (userInfo.getBirthdate() == null) {
            throw new IllegalArgumentException("Birthdate cannot be null");
        }
    }
}
