package app;

public class FilmCategory {
	private Integer film_id;
	private Integer category_id;
	
	public FilmCategory(Integer film_id, Integer category_id) {
		this.film_id = film_id;
		this.category_id = category_id;
	}

	public Integer getFilm_id() {
		return film_id;
	}

	public void setFilm_id(Integer film_id) {
		this.film_id = film_id;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
}
