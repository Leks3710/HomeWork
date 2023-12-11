package pet.base;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import pet.dto.PetModel;
import pet.steps.StepsPet;
import pet.utils.ResponseWrapperPet;
import store.config.BaseConfig;

/**
 * Базовый тестовый класс с общими настройками
 */
public class BaseTestPet {

    /**
     * Экземпляр интерфейса с конфигурацией
     */
    private final BaseConfig config = ConfigFactory.create(BaseConfig.class, System.getenv());

    /**
     * Экземпляр класса с REST шагами
     */
    protected final StepsPet stepsPet = new StepsPet(getRequestSpecification());

    /**
     * Экземпляр класса с телом запроса
     */
    protected PetModel request;

    /**
     * Экземпляр класса с оболочкой ответа
     */
    protected ResponseWrapperPet responseWrapperPet;

    /**
     * Метод для получения спецификации RestAssured
     * <p>
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
