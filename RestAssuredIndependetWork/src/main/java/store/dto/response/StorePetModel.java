package store.dto.response;

import lombok.Builder;
import lombok.Data;

/**
 * Модель JSON Store
 */
@Data
@Builder
public class StorePetModel {

	private final long id;
	private final long petId;
	private final long quantity;
	private final String shipDate;
	private final String status;
	private final boolean complete;
}
