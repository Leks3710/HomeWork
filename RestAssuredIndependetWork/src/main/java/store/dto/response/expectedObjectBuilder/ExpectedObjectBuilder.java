package store.dto.response.expectedObjectBuilder;

import store.dto.response.ErrorStorePetModel;

/**
 * Вспомагательный класс для формирования ожидаемых результатов
 */
public class ExpectedObjectBuilder {

    /**
     * Код неизвестной ошибки
     */
    private static final int UNKNOWN_CODE = 500;

    /**
     * Код неизвестной ошибки
     */
    private static final int UNKNOWN_CODE_PET_ID = 1;

    /**
     * Тип неизвестной ошибки
     */
    private static final String UNKNOWN_TYPE = "unknown";

    /**
     * Тип неизвестной ошибки
     */
    private static final String ERROR_TYPE_PET_ID = "error";

    /**
     * Сообщение неизвестной ошибки
     */
    private static final String UNKNOWN_MESSAGE = "something bad happened";

    /**
     * Сообщение неизвестной ошибки
     */
    private static final String ORDER_NOT_FOUND = "Order not found";

    /**
     * Метод формирования ожидаемого результата ошибки 500
     *
     * @return тело ошибки
     */
    public static ErrorStorePetModel getUnknownErrorPlaceAnOrderForAPet() {
        return ErrorStorePetModel.builder()
                .code(UNKNOWN_CODE)
                .type(UNKNOWN_TYPE)
                .message(UNKNOWN_MESSAGE)
                .build();
    }

    /**
     * Метод формирования ожидаемого результата ошибки поиска по ID
     *
     * @return тело ошибки
     */
    public static ErrorStorePetModel getUnknownErrorFindPurchaseOrderByID() {
        return ErrorStorePetModel.builder()
                .code(UNKNOWN_CODE_PET_ID)
                .type(ERROR_TYPE_PET_ID)
                .message(ORDER_NOT_FOUND)
                .build();
    }
}
