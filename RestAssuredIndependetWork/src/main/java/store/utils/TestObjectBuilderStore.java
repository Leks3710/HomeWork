package store.utils;

import store.dto.response.StorePetModel;

import static store.utils.TestDataHelperStore.*;

/**
 * Вспомагательный класс для формирования тестовых объектов
 */
public class TestObjectBuilderStore {

    /**
     * Метод для формирования тела запроса создания заказа на домашнее животное
     *
     * @param shipDate дата заказа
     * @param complete статус выполнения заказа
     * @return тело запроса
     */
    public static StorePetModel getPlaceAndOrderForAPetModel(long storePetId, String shipDate, Boolean complete) {
        return StorePetModel.builder()
                .id(storePetId)
                .petId(getValidPetId())
                .quantity(getPlaceAndOrderForAPetQuantity())
                .shipDate(shipDate)
                .status(VALID_STATUS)
                .complete(complete)
                .build();
    }

}

