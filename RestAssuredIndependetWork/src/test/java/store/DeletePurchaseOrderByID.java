package store;

import store.dto.response.ErrorStorePetModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.base.BaseTestStore;

import static store.dto.response.expectedObjectBuilder.ExpectedDelete.getDeletePurchaseOrderByIDPositive;
import static store.dto.response.expectedObjectBuilder.ExpectedDelete.getErrorDeletePurchaseOrderByID;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static store.utils.TestDataHelperStore.*;
import static store.utils.TestObjectBuilderStore.getPlaceAndOrderForAPetModel;

/**
 * Тест сьют метода DELETE/store/Delete purchase order by ID
 */
@Epic("Store контроллер")
@Feature("Delete purchase order by ID")
public class DeletePurchaseOrderByID extends BaseTestStore {

    /**
     * Метод для создания тела запроса
     */
    @BeforeEach
    public void RequestBodyCreation () {

        step("Создание тела запроса с валидным SHIPDATE", () -> {
            request = getPlaceAndOrderForAPetModel(VALID_PET_ID, VALID_SHIPDATE, COMPLETE_TRUE_STATUS);
        });

        step("Выполнение запроса POST/store/order", () -> {
            responseWrapperStore = stepsStore.createPlaceAndOrderForAPetModel(request);
        });
    }
    @Test
    @DisplayName("Delete purchase order by ID. Positive case")
    @Story("Удаление заказа, позитивный сценарий")
    public void testDeletePurchaseOrderByIDPositive() {

        step("Выполнение запроса DELETE/store/order c валидным id", () -> {
            responseWrapperStore = stepsStore.deleteDeletePurchaseOrderByID(VALID_PET_ID);
        });

        step("Проверка результата", () -> {
            long statusCode = responseWrapperStore.getStatusCode();
            ErrorStorePetModel responseDeletePurchaseOrderByIDPositive = responseWrapperStore.as(ErrorStorePetModel.class);
            ErrorStorePetModel expectedDeletePurchaseOrderByIDPositive = getDeletePurchaseOrderByIDPositive(VALID_PET_ID);

            assertSoftly(
                    softAssertions -> {
                        softAssertions
                                .assertThat(statusCode)
                                .withFailMessage("Status code doesn't match")
                                .isEqualTo(STATUS_CODE_OK);
                        softAssertions
                                .assertThat(responseDeletePurchaseOrderByIDPositive)
                                .withFailMessage("Response body is empty")
                                .isEqualTo(expectedDeletePurchaseOrderByIDPositive);

                    }
            );
        });


    }

    @Test
    @DisplayName("Delete purchase order by ID. Negative case")
    @Story("Удаление заказа, негативный сценарий")
    public void testDeletePurchaseOrderByIDNegative() {

        step("Выполнение запроса DELETE/store/order c не валидным id", () -> {
            responseWrapperStore = stepsStore.deleteDeletePurchaseOrderByID(getNotValidPetID());
        });

        step("Проверка результата", () -> {
            long statusCode = responseWrapperStore.getStatusCode();
            ErrorStorePetModel errorDeletePurchaseOrderByID = responseWrapperStore.as(ErrorStorePetModel.class);
            ErrorStorePetModel expectedDeletePurchaseOrderByID = getErrorDeletePurchaseOrderByID();

            assertSoftly(
                    softAssertions -> {
                        softAssertions
                                .assertThat(statusCode)
                                .withFailMessage("Status code doesn't match")
                                .isEqualTo(STATUS_CODE_ERROR_404);
                        softAssertions
                                .assertThat(errorDeletePurchaseOrderByID)
                                .withFailMessage("Response body doesn't match")
                                .isEqualTo(expectedDeletePurchaseOrderByID);

                    }
            );
        });




    }


}
