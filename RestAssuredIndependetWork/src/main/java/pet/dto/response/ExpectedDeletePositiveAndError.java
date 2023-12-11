package pet.dto.response;

import static store.utils.TestDataHelperStore.STATUS_CODE_OK;

/**
 * Вспомогательный класс для создания ответа метода удаления и ошибки по поиску животного
 */
public class ExpectedDeletePositiveAndError {


    /**
     * Тип неизвестный тип
     */
    private static final String UNKNOWN_TYPE = "unknown";

    /**
     * Код неизвестной ошибки
     */
    private static final int UNKNOWN_CODE_PET_ID = 1;

    /**
     * Тип неизвестной ошибки
     */
    private static final String ERROR_TYPE_PET_ID = "error";

    /**
     * Сообщение неизвестной ошибки
     */
    private static final String PET_NOT_FOUND = "Pet not found";

    /**
     * Статус код не успешного результата запроса 404
     */
    public static final long STATUS_CODE_ERROR_404 = 404;

    /**
     * Метод формирования ожидаемого позитивного результата удаления животного по ID
     *
     * @return тело ошибки
     */
    public static ErrorResponse getDeletesAPetPositive (long id){
        return ErrorResponse.builder()
                .code(STATUS_CODE_OK)
                .type(UNKNOWN_TYPE)
                .message(String.valueOf(id))
                .build();
    }

    public static ErrorResponse getErrorFindPetByIDNegative(){
        return ErrorResponse.builder()
                .code(UNKNOWN_CODE_PET_ID)
                .type(ERROR_TYPE_PET_ID)
                .message(PET_NOT_FOUND)
                .build();
    }


}
