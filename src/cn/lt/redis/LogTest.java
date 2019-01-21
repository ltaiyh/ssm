package cn.lt.redis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import redis.clients.jedis.Jedis;

/**
 * 使用redis中的list实现操作日志的消息队列
 * @author lt
 * @Date 2018年1月2日 下午4:47:49
 */
public class LogTest {

	public static void main(String[] args) {
		// 模拟四个用户操作
		new UserThread().start();
		new UserThread().start();
		new UserThread().start();
		new UserThread().start();

		new OtherThread().start();
	}
}

class UserThread extends Thread {
	// 用户线程，模拟用户操作时产生日志信息
	@Override
	public void run() {
		// 作为消息队列使用
		Jedis jedis = new Jedis("localhost");
		Random random = new Random();

		while (true) {
			try {
				Thread.sleep(random.nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// 产生日志信息
			String log = "logloglog-----------"+Thread.currentThread().getName();

			// 把日志信息放入到消息队列
			jedis.rpush("log_mq", log);
		}
	}
}

class OtherThread extends Thread {
	// 专门用来从消息队列中取数据，写入到缓冲输出流
	@Override
	public void run() {
		// 消息队列
		Jedis jedis = new Jedis("localhost");

		// 缓冲输出流
		File file = new File("d:\\system.log");
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);

			// 不停的从消息队列中读数据，如果读到，写入到输出流中，
			// 如果没有读到，不写，然后让线程“休息”一下--2秒
			while (true) {
				// 从消息队列取出一条消息
				String log = jedis.lpop("log_mq");

				if (log != null) {
					bufferedWriter.write(log);
					bufferedWriter.write("\r\n");
					System.out.println(log);
					//System.out.println("读到了一条日记信息");
				} else {
					// 睡眠2秒钟
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				bufferedWriter.flush();//刷新缓存
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (bufferedWriter != null) {
				try {
					bufferedWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
