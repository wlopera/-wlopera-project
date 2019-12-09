package org.wlopera.project.model;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class HeadDTO implements Serializable {
	private static final long serialVersionUID = -4838351740775530922L;

	private String iteractionCode;
	private String serviceId;
	private String senderId;
	private String senderCountry;
	private String receiverId;
	private String receiverCountry;
	private String transactionId;
	private String transactionOriginalId;
	private String transactionStatus;
	private LocalDateTime date;
	private String documentType;
	private String documentId;

}
