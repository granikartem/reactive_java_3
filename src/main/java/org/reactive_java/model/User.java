package org.reactive_java.model;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Builder
@RequiredArgsConstructor
public class User {

    private final String login;

    private final Set<String> groups;
}
