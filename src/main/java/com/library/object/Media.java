package com.library.object;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="MEDIA")
public abstract class Media extends ObjectInformation{

	@Column(name="DISKS")
	private int disks;
	
	@Column(name="MOVIE_GENRE")
	private MovieGenre movieGenre;
	
	@Column(name="MUSIC_GENRE")
	private MusicGenre musicGenre;
	
	@Column(name="DURATION")
	private int duration;

	public int getDisks() {
		return disks;
	}

	public void setDisks(int disks) {
		this.disks = disks;
	}

	public MovieGenre getMovieGenre() {
		return movieGenre;
	}

	public void setMovieGenre(MovieGenre movieGenre) {
		this.movieGenre = movieGenre;
	}

	public MusicGenre getMusicGenre() {
		return musicGenre;
	}

	public void setMusicGenre(MusicGenre musicGenre) {
		this.musicGenre = musicGenre;
	}

	public int getDuration() {
		return duration;
	}

	public void setDurationInMin(int duration) {
		this.duration = duration;
	}
	
	
}
