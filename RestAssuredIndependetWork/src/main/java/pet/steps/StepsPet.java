package pet.steps;

import io.restassured.specification.RequestSpecification;
import pet.dto.PetModel;
import pet.utils.ResponseWrapperPet;

import static io.restassured.RestAssured.given;

/**
 * Класс с реализацией всех Rest шагов
 */
public class StepsPet {

    /**
     * Экземпляр спецификации RestAssured
     */
    private final RequestSpecification requestSpecification;

    /**
     * Часть URL для запросов /pet
     */
    private static final String PET_PATH = "pet/";

    public StepsPet(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    /**
     * Метод создания питомца
     *
     * @param request тело запроса
     * @return оболочка для работы с ответом
     */
    public ResponseWrapperPet createNewPetToStore(PetModel request) {
        return new ResponseWrapperPet(given(requestSpecification)
                .when()
                .body(request)
                .post(PET_PATH)
                .andReturn());
    }

    /**
     * Метод поиска питомцев по статусу
     *
     * @param id - id питомца
     * @return оболочка для работы с ответом
     */
    public ResponseWrapperPet getPetByID(long id) {
        return new ResponseWrapperPet(given(requestSpecification)
                .when()
                .get(PET_PATH + id)
                .andReturn());
    }

    /**
     * Метод удаления питомцев по статусу
     *
     * @param id - id питомца
     * @return оболочка для работы с ответом
     */
    public ResponseWrapperPet deletePetByID(long id) {
        return new ResponseWrapperPet(given(requestSpecification)
                .when()
                .delete(PET_PATH + id)
                .andReturn());
    }

}
