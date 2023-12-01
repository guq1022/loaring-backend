package com.lams.loaring.adventurer.domain;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Builder
@Entity
@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class AdventureAuthority implements GrantedAuthority {

	@Id
	private Long id;

	private String role;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "adventure_id", nullable = false)
	private Adventurer adventurer;

	@Override
	public String getAuthority() {
		return role;
	}
}
