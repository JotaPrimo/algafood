package com.algaworks.api.algafood.commands;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class HelloCommand {

    @ShellMethod(key = "hello", value = "Vamos lá")
    public String hello() {
        return "Hello World";
    }

    @ShellMethod(key = "goodbye", value = "Até mais")
    public String goodbye() {
        return "goodbye goodbye";
    }
}
