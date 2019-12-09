package org.wlopera.project.web.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistryDTO implements Serializable {
	private static final long serialVersionUID = 1870898536373557495L;

	private String country;
	private String date;
	private String sequential;
	
	@NonNull
	private String certificate;
}
