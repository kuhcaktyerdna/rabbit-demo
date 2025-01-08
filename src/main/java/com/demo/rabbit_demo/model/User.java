package com.demo.rabbit_demo.model;

import java.io.Serializable;

public record User(String username, String password, String email) implements Serializable {
}

