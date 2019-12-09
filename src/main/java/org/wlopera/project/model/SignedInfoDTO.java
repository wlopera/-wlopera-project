package org.wlopera.project.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SignedInfoDTO implements Serializable {
	private static final long serialVersionUID = 5155099534740855096L;

	private HeadDTO header;
	private String psCert;
}
