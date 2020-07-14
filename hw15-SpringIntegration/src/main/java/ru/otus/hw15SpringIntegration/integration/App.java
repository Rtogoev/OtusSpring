package ru.otus.hw15SpringIntegration.integration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.hw15SpringIntegration.integration.domain.Butterfly;
import ru.otus.hw15SpringIntegration.integration.domain.Caterpillar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@IntegrationComponentScan
@ComponentScan
@Configuration
@EnableIntegration
public class App {

    public static void main(String[] args) {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);

        // here we works with cafe using interface
        Nature nature = ctx.getBean(Nature.class);
        Collection<Caterpillar> babes = gotBabesToBeBorn();
        Collection<Butterfly> butterflies = nature.magicTurn(babes);
    }

    private static Collection<Caterpillar> gotBabesToBeBorn() {
        List<Caterpillar> items = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            items.add(new Caterpillar());
        }
        return items;
    }

    @Bean
    public QueueChannel caterpillarChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public PublishSubscribeChannel butterflyChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
    }

    @Bean
    public IntegrationFlow magicFlow() {
        return IntegrationFlows.from("caterpillarChannel")
                .split()
                .handle("caterpillarToButterflyService", "turn")
                .aggregate()
                .channel("butterflyChannel")
                .get();
    }
}
