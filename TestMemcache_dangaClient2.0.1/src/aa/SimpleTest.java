package aa;

import java.util.Date;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.danga.MemCached.SockIOPool.SockIO;

public class SimpleTest {
	protected static MemCachedClient mcc = new MemCachedClient();
	static {
		//��������̨memcached��������ˮƽ�ֲ�ʽ��չ
//		String[] servers = { "192.168.149.128:11211","192.168.149.128:11212" };
//		Integer[] weights = { 3, 3 };
		String[] servers = { "127.0.0.1:11211" };
		Integer[] weights = { 1 };
		// grab an instance of our connection pool
		SockIOPool pool = SockIOPool.getInstance();

		// set the servers and the weights
		pool.setServers(servers);
		pool.setWeights(weights);


		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(1000 * 60 * 60 * 6);

		// set the sleep for the maint thread
		// it will wake up every x seconds and
		// maintain the pool size
		pool.setMaintSleep(30);

		// set some TCP settings
		// disable nagle
		// set the read timeout to 3 secs
		// and don't set a connect timeout
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(0);
		
		//���������
		pool.setAliveCheck(false);
		pool.setHashingAlg(pool.NEW_COMPAT_HASH);

		// initialize the connection pool
		pool.initialize();

		//ѹ���㷨�Ƚ���,��Ҫ����
		mcc.setCompressEnable(false);
	}
	
	/**
	 * �򵥲���Key��value�Ƿ�֧������
	 */
	public static void get() {
		System.out.println("in main get:" + mcc.get("fo���߰��㣬����o2"));
	}

	public static void put() {
		mcc.set("fo���߰��㣬����o2", "=========�й��ˣ�====sdfdfd==+++");

	}
	/**
	 * ����key��ֵ���Ȳ��ܳ���250���ַ�
	 */
	public static void largeKey(){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<51;i++) sb.append("12345");
		String key = sb.toString();
		System.out.println("key.length="+key.length());
		mcc.set(key, new Date());
		System.out.println("get from large key :" + mcc.get(key));
	}
	/**
	 * ����value���ܳ���1M
	 */
	public static void largeValue(){
		StringBuffer sb = new StringBuffer(2000000);
		for(int i=0;i<1024*1024;i++) sb.append("1");
		String value = sb.toString();
		System.out.println("value.length="+value.length());
		mcc.set("aa", value);
		System.out.println("get from large value :" + mcc.get("aa"));
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleTest.put();
		SimpleTest.get();
		
		SimpleTest.largeKey();
		SimpleTest.largeValue();
	}

}
