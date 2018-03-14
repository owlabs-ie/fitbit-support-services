package es.flaviojmend.fitbittracker.persistence.entity.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Shortname {
    String value();
}
