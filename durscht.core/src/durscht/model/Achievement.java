package durscht.model;

import durscht.contracts.logic.model.IAchievement;

public class Achievement implements IAchievement {

	private String name;
	private int progress;
	private String imageUrl;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getProgress() {
		return progress;
	}

	@Override
	public String getImageUrl() {
		return imageUrl;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
