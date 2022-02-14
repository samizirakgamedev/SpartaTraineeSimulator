package com.purerangers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;

public class PersonTests
{
    @Test
    @DisplayName("When given a trainee the training centre should add it.")
    public void canBeAddedTo()
    {
        Person person = new Person();

        Assertions.assertThrows(NullPointerException.class, () ->
        {
            person.checkGraduation(null);
        });
    }
}
