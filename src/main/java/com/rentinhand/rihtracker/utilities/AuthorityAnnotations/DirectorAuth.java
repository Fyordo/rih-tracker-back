package com.rentinhand.rihtracker.utilities.AuthorityAnnotations;


import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@PreAuthorize("hasRole(T(com.rentinhand.rihtracker.utilities.Role).DIRECTOR)")
public @interface DirectorAuth {
}