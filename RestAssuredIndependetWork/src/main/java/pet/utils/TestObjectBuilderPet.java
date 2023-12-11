package pet.utils;

import pet.dto.PetModel;

import java.util.List;

import static pet.utils.TestDataHelperPet.*;

/**
 * Вспомагательный класс для формирования тестовых объектов
 */
public class TestObjectBuilderPet {

    /**
     * Метод для формирования тела запроса создания питомца
     *
     * @param id идентификатор питомца
     * @return тело запроса
     */
    public static PetModel getAddNewPetModel(long id) {
        return PetModel.builder()
                .id(id)
                .category(PetModel.CategoryAndTagsItem.builder()
                        .id(getValidCategoryID())
                        .name(getRandomCategoryName())
                        .build())
                .name(getRandomPetName())
                .photoUrls(List.of(getRandomUrl()))
                .tags(List.of(PetModel.CategoryAndTagsItem.builder()
                        .id(getValidTagID())
                        .name(VALID_TAG_NAME)
                        .build()))
                .status(VALID_STATUS)
                .build();
    }
}
