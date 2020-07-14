package ru.otus.hw15SpringIntegration.integration;


import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.hw15SpringIntegration.integration.domain.Caterpillar;
import ru.otus.hw15SpringIntegration.integration.domain.Butterfly;


import java.util.Collection;

@MessagingGateway
public interface Nature {

    @Gateway(requestChannel = "caterpillarChannel", replyChannel = "butterflyChannel")
    Collection<Butterfly> magicTurn(Collection<Caterpillar> caterpillars);
}
