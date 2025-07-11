module br.com.jeb.produtos_api {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires spring.beans;
    requires spring.context;
    requires spring.core;
    requires spring.data.jpa;
    requires java.persistence;

    exports br.com.jeb.produtos_api;
    exports br.com.jeb.produtos_api.controller;
    exports br.com.jeb.produtos_api.model;
    exports br.com.jeb.produtos_api.repository;
}
