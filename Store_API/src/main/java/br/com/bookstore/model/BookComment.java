package br.com.bookstore.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Embeddable
public class BookComment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String reader;
	private String description;

	public BookComment() {

	}

	public BookComment(String reader, String description) {
		this.reader = reader;
		this.description = description;
	}

	public String getReader() {
		return reader;
	}

	public void setReader(String reader) {
		this.reader = reader;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("reader", reader)
				.append("description", description)
				.toString();
	}

}
