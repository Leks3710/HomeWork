package store;

import store.dto.response.StorePetModel;
import store.dto.response.ErrorStorePetModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.base.BaseTestStore;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static store.dto.response.expectedObjectBuilder.ExpectedObjectBuilder.getUnknownErrorPlaceAnOrderForAPet;
import static store.utils.TestDataHelperStore.*;
import static store.utils.TestObjectBuilderStore.getPlaceAndOrderForAPetModel;

/**
 * Тест сьют метода POST/store
 */
@Epic("Store контроллер")
@Feature("Place an order for a pet")
public class PlaceAnOrderForAPet extends BaseTestStore {


    @Test
    @DisplayName("Place an order for a pet. Positive case")
    @Story("Добавление нового заказа, позитивный сценарий")
    public void testPlaceAnOrderForAPetPositive() {

        step("Создание тела запроса с валидным SHIPDATE", () -> {
            request = getPlaceAndOrderForAPetModel(VALID_PET_ID, VALID_SHIPDATE, COMPLETE_TRUE_STATUS);
        });

        step("Выполнение запроса POST/store/order", () -> {
            responseWrapperStore = stepsStore.createPlaceAndOrderForAPetModel(request);
        });

        step("Выполнение запроса GET/store/order c валидным id", () -> {
            responseWrapperStore = stepsStore.getFindPurchaseOrderByID(VALID_PET_ID);
        });

        step("Проверка результата", () -> {
            int statusCode = responseWrapperStore.getStatusCode();
            StorePetModel response = responseWrapperStore.as(StorePetModel.class);


            assertSoftly(
                    softAssertions -> {
                        softAssertions
                                .assertThat(statusCode)
                                .withFailMessage("Status code doesn't match")
                                .isEqualTo(STATUS_CODE_OK);
                        softAssertions
                                .assertThat(response)
                                .withFailMessage("Response body doesn't match")
                                .isEqualTo(request);
                    }
            );
        });

    }

    @Test
    @DisplayName("Place an order for a pet. Negative case with not valid shipDate")
    @Story("Добавление нового заказа с не валидным shipDate, негативный сценарий")
    public void testPlaceAnOrderForAPetNegative() {

        step("Создание тела запроса с не валидным SHIPDATE", () -> {
            request = getPlaceAndOrderForAPetModel(VALID_PET_ID, getRegexpNotValidShipdate(), COMPLETE_FALSE_STATUS);
        });

        step("Выполнение запроса POST/store/order", () -> {
            responseWrapperStore = stepsStore.createPlaceAndOrderForAPetModel(request);
        });

        step("Проверка результата", () -> {
            int statusCode = responseWrapperStore.getStatusCode();
            ErrorStorePetModel errorStorePetModel = responseWrapperStore.as(ErrorStorePetModel.class);
            ErrorStorePetModel expectedStorePetModel = getUnknownErrorPlaceAnOrderForAPet();

            assertSoftly(
                    softAssertions -> {
                        softAssertions
                                .assertThat(statusCode)
                                .withFailMessage("Status code doesn't match")
                                .isEqualTo(STATUS_CODE_ERROR_500);
                        softAssertions
                                .assertThat(errorStorePetModel)
                                .withFailMessage("Response body doesn't match")
                                .isEqualTo(expectedStorePetModel);

                    }
            );
        });

    }
    }
