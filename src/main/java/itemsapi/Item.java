package itemsapi;

public class Item {
	private long id;
	private String description;
	private boolean checked;
	
	public Item(){
		
	}
	
	public Item(long id, String description, boolean checked){
		this.setId(id);
		this.setDescription(description);
		this.setChecked(checked);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
