package models.entities;

public class Shop {

	private int id;
	private String name;
	public String srcImg;
	public static int ID_BASE;

	public Shop(String name, String srcImg) {
		this.id  = ID_BASE++;
		this.name = name;
		this.srcImg = srcImg;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSrcImg() {
		return srcImg;
	}

	public void setSrcImg(String srcImg) {
		this.srcImg = srcImg;
	}

	public Object[] toObjectVector(){
		return new Object[]{id, name};
	}
}
