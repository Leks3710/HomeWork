package pet;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pet.base.BaseTestPet;
import pet.dto.PetModel;
import pet.dto.response.ErrorResponse;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static pet.dto.response.ExpectedDeletePositiveAndError.STATUS_CODE_ERROR_404;
import static pet.dto.response.ExpectedDeletePositiveAndError.getErrorFindPetByIDNegative;
import static pet.utils.TestDataHelperPet.*;
import static pet.utils.TestObjectBuilderPet.getAddNewPetModel;

/**
 * Тест сьют метода GET/pet
 */
@Epic("Pet контроллер")
@Feature("Find pet by ID")
public class FindPetByID extends BaseTestPet {

    /**
     * Метод создания тела запроса
     */
    @BeforeEach
    public void requestBodyCreation() {
        step("Создание тела запроса с валидным ID", () -> {
           request = getAddNewPetModel(VALID_PET_ID);
        });

        step("Выполнение запроса POST /pet", () -> {
            responseWrapperPet = stepsPet.createNewPetToStore(request);
        });
    }

    @Test
    @DisplayName("Find pet by ID. Positive case")
    @Story("Поиск животного по ID, позитивный сценарий")
    public void testFindPetByIDPositive(){

        step("Выполнение запроса GET/store/order c валидным id", () -> {
            responseWrapperPet = stepsPet.getPetByID(VALID_PET_ID);
        });

        step("Проверка результата", () -> {
            long statusCode = responseWrapperPet.getStatusCode();
            PetModel response = responseWrapperPet.as(PetModel.class);

            assertSoftly(
                    softAssertions -> {
                        softAssertions
                                .assertThat(statusCode)
                                .withFailMessage("Status code doesn't match")
                                .isEqualTo(STATUS_CODE_OK);
                        softAssertions
                                .assertThat(response)
                                .withFailMessage("Response body is empty")
                                .isEqualTo(request);
                    }
            );
        });

    }

    @Test
    @DisplayName("Find pet by ID. Negative case")
    @Story("Поиск животного по ID, негативный сценарий")
    public void testFindPetByIDNegative() {

        step("Выполнение запроса GET/store/order c валидным id", () -> {
            responseWrapperPet = stepsPet.getPetByID(getNotValidPetID());
        });

        step("Проверка результата", () -> {
            long statusCode = responseWrapperPet.getStatusCode();
            ErrorResponse responseFindPetbyIDNegative = responseWrapperPet.as(ErrorResponse.class);
            ErrorResponse expectedFindPetbyIDNegative = getErrorFindPetByIDNegative();


            assertSoftly(
                    softAssertions -> {
                        softAssertions
                                .assertThat(statusCode)
                                .withFailMessage("Status code doesn't match")
                                .isEqualTo(STATUS_CODE_ERROR_404);
                        softAssertions
                                .assertThat(responseFindPetbyIDNegative)
                                .withFailMessage("Response body is empty")
                                .isEqualTo(expectedFindPetbyIDNegative);
                    }
            );
        });

    }

}
