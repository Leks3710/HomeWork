package store.utils;

import io.restassured.response.Response;
import lombok.AllArgsConstructor;

/**
 * Вспомогательный класс, оболочка для работы с ответами
 */
@AllArgsConstructor
public class ResponseWrapperStore {

    /**
     * ответ в RestAssure
     */
    private final Response response;

    /**
     * Метод преобразования тела ответа к объекту
     *
     * @param Clazz класс объекта
     * @param <T>   тип данных объекта
     * @return объект
     */
    public <T> T as(Class<T> Clazz) {
        return response.as(Clazz);
    }

    /**
     * Метод получения статус кода ответа
     *
     * @return статус код
     */
    public int getStatusCode(){return response.getStatusCode();}

}

