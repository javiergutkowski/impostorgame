package com.example.impostorgame.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table 
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Word implements Serializable{

	@Id
	@SequenceGenerator(
			name = "word_sequence",
			sequenceName = "word_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "word_sequence"
	)
	private Long wordId;
	private String word;
	private String category;
	
	public Word(Long wordId, String word, String category) {
		super();
		this.wordId = wordId;
		this.word = word;
		this.category = category;
	}

	public Word(String word, String category) {
		super();
		this.word = word;
		this.category = category;
	}

	public Word() {
		super();
	}

	@Override
	public String toString() {
		return "Word [wordId=" + wordId + ", word=" + word + ", category=" + category + "]";
	}

	public Long getWordId() {
		return wordId;
	}

	public void setWordId(Long wordId) {
		this.wordId = wordId;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	
	
}
