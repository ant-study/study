package kr.co.ant.study.reflect.spring;

public class Order {

	private int num;
	private String goods;
	private int qty;
	private DeliveryStatus deleveryStatus;
	
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getGoods() {
		return goods;
	}
	public void setGoods(String goods) {
		this.goods = goods;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public DeliveryStatus getDeleveryStatus() {
		return deleveryStatus;
	}
	public void setDeleveryStatus(DeliveryStatus deleveryStatus) {
		this.deleveryStatus = deleveryStatus;
	}
	
}
