package pet.utils;

import com.github.javafaker.Faker;

import java.util.Random;

public class TestDataHelperPet {

    /**
     * Экземпляр класса Random
     */
    private static final Random random = new Random();

    /**
     * Валидный идентификатор питомца
     */
    public static final long VALID_PET_ID = 5;

    /**
     * Не валидный ID животного
     */
    public static long getNotValidPetID () {return random.nextInt(50,1000);}

    /**
     * Валидная категория ID животного
     */
    public static long getValidCategoryID () {return random.nextInt(1,1000);}


    /**
     * Валидный идентификатор тэга
     */
    public static long getValidTagID () {return random.nextInt(1,1000);}

    /**
     * Валидное имя тэга
     */
    public static final String VALID_TAG_NAME = "tagOne";

    /**
     * Валидный статус питомца
     */
    public static final String VALID_STATUS = "available";

    /**
     * Статус код успешного результата запроса
     */
    public static final int STATUS_CODE_OK = 200;

    /**
     * Экземпляр класса Faker
     */
    private static final Faker faker = new Faker();

    /**
     * Регулярное выражениек для генерации случайного URL
     */
    private static final String REGEXP_PHOTO_URL = "http:\\\\[a-z]{5}";

    /**
     * Метод получения случайного имени питомца
     *
     * @return случайное имя питомца
     */
    public static String getRandomPetName() {
        return faker.cat().name();
    }

    /**
     * Метод получения случайного имени категории
     *
     * @return случайное имя категории
     */
    public static String getRandomCategoryName() {
        return faker.cat().breed();
    }

    /**
     * Метод получения случайного URL
     *
     * @return случайное URL
     */
    public static String getRandomUrl() {
        return faker.regexify(REGEXP_PHOTO_URL);
    }

    }
