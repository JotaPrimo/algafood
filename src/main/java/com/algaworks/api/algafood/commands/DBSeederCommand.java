package com.algaworks.api.algafood.commands;

import com.algaworks.api.algafood.infrastructure.repository.seeder.EstadoSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class DBSeederCommand {

    @Autowired
    private EstadoSeeder estadoSeeder;

    @ShellMethod(key = "seed", value = "Populando banco de dados")
    public String seed() {
        estadoSeeder.seed(5);

        return "DBSeederCommand exercutado com sucesso";
    }
}
