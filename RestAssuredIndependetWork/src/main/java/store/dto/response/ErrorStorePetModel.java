package store.dto.response;

import lombok.Builder;
import lombok.Data;

/**
 * Модель ошибки JSON Store
 */
@Data
@Builder
public class ErrorStorePetModel {
	private final long code;
	private final String type;
	private final String message;
}
