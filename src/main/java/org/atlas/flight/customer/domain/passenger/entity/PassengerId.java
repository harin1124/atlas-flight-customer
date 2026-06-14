package org.atlas.flight.customer.domain.passenger.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * {@link Passenger} 복합키 — (소유자, 탑승자) 한 쌍이 곧 식별자다.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PassengerId implements Serializable {
	private String ownerCustomerId;
	private String passengerCustomerId;
}
