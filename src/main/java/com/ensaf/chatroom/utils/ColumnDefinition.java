package com.ensaf.chatroom.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ColumnDefinition {
    public static final int UUID_LENGTH = 36;
    public static final int REFERENCE_LENGTH = 80;

    public static final int VERY_SHORT_LENGTH = 10;
    public static final int SHORT_LENGTH = 60;
    public static final int LONG_LENGTH = 255;

    public static final int NAME_LENGTH = 60;
    public static final int VALUE_LENGTH = 100;

    public static final int STATUS_LENGTH = 20;
    public static final int GENDER_LENGTH = 6;

    public static final int SHORT_CODE_LENGTH = 5;
    public static final int CODE_LENGTH = 10;
    public static final int LONG_CODE_LENGTH = 20;

    public static final int EMAIL_LENGTH = 100;
    public static final int PHONE_LENGTH = 20;
    public static final int URL_LENGTH = 255;

    public static final int DOCUMENT_NUMBER_LENGTH = 20;
    public static final int SIRET_NUMBER_LENGTH = 20;

    public static final int IBAN_LENGTH = 34;
    public static final int RIB_LENGTH = 24;
    public static final int SWIFT_LENGTH = 11;

    public static final int ADDRESS_LINE_LENGTH = 120;
    public static final int POSTAL_CODE_LENGTH = 20;

    public static final String TEXT = "TEXT";
    public static final String JSONB = "jsonb";
    public static final String GEOGRAPHY_POINT = "geography(Point, 4326)";
    public static final String TIMESTAMP_DEFAULT_NOW = "timestamp default now()";
    public static final String USER_DEFAULT_SYSTEM = "varchar(36) default 'system'";
    public static final String BOOLEAN_DEFAULT_FALSE = "BOOLEAN default false";

}
