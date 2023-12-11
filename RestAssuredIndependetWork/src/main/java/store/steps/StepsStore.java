package store.steps;

import store.dto.response.StorePetModel;
import io.restassured.specification.RequestSpecification;
import store.utils.ResponseWrapperStore;

import static io.restassured.RestAssured.given;

/**
 * Класс с реализацией всех Rest шагов
 */
public class StepsStore {

    /**
     * Экземпляр спецификации RestAssured
     */
    private final RequestSpecification requestSpecification;

    /**
     * Часть URL для запросов /store/order
     */
    private static final String STORE_ORDER_PATH = "store/order/";


    /**
     * Конструктор для создания экземпляра класса
     *
     * @param requestSpecification спецификация RestAssurted
     */
    public StepsStore(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }


    /**
     * Метод создания заказа на домашнее животное
     *
     * @param request тело запроса
     * @return оболочка для работы с ответом
     */
    public ResponseWrapperStore createPlaceAndOrderForAPetModel (StorePetModel request){

        return new ResponseWrapperStore(given(requestSpecification)
                .when()
                .body(request)
                .post(STORE_ORDER_PATH)
                .andReturn());
    }

    /**
     * Метод поиска заказа на домашнее животное по ID
     *
     * @param id - id заказа
     * @return оболочка для работы с ответом
     */
    public ResponseWrapperStore getFindPurchaseOrderByID (long id){

        return new ResponseWrapperStore(given(requestSpecification)
                .when()
                .log().all()
                .get(STORE_ORDER_PATH+id)
                .andReturn());
    }

    /**
     * Метод удаления заказа на домашнее животное по ID
     *
     * @param id - id заказа
     * @return оболочка для работы с ответом
     */
    public ResponseWrapperStore deleteDeletePurchaseOrderByID(long id){

        return new ResponseWrapperStore(given(requestSpecification)
                .when()
                .log().all()
                .delete(STORE_ORDER_PATH+id)
                .andReturn());
    }


}
