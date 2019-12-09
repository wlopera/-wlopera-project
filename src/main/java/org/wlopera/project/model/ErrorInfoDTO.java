package org.wlopera.project.model;

import java.io.Serializable;
import java.util.List;

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
public class ErrorInfoDTO implements Serializable {
	private static final long serialVersionUID = -6952146078690027403L;
	
	private HeadDTO head;
	private List<DetailErrorDTO> details;

}
