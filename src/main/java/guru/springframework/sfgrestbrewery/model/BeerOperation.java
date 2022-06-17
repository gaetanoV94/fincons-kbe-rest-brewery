package guru.springframework.sfgrestbrewery.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import guru.springframework.sfgrestbrewery.enums.BeerOperationEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Beer_Ops")
@NoArgsConstructor
@AllArgsConstructor
public class BeerOperation {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "operation_id")
    private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "operation")
	private BeerOperationEnum operationEnum;
	
	@Column(name = "before_operation")
	private String before;
	
	@Column(name = "after_operation")
	private String after;
	
	@CreationTimestamp
    @Column(name = "created_date", updatable = false)
    @JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date createdDate;

}
