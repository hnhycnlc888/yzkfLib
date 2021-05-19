package yzkf.test;

import yzkf.api.result.Describe;
import yzkf.api.result.Result;
import yzkf.config.ConfigFactory;
import yzkf.config.EnumConfig;

public enum MyResult implements Result {
	OK(0){
		@Override
		public boolean isOK(){
			return true;
		}
	},
	Unknow(100),
	InvalidReceiver(1),
	InvalidUid(2),
	
	NotLogin(3),
	NotChinaMobile(4),
	InvalidVerify(5),
	FailedLogin(6),
	Frozen(7),
	
	FailedShare(8),
	NoPrize(9),
	RepeatShare(10),
	OffLine(11),
	
	MailUrl(101);
	
	private int value;
	MyResult(int value){
		this.value = value;
	}
	public Object getValue(){
		return this.value;
	}

	@Override
	public boolean isOK() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getDescr() {
		return getDescr(ConfigFactory.getInstance().newEnumConfig());
	}

	@Override
	public String getDescr(EnumConfig config) {
		return Describe.query(config, this);
	}
	@Override
	public <T> T getValue(Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Result setValue(Object value) {
		// TODO Auto-generated method stub
		return null;
	}

}
