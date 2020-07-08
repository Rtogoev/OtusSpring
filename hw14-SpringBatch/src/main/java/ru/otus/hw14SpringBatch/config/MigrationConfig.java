package ru.otus.hw14SpringBatch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.hw14SpringBatch.model.JdbcBook;
import ru.otus.hw14SpringBatch.model.MongoBook;
import ru.otus.hw14SpringBatch.repository.MongoBookRepository;
import ru.otus.hw14SpringBatch.service.ProcessService;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


@Configuration
public class MigrationConfig {

    private static final String JOB_NAME = "job_name";
    private static final int CHUNK_SIZE = 5;
    private static final Logger LOGGER = LoggerFactory.getLogger("BookMigration");
    private final MongoBookRepository mongoBookRepository;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public MigrationConfig(
            MongoBookRepository mongoBookRepository,
            JobBuilderFactory jobBuilderFactory,
            StepBuilderFactory stepBuilderFactory
    ) {
        this.mongoBookRepository = mongoBookRepository;
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @PostConstruct
    void init() {
        mongoBookRepository.deleteAll();
        for (int i = 0; i < 100; i++) {
            mongoBookRepository.save(new MongoBook(null, random(), random(), random()));
        }
    }

    private String random() {
        return UUID.randomUUID().toString();
    }

    @StepScope
    @Bean
    public MongoItemReader<MongoBook> reader(MongoTemplate template) {
        return new MongoItemReaderBuilder<MongoBook>()
                .name("bookItemWriter")
                .template(template)
                .jsonQuery("{}")
                .targetType(MongoBook.class)
                .sorts(new HashMap<>())
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<MongoBook, JdbcBook> processor(ProcessService processService) {
        return processService::process;
    }


    @StepScope
    @Bean
    public JdbcBatchItemWriter<JdbcBook> writer(DataSource dataSource) {
        JdbcBatchItemWriter<JdbcBook> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(
                new BeanPropertyItemSqlParameterSourceProvider<>()
        );
        writer.setSql("INSERT INTO book (id, name, author, genre) VALUES (:id, :name, :author, :genre)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public Job importBookJob(Step step) {
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .flow(step)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        LOGGER.info("Начало");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        LOGGER.info("Конец");
                    }
                })
                .build();
    }


    @Bean
    public Step step(
            JdbcBatchItemWriter<JdbcBook> bookWriter,
            MongoItemReader<MongoBook> bookReader,
            ItemProcessor<MongoBook, JdbcBook> bookProcessor
    ) {
        return stepBuilderFactory.get("step")
                .<MongoBook, JdbcBook>chunk(CHUNK_SIZE)
                .reader(bookReader)
                .processor(bookProcessor)
                .writer(bookWriter)
                .listener(new ItemReadListener<>() {
                    public void beforeRead() {
                        LOGGER.info("Начало чтения");
                    }

                    public void afterRead(MongoBook mongoBook) {
                        LOGGER.info("Конец чтения");
                    }

                    public void onReadError(Exception e) {
                        LOGGER.info("Ошибка чтения");
                    }
                })
                .listener(new ItemWriteListener<JdbcBook>() {
                    public void beforeWrite(List list) {
                        LOGGER.info("Начало записи");
                    }

                    public void afterWrite(List list) {
                        LOGGER.info("Конец записи");
                    }

                    public void onWriteError(Exception e, List list) {
                        LOGGER.info("Ошибка записи");
                    }
                })
                .listener(new ItemProcessListener<MongoBook, JdbcBook>() {
                    public void beforeProcess(MongoBook mongoBook) {
                        LOGGER.info("Начало обработки");
                    }

                    public void afterProcess(MongoBook mongoBook, JdbcBook jdbcBook) {
                        LOGGER.info("Конец обработки");
                    }

                    public void onProcessError(MongoBook mongoBook, Exception e) {
                        LOGGER.info("Ошибка обработки");
                    }
                })
                .listener(new ChunkListener() {
                    public void beforeChunk(ChunkContext chunkContext) {
                        LOGGER.info("Начало пачки");
                    }

                    public void afterChunk(ChunkContext chunkContext) {
                        LOGGER.info("Конец пачки");
                    }

                    public void afterChunkError(ChunkContext chunkContext) {
                        LOGGER.info("Ошибка пачки");
                    }
                })
                .build();
    }
}
