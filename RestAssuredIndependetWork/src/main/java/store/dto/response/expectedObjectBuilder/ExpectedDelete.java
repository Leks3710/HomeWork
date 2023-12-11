package store.dto.response.expectedObjectBuilder;

import store.dto.response.ErrorStorePetModel;

import static store.utils.TestDataHelperStore.*;

/**
 * Вспомогательный класс для создания ответов методов удаления
 */
public class ExpectedDelete {

    /**
     * Код неизвестной ошибки 404
     */
    private static final int UNKNOWN_CODE_DELETE_PET_ID = 404;

    /**
     * Тип неизвестной ошибки
     */
    private static final String UNKNOWN_TYPE = "unknown";

    /**
     * Сообщение неизвестной ошибки
     */
    private static final String ORDER_MESSAGE_DELETE = "Order Not Found";

    /**
     * Метод формирования ожидаемого позитивного результата удаления заказа по ID
     *
     * @return тело ошибки
     */
    public static ErrorStorePetModel getDeletePurchaseOrderByIDPositive(long id) {
        return ErrorStorePetModel.builder()
                .code(STATUS_CODE_OK)
                .type(UNKNOWN_TYPE)
                .message(String.valueOf(id))
                .build();
    }
    /**
     * Метод формирования ожидаемого негативного результата удаления заказа по ID
     *
     * @return тело ошибки
     */
    public static ErrorStorePetModel getErrorDeletePurchaseOrderByID(){
        return ErrorStorePetModel.builder()
                .code(UNKNOWN_CODE_DELETE_PET_ID)
                .type(UNKNOWN_TYPE)
                .message(ORDER_MESSAGE_DELETE)
                .build();
    }
}
