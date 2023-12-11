package pet;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pet.base.BaseTestPet;
import pet.dto.response.ErrorResponse;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static pet.dto.response.ExpectedDeletePositiveAndError.getDeletesAPetPositive;
import static pet.utils.TestDataHelperPet.STATUS_CODE_OK;
import static pet.utils.TestDataHelperPet.VALID_PET_ID;
import static pet.utils.TestObjectBuilderPet.getAddNewPetModel;

/**
 * Тест сьют метода DELETE/pet
 */
@Epic("Pet контроллер")
@Feature("Deletes a pet")
public class DeletesAPet extends BaseTestPet {

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
    @DisplayName("Deletes a pet by ID. Positive case")
    @Story("Удаление животного, позитивный сценарий")
    public void testDeletesAPetByIDPositive() {

        step("Выполнение запроса DELETE/pet/order c валидным id", () -> {
            responseWrapperPet = stepsPet.deletePetByID(VALID_PET_ID);
        });

        step("Проверка результата", () -> {
            long statusCode = responseWrapperPet.getStatusCode();
            ErrorResponse responseDeleteAPetByID = responseWrapperPet.as(ErrorResponse.class);
            ErrorResponse expectedDeleteAPetByID = getDeletesAPetPositive(VALID_PET_ID);


            assertSoftly(
                    softAssertions -> {
                        softAssertions
                                .assertThat(statusCode)
                                .withFailMessage("Status code doesn't match")
                                .isEqualTo(STATUS_CODE_OK);
                        softAssertions
                                .assertThat(responseDeleteAPetByID)
                                .withFailMessage("Response body is empty")
                                .isEqualTo(expectedDeleteAPetByID);
                    }
            );
        });
    }

}
