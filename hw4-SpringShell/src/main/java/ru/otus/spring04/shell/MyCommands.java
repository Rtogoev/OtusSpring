package ru.otus.spring04.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring04.service.TestService;

import java.io.IOException;

@ShellComponent
public class MyCommands {

    private final TestService testService;

    public MyCommands(TestService testService) {
        this.testService = testService;
    }

    @ShellMethod("To start test, input 'go'")
    public void run(String command) throws IOException {
        if (command.equals("go")) {
            testService.execute();
        }
    }
}
