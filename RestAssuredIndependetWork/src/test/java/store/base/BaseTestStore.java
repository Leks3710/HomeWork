package store.base;

import store.dto.response.StorePetModel;
import store.steps.StepsStore;
import store.config.BaseConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import store.utils.ResponseWrapperStore;

/**
 * Базовый тестовый класс с общими настройками
 */
public class BaseTestStore {

    /**
     * Экземпляр интерфейса с конфигурацией
     */
    private final BaseConfig config = ConfigFactory.create(BaseConfig.class, System.getenv());

    /**
     * Экземпляр класса с REST шагами
     */
    protected final StepsStore stepsStore = new StepsStore(getRequestSpecification());

    /**
     * Экземпляр класса с телом запроса
     */
    protected StorePetModel request;

    /**
     * Экземпляр класса с оболочкой ответа
     */
    protected ResponseWrapperStore responseWrapperStore;

    /**
     * Метод для получения спецификации RestAssured
     *
     * baseUrl - базовый URL
     * contentType - параметр в header со значением JSON
     * accept - параметр в header со значением JSON
     * filter - создает фильтр для allure
     * log - логирование всех деталей
     *
     * @return спецификация
     */
    private RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(config.baseUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
    }
}
