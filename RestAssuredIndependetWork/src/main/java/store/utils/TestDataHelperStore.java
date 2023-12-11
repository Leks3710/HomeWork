package store.utils;

import com.github.javafaker.Faker;

import java.util.Random;

/**
 * Вспомогательный класс, для генерации случайных, валидных и не валидных данных
 */
public class TestDataHelperStore {

    /**
     * Экземпляр класса Random
     */
    private static final Random random = new Random();

    /**
     * Экземпляр класса Faker
     */
    private static final Faker faker = new Faker();


    /**
     * Валидный идентификатор заказа
     */
    public static final long VALID_PET_ID = 1050;

    /**
     * Не валидный идентификатор заказа
     */
    public static long getValidPetId () {return random.nextInt(10,100);}

    /**
     * Не валидный идентификатор заказа
     */
    public static long getNotValidPetID () {return random.nextInt(50,1000);}

    /**
     * Валидный идентификатор количества питомцев
     */
    public static long getPlaceAndOrderForAPetQuantity () {return random.nextInt(1,5);}

    /**
     * Валидное время заказа
     */
    public static final String VALID_SHIPDATE = "2023-12-01T21:00:00.000+0000";

    /**
     * Не валидное время заказа
     */
    private static final String REGEXP_NOT_VALID_SHIPDATE = "[a-z]{20}";

    public static String getRegexpNotValidShipdate(){
        return faker.regexify(REGEXP_NOT_VALID_SHIPDATE);
    }

    /**
     * Валидный статус питомца
     */
    public static final String VALID_STATUS = "available";

    /**
     * Валидный подтвержденный статус выполнения заказа
     */
    public static final Boolean COMPLETE_TRUE_STATUS = true;

    /**
     * Валидный неподтвержденный статус выполнения заказа
     */
    public static final Boolean COMPLETE_FALSE_STATUS = false;

    /**
     * Статус код успешного результата запроса
     */
    public static final long STATUS_CODE_OK = 200;

    /**
     * Статус код не успешного результата запроса 500
     */
    public static final long STATUS_CODE_ERROR_500 = 500;

    /**
     * Статус код не успешного результата запроса 404
     */
    public static final long STATUS_CODE_ERROR_404 = 404;


}

