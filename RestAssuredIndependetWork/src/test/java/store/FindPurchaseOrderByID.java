package store;

import store.dto.response.StorePetModel;
import store.dto.response.ErrorStorePetModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.base.BaseTestStore;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static store.dto.response.expectedObjectBuilder.ExpectedObjectBuilder.getUnknownErrorFindPurchaseOrderByID;
import static store.utils.TestDataHelperStore.*;
import static store.utils.TestObjectBuilderStore.getPlaceAndOrderForAPetModel;

/**
 * Тест сьют метода GET/store/Find purchase order by ID
 */
@Epic("Store контроллер")
@Feature("Find purchase order by ID")
public class FindPurchaseOrderByID extends BaseTestStore {

    /**
     * Метод для создания тела запроса
     */
    @BeforeEach
    public void requestBodyCreation() {

        step("Создание тела запроса с валидным SHIPDATE", () -> {
            request = getPlaceAndOrderForAPetModel(VALID_PET_ID, VALID_SHIPDATE, COMPLETE_TRUE_STATUS);
        });

        step("Выполнение запроса POST/store/order", () -> {
            responseWrapperStore = stepsStore.createPlaceAndOrderForAPetModel(request);
        });
    }
    @Test
    @DisplayName("Find purchase order by ID. Positive case")
    @Story("Поиск заказа по ID, позитивный сценарий")
    public void testFindPurchaseOrderByIDPositive() {

        step("Выполнение запроса GET/store/order c валидным id", () -> {
        responseWrapperStore = stepsStore.getFindPurchaseOrderByID(VALID_PET_ID);
        });

        step("Проверка результата", () -> {
        long statusCode = responseWrapperStore.getStatusCode();
        StorePetModel response = responseWrapperStore.as(StorePetModel.class);

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
    @DisplayName("Find purchase order by ID. Negative case")
    @Story("Поиск заказа по ID, негативный сценарий")
    public void testFindPurchaseOrderByIDNegative() {

        step("Выполнение запроса GET/store/order c не валидным id", () -> {
        responseWrapperStore = stepsStore.getFindPurchaseOrderByID(getNotValidPetID());
        });

        step("Проверка результата", () -> {
        long statusCode = responseWrapperStore.getStatusCode();
        ErrorStorePetModel errorFindPurchaseOrderByID = responseWrapperStore.as(ErrorStorePetModel.class);
        ErrorStorePetModel expectedErrorFindPurchaseOrderByID = getUnknownErrorFindPurchaseOrderByID();

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(statusCode)
                            .withFailMessage("Status code doesn't match")
                            .isEqualTo(STATUS_CODE_ERROR_404);
                    softAssertions
                            .assertThat(errorFindPurchaseOrderByID)
                            .withFailMessage("Response body doesn't match")
                            .isEqualTo(expectedErrorFindPurchaseOrderByID);

                }
        );
        });

    }

}
