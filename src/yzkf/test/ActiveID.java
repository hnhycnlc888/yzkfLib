package yzkf.test;

public enum ActiveID {
	/**
	 * 登录
	 */
	login(1),
	/**
	 * 发送邮件
	 */
	sendmail(1),
	/**
	 * PE客户端收发邮件
	 */
	mail(5),
	/**
	 * 公共日历订阅
	 */
	pub_reser(10),
	/**
	 * 云邮局体育杂志
	 */
	spo_reser(5);
	
	private int value;
    public int getValue() {
        return value;
    }    
    public ActiveID setValue(int value) {
		this.value = value;
		return this;
	}
    ActiveID(int value) {
        this.value = value;
    }

}
