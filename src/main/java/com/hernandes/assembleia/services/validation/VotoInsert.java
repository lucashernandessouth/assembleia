package com.hernandes.assembleia.services.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Constraint(validatedBy = VotoInsertValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface VotoInsert {
	String message() default "Erro ao validar.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
