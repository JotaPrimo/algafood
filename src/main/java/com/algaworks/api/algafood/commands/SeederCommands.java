package com.algaworks.api.algafood.commands;

import com.algaworks.api.algafood.infrastructure.repository.seeder.SeederExecutor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class SeederCommands {

    private final SeederExecutor executor;

    public SeederCommands(SeederExecutor executor) {
        this.executor = executor;
    }

    @ShellMethod(key = "seedAll", value = "Executa todos os seeders")
    public void seedAll(@ShellOption(defaultValue = "10") int quantidade) {
        executor.seedAll(quantidade);
    }


    /**
     * Exemplo: seed --nome estado --qtde 5
     * qtde é fixo como 10 se não passar nenhum valor
     * */
    @ShellMethod(key = "seed", value = "Executa um seeder específico, recebendo o nome por parâmetro")
    public void seed(@ShellOption String nome, @ShellOption(defaultValue = "10") int qtde) {
        executor.seedByNome(nome, qtde);
    }

    @ShellMethod(key = "truncateTable", value = "Limpa (trunca) uma tabela pelo nome")
    public void truncateTable(@ShellOption String tabela) {
        executor.truncateTable(tabela);
    }
}