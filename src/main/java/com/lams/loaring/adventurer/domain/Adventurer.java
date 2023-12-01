package com.lams.loaring.adventurer.domain;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class Adventurer {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Embedded
	private Email email;

	@Embedded
	private Password password;

	@Embedded
	@Builder.Default
	private Account account = Account.Default();

	@Builder.Default
	@OneToMany(mappedBy = "adventurer", fetch = LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	List<AdventureAuthority> adventureAuthority = new ArrayList<>();

	public String getUserName() {
		return email.getEmail();
	}

	public String getUserPassword() {
		return password.getPassword();
	}

}